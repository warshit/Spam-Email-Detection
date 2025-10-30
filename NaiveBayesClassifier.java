import java.util.*;

public class NaiveBayesClassifier {
    private Map<String, Integer> spamWordCounts = new HashMap<>();
    private Map<String, Integer> hamWordCounts = new HashMap<>();
    private int spamEmails = 0, hamEmails = 0;
    private int spamWordTotal = 0, hamWordTotal = 0;

    // Train the classifier
    public void train(String email, boolean isSpam) {
        List<String> words = EmailPreprocessor.tokenize(email);
        if (isSpam) {
            spamEmails++;
            for (String word : words) {
                spamWordCounts.put(word, spamWordCounts.getOrDefault(word, 0) + 1);
                spamWordTotal++;
            }
        } else {
            hamEmails++;
            for (String word : words) {
                hamWordCounts.put(word, hamWordCounts.getOrDefault(word, 0) + 1);
                hamWordTotal++;
            }
        }
    }

    // Classify an email
    public boolean classify(String email) {
        List<String> words = EmailPreprocessor.tokenize(email);

        double spamProb = Math.log((double) spamEmails / (spamEmails + hamEmails));
        double hamProb = Math.log((double) hamEmails / (spamEmails + hamEmails));

        for (String word : words) {
            // Laplace smoothing
            double pwSpam = (spamWordCounts.getOrDefault(word, 0) + 1.0) / (spamWordTotal + spamWordCounts.size());
            double pwHam  = (hamWordCounts.getOrDefault(word, 0) + 1.0) / (hamWordTotal + hamWordCounts.size());

            spamProb += Math.log(pwSpam);
            hamProb  += Math.log(pwHam);
        }

        return spamProb > hamProb; // true = spam, false = ham
    }
}
