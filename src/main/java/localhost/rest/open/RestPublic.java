package localhost.rest.open;

import localhost.pojo.Item;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static localhost.Application.PUBLIC;

@RestController
@RequestMapping(PUBLIC)
public class RestPublic {

    @GetMapping
    public Item getFooBar() {
        return new Item();
    }
}
