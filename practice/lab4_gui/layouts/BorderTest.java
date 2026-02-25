import java.awt.*;
import javax.swing.*;

class BorderTest extends JFrame {
    BorderTest(String s) {
        super(s);
        add(new JButton("North"), BorderLayout.NORTH);
        add(new JButton("South"), BorderLayout.SOUTH);
        add(new JButton("West"), BorderLayout.WEST);
        add(new JButton("East"), BorderLayout.EAST);
        JTextField textField = new JTextField("Center");
        textField.setBackground(Color.GRAY);
        textField.setForeground(Color.BLACK);
        textField.setHorizontalAlignment(JTextField.CENTER);
        add(textField);
        setSize(300, 200);
        setVisible(true);       
    }
    public static void main(String[] args) {
        JFrame frame = new BorderTest("BorderLayout Test");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
