package localhost.froala.impl;

import localhost.froala.OctoTextFile;
import localhost.froala.Octopath;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

import static localhost.froala.util.ByteDigester.generateHash;

public class OctoTextFileImpl extends AbstractOctoFile<String> implements OctoTextFile {
    private String content;
    private byte[] hash;

    public OctoTextFileImpl(Octopath path) {
        super(path);
    }

    public OctoTextFileImpl setFileContent(byte[] bb) throws NoSuchAlgorithmException {
        this.content = new String(bb, StandardCharsets.UTF_8);
        hash = generateHash(bb);
        return this;
    }

    @Override
    public String getFileContent() {
        return content;
    }

    @Override
    public byte[] hash() {
        return hash;
    }
}
