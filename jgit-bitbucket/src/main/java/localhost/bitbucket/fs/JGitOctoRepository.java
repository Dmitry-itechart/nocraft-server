package localhost.bitbucket.fs;

import localhost.froala.Froala;
import localhost.froala.OctoByteFile;
import localhost.froala.OctoFile;
import localhost.froala.OctoKit;
import localhost.froala.OctoTextFile;
import localhost.froala.Octopath;
import localhost.froala.effect.CommitEffectImpl;
import localhost.froala.effect.OctoEffect;
import localhost.froala.impl.FroalaTextImpl;
import localhost.froala.util.ByteDigester;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static localhost.froala.util.CommitMessageGenerator.getCommitMessage;

@Slf4j
@RequiredArgsConstructor
public class JGitOctoRepository implements OctoKit {

    private Path path;
    private final String username;
    private final String password;
    private final String remoteRepository;

    private CredentialsProvider credentialsProvider;

    private Git git;

    // make it @PostConstruct
    public void init() throws GitAPIException {
        credentialsProvider = new UsernamePasswordCredentialsProvider(username, password);

        path = Path.of("/tmp/git-" + UUID.randomUUID().toString());

        git = Git.cloneRepository()
                .setDirectory(path.toFile())
                .setCredentialsProvider(credentialsProvider)
                .setURI(remoteRepository).call();

//        git = Git.open(path.toFile());

        if (git.getRepository().isBare()) {
            throw new RuntimeException("Empty repository: " + path.toString());
        }
    }

    @Override
    public Optional<Froala> getFroala(Octopath op) throws IOException {
        var path = toFileSystemPath(op);
        return toTextFroala(path);
    }

    @Override
    public OctoEffect commitFroala(Octopath op, Froala froala) throws IOException {
        return commitFroala(op, froala, Collections.emptyList());
    }

    @Override
    public OctoEffect commitFroala(Octopath op, Froala froala, List<OctoFile> list) throws IOException {
        var path = toFileSystemPath(op);
        // here can throw IOException
        Files.write(path, froala.getFroala().getBytes(StandardCharsets.UTF_8));

        // refactor it.
        try {
            processChangedFiles(list);
        } catch (NoSuchAlgorithmException e) {
            throw new IOException(e);
        }

        try {
            //refactor to more informative result message

            git.add().addFilepattern(".").call();
            git.commit().setAllowEmpty(false)
                    .setMessage(getCommitMessage(op))
                    .setAuthor(username, username)
                    .setCommitter(username, username)
                    .call();
            git.push().setCredentialsProvider(credentialsProvider).call();
        } catch (GitAPIException e) {

            log.error("Unable to save changes: " + git.toString(), e);

            return new CommitEffectImpl().isOk(false);
        }


        return new CommitEffectImpl().isOk(true);
    }

    private Path toFileSystemPath(Octopath op) {
        return path.toAbsolutePath().resolve(op.getItemPath());
    }

    private Optional<Froala> toTextFroala(Path path) throws IOException {
        if (path.toFile().exists()) {
            String textFroala = Files.lines(path).collect(Collectors.joining());
            return Optional.of(new FroalaTextImpl(textFroala));
        } else {
            return Optional.empty();
        }
    }

    private void processChangedFiles(List<OctoFile> list) throws IOException, NoSuchAlgorithmException {
        Set<Octopath> seen = new HashSet<>();

        for (OctoFile f : list) {
            var p = toFileSystemPath(f.getFilePath());
            byte[] digest = ByteDigester.generateHash(Files.readAllBytes(p));

            if (seen.contains(f.getFilePath())) {
                log.error("Attempt to change same file more then once");
            } else {
                if (Arrays.equals(f.hash(), digest)) {
                    log.error("Attempt to change file with same content (empty commit)");
                } else {
                    byte[] content;
                    if (OctoFile.bytePredicate.test(f)) {
                        content = ((OctoByteFile) f).getFileContent();
                    } else if (OctoFile.stringPredicate.test(f)) {
                        content = ((OctoTextFile) f).getFileContent().getBytes(StandardCharsets.UTF_8);
                    } else {
                        throw new IOException("Unknown file: " + f.getFilePath());
                    }
                    Files.write(p, content);
                    seen.add(f.getFilePath());
                }
            }
        }
    }
}
