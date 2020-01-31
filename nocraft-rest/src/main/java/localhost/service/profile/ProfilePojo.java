package localhost.service.profile;


import lombok.Data;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class ProfilePojo {

    private String givenName;

    private String familyName;

    private AccessToken token;

    private String email;

    private Collection<GrantedAuthority> authorities;

}
