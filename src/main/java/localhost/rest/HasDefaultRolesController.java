package localhost.rest;


import localhost.service.DummyServiceInterface;
import localhost.service.UMARoleDummyService;
import localhost.service.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static localhost.Application.API;

/**
 * Fetch data from non-anonymous data provider
 */
@RestController
@RequestMapping(API + "/def")
public class HasDefaultRolesController {

    private DummyServiceInterface service;

    @Autowired
    @Qualifier(UMARoleDummyService.NAME)
    private void setService(DummyServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> getItems() {
        return service.getItems().orElse(Collections.emptyList());
    }


}
