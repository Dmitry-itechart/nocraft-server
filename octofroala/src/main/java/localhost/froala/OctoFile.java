package localhost.froala;

import java.util.Objects;
import java.util.function.Predicate;

public interface OctoFile<T> {

    Predicate<OctoFile> bytePredicate = octoFile -> Objects.equals(octoFile.getContentClass(), byte[].class);
    Predicate<OctoFile> stringPredicate = octoFile -> Objects.equals(octoFile.getContentClass(), String.class);

    Class<T> getContentClass();

    Octopath getFilePath();

    T getFileContent();
}
