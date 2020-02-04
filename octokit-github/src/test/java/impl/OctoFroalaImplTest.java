package impl;

import localhost.froala.Froala;
import localhost.froala.OctoKit;
import localhost.froala.Octopath;
import localhost.froala.impl.FroalaImpl;
import localhost.froala.impl.OctoFroalaImpl;
import localhost.froala.impl.OctopathImpl;
import localhost.octokit.github.OctoKitGithubDeveloperTokenBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

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

        Froala<String> fr = new FroalaImpl(content);
        Octopath path = new OctopathImpl("", "test.txt");

        impl.commitFroala(path, fr);

        Froala f = impl.getFroala(path).orElseThrow();

        Assertions.assertEquals(content, f.getFroala());
    }
}
