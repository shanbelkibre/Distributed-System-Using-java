import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in)
            );

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );

            String input;

            while ((input = keyboard.readLine()) != null) {
                out.println(input);
                System.out.println("Server: " + in.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}