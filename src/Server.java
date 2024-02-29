import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.Naming;

public class Server {
    public static void main(String[] args) {
        try {
            CalculatorImpl obj = new CalculatorImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            Naming.rebind("CalculatorService", obj);
            System.out.println("Serveur prêt.");
        } catch (Exception e) {
            System.out.println("Serveur exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
