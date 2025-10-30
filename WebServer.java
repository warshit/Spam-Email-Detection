// import com.sun.net.httpserver.HttpServer;
// import com.sun.net.httpserver.HttpExchange;
// import java.io.IOException;
// import java.io.OutputStream;
// import java.net.InetSocketAddress;

// public class WebServer {
//     public static void main(String[] args) throws Exception {
//         // Start a simple HTTP server on port 8080
//         HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
//         server.createContext("/", (exchange -> {
//             try {
//                 String response = getHtmlPage("");
//                 byte[] responseBytes = response.getBytes("UTF-8");
//                 exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
//                 exchange.sendResponseHeaders(200, responseBytes.length);
//                 try (OutputStream os = exchange.getResponseBody()) {
//                     os.write(responseBytes);
//                 }
//             } catch (Exception e) {
//                 e.printStackTrace();
//                 String errorResponse = "Internal Server Error";
//                 exchange.sendResponseHeaders(500, errorResponse.length());
//                 try (OutputStream os = exchange.getResponseBody()) {
//                     os.write(errorResponse.getBytes());
//                 }
//             } finally {
//                 exchange.close();
//             }
//         }));

//         server.createContext("/classify", (exchange -> {
//             try {
//                 if ("POST".equals(exchange.getRequestMethod())) {
//                     String body = new String(exchange.getRequestBody().readAllBytes(), "UTF-8");
//                     String message = java.net.URLDecoder.decode(body.replace("message=", ""), "UTF-8");

//                     boolean isSpam = Main.getTrainedClassifier().classify(message);
//                     String result = isSpam ? "SPAM" : "HAM";

//                     String response = getHtmlPage("Message: " + message + "<br>Prediction: <b>" + result + "</b>");
//                     byte[] responseBytes = response.getBytes("UTF-8");
//                     exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
//                     exchange.sendResponseHeaders(200, responseBytes.length);
//                     try (OutputStream os = exchange.getResponseBody()) {
//                         os.write(responseBytes);
//                     }
//                 } else {
//                     String errorResponse = "Method not allowed";
//                     exchange.sendResponseHeaders(405, errorResponse.length());
//                     try (OutputStream os = exchange.getResponseBody()) {
//                         os.write(errorResponse.getBytes());
//                     }
//                 }
//             } catch (Exception e) {
//                 e.printStackTrace();
//                 String errorResponse = "Internal Server Error";
//                 exchange.sendResponseHeaders(500, errorResponse.length());
//                 try (OutputStream os = exchange.getResponseBody()) {
//                     os.write(errorResponse.getBytes());
//                 }
//             } finally {
//                 exchange.close();
//             }
//         }));

//         System.out.println("Server running at http://localhost:8081");
//         server.start();
//     }

//     private static String formatResult(String result) {
//         if (result.isEmpty()) return "";
        
//         String resultClass = result.contains("SPAM") ? "spam" : "ham";
//         return String.format("<div class='result %s'>%s</div>", resultClass, result);
//     }

//     private static String getHtmlPage(String result) {
//         return """
//         <!DOCTYPE html>
//     <html lang="en">
//     <head>
//         <meta charset="UTF-8">
//         <meta name="viewport" content="width=device-width, initial-scale=1.0">
//         <title>Email Spam Detector</title>
//         <style>
//             @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
            
//             body {
//                 margin: 0;
//                 font-family: 'Poppins', sans-serif;
//                 background: linear-gradient(135deg, #6e8efb, #a777e3);
//                 display: flex;
//                 justify-content: center;
//                 align-items: center;
//                 height: 100vh;
//                 color: #333;
//                 overflow: hidden;
//             }

//             .container {
//                 background: rgba(255, 255, 255, 0.2);
//                 backdrop-filter: blur(15px);
//                 padding: 40px 50px;
//                 border-radius: 20px;
//                 box-shadow: 0 10px 30px rgba(0,0,0,0.2);
//                 width: 90%%;
//                 max-width: 700px;
//                 text-align: center;
//                 animation: slideIn 1s ease;
//             }

//             @keyframes slideIn {
//                 from { transform: translateY(-50px); opacity: 0; }
//                 to { transform: translateY(0); opacity: 1; }
//             }

//             h1 {
//                 font-size: 2.3em;
//                 color: white;
//                 margin-bottom: 20px;
//                 text-shadow: 0 2px 5px rgba(0,0,0,0.3);
//             }

//             .form-group {
//                 margin-top: 20px;
//                 position: relative;
//             }

//             textarea {
//                 width: 100%%;
//                 padding: 15px;
//                 border-radius: 10px;
//                 border: none;
//                 outline: none;
//                 font-size: 16px;
//                 resize: vertical;
//                 min-height: 130px;
//                 box-shadow: 0 3px 8px rgba(0,0,0,0.2);
//                 transition: transform 0.3s ease;
//             }

