package localhost.service.git;

import localhost.froala.OctoFroala;
import localhost.froala.Octopath;
import localhost.froala.impl.FroalaImpl;
import localhost.froala.impl.OctoFroalaImpl;
import localhost.froala.impl.OctopathImpl;
import localhost.rest.git.pojo.FroalaBasket;
import localhost.rest.git.pojo.OneFroala;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Comparator;

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

    private OctoFroala octoFroala;

    @PostConstruct
    void init() throws IOException {
        // builder or something...
        OctoFroalaImpl impl = new OctoFroalaImpl(
                config.getToken(),
                config.getUsername(),
                config.getRepository()
        );

        // can fail here is wrong git credentials
        impl.init();

        octoFroala = impl;
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
