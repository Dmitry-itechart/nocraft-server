package localhost.service.git;

import localhost.froala.OctoFroalaListener;
import localhost.froala.event.OctoEffect;
import localhost.froala.event.OctoEffectImpl;
import localhost.froala.event.OctoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class DockerGitPullKicker implements OctoFroalaListener {

    private final RestTemplate template;
    private final FroalaUINodeServerProperties properties;

    @Override
    public OctoEffect onOctoEvent(OctoEvent event) {
        try {
            ResponseEntity resp = template.getForEntity(properties.getUrl(), ResponseEntity.class);
            if (resp.getStatusCode().isError()) {
                return new OctoEffectImpl(false);
            }
        } catch (RestClientException e) {
            // log it here?

            return new OctoEffectImpl(false);
        }
        return new OctoEffectImpl();
    }
}
