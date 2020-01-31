package localhost.rest.git.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OneFroala {

    @ApiModelProperty(example = "<p style=\\\"margin-left: 100px;\\\"><strong>Hello World</strong></p>")
    private String htmlContent;

    @ApiModelProperty(example = "testText")
    @NotBlank
    private String name;
}
