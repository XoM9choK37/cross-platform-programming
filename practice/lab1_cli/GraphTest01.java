import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;

public class GraphTest01 extends Frame {
    private Line2D.Double[] lines;
    private int lineCount = 0;
    public GraphTest01(String title) {
        super(title);
        setBounds(0, 0, 800, 800);
        setBackground(Color.BLACK); 
        drawPattern(20000, 10, 11, 30);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
        setVisible(true);
    }
    private void drawPattern(int steps, double angle, int divisor, double step) {
        lines = new Line2D.Double[steps];
        lineCount = steps;
        double x = getWidth() / 2.0;
        double y = getHeight() / 2.0;
        double direction = 0;
        for (int i = 0; i < steps; i++) {
            double currentAngle = Math.toRadians(angle);
            direction += currentAngle * Math.abs(Math.sin(i)) * (i % divisor);
            direction -= currentAngle * Math.abs(Math.cos(i)) * (i % divisor + 1);
            double newX = x + step * Math.cos(direction);
            double newY = y + step * Math.sin(direction);
            lines[i] = new Line2D.Double(x, y, newX, newY);
            x = newX;
            y = newY;
        }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.GREEN);
        for (int i = 0; i < lineCount; i++) {
            if (lines[i] != null) {
                g2d.draw(lines[i]);
            }
        }
    }
    
    public static void main(String[] args) {
        new GraphTest01("Картинка из примитивных линий");
    }
}

