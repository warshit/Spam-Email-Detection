import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.*;

public class Main {
    private static NaiveBayesClassifier classifier;

    public static void main(String[] args) {
        // Train once
        classifier = trainClassifier();

        // Console testing (optional)
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a new message: ");
        String newMessage = sc.nextLine();
        System.out.println("Predicted: " + (classifier.classify(newMessage) ? "SPAM" : "HAM"));
    }

    // This method lets WebServer.java get the trained classifier
    public static NaiveBayesClassifier getTrainedClassifier() {
        if (classifier == null) {
            classifier = trainClassifier();
        }
        return classifier;
    }

    private static NaiveBayesClassifier trainClassifier() {
        String csvFile = "Mails.csv";  
        NaiveBayesClassifier clf = new NaiveBayesClassifier();
        List<String[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                data.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Shuffle & split (80/20)
        Collections.shuffle(data, new Random(42));
        int split = (int)(data.size() * 0.8);

        List<String[]> trainData = data.subList(0, split);
        List<String[]> testData  = data.subList(split, data.size());

        // Train
        for (String[] row : trainData) {
            String category = row[0].trim().toLowerCase();
            String message  = row[1].trim();
            clf.train(message, category.equals("spam"));
        }

        // Test accuracy (optional)
        int correct = 0;
        for (String[] row : testData) {
            String category = row[0].trim().toLowerCase();
            String message  = row[1].trim();
            boolean predictedSpam = clf.classify(message);
            boolean actualSpam = category.equals("spam");
            if (predictedSpam == actualSpam) correct++;
        }
        double accuracy = 100.0 * correct / testData.size();
        System.out.println("Training finished. Accuracy = " + accuracy + "%");

        return clf;
    }
}


//javac -cp ".:opencsv-5.7.1.jar:commons-lang3-3.14.0.jar" *.java    //for compiling

//java -cp ".:opencsv-5.7.1.jar:commons-lang3-3.14.0.jar" Main        // for executing

// javac -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" Main.java //compile
// java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" WebServer  //runjavac -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" *.javajavac -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" *.javajavac -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" *.javajavac -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" *.java


// java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" WebServer