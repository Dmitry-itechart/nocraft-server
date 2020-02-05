package localhost.rest.git.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Valid
public class FroalaBasket {

    @ApiModelProperty(example = "src/app/")
    @NotBlank
    private String path;

    @ApiModelProperty(example = "app.component.json")
    @NotBlank
    private String componentName;

    @NotEmpty
    private JsonNode elements;

}