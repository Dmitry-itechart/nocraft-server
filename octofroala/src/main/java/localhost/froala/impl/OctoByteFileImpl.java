package localhost.froala.impl;

import localhost.froala.OctoByteFile;
import localhost.froala.Octopath;

public class OctoByteFileImpl extends AbstractOctoFile<byte[]> implements OctoByteFile {

    private byte[] fileContent;

    public OctoByteFileImpl(Octopath path) {
        super(path);
    }

    @Override
    public byte[] getFileContent() {
        return fileContent;
    }

    public OctoByteFileImpl setFileContent(byte[] content) {
        this.fileContent = content;
        return this;
    }
}
