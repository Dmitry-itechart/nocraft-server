package localhost.froala.impl;

import localhost.froala.OctoFile;
import localhost.froala.Octopath;

public abstract class AbstractOctoFile<T> implements OctoFile<T> {

    private Octopath path;

    AbstractOctoFile(Octopath octopath) {
        this.path = octopath;
    }

    @Override
    public Octopath getFilePath() {
        return path;
    }
}
