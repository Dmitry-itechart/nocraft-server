package localhost.service.git;

import localhost.froala.OctoFroala;
import localhost.froala.OctoFroalaListener;
import localhost.froala.Octopath;
import localhost.froala.impl.FroalaImpl;
import localhost.froala.impl.OctoFroalaImpl;
import localhost.froala.impl.OctopathImpl;
import localhost.octokit.github.OctoKitGithubDeveloperTokenBuilder;
import localhost.rest.git.pojo.FroalaBasket;
import localhost.rest.git.pojo.OneFroala;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/*
 * Probably can resolve proper per-user repository here, check auht or other
 * for simplicity assume just working with one correct git repository.
 *
 */
@RequiredArgsConstructor
@Service
public class GitFroalatorService {

    private final GitFroalaConfigProperties config;

    private final FroalaGitSerializer serializer;

    private final List<OctoFroalaListener> listeners;

    private OctoFroala octoFroala;

    @PostConstruct
    void init() throws IOException {
        val kit = OctoKitGithubDeveloperTokenBuilder.builder()
                .setLogin(config.getUsername())
                .setRepository(config.getRepository())
                .setOauthAccessToken(config.getToken())
                .build();

        // builder or something...
        octoFroala = new OctoFroalaImpl(kit, listeners);
    }

    public void commit(FroalaBasket basket) throws IOException {
        Octopath path = new OctopathImpl(basket.getPath(), basket.getComponentName());

        var elements = basket.getElements();
        elements.sort(Comparator.comparing(OneFroala::getName));
        basket.setElements(elements);

        var froala = new FroalaImpl(serializer.convert(basket));

        octoFroala.commitFroala(path, froala);
    }
}
