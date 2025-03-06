import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculator extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton[] buttons;
    private StringBuilder currentInput = new StringBuilder();
    private String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
    };

    public calculator() {
        super("Calculator");
        // Window
        setTitle("Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null); // Center the window

        // Text field
        textField = new JTextField();
        textField.setBounds(50, 50, 400, 50);
        textField.setEditable(false);
        add(textField);

        // Buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBounds(50, 120, 400, 300);

        // Initialization of buttons
        buttons = new JButton[16];
        for (int i = 0; i < 16; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            try {
                double result = eval(currentInput.toString());
                if (Double.isNaN(result) || Double.isInfinite(result)) {
                    textField.setText("ERROR");
                } else {
                    textField.setText(String.valueOf(result));
                }
                currentInput.setLength(0);
            } catch (Exception ex) {
                textField.setText("ERROR");
                currentInput.setLength(0);
            }
        } else if (command.equals("C")) {
            currentInput.setLength(0);
            textField.setText("");
        } else {
            currentInput.append(command);
            textField.setText(currentInput.toString());
        }
    }

    private double eval(String expression) {
        try {
            // Handle basic arithmetic operations
            // Check if the expression contains operators
            if (expression.contains("+")) {
                String[] parts = expression.split("\\+");
                return Double.parseDouble(parts[0]) + Double.parseDouble(parts[1]);
            } else if (expression.contains("-")) {
                String[] parts = expression.split("-");
                return Double.parseDouble(parts[0]) - Double.parseDouble(parts[1]);
            } else if (expression.contains("*")) {
                String[] parts = expression.split("\\*");
                return Double.parseDouble(parts[0]) * Double.parseDouble(parts[1]);
            } else if (expression.contains("/")) {
                String[] parts = expression.split("/");
                // Check for division by zero
                if (Double.parseDouble(parts[1]) == 0) {
                    return Double.NaN; // Not a Number for division by zero
                }
                return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
            }

            // If no operations are found, return the number itself
            return Double.parseDouble(expression);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new calculator());
    }
}