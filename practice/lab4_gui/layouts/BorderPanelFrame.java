import java.awt.*;
import javax.swing.*;

class BorderPanelTest extends JFrame {
    BorderPanelTest(String s) {
        super(s);
        JPanel panel2 = new JPanel();
        JLabel label1 = new JLabel("This Is The Panel 2");
        label1.setForeground(Color.BLACK);
        panel2.add(label1);
        panel2.add(new JButton("Execute"));
        panel2.add(new JButton("Cancel"));
        panel2.add(new JButton("Exit"));
        panel2.setBounds(5, 5, 5, 5);
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        panel1.add(panel2, BorderLayout.SOUTH);
        panel1.add(new JTextField("Text Field", 20), BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(new JTextArea("Input Field", 20, 5));
        panel1.add(scrollPane, BorderLayout.CENTER);
        panel1.setBorder(BorderFactory.createEtchedBorder(100, Color.GRAY, Color.BLACK));
        panel1.setBounds(5, 5, 5, 5);
        add(panel1, BorderLayout.CENTER);
        add(new JButton("Exit 2"), BorderLayout.SOUTH);
        setSize(400, 200);
        setVisible(true);
    }
}

public class BorderPanelFrame {
    public static void main(String[] args) {
        JFrame frame = new BorderPanelTest("BorderLayout Test");
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
