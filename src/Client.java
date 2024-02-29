import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            String nom = "CalculatorService";
            Registry registry = LocateRegistry.getRegistry("localhost");
            Calculator stub = (Calculator) registry.lookup(nom);
            double response = stub.add(1.5, 2.5);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            System.out.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
