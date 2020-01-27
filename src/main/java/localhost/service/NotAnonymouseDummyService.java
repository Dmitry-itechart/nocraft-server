package localhost.service;

import localhost.service.pojo.Item;
import localhost.service.util.DummyService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Permits anyone who is not anonymous authenticated
 */
@Component(NotAnonymouseDummyService.NAME)
@AllArgsConstructor
public class NotAnonymouseDummyService implements DummyServiceInterface {

    public static final String NAME = "NO_ANON";

    private final DummyService service;

    @PreAuthorize("!isAnonymous()")
    @Override
    public Optional<List<Item>> getItems() {
        return Optional.of(service.getItems("NON ANONYMOUSE -> ", 2));
    }
}
