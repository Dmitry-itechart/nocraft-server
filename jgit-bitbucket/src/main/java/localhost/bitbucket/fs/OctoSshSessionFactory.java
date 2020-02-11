package localhost.bitbucket.fs;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.util.FS;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class OctoSshSessionFactory extends JschConfigSessionFactory {

    private final String pathToKey;
    private final String password;

    @Override
    protected JSch createDefaultJSch(FS fs) throws JSchException {
        JSch jSch = super.createDefaultJSch(fs);
        jSch.addIdentity(pathToKey, password.getBytes(StandardCharsets.UTF_8));
        return jSch;
    }

    @Override
    protected void configure(OpenSshConfig.Host hc, Session session) {
        // no, thanks.
        session.setConfig("StrictHostKeyChecking", "no");
    }
}
