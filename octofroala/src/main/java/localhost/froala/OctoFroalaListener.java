package localhost.froala;

import localhost.froala.event.OctoEffect;
import localhost.froala.event.OctoEvent;

import java.util.EventListener;

public interface OctoFroalaListener extends EventListener {

    OctoEffect onOctoEvent(OctoEvent event);
}
