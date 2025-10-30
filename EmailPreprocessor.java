import java.util.*;
import java.util.regex.*;

public class EmailPreprocessor {
    private static final Set<String> stopwords = new HashSet<>(Arrays.asList(
        "a", "an", "the", "is", "are", "to", "of", "in", "and", "on", "for", "with", "this", "that"
    ));

    public static List<String> tokenize(String text) {
        text = text.toLowerCase();
        List<String> words = new ArrayList<>();
        Matcher matcher = Pattern.compile("[a-zA-Z]+").matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
            if (!stopwords.contains(word)) {
                words.add(word);
            }
        }
        return words;
    }
}
