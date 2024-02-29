import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.InputMismatchException;

import java.lang.reflect.Method;

public class Client{
    private static CalcInterface calc;
    private final static Method[] methods = CalcInterface.class.getDeclaredMethods();

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("*** Welcome to our Remote Calculator ***");
        
        try {
            Registry regs = LocateRegistry.getRegistry("localhost", 1098);
            calc = (CalcInterface) regs.lookup("CalcServices");     
        } catch (Exception ex) {
            ex.printStackTrace();
            scanner.close();
            return;
        } 
        
        do {
            printMenu();
            int choice;
            do {
                try {
                    System.out.print("Choose the number of an arithmetic operation: ");
                    choice = scanner.nextInt();
                    if (choice < 0 || choice > methods.length) {
                        System.out.println("!You have to enter a digit between 1 and " + methods.length + "!");
                        continue;
                    }
                    break;
                } catch (InputMismatchException ex) {
                    System.err.println("!You have to enter an Double!");
                    scanner.nextLine();
                }
            } while (true);

            if (choice == methods.length) {
                System.out.println("Glad to have served you!");
                scanner.close();
                return;
            }

            Method method = methods[choice];
            Double[] params;
            do {
                try {
                    scanner.nextLine();
                    System.out.print("Enter the parameters (separe them with ',' directly): ");
                    String[] paramsStr = scanner.nextLine().split(",");
                    params = new Double[paramsStr.length];
                    for (int i = 0; i < paramsStr.length; i++) {
                        params[i] = Double.valueOf(paramsStr[i]);
                    }
                    break;
                } catch (InputMismatchException ex) {
                    System.err.println("!You have to enter real numbers in ther form a,b!");
                    scanner.nextLine();
                    continue;
                }
            } while (true);

            double result;
            try {
                result = (double) method.invoke(calc, (Object) params);
            } catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            System.out.println("\nThe result is: " + result + ".");
        } while (true);
    }

    private static void printMenu() {
        System.out.println("\n* Arithmetic Operations Menu *");
        for (int i = 0; i < methods.length; i++) {
            System.out.println(i + "/ " + methods[i].getName());
        }
        System.out.println(methods.length + "/ Exit\n");
    }
}
