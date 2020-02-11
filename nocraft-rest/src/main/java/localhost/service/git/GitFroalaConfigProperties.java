package localhost.service.git;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Configuration
@ConfigurationProperties(prefix = "froala.git")
@Validated
public class GitFroalaConfigProperties {

    @NotBlank
    private String username;

    @NotBlank
    private String keyPath;

    @NotNull
    private String keyPassword;

    @NotBlank
    private String repository;
}
