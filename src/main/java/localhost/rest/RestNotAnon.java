package localhost.rest;


import localhost.service.DummyServiceInterface;
import localhost.service.NotAnonymouseDummyService;
import localhost.service.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static localhost.Application.PUBLIC;

/**
 * Fetch data from non-anonymous data provider
 */
@RestController
@RequestMapping(PUBLIC + "/nonanon")
public class RestNotAnon {

    private DummyServiceInterface service;

    @Autowired
    @Qualifier(NotAnonymouseDummyService.NAME)
    private void setService(DummyServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<Item> getItems() {
        return service.getItems().orElse(Collections.emptyList());
    }
}
