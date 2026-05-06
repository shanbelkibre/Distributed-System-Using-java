// CalculatorImpl.java
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    
    // Constructor must throw RemoteException
    protected CalculatorImpl() throws RemoteException {
        super();
    }
    
    // Implement interface methods
    @Override
    public int add(int a, int b) throws RemoteException {
        System.out.println("Adding " + a + " + " + b);
        return a + b;
    }
    
    @Override
    public int subtract(int a, int b) throws RemoteException {
        System.out.println("Subtracting " + a + " - " + b);
        return a - b;
    }
    
    @Override
    public int multiply(int a, int b) throws RemoteException {
        System.out.println("Multiplying " + a + " * " + b);
        return a * b;
    }
    
    @Override
    public int divide(int a, int b) throws RemoteException {
        if (b == 0) {
            throw new RemoteException("Division by zero not allowed");
        }
        System.out.println("Dividing " + a + " / " + b);
        return a / b;
    }
    

    
    // Main method to start the RMI server
    public static void main(String[] args) {
        try {
            // Create remote object
            CalculatorImpl calculator = new CalculatorImpl();
            
            // Start RMI registry on port 1099 (default)
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Bind the remote object to a name in the registry
            registry.rebind("CalculatorService", calculator);
            
            System.out.println("RMI Server is running on port 1099...");
            System.out.println("Service bound as 'CalculatorService'");
            
        } catch (RemoteException e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}