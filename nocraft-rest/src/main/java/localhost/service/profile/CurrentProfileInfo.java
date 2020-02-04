package localhost.service.profile;

import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * determine authentication type and extract data accordingly
 */
@Component
public class CurrentProfileInfo {

    public ProfilePojo getProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ProfilePojo p = new ProfilePojo();
        p.setAuthorities(new ArrayList<>(auth.getAuthorities()));

        if (auth instanceof KeycloakAuthenticationToken) {
            KeycloakAuthenticationToken tk = (KeycloakAuthenticationToken) auth;

            OidcKeycloakAccount details = (OidcKeycloakAccount) tk.getDetails();

            AccessToken token = details.getKeycloakSecurityContext().getToken();

            // here a lot of unused info, we may refactor it someday.
            p.setToken(token);

            String email = tk.getAccount().getKeycloakSecurityContext().getToken().getEmail();
            p.setEmail(email);

        } else if (auth instanceof AnonymousAuthenticationToken) {
            AnonymousAuthenticationToken tk = (AnonymousAuthenticationToken) auth;
        }

        return p;
    }
}
