package localhost.service.git;


import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Configuration
@ConfigurationProperties(prefix = "froala.ui")
@Validated
public class FroalaUINodeServerProperties {

    @URL
    @NotBlank
    private String url;

}
