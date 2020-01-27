package localhost.service.profile;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CurrentProfileInfo {


    public ProfilePojo getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ProfilePojo p = new ProfilePojo();
        p.setAuthorities(new ArrayList<>(auth.getAuthorities()));

        if (auth instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken tk = (KeycloakAuthenticationToken) auth;
            String email = tk.getAccount().getKeycloakSecurityContext().getToken().getEmail();
            p.setEmail(email);
            p.setName(tk.getName());
        } else if (auth instanceof AnonymousAuthenticationToken) {
            AnonymousAuthenticationToken tk = (AnonymousAuthenticationToken) auth;
            p.setName(tk.getName());
        }

        return p;
    }
}
