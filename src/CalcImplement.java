import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcImplement extends UnicastRemoteObject implements CalcInterface{
    public CalcImplement() throws RemoteException {
        super();
    }
    
    public double add(Double[] numbers) throws RemoteException {
        Double sum = 0.0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }
    public double subtract(Double[] numbers) throws RemoteException {
        return numbers[0] - numbers[1];
    }
    public double multiply(Double[] numbers) throws RemoteException {
        Double prod = 1.0;
        for (int i = 0; i < numbers.length; i++) {
            prod *= numbers[i];
        }
        return prod;
    }
    public double divide(Double[] numbers) throws RemoteException {
        return numbers[0] / numbers[1];
    }
    public double modulo(Double[] numbers) throws RemoteException {
        return numbers[0] % numbers[1];
    }
}
