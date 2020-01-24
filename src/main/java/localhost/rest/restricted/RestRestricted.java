package localhost.rest.restricted;

import localhost.pojo.Item;
import localhost.pojo.RestrictedItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static localhost.Application.RESTRICTED;

@RestController
@RequestMapping(RESTRICTED)
public class RestRestricted {

    @GetMapping
    public Item getFooBar() {
        return new RestrictedItem();
    }
}
