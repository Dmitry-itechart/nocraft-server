package localhost.froala.impl;

import localhost.froala.Froala;
import localhost.froala.OctoFroala;
import localhost.froala.Octopath;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHContentUpdateResponse;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static localhost.froala.util.CommitMessageGenerator.getCommitMessage;

@RequiredArgsConstructor
public class OctoFroalaImpl implements OctoFroala {

    private final String oauthAccessToken;
    private final String login;
    private final String repository;

    private GHRepository git;

    public void init() throws IOException {
        GitHub server = GitHub.connect(login, oauthAccessToken);
        git = server.getRepository(login + "/" + repository);
    }

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
                    return Optional.of(new FroalaImpl(asString));
                }
            }
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    @Override
    public void commitFroala(Octopath path, Froala froala) throws IOException {
        var o = getGitItem(git, path);

        GHContentUpdateResponse resp;

        if (o.isPresent()) {
            resp = o.get().update(froala.getFroala(), getCommitMessage(path));
        } else {
            resp = git.createContent()
                    .branch("master")
                    .path(path.getItemPath())
                    .content(froala.getFroala())
                    .message(getCommitMessage(path))
                    .commit();

        }

        var commit = resp.getCommit();

        // LOG ???
        commit.getSHA1();
    }

    private Optional<GHContent> getGitItem(GHRepository git, Octopath path) throws IOException {
        try {
            List<GHContent> list = git.getDirectoryContent(path.getPath());

            return list.stream()
                    .filter(i -> Objects.equals(i.getName().toLowerCase(), path.getItem().toLowerCase()))
                    .findAny();
        } catch (FileNotFoundException e) {
            // if file not found (not created) api throws FileNotFoundException.
            return Optional.empty();
        }
    }
}
