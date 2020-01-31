package localhost.froala.impl;

import localhost.froala.Froala;
import localhost.froala.Octopath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OctoFroalaImplTest {

    private String login;
    private String token;

    @BeforeEach
    void init() {
        login = System.getenv("github.login");
        token = System.getenv("github.token");
    }

    //@Disabled
    @Test
    void test() throws IOException {
        OctoFroalaImpl impl = new OctoFroalaImpl(token, login, "test");
        impl.init();

        String content = "piu piu " + UUID.randomUUID().toString();

        Froala<String> fr = new FroalaImpl(content);
        Octopath path = new OctopathImpl("", "test.txt");

        impl.commitFroala(path, fr);

        Froala f = impl.getFroala(path).orElseThrow();

        assertEquals(content, f.getFroala());
    }
}
