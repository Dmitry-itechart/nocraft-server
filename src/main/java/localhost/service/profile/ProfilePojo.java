package localhost.service.profile;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class ProfilePojo {

    private String name;

    private String email;

    private Collection<GrantedAuthority> authorities;

}
