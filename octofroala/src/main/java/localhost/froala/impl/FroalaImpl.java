package localhost.froala.impl;

import localhost.froala.Froala;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FroalaImpl implements Froala<String> {

    private final String content;

    @Override
    public String getFroala() {
        return content;
    }
}
