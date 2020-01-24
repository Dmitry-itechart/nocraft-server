package localhost.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
public class ImageService {

    @Value("${image.path}")
    private URL url;

    URL getRandomPhotoUrl() {
        return url;
    }

}
