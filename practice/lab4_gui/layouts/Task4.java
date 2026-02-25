import javax.swing.*;

public class Task4 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("𝛑 ≈ 3.141592653589793");
        JLabel label2 = new JLabel("e ≈ 2.718281828459045");
        JLabel label3 = new JLabel("ᵠ ≈ 1.618033988749895");
        JButton button = new JButton("Don't Push This Button!");
        for (int i = 1; i <= 5; ++i) {
            panel.add(new JLabel("Label" + i));
        }
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(button);
        frame.add(panel);
        frame.pack();
        frame.setSize(300, 200);
        frame.setLocation(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
