package localhost.rest;

import localhost.service.CompositeDummyService;
import localhost.service.pojo.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static localhost.Application.PUBLIC;

/**
 * try to fetch from all items providers. If any of providers is restricted - whole request is restricted.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(PUBLIC + "/all")
public class RestAll {

    private final CompositeDummyService service;

    @GetMapping
    public List<Item> getAllItems() {
        return service.getItems().orElse(Collections.emptyList());
    }
}
