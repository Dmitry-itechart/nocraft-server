package localhost.service.util;

import localhost.service.pojo.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DummyService {

    private final ImageService imageService;

    public List<Item> getItems(String title, int count) {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Item item = new Item();
            item.setTitle(title + UUID.randomUUID().toString());
            item.setImg(imageService.getRandomPhotoUrl());
            list.add(item);

        }
        return list;
    }
}
