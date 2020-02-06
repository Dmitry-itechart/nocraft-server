package localhost.froala.impl;

import localhost.froala.Froala;
import localhost.froala.OctoFile;
import localhost.froala.OctoFroala;
import localhost.froala.OctoFroalaListener;
import localhost.froala.OctoKit;
import localhost.froala.Octopath;
import localhost.froala.effect.OctoEffect;
import localhost.froala.event.CommitEvent;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OctoFroalaImpl implements OctoFroala {

    private final OctoKit octoKit;
    private final List<OctoFroalaListener> listeners;

    @Override
    public Optional<Froala> getFroala(Octopath path) throws IOException {
        return octoKit.getFroala(path);
    }

    @Override
    public OctoEffect commitFroala(Octopath path, Froala froala) throws IOException {
        var mainResult = octoKit.commitFroala(path, froala);

        return this.notifyAllStream(new CommitEvent()).reduce(mainResult, OctoEffect::setPrevious);
    }

    @Override
    public OctoEffect commitFroala(Octopath path, Froala froala, List<OctoFile> list) throws IOException {
        var mainResult = octoKit.commitFroala(path, froala, list);

        return this.notifyAllStream(new CommitEvent()).reduce(mainResult, OctoEffect::setPrevious);
    }


    @Override
    public Collection<OctoFroalaListener> getListeners() {
        return listeners;
    }
}
