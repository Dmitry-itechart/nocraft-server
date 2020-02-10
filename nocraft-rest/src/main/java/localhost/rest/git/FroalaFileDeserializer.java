package localhost.rest.git;

import localhost.froala.OctoFile;
import localhost.froala.Octopath;
import localhost.froala.impl.OctoByteFileImpl;
import localhost.froala.impl.OctoTextFileImpl;
import localhost.froala.impl.OctopathImpl;
import localhost.rest.git.pojo.FroalaFile;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/*
 * data:image/jpeg;base64
 * data:image/png;base64
 * data:image/svg+xml;base64
 */
public class FroalaFileDeserializer {

    public static List<OctoFile> decodeList(List<FroalaFile> input) throws NoSuchAlgorithmException {
        if (input.isEmpty()) {
            return Collections.emptyList();
        }
        List<OctoFile> list = new ArrayList<>(input.size());
        for (FroalaFile f : input) {
            var file = decode(f);
            list.add(file);
        }
        return list;
    }

    private static OctoFile decode(FroalaFile file) throws NoSuchAlgorithmException {
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
            byte[] bb = Base64.getDecoder().decode(data);
            return new OctoTextFileImpl(path).setFileContent(bb);
        }
    }

    // omg refactor it!
    private static boolean isBinaryFile(String bHeader) {
        return !bHeader.contains("image/svg+xml");
    }


}
