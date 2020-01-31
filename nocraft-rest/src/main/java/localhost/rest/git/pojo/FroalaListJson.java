package localhost.rest.git.pojo;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Valid
public class FroalaListJson {

    @NotBlank
    private String path;

    @NotBlank
    private String componentName;

    @NotEmpty
    private List<FroalaJson> elements;

}