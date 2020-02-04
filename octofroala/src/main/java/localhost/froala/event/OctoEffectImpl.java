package localhost.froala.event;

import lombok.Getter;

@Getter
public class OctoEffectImpl implements OctoEffect {

    private final boolean isOk;

    public OctoEffectImpl() {
        this(true);
    }

    public OctoEffectImpl(boolean ok) {
        this.isOk = ok;
    }

}
