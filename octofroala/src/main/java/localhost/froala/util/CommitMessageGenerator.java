package localhost.froala.util;

import localhost.froala.Octopath;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommitMessageGenerator {

    private static DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static String getCommitMessage(Octopath path) {
        return String.join(", ", LocalDateTime.now().format(dtf), path.toString());
    }
}