//             textarea:focus {
//                 transform: scale(1.02);
//             }

//             button {
//                 background: linear-gradient(90deg, #4CAF50, #2ecc71);
//                 color: white;
//                 font-weight: 600;
//                 padding: 12px 35px;
//                 border: none;
//                 border-radius: 10px;
//                 margin-top: 20px;
//                 font-size: 17px;
//                 cursor: pointer;
//                 box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
//                 transition: all 0.3s ease;
//             }

//             button:hover {
//                 transform: translateY(-3px);
//                 box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
//                 background: linear-gradient(90deg, #2ecc71, #4CAF50);
//             }

//             .result {
//                 margin-top: 30px;
//                 padding: 25px;
//                 font-size: 18px;
//                 border-radius: 10px;
//                 background: rgba(255,255,255,0.85);
//                 box-shadow: 0 3px 8px rgba(0,0,0,0.2);
//                 animation: fadeIn 0.8s ease;
//             }

//             .result.spam {
//                 border-left: 6px solid #e74c3c;
//                 color: #c0392b;
//             }

//             .result.ham {
//                 border-left: 6px solid #2ecc71;
//                 color: #27ae60;
//             }

//             @keyframes fadeIn {
//                 from { opacity: 0; transform: translateY(20px); }
//                 to { opacity: 1; transform: translateY(0); }
//             }

//             .loader {
//                 display: none;
//                 margin: 20px auto;
//                 border: 6px solid rgba(255,255,255,0.2);
//                 border-top: 6px solid white;
//                 border-radius: 50%%;
//                 width: 40px;
//                 height: 40px;
//                 animation: spin 1s linear infinite;
//             }

//             @keyframes spin {
//                 0%% { transform: rotate(0deg); }
//                 100%% { transform: rotate(360deg); }
//             }

//             footer {
//                 position: absolute;
//                 bottom: 10px;
//                 text-align: center;
//                 color: rgba(255,255,255,0.8);
//                 font-size: 14px;
//             }
//         </style>
//         <script>
//             function showLoader() {
//                 document.querySelector('.loader').style.display = 'block';
//             }
//         </script>
//     </head>
//     <body>
//         <div class="container">
//             <h1>📧 Smart Email Spam Detector</h1>
//             <form method="POST" action="/classify" onsubmit="showLoader()">
//                 <div class="form-group">
//                     <textarea name="message" placeholder="Paste or type your email message here..." required></textarea>
//                 </div>
//                 <button type="submit">🔍 Analyze Message</button>
//                 <div class="loader"></div>
//             </form>
//             %s
//         </div>
//         <footer>Powered by Machine Learning • Designed with ❤️ by Varshith</footer>
//     </body>
//     </html>
//     """.formatted(formatResult(result));
//     }
// }



// //javac -cp ".:opencsv-5.7.1.jar:commons-lang3-3.14.0.jar" *.java         //Compile
// //java -cp ".:opencsv-5.7.1.jar:commons-lang3-3.14.0.jar" WebServer       //Run




// // java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" Main
// // java -cp ".;opencsv-5.7.1.jar;commons-lang3-3.14.0.jar" WebServer


