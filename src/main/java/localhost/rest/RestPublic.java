package localhost.rest;

import localhost.service.PublicDummyService;
import localhost.service.pojo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static localhost.Application.PUBLIC;

@RequiredArgsConstructor
@RestController
@RequestMapping(PUBLIC)
public class RestPublic {

    private final PublicDummyService service;

    @GetMapping
    public List<Item> getItems() {
        return service.getItems().orElse(Collections.emptyList());
    }
}
