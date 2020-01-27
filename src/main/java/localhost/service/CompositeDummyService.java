package localhost.service;

import localhost.service.pojo.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Collect data from all 'DummyService' providers, some requires authentication...
 */
@Component
@AllArgsConstructor
public class CompositeDummyService {

    private final List<DummyServiceInterface> providers;

    public Optional<List<Item>> getItems() {
        return Optional.of(
                providers.stream()
                        .map(p -> p.getItems().orElse(Collections.emptyList()))
                        .flatMap(List::stream).collect(Collectors.toList())
        );
    }
}
