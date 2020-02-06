package localhost.octokit.github;

import localhost.froala.Froala;
import localhost.froala.OctoByteFile;
import localhost.froala.OctoFile;
import localhost.froala.OctoKit;
import localhost.froala.OctoTextFile;
import localhost.froala.Octopath;
import localhost.froala.effect.CommitEffectImpl;
import localhost.froala.effect.OctoEffect;
import localhost.froala.impl.FroalaTextImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHCommit;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHTreeBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static localhost.froala.util.CommitMessageGenerator.getCommitMessage;

@RequiredArgsConstructor
public class OctoKitRestGithub implements OctoKit {

    private final GHRepository git;

    @Override
    public Optional<Froala> getFroala(Octopath path) {
        try {
            var opItem = getGitItem(git, path);
            if (opItem.isEmpty()) {
                return Optional.empty();
            } else {
                var item = opItem.get();
                try (InputStream in = item.read()) {
                    var asString = String.join("", IOUtils.readLines(in));
                    return Optional.of(new FroalaTextImpl(asString));
                }
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public OctoEffect commitFroala(Octopath path, Froala froala) {
        var o = getGitItem(git, path);

        try {
            if (o.isPresent()) {
                o.get().update(froala.getFroala(), getCommitMessage(path));
            } else {
                git.createContent()
                        .branch("master")
                        .path(path.getItemPath())
                        .content(froala.getFroala())
                        .message(getCommitMessage(path))
                        .commit();

            }
        } catch (IOException e) {
            return new CommitEffectImpl().isOk(false);
        }

        return new CommitEffectImpl()
                .affectedFiles(Collections.singletonList(path))
                .isOk(true);
    }

    @Override
    public OctoEffect commitFroala(Octopath path, Froala froala, List<OctoFile> list) {

        // here assume all commits came to master branch
        GHBranch masterBranch;
        try {
            masterBranch = git.getBranch("master");
        } catch (IOException e) {
            return new CommitEffectImpl().isOk(false);
        }

        GHTreeBuilder builder = git.createTree().baseTree(masterBranch.getSHA1());

        list.stream().filter(OctoFile.stringPredicate).map(OctoTextFile.class::cast)
                .forEach(f -> builder.add(f.getFilePath().getItemPath(), f.getFileContent(), false));

        list.stream().filter(OctoFile.bytePredicate).map(OctoByteFile.class::cast)
                .forEach(f -> builder.add(f.getFilePath().getItemPath(), f.getFileContent(), false));

        builder.add(path.getItemPath(), froala.getFroala(), false);

        try {
            var tree = builder.create();
            GHCommit commit = git.createCommit()
                    .tree(tree.getSha())
                    .parent(masterBranch.getSHA1())
                    .message(getCommitMessage(path))
                    .create();
            git.getRef("heads/master").updateTo(commit.getSHA1());
        } catch (IOException e) {
            return new CommitEffectImpl().isOk(false);
        }

        var affected = list.stream().map(OctoFile::getFilePath).collect(Collectors.toList());
        affected.add(path);

        return new CommitEffectImpl().affectedFiles(affected).isOk(true);
    }

    private Optional<GHContent> getGitItem(GHRepository git, Octopath path) {
        try {
            List<GHContent> list = git.getDirectoryContent(path.getPath());

            return list.stream()
                    .filter(i -> Objects.equals(i.getName().toLowerCase(), path.getItem().toLowerCase()))
                    .findAny();
        } catch (IOException e) {
            // if file not found (not created) api throws FileNotFoundException.
            return Optional.empty();
        }
    }
}
