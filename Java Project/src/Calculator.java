import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Calculator 
{
    private JFrame frame;
    private JTextField TextField;
    private StringBuilder expression;
    Calculator() 
    {
        expression = new StringBuilder();
        createGui();
    }
    public void createGui()
    {
        frame = new JFrame("Calculator");
        TextField = new JTextField(30);
        TextField.setEditable(false);
        TextField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(TextField, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        JButton b0=new JButton("0");
        JButton b1=new JButton("1");
        JButton b2=new JButton("2");
        JButton b3=new JButton("3");
        JButton b4=new JButton("4");
        JButton b5=new JButton("5");
        JButton b6=new JButton("6");
        JButton b7=new JButton("7");
        JButton b8=new JButton("8");
        JButton b9=new JButton("9");
        JButton b10=new JButton("+");
        JButton b11=new JButton("-");
        JButton b12=new JButton("*");
        JButton b13=new JButton("/");
        JButton b14=new JButton("=");
        JButton b15=new JButton("C");
        JButton[] buttons={b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15};
        for (JButton button : buttons) 
        {
            button.setBackground(Color.WHITE);
            button.addActionListener(new ButtonClickListener());
        }
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b10);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(b11);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(b12);
        panel.add(b15);
        panel.add(b0);
        panel.add(b14);
        panel.add(b13);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 400);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9')
            {
                expression.append(command);
                TextField.setText(expression.toString());
            } 
            else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) 
            {
                expression.append(" ").append(command).append(" ");
                TextField.setText(expression.toString());
            } 
            else if (command.equals("=")) 
            {
                try 
                {
                    double result = evaluateExpression(expression.toString());
                    TextField.setText(String.valueOf(result));
                    expression.setLength(0);
                    expression.append(result); 
                } 
                catch (Exception ex) 
                {
                    TextField.setText("Error");
                    expression.setLength(0); 
                }
            } 
            else if (command.equals("C")) 
            {
                TextField.setText("");
                expression.setLength(0);
            }
        }
        private double evaluateExpression(String expr) 
        {
            String[] tokens = expr.split(" ");
            double result = Double.parseDouble(tokens[0]);
            for (int i = 1; i < tokens.length; i += 2) 
            {
                String operator = tokens[i];
                double operand = Double.parseDouble(tokens[i + 1]);
                switch (operator) 
                {
                    case "+":
                        result+=operand;
                        break;
                    case "-":
                        result-=operand;
                        break;
                    case "*":
                        result*=operand;
                        break;
                    case "/":
                        result/=operand;
                        break;
                }
            }

            return result;
        }
    }
    public static void main(String[] args) 
    {
        new Calculator();
    }
}
