import java.awt.FlowLayout;
import javax.swing.*;

class FlowTest extends JFrame {
    FlowTest(String s) {
        super(s);
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        add(new JButton("Button"));
        add(new JLabel("Label"));
        add(new JCheckBox("Choice"));
        add(new JTextField("Input", 10));
        setSize(300, 200);
        setVisible(true);
    }
}

public class SimpleFrame {
    public static void main(String[] args) {
        JFrame frame = new FlowTest("FlowLayout Test");
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
