package localhost.froala;

import localhost.froala.event.OctoEffect;
import localhost.froala.event.OctoEvent;

import java.util.Collection;
import java.util.stream.Stream;

public interface OctoFroala extends OctoKit {

    default void addListener(OctoFroalaListener l) {
        getListeners().add(l);
    }

    default boolean removeListener(OctoFroalaListener l) {
        return getListeners().remove(l);
    }

    default Stream<OctoEffect> notifyAll(OctoEvent e) {
        return getListeners().parallelStream().map(l -> l.onOctoEvent(e));
    }

    Collection<OctoFroalaListener> getListeners();

}
