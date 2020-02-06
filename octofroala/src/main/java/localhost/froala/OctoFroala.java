package localhost.froala;

import localhost.froala.effect.OctoEffect;
import localhost.froala.event.OctoEvent;

import java.util.Collection;
import java.util.stream.Stream;

public interface OctoFroala extends OctoKit {

    default Stream<OctoEffect> notifyAllStream(OctoEvent e) {
        return getListeners().stream().map(l -> l.onOctoEvent(e));
    }

    Collection<OctoFroalaListener> getListeners();
}
