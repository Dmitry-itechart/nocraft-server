package localhost.service.git;

import localhost.bitbucket.fs.JGitOctoRepository;
import localhost.froala.OctoFile;
import localhost.froala.OctoFroala;
import localhost.froala.OctoFroalaListener;
import localhost.froala.Octopath;
import localhost.froala.effect.OctoEffect;
import localhost.froala.impl.FroalaTextImpl;
import localhost.froala.impl.OctoFroalaImpl;
import localhost.froala.impl.OctopathImpl;
import localhost.rest.git.FroalaFileDeserializer;
import localhost.rest.git.pojo.FroalaBasket;
import localhost.rest.git.pojo.FroalaInputBasket;
import lombok.RequiredArgsConstructor;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

    // remove dependency on octokit-github code (refactor to more flexible approach)
//    @PostConstruct
//    void init() throws IOException {
//        val kit = OctoKitGithubDeveloperTokenBuilder.builder()
//                .setLogin(config.getUsername())
//                .setRepository(config.getRepository())
//                .setOauthAccessToken(config.getToken())
//                .build();
//
//        // builder or something...
//        octoFroala = new OctoFroalaImpl(kit, listeners);
//    }

    @PostConstruct
    void init() throws GitAPIException {
        JGitOctoRepository jgit = new JGitOctoRepository(
                //filepath,
                config.getUsername(),
                config.getKeyPath(),
                config.getKeyPassword(),
                config.getRepository()
        );
        // refactor here to init automatically
        jgit.init();


        octoFroala = new OctoFroalaImpl(jgit, listeners);
    }


    public OctoEffect commit(FroalaInputBasket basket) throws IOException, NoSuchAlgorithmException {
        Octopath path = new OctopathImpl(basket.getPath(), basket.getComponentName());

        FroalaBasket cutOff = new FroalaBasket();
        cutOff.setComponentName(basket.getComponentName());
        cutOff.setPath(basket.getPath());
        cutOff.setElements(basket.getElements());

        var froala = new FroalaTextImpl(serializer.convert(cutOff));

        // we may want to refactor this later.
        if (basket.getFiles().isEmpty()) {
            return octoFroala.commitFroala(path, froala);
        } else {
            List<OctoFile> files = FroalaFileDeserializer.decodeList(basket.getFiles());
            return octoFroala.commitFroala(path, froala, files);
        }
    }
}
