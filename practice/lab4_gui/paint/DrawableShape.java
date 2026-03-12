import java.awt.*;

public class DrawableShape {
    private Shape shape;
    private Color color;
    private float strokeWidth;

    public DrawableShape(Shape shape, Color color, float strokeWidth) {
        this.shape = shape;
        this.color = color;
        this.strokeWidth = strokeWidth;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.draw(shape);
    }
}
