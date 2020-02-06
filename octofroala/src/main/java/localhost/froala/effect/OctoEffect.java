package localhost.froala.effect;

import localhost.froala.Octopath;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface OctoEffect {

    Optional<OctoEffect> previous();

    OctoEffect setPrevious(OctoEffect prev);

    default boolean isOk() {
        return previous().map(OctoEffect::isOk).orElse(false);
    }

    default List<Octopath> getAffectedFiles() {
        return previous().map(OctoEffect::getAffectedFiles)
                .orElse(Collections.emptyList());
    }

}
