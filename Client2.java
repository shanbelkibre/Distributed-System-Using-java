import java.io.*;
import java.net.*;
import java.util.Scanner;

// Client2 class - Second client instance
public class Client2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Client2 connected to server on port 1234");
            String userInput;
            
            while (true) {
                System.out.print("Client2 - Enter message: ");
                userInput = scanner.nextLine();
                if ("exit".equalsIgnoreCase(userInput)) break;
                out.println(userInput); // Send message to server
                System.out.println("Server replied to Client2: " + in.readLine()); // Read response from server
            }
            
            System.out.println("Client2 disconnected");
            
        } catch (IOException e) {
            System.err.println("Client2 error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}