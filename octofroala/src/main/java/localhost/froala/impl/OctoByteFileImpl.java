package localhost.froala.impl;

import localhost.froala.OctoByteFile;
import localhost.froala.Octopath;

import java.security.NoSuchAlgorithmException;

import static localhost.froala.util.ByteDigester.generateHash;

public class OctoByteFileImpl extends AbstractOctoFile<byte[]> implements OctoByteFile {

    private byte[] fileContent;
    private byte[] hash;

    public OctoByteFileImpl(Octopath path) {
        super(path);
    }

    @Override
    public byte[] getFileContent() {
        return fileContent;
    }

    @Override
    public byte[] hash() {
        return hash;
    }

    public OctoByteFileImpl setFileContent(byte[] content) throws NoSuchAlgorithmException {
        this.fileContent = content;
        hash = generateHash(content);
        return this;
    }
}
