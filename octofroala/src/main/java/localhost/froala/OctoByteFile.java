package localhost.froala;

public interface OctoByteFile extends OctoFile<byte[]> {

    default Class<byte[]> getContentClass() {
        return byte[].class;
    }

    byte[] getFileContent();
}
