package localhost.service;

import localhost.service.pojo.Item;
import localhost.service.util.DummyService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class HasNoRoleDummyService implements DummyServiceInterface {

    private final DummyService service;

    @Secured("ROLE_NOROLE")
    @Override
    public Optional<List<Item>> getItems() {
        return Optional.of(service.getItems("NOROLE -> ", 2));
    }
}
