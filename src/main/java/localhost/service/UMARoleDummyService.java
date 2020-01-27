package localhost.service;

import localhost.service.pojo.Item;
import localhost.service.util.DummyService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * Here we assume that 'uma_authorization' is granted by default for every
 * new keycloak user.
 */
@Component(UMARoleDummyService.NAME)
@AllArgsConstructor
public class UMARoleDummyService implements DummyServiceInterface {

    public static final String NAME = "UMA";

    private final DummyService service;

    @Secured("ROLE_uma_authorization")
    @Override
    public Optional<List<Item>> getItems() {
        return Optional.of(service.getItems("UMA_ROLE -> ", 2));
    }
}

