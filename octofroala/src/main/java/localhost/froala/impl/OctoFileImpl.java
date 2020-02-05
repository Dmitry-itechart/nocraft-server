package localhost.froala.impl;

import localhost.froala.OctoFile;
import localhost.froala.Octopath;

import java.util.Base64;

public class OctoFileImpl implements OctoFile {
    private Octopath filePath;
    private byte[] fileContent;

    @Override
    public Octopath getFilePath() {
        return filePath;
    }

    @Override
    public byte[] getFileContent() {
        return fileContent;
    }

    public OctoFileImpl setFilePath(Octopath filePath) {
        this.filePath = filePath;
        return this;
    }

    /**
     * Pay attention base64 data can come in form of 'data:image/png;base64,iVBORw0KGgoAA...'
     * Where we need to cut off all before firs comma.
     *
     * @param fileContent base64 html-style content
     * @return builder
     */
    public OctoFileImpl setFileContent(String fileContent) {
        String[] parts = fileContent.split(",");

        String data;
        if (parts.length <= 1) {
            data = parts[0];
        } else {
            data = parts[1];
        }
        this.fileContent = Base64.getDecoder().decode(data);
        return this;
    }
}
