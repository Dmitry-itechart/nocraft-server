package localhost.rest.open;

import localhost.pojo.Item;
import localhost.service.DummyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static localhost.Application.PUBLIC;

@RequiredArgsConstructor
@RestController
@RequestMapping(PUBLIC)
public class RestPublic {

    private final DummyService service;

    @GetMapping
    public List<Item> getFooBar() {
        return service.getItems();
    }
}
