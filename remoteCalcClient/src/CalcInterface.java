import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CalcInterface extends Remote{
    public double add(Double[] numbers) throws RemoteException;
    
    public double subtract(Double[] numbers) throws RemoteException ;
    
    public double multiply(Double[] numbers) throws RemoteException;
    
    public double divide(Double[] numbers) throws RemoteException;
    
    public double modulo(Double[] numbers) throws RemoteException;
}
