package localhost.rest.profile;

import localhost.service.profile.CurrentProfileInfo;
import localhost.service.profile.ProfilePojo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static localhost.Application.API;

@AllArgsConstructor
@RestController
@RequestMapping(API + "/profile")
public class CurrentProfileController {

    private final CurrentProfileInfo info;

    @GetMapping
    public ProfilePojo getProfile() {
        return info.getProfile();
    }
}
