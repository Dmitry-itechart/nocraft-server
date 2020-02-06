package localhost.froala.impl;

import localhost.froala.OctoTextFile;
import localhost.froala.Octopath;

public class OctoTextFileImpl extends AbstractOctoFile<String> implements OctoTextFile {
    private String content;

    public OctoTextFileImpl(Octopath path) {
        super(path);
    }

    public OctoTextFileImpl setFileContent(String text) {
        this.content = text;
        return this;
    }

    @Override
    public String getFileContent() {
        return content;
    }
}
