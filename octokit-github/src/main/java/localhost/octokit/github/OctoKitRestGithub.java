package localhost.octokit.github;

import localhost.froala.Froala;
import localhost.froala.OctoKit;
import localhost.froala.Octopath;
import localhost.froala.event.OctoEffect;
import localhost.froala.event.OctoEffectImpl;
import localhost.froala.impl.FroalaImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.kohsuke.github.GHContent;
import org.kohsuke.github.GHRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
                    return Optional.of(new FroalaImpl(asString));
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
            return new OctoEffectImpl(false);
        }

        return new OctoEffectImpl();
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
