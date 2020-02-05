package localhost.froala.event;

import localhost.froala.Octopath;

import java.util.List;


public class OctoEffectImpl implements OctoEffect {

    private final boolean isOk;
    private List<Octopath> paths;

    public OctoEffectImpl() {
        this(true);
    }

    public OctoEffectImpl(boolean ok) {
        this.isOk = ok;
    }

}
