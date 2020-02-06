package localhost.froala.effect;

import java.util.Optional;

public abstract class AbstractOctoEffect implements OctoEffect {

    private OctoEffect prev;
    private boolean isOk = true;

    public OctoEffect setPrevious(OctoEffect prev) {
        this.prev = prev;
        return this;
    }

    public AbstractOctoEffect isOk(boolean isOk) {
        this.isOk = isOk;
        return this;
    }

    @Override
    public Optional<OctoEffect> previous() {
        return Optional.ofNullable(prev);
    }

    @Override
    public boolean isOk() {
        return previous().map(OctoEffect::isOk).orElse(true) && isOk;
    }
}
