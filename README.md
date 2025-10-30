# 📧 Spam Email Detection

A small Java project demonstrating a Naive Bayes spam detector with both a console and a web UI.

## Features

- Train a Naive Bayes classifier from `Mails.csv`.
- Web UI served by an embedded Java HttpServer (`WebServer.java`).
- Console UI via `Main.java` for quick testing.

## Prerequisites

- Java (JDK 8+)
- opencsv-5.7.1.jar and commons-lang3-3.14.0.jar (place in the same folder as sources or adjust classpath)

## Quick start (Windows PowerShell)

1. Open PowerShell and change to the source folder:

```powershell
Set-Location 'E:\javaFullstack\Spam-mail-main\jfs'
```

2. Compile the sources (PowerShell-safe):

```powershell
$files = Get-ChildItem -Filter *.java | ForEach-Object { $_.FullName }
javac -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" $files
```

3. Run the web UI (default port 8081):

```powershell
java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" WebServer
```

Open http://localhost:8081 in your browser.

4. (Optional) Run console UI:

```powershell
java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" Main
```

## Changing the server port

Edit `WebServer.java`, change the port passed to `new InetSocketAddress(...)`, recompile and run.

## Keep build artifacts out of git

This repo includes a `.gitignore` that excludes `*.class` files. If class files were accidentally committed earlier, they must be removed from the index (but can remain locally) with:

```powershell
git rm --cached "*.class"
git commit -m "Remove .class files from repository"
git push origin main
```

## Project layout

```
jfs/
├── Main.java
├── WebServer.java
├── NaiveBayesClassifier.java
├── EmailPreprocessor.java
├── Mails.csv
├── opencsv-5.7.1.jar
└── commons-lang3-3.14.0.jar
```

---
If you'd like, I can also:
- push this README to the remote (I'll run the git commands),
- or open a PR if you prefer a branch-based workflow.

Which would you like me to do next?
