import java.awt.*;
import javax.swing.*;
import java.util.*;

class GridTest extends JFrame {
    GridTest(String s) {
        super(s);
        setLayout(new GridLayout(4, 4, 5, 5));
        StringTokenizer stringTokenizer = new StringTokenizer("7 8 9 / 4 5 6 * 1 2 3 - 0 . = +");
        while (stringTokenizer.hasMoreTokens()) {
            add(new JButton(stringTokenizer.nextToken()));
        }
        setSize(200, 200);
        setVisible(true);
    }
}

public class GridLayoutFrame {
    public static void main(String[] args) {
        JFrame frame = new GridTest("GridLayout Test");
        frame.setLocation(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
