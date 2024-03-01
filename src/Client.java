import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.lang.reflect.Method;

public class Client extends JFrame implements ActionListener {
    private static CalcInterface calc;
    private final static Method[] methods = CalcInterface.class.getDeclaredMethods();
    private JButton[] buttons;
    private JTextField inputField;
    private JLabel outputLabel;

    public Client() {
        super("Remote Calculator");
        JPanel buttonPanel = new JPanel(new GridLayout(3, methods.length / 3));
        buttons = new JButton[methods.length];
        for (int i = 0; i < methods.length; i++) {
            buttons[i] = new JButton(methods[i].getName());
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }
        inputField = new JTextField(20);
        outputLabel = new JLabel("Result: ");
        add(buttonPanel, BorderLayout.NORTH);
        add(inputField, BorderLayout.CENTER);
        add(outputLabel, BorderLayout.SOUTH);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 150);
        outputLabel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String operation = button.getText();
            if (operation.equals("Exit")) {
                System.exit(0);
            } else {
                try {
                    Method method = getMethodByName(operation);
                    if (method != null) {
                        Double[] params = getParamsFromInput();
                        if (params != null) {
                            double result = (double) method.invoke(calc, (Object) params);
                            outputLabel.setText("Result: " + result);
                        } else {
                            outputLabel.setText("Invalid input. Please enter numbers separated by commas.");
                        }
                    } else {
                        outputLabel.setText("Unknown operation. Please choose a valid one.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    outputLabel.setText("An error occurred. Please try again.");
                }
            }
        }
    }

    private Method getMethodByName(String name) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    private Double[] getParamsFromInput() {
        try {
            String input = inputField.getText();
            String[] paramsStr = input.split(",");
            Double[] params = new Double[paramsStr.length];
            for (int i = 0; i < paramsStr.length; i++) {
                params[i] = Double.valueOf(paramsStr[i]);
            }
            return params;
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Registry regs = LocateRegistry.getRegistry("localhost", 1100);
            calc = (CalcInterface) regs.lookup("CalcServices");
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        new Client();
    }
}
