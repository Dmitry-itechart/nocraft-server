package localhost.rest;

import localhost.service.PublicDummyService;
import localhost.service.pojo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static localhost.Application.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API)
public class NoAuthenticationRequiredController {

    // here we can inject by class name directly since
    // it is not proxied as not using security specific annotations.
    private final PublicDummyService service;

    @GetMapping
    public List<Item> getItems() {
        return service.getItems().orElse(Collections.emptyList());
    }
}
