package localhost.rest.git;


import localhost.rest.git.pojo.FroalaInputBasket;
import localhost.service.git.GitFroalatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static localhost.Application.API;

/**
 * try to fetch from all items providers. If any of providers is restricted - whole request is restricted.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/github")
@Validated
public class SaveItToGitController {

    private final GitFroalatorService service;

    @PostMapping
    @ResponseBody
    public ResponseEntity commitFroala(@RequestBody FroalaInputBasket froalaList) throws IOException {
        // log, or some more meaning value?

        // one more, we need to determine floara type and use different flows for different
        // froala types like text-based or with images.
        service.commit(froalaList);

        return ResponseEntity.ok().build();
    }
}
