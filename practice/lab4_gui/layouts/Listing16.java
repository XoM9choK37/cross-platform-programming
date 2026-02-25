import javax.swing.*;

public class Listing16 {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel lab = new JLabel("Hello, World!");
        f.setSize(300, 200);
        f.setLocation(500, 200);
        f.add(lab);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
