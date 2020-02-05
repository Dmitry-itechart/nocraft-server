package localhost.froala.impl;

import localhost.froala.Froala;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FroalaTextImpl implements Froala {

    private final String content;

    @Override
    public String getFroala() {
        return content;
    }
}
