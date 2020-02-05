package localhost.froala.impl;

import localhost.froala.Froala;
import localhost.froala.OctoFile;
import localhost.froala.OctoFroala;
import localhost.froala.OctoFroalaListener;
import localhost.froala.OctoKit;
import localhost.froala.Octopath;
import localhost.froala.event.CommitEvent;
import localhost.froala.event.OctoEffect;
import localhost.froala.event.OctoEffectImpl;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        var fails = this.notifyAllStream(new CommitEvent())
                .filter(e -> !e.isOk()).collect(Collectors.toList());

        // log fail here?

        if (fails.isEmpty()) {
            return new OctoEffectImpl(true);
        } else {
            return new OctoEffectImpl(false);
        }
    }

    @Override
    public OctoEffect commitFroala(Octopath path, Froala froala, List<OctoFile> list) throws IOException {

        // track effects?

        var mainResult = octoKit.commitFroala(path, froala, list);

        var fails = this.notifyAllStream(new CommitEvent())
                .filter(e -> !e.isOk()).collect(Collectors.toList());

        // log fail here?

        if (fails.isEmpty()) {
            return new OctoEffectImpl(true);
        } else {
            return new OctoEffectImpl(false);
        }
    }


    @Override
    public Collection<OctoFroalaListener> getListeners() {
        return listeners;
    }
}
