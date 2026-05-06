// CalculatorClient.java
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;

public class CalculatorClient {
    public static void main(String[] args) {
        String host = "localhost"; // Change to server IP if remote
        
        try {
            // Get registry from the server
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            
            // Look up the remote object by its name
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");
            
            // Call remote methods - they look like local calls!
            int sum = calculator.add(25, 17);
            System.out.println("25 + 17 = " + sum);
            
            int difference = calculator.subtract(100, 33);
            System.out.println("100 - 33 = " + difference);
            
            int product = calculator.multiply(6, 7);
            System.out.println("6 * 7 = " + product);
            
            int quotient = calculator.divide(81, 9);
            System.out.println("81 / 9 = " + quotient);
            
            // Test exception handling
            try {
               int result= calculator.divide(10, 2);
               System.out.println("10 / 2 = " + result);
            } catch (RemoteException e) {
                System.err.println("Caught expected error: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}