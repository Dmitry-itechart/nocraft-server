package localhost.service.git;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import localhost.rest.git.pojo.FroalaBasket;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
For IDE we use checkstyle, for git we have to use stable mapper configuration
 */
@Component
public class FroalaGitSerializer {

    private ObjectWriter om;

    @PostConstruct
    void init() {
        var oom = new ObjectMapper();
        om = oom.writerWithDefaultPrettyPrinter();
    }

    String convert(FroalaBasket basket) throws JsonProcessingException {
        // git files requires new line at the end, may be some elegant solution later.
        return om.writeValueAsString(basket) + System.lineSeparator();
    }
}
