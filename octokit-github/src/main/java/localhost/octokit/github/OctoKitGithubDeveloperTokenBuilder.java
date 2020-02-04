package localhost.octokit.github;

import localhost.froala.OctoKit;
import localhost.froala.OctoKitBuilder;
import org.kohsuke.github.GitHub;

import java.io.IOException;

public class OctoKitGithubDeveloperTokenBuilder implements OctoKitBuilder {

    private String oauthAccessToken;
    private String login;
    private String repository;

    private OctoKitGithubDeveloperTokenBuilder() {
    }

    public static OctoKitGithubDeveloperTokenBuilder builder() {
        return new OctoKitGithubDeveloperTokenBuilder();
    }

    public OctoKitGithubDeveloperTokenBuilder setOauthAccessToken(String oauthAccessToken) {
        this.oauthAccessToken = oauthAccessToken;
        return this;
    }

    public OctoKitGithubDeveloperTokenBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public OctoKitGithubDeveloperTokenBuilder setRepository(String repository) {
        this.repository = repository;
        return this;
    }

    @Override
    public OctoKit build() throws IOException {
        GitHub server = GitHub.connect(login, oauthAccessToken);
        var git = server.getRepository(login + "/" + repository);
        return new OctoKitRestGithub(git);
    }
}
