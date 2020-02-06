package localhost.froala.effect;

public class KickPullOctoEffect extends AbstractOctoEffect {

    private String url;
    private int code;

    public String getUrl() {
        return url;
    }

    public KickPullOctoEffect url(String url) {
        this.url = url;
        return this;
    }

    public int getCode() {
        return code;
    }

    public KickPullOctoEffect code(int code) {
        this.code = code;
        return this;
    }
}
