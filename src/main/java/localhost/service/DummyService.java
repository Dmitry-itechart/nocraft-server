package localhost.service;

import localhost.pojo.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DummyService {

    private final ImageService imageService;

    public List<Item> getItems() {
        ArrayList<Item> list = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Item item = new Item();
            item.setImg(imageService.getRandomPhotoUrl());
            list.add(item);

        }
        return list;
    }


}
