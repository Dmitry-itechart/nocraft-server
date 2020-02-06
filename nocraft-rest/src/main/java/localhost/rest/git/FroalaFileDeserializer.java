package localhost.rest.git;

import localhost.froala.OctoFile;
import localhost.froala.Octopath;
import localhost.froala.impl.OctoByteFileImpl;
import localhost.froala.impl.OctoTextFileImpl;
import localhost.froala.impl.OctopathImpl;
import localhost.rest.git.pojo.FroalaFile;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * a
 * data:image/jpeg;base64
 * data:image/png;base64
 * data:image/svg+xml;base64
 */
public class FroalaFileDeserializer {

    public static OctoFile decode(FroalaFile file) {
        String base64 = file.getBase64();
        String[] parts = base64.split(",");

        if (parts.length != 2) {
            throw new RuntimeException("incorrect file content!");
        }
        String data = parts[1];

        Octopath path = new OctopathImpl(file.getFilePath(), file.getFileName());
        if (isBinaryFile(parts[0])) {

            return new OctoByteFileImpl(path).setFileContent(Base64.getDecoder().decode(data));

        } else {

            String sFile = new String(Base64.getDecoder().decode(data), StandardCharsets.UTF_8);
            return new OctoTextFileImpl(path).setFileContent(sFile);

        }
    }

    // omg refactor it!
    private static boolean isBinaryFile(String bHeader) {
        return !bHeader.contains("image/svg+xml");
    }


}
