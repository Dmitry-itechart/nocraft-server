package localhost.rest.git.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FroalaInputBasket extends FroalaBasket {

    private List<FroalaFile> files;
}
