import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.createRegistry(1098);
            CalcImplement cl = new CalcImplement();
            reg.rebind("CalcServices", cl);
            System.out.println("Server starts ...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}