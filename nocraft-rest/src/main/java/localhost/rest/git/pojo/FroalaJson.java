package localhost.rest.git.pojo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FroalaJson {

    private String htmlContent;

    @NotBlank
    private String name;
}
