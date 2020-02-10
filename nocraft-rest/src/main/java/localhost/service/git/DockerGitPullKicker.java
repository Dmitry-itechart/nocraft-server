package localhost.service.git;

import localhost.froala.OctoFroalaListener;
import localhost.froala.effect.CommitEffectImpl;
import localhost.froala.effect.KickPullOctoEffect;
import localhost.froala.effect.OctoEffect;
import localhost.froala.event.OctoEvent;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor
@Component
public class DockerGitPullKicker implements OctoFroalaListener {

    private final FroalaUINodeServerProperties properties;

    private RestTemplate template;

    @PostConstruct
    void init() {

        // short timeout
        int timeout = 100;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();

        // no retry for empty socat response (see UI container notification listen.sh)
        DefaultHttpRequestRetryHandler h =
                new DefaultHttpRequestRetryHandler(0, false);

        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .setRetryHandler(h)
                .build();

        template = new RestTemplate(new HttpComponentsClientHttpRequestFactory(client));
    }

    @Override
    public OctoEffect onOctoEvent(OctoEvent event) {
        try {
            ResponseEntity resp = template.getForEntity(properties.getUrl(), ResponseEntity.class);
            if (resp.getStatusCode().isError()) {
                return new KickPullOctoEffect().isOk(false);
            } else {
                return new KickPullOctoEffect().url(properties.getUrl()).code(resp.getStatusCodeValue());
            }
        } catch (RestClientException e) {
            // log it here?
            return new CommitEffectImpl().isOk(false);
        }
    }
}
