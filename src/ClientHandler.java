import java.io.*;
import java.net.*;
import java.sql.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Connection conn;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // DB connection
            conn = DBConnection.getConnection();

            // Streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            String msg;

            while ((msg = in.readLine()) != null) {
                System.out.println("Client: " + msg);

                // Save to DB
                PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO messages(message) VALUES (?)"
                );
                stmt.setString(1, msg);
                stmt.executeUpdate();

                out.println("Saved: " + msg);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
                if (in != null) in.close();
                if (out != null) out.close();
                if (socket != null) socket.close();
                System.out.println("Client disconnected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}