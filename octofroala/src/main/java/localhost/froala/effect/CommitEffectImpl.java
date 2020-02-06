package localhost.froala.effect;

import localhost.froala.Octopath;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Stream.concat;


public class CommitEffectImpl extends AbstractOctoEffect {

    private List<Octopath> affectedFiles;

    public CommitEffectImpl affectedFiles(List<Octopath> list) {
        this.affectedFiles = list;
        return this;
    }

    @Override
    public List<Octopath> getAffectedFiles() {
        var prev = previous().map(OctoEffect::getAffectedFiles)
                .orElse(Collections.emptyList());
        return affectedFiles == null ? prev :
                concat(prev.stream(), affectedFiles.stream())
                        .collect(Collectors.toList());
    }
}
