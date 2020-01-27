package localhost.service;

import localhost.service.pojo.Item;
import localhost.service.util.DummyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class PublicDummyService implements DummyServiceInterface {

    private final DummyService service;

    @Override
    public boolean isRestricted() {
        return false;
    }

    @Override
    public Optional<List<Item>> getItems() {
        return Optional.of(service.getItems("PUBLIC -> ", 2));
    }
}
