package localhost.froala;

public interface OctoTextFile extends OctoFile<String> {

    default Class<String> getContentClass() {
        return String.class;
    }

    String getFileContent();
}
