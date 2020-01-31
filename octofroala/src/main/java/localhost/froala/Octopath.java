package localhost.froala;

public interface Octopath {
    String getPath();

    String getItem();

    default String getItemPath() {
        return getPath() + getItem();
    }
}
