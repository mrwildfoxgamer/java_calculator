import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class calculator extends JFrame implements ActionListener {

    private JTextField textField;
    private JButton[] buttons;
    private StringBuilder currentInput = new StringBuilder();
    private String[] buttonLabels ={
            "7","8","9","/",
            "4","5","5","*",
            "1","2","3","-",
            "0","C","=","+"
    };

    public  calculator (){
        super();
        //window
        setTitle("Calculator");
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        //text field
        textField = new JTextField();
        textField.setBounds(50,50,400,50);
        textField.setEditable(false);
        add(textField);

        //buttons

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,4,10,10));
        panel.setBounds(50,120,400,300);

        //inizilition of buttons

        buttons= new JButton[16];
        for (int i=0;i<16;i++){
            buttons[i] = new JButton(buttonLabels[i]);
            panel.add(buttons[i]);
        }

        add(panel);

        setVisible(true);
    }
    @Override


    public void actionPerformed(ActionEvent e){

        String command = e.getActionCommand();

        if (command.equals("=")){
            try {
                double result = eval(currentInput.toString());
                textField.setText(String.valueOf(result));
                currentInput.setLength(0);
            } catch (Exception ex){

                textField.setText("ERROR");
                currentInput.setLength(0);
            }

        } else if (command.equals("C")) {

            currentInput.setLength(0);
            textField.setText("");

        }else {
            currentInput.append(command);
            textField.setText(currentInput.toString());
        }

    }
//fix expretion evaluation
    private double eval(String expression ) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        Object evalResult = engine.eval(expression);
        return Double.parseDouble(evalResult.toString());
    }

    public static void main(String[] args) {

        new calculator();

    }


}
