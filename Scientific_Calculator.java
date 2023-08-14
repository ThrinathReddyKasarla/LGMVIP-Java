import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Scientific_Calculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private String currentInput = "";
    
    public Scientific_Calculator() {
        setTitle("Scientific Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "sin", "cos", "tan", "√"
        };
        
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        
        setLayout(new BorderLayout());
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        
        if ("0123456789.".contains(actionCommand)) {
            currentInput += actionCommand;
            displayField.setText(currentInput);
        } else if ("+-*/".contains(actionCommand)) {
            currentInput += " " + actionCommand + " ";
            displayField.setText(currentInput);
        } else if ("sin cos tan √".contains(actionCommand)) {
            double result = performTrigonometricOrRoot(actionCommand);
            currentInput = String.valueOf(result);
            displayField.setText(currentInput);
        } else if ("=".equals(actionCommand)) {
            try {
                double result = evaluateExpression(currentInput);
                currentInput = String.valueOf(result);
                displayField.setText(currentInput);
            } catch (Exception ex) {
                displayField.setText("Error");
            }
        }
    }
    
    private double performTrigonometricOrRoot(String actionCommand) {
        double input = Double.parseDouble(currentInput);
        
        switch (actionCommand) {
            case "sin":
                return Math.sin(input);
            case "cos":
                return Math.cos(input);
            case "tan":
                return Math.tan(input);
            case "√":
                return Math.sqrt(input);
            default:
                return 0.0;
        }
    }
    
    private double evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        double result = Double.parseDouble(tokens[0]);
        
        for (int i = 1; i < tokens.length - 1; i += 2) {
            double nextNumber = Double.parseDouble(tokens[i + 1]);
            
            switch (tokens[i]) {
                case "+":
                    result += nextNumber;
                    break;
                case "-":
                    result -= nextNumber;
                    break;
                case "*":
                    result *= nextNumber;
                    break;
                case "/":
                    result /= nextNumber;
                    break;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Scientific_Calculator calculator = new Scientific_Calculator();
            calculator.setVisible(true);
        });
    }
}
