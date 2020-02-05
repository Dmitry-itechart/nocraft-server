package localhost.froala;

import localhost.froala.event.OctoEffect;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * Any target git repository. Encapsulates all git-related operations
 * like get something from git or commit etc.
 */
public interface OctoKit {

    /**
     * Get git file content
     *
     * @param path - git path
     * @return froala if found, empty if not
     * @throws IOException if something going unrecoverably wrong
     */
    Optional<Froala> getFroala(Octopath path) throws IOException;


    // really void?
    OctoEffect commitFroala(Octopath path, Froala froala) throws IOException;


    OctoEffect commitFroala(Octopath path, Froala froala, List<OctoFile> list) throws IOException;


}
