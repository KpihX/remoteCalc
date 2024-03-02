import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerRMI {
    public static void main(String[] args) {
        System.out.println("This program launches a javaRMI server. By default the port is 1100.");
        int port;
        if (args.length == 0) {
            port = 1100;
        } else if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else {
            System.err.println("!You have to pass at most one port number!");
            return;
        }
        
        try {
            Registry reg = LocateRegistry.createRegistry(port);
            CalcImplement cl = new CalcImplement();
            reg.rebind("CalcServices", cl);
            System.out.println("Server is ready at port " + port + "!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
