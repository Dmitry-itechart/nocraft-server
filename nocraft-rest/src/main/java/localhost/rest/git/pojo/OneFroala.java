package localhost.rest.git.pojo;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OneFroala {

    @ApiModelProperty("text")
    @NotBlank
    private String type;

    @ApiModelProperty("froalaName")
    @NotBlank
    private String name;

    private JsonNode details;
}