import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WebServer {
    public static void main(String[] args) throws Exception {
        // Start a simple HTTP server on port 8081
       HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);

        // Home page (form)
        server.createContext("/", (exchange -> {
            try {
                String response = getHtmlPage("");
                byte[] responseBytes = response.getBytes("UTF-8");
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(200, responseBytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(responseBytes);
                }
            } catch (Exception e) {
                e.printStackTrace();
                String errorResponse = "Internal Server Error";
                exchange.sendResponseHeaders(500, errorResponse.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(errorResponse.getBytes());
                }
            } finally {
                exchange.close();
            }
        }));

        // Classification route
        server.createContext("/classify", (exchange -> {
            try {
                if ("POST".equals(exchange.getRequestMethod())) {
                    String body = new String(exchange.getRequestBody().readAllBytes(), "UTF-8");
                    String message = java.net.URLDecoder.decode(body.replace("message=", ""), "UTF-8");

                    boolean isSpam = Main.getTrainedClassifier().classify(message);
                    String result = isSpam ? "SPAM" : "HAM";

                    String response = getHtmlPage("Message: " + message + "<br>Prediction: <b>" + result + "</b>");
                    byte[] responseBytes = response.getBytes("UTF-8");
                    exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                    exchange.sendResponseHeaders(200, responseBytes.length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(responseBytes);
                    }
                } else {
                    String errorResponse = "Method not allowed";
                    exchange.sendResponseHeaders(405, errorResponse.length());
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(errorResponse.getBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                String errorResponse = "Internal Server Error";
                exchange.sendResponseHeaders(500, errorResponse.length());
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(errorResponse.getBytes());
                }
            } finally {
                exchange.close();
            }
        }));

        System.out.println("🚀 Server running at http://localhost:8081");
        server.start();
    }

    // ✅ Beautified result formatter
    private static String formatResult(String result) {
        if (result.isEmpty()) return "";
        String emoji = result.contains("SPAM") ? "🛑" : "✅";
        String resultClass = result.contains("SPAM") ? "spam" : "ham";
        return String.format("<div class='result %s'><b>%s %s</b></div>", resultClass, emoji, result);
    }

    // 🎨 Beautiful UI (Glassmorphism + Animations + Loader)
    private static String getHtmlPage(String result) {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Email Spam Detector</title>
            <style>
                @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap');
                
                body {
                    margin: 0;
                    font-family: 'Poppins', sans-serif;
                    background: linear-gradient(135deg, #6e8efb, #a777e3);
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    height: 100vh;
                    color: #333;
                    overflow: hidden;
                }

                .container {
                    background: rgba(255, 255, 255, 0.2);
                    backdrop-filter: blur(15px);
                    padding: 40px 50px;
                    border-radius: 20px;
                    box-shadow: 0 10px 30px rgba(0,0,0,0.2);
                    width: 90%%;
                    max-width: 700px;
                    text-align: center;
                    animation: slideIn 1s ease;
                }

                @keyframes slideIn {
                    from { transform: translateY(-50px); opacity: 0; }
                    to { transform: translateY(0); opacity: 1; }
                }

                h1 {
                    font-size: 2.3em;
                    color: white;
                    margin-bottom: 20px;
                    text-shadow: 0 2px 5px rgba(0,0,0,0.3);
                }

                .form-group {
                    margin-top: 20px;
                    position: relative;
                }

                textarea {
                    width: 100%%;
                    padding: 15px;
                    border-radius: 10px;
                    border: none;
                    outline: none;
                    font-size: 16px;
                    resize: vertical;
                    min-height: 130px;
                    box-shadow: 0 3px 8px rgba(0,0,0,0.2);
                    transition: transform 0.3s ease;
                }

                textarea:focus {
                    transform: scale(1.02);
                }

                button {
                    background: linear-gradient(90deg, #4CAF50, #2ecc71);
                    color: white;
                    font-weight: 600;
                    padding: 12px 35px;
                    border: none;
                    border-radius: 10px;
                    margin-top: 20px;
                    font-size: 17px;
                    cursor: pointer;
                    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
                    transition: all 0.3s ease;
                }

                button:hover {
                    transform: translateY(-3px);
                    box-shadow: 0 8px 20px rgba(0, 0, 0, 0.3);
                    background: linear-gradient(90deg, #2ecc71, #4CAF50);
                }

                .result {
                    margin-top: 30px;
                    padding: 25px;
                    font-size: 18px;
                    border-radius: 10px;
                    background: rgba(255,255,255,0.85);
                    box-shadow: 0 3px 8px rgba(0,0,0,0.2);
                    animation: fadeIn 0.8s ease;
                }

                .result.spam {
                    border-left: 6px solid #e74c3c;
                    color: #c0392b;
                }

                .result.ham {
                    border-left: 6px solid #2ecc71;
                    color: #27ae60;
                }

                @keyframes fadeIn {
                    from { opacity: 0; transform: translateY(20px); }
                    to { opacity: 1; transform: translateY(0); }
                }

                .loader {
                    display: none;
                    margin: 20px auto;
                    border: 6px solid rgba(255,255,255,0.2);
                    border-top: 6px solid white;
                    border-radius: 50%%;
                    width: 40px;
                    height: 40px;
                    animation: spin 1s linear infinite;
                }

                @keyframes spin {
                    0%% { transform: rotate(0deg); }
                    100%% { transform: rotate(360deg); }
                }

                footer {
                    position: absolute;
                    bottom: 10px;
                    text-align: center;
                    color: rgba(255,255,255,0.8);
                    font-size: 14px;
                }
            </style>
            <script>
                function showLoader() {
                    document.querySelector('.loader').style.display = 'block';
                }
            </script>
        </head>
        <body>
            <div class="container">
                <h1>📧 Smart Email Spam Detector</h1>
                <form method="POST" action="/classify" onsubmit="showLoader()">
                    <div class="form-group">
                        <textarea name="message" placeholder="Paste or type your email message here..." required></textarea>
                    </div>
                    <button type="submit">🔍 Analyze Message</button>
                    <div class="loader"></div>
                </form>
                %s
            </div>
            <footer>Powered by Machine Learning </footer>
        </body>
        </html>
        """.formatted(formatResult(result));
    }
}

