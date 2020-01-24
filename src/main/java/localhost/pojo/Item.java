package localhost.pojo;

import lombok.Data;

import java.util.UUID;

@Data
public class Item {

    private String foo = UUID.randomUUID().toString();
    private String bar = UUID.randomUUID().toString();

}
