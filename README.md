# 📧 Spam Email Detection

A machine learning-based email spam detection system with both web and console interfaces. Built with Java and uses Naive Bayes classification for spam detection.

## 🌟 Features

- **Web Interface**: Modern, responsive UI for easy email classification
- **Console Interface**: Quick command-line testing option
- **Machine Learning**: Uses Naive Bayes classifier for accurate spam detection
- **Real-time Processing**: Instant classification results
- **CSV Training**: Pre-trained on a dataset of spam and ham emails

## 🛠️ Prerequisites

- Java Development Kit (JDK) 8 or higher
- Required JAR files (included in the project):
  - `opencsv-5.7.1.jar`
  - `commons-lang3-3.14.0.jar`

## 🚀 Getting Started

### Clone the Repository

```bash
git clone https://github.com/warshit/Spam-Email-Detection.git
cd Spam-Email-Detection/jfs
```

### Compile the Project

Windows:
```powershell
javac -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" *.java
```

Linux/Mac:
```bash
javac -cp ".:opencsv-5.7.1.jar:commons-lang3-3.14.0.jar" *.java
```

### Run the Web Interface

Windows:
```powershell
java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" WebServer
```

Linux/Mac:
```bash
java -cp ".:opencsv-5.7.1.jar:commons-lang3-3.14.0.jar" WebServer
```

Then open your browser and navigate to: `http://localhost:8081`

### Run the Console Interface (Optional)

Windows:
```powershell
java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" Main
```

Linux/Mac:
```bash
java -cp ".:opencsv-5.7.1.jar:commons-lang3-3.14.0.jar" Main
```

## 💻 Usage

### Web Interface

1. Open `http://localhost:8081` in your browser
2. Enter or paste the email message in the text area
3. Click "Analyze Message"
4. View the classification result (SPAM or HAM)

### Console Interface

1. Run the Main class
2. Enter the email message when prompted
3. View the classification result

## 🏗️ Project Structure

```
jfs/
├── Main.java              # Console interface and classifier training
├── WebServer.java         # Web interface implementation
├── NaiveBayesClassifier.java  # Spam detection algorithm
├── EmailPreprocessor.java # Text processing utilities
├── Mails.csv             # Training dataset
└── lib/                  # Required JAR files
```

## 🤖 Technical Details

- **Classification Algorithm**: Naive Bayes
- **Training Data**: 80% of dataset
- **Testing Data**: 20% of dataset
- **Web Server**: Java's built-in HttpServer
- **UI Framework**: Custom HTML/CSS with modern design
- **Data Format**: CSV for training data

## 🔍 API Endpoints

- `GET /` - Serves the main UI page
- `POST /classify` - Accepts email text and returns classification result

## ⚙️ Configuration

The web server runs on port 8081 by default. To change the port:
1. Open `WebServer.java`
2. Modify the port number in `new InetSocketAddress(8081)`
3. Recompile and run

## 👥 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.



---
Built with ❤️ and Java
