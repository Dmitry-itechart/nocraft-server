package localhost.rest;

import localhost.service.profile.CurrentProfileInfo;
import localhost.service.profile.ProfilePojo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static localhost.Application.PUBLIC;

@AllArgsConstructor
@RestController
@RequestMapping(PUBLIC + "/profile")
public class RestPublicProfile {

    private final CurrentProfileInfo info;

    @GetMapping
    public ProfilePojo getProfile() {
        return info.getProfile();
    }
}
