package localhost.froala;

import localhost.froala.effect.OctoEffect;
import localhost.froala.event.OctoEvent;

import java.util.EventListener;

public interface OctoFroalaListener extends EventListener {

    OctoEffect onOctoEvent(OctoEvent event);
}
