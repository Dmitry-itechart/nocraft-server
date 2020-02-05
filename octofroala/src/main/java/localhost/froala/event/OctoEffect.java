package localhost.froala.event;

import localhost.froala.Octopath;

import java.util.List;
import java.util.Optional;

public interface OctoEffect {

    default Optional<List<Octopath>> getOctoPath() {
        return Optional.empty();
    }

    default boolean isOk() {
        return true;
    }

}
