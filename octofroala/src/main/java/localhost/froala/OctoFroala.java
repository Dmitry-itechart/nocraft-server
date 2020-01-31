package localhost.froala;

import java.io.IOException;
import java.util.Optional;

public interface OctoFroala {

    /**
     * Get git file content
     *
     * @param path - git paht. Root is empty list.
     * @return froala if file found, empty if not
     * @throws IOException if something going wrong
     */
    Optional<Froala> getFroala(Octopath path) throws IOException;

    // really void?
    void commitFroala(Octopath path, Froala froala) throws IOException;

}
