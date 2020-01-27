package localhost.service.pojo;

import lombok.Data;

import java.net.URL;
import java.util.UUID;

@Data
public class Item {

    private String title;
    private URL img;
    private String description = UUID.randomUUID().toString();

}
