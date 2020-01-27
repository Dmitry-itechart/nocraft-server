package localhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@SpringBootApplication
public class Application {

    public static final String PUBLIC = "/api";

    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args);
    }
}
