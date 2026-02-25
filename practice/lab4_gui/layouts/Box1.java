import javax.swing.*;
import java.awt.*;

public class Box1 extends JFrame {
    public Box1() {
        super("Box1 - Y");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = getContentPane();
        BoxLayout boxy = new BoxLayout(container, BoxLayout.Y_AXIS);
        container.setLayout(boxy);
        container.add(new JButton("One"));
        container.add(new JButton("Two"));
        container.add(new JButton("Three"));
        setVisible(true);
    }
    static class Box2 extends JFrame {
        public Box2() {
            super("Box2 - X");
            setSize(400, 200);
            setLocation(100, 100);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            Container container = getContentPane();
            BoxLayout boxx = new BoxLayout(container, BoxLayout.X_AXIS);
            container.setLayout(boxx);
            container.add(new JButton("One"));
            container.add(new JButton("Two"));
            container.add(new JButton("Three"));
            setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Box1();
        new Box2();
    }
}
