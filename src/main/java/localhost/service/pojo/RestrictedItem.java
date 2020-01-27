package localhost.service.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RestrictedItem extends Item {

    private String access = "restricted: openid";

}
