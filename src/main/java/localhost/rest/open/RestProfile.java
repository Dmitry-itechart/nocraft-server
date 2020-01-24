package localhost.rest.open;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static localhost.Application.PUBLIC;

@RestController
@RequestMapping(PUBLIC + "/profile")
public class RestProfile {

    @GetMapping
    public Object getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof AbstractAuthenticationToken) {
            return ((AbstractAuthenticationToken) auth).getAuthorities();
        } else {
            return auth;
        }
    }
}
