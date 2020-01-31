package localhost.froala.impl;

import localhost.froala.Octopath;
import lombok.Data;

@Data
public class OctopathImpl implements Octopath {
    private final String path;
    private final String item;
}
