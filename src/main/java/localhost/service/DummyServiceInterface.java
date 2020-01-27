package localhost.service;

import localhost.service.pojo.Item;

import java.util.List;
import java.util.Optional;

public interface DummyServiceInterface {

    default boolean isRestricted() {
        return true;
    }

    Optional<List<Item>> getItems();
}
