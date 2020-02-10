package impl;

import localhost.froala.Froala;
import localhost.froala.OctoFile;
import localhost.froala.OctoKit;
import localhost.froala.Octopath;
import localhost.froala.impl.FroalaTextImpl;
import localhost.froala.impl.OctoFroalaImpl;
import localhost.froala.impl.OctoTextFileImpl;
import localhost.froala.impl.OctopathImpl;
import localhost.octokit.github.OctoKitGithubDeveloperTokenBuilder;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OctoFroalaImplTest {

    private OctoKit kit;

    @BeforeEach
    void init() throws IOException {
        var login = System.getenv("github.login");
        var token = System.getenv("github.token");

        assertNotNull(login);
        assertNotNull(token);

        kit = OctoKitGithubDeveloperTokenBuilder.builder()
                .setOauthAccessToken(token).setLogin(login).setRepository("test").build();
    }

    @Disabled
    @Test
    void test() throws IOException {
        OctoFroalaImpl impl = new OctoFroalaImpl(kit, Collections.emptyList());

        String content = "piu piu " + UUID.randomUUID().toString();

        Froala fr = new FroalaTextImpl(content);
        Octopath path = new OctopathImpl("", "test.txt");

        impl.commitFroala(path, fr);

        Froala f = impl.getFroala(path).orElseThrow();

        Assertions.assertEquals(content, f.getFroala());
    }

    @Disabled
    @Test
    void testCanSaveBinary() throws IOException, NoSuchAlgorithmException {
        OctoFroalaImpl impl = new OctoFroalaImpl(kit, Collections.emptyList());

        List<String> list;
        try (InputStream in = OctoFroalaImplTest.class.getResourceAsStream("base64")) {
            list = IOUtils.readLines(in);
        }
        assertNotNull(list);
        assertFalse(list.isEmpty());

        var sContent = String.join("", list);

        OctoFile f = new OctoTextFileImpl(new OctopathImpl("", "image.png"))
                .setFileContent(sContent.getBytes(StandardCharsets.UTF_8));

        String s = "piu piu " + UUID.randomUUID().toString();
        Froala fr = new FroalaTextImpl(s);
        Octopath path = new OctopathImpl("", "test.txt");

        impl.commitFroala(path, fr, Collections.singletonList(f));
    }
}
