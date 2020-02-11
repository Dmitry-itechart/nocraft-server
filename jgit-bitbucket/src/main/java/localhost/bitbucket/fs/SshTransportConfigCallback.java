package localhost.bitbucket.fs;

import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;

@RequiredArgsConstructor
public class SshTransportConfigCallback implements TransportConfigCallback {

    private final OctoSshSessionFactory factory;

    @Override
    public void configure(Transport transport) {
        SshTransport sshTransport = (SshTransport) transport;
        sshTransport.setSshSessionFactory(factory);
    }
}
