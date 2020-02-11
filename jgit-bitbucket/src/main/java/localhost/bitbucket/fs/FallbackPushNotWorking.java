package localhost.bitbucket.fs;

import localhost.froala.effect.CommitEffectImpl;
import localhost.froala.effect.OctoEffect;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/*
Yeah, I know it is shame to call OS level ssh,
but we have no more time to debug java/jsch for now.
 */
@Slf4j
class FallbackPushNotWorking {

    OctoEffect pushFromOs(Path gitDir) throws IOException {
        Path tmp = Files.createTempFile(
                Paths.get("/tmp"),
                UUID.randomUUID().toString(),
                ".txt"
        );

        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(gitDir.toFile());
        builder.command("/opt/push.sh");

        builder.redirectError(ProcessBuilder.Redirect.appendTo(tmp.toFile()));

        Process p = builder.start();
        try {
            Process dead = p.onExit().get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            log.error("git push process dead");
            return new CommitEffectImpl().isOk(false);
        }

        String s = Files.lines(tmp).collect(Collectors.joining());

        return new CommitEffectImpl();
    }
}
