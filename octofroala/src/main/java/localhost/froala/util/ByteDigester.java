package localhost.froala.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ByteDigester {

    public static byte[] generateHash(byte[] in) throws NoSuchAlgorithmException {
        return DigestUtils.digest(MessageDigest.getInstance("SHA"), in);
    }
}
