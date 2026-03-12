import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class PaintModel {
    private Color currentColor = Color.BLACK;
    private int currentBrushSize = 5;
    private boolean drawingMode = true;
    private List<DrawableShape> shapes = new ArrayList<>();

    public Color getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(Color color) {
        this.currentColor = color;
    }

    public int getCurrentBrushSize() {
        return currentBrushSize;
    }

    public void setCurrentBrushSize(int size) {
        this.currentBrushSize = size;
    }

    public boolean isDrawingMode() {
        return drawingMode;
    }

    public void setDrawingMode(boolean mode) {
        this.drawingMode = mode;
    }

    public List<DrawableShape> getShapes() {
        return shapes;
    }

    public void addShape(Shape shape, Color color, float strokeWidth) {
        shapes.add(new DrawableShape(shape, color, strokeWidth));
    }

    public void clearShapes() {
        shapes.clear();
    }

    public void addPattern(int steps, double angle, int divisor, double step, 
                          int startX, int startY, Color color) {
        double x = startX;
        double y = startY;
        double direction = 0;
        
        for (int i = 0; i < steps; i++) {
            double currentAngle = Math.toRadians(angle);
            direction += currentAngle * Math.abs(Math.sin(i)) * (i % divisor);
            direction -= currentAngle * Math.abs(Math.cos(i)) * (i % divisor + 1);
            
            double newX = x + step * Math.cos(direction);
            double newY = y + step * Math.sin(direction);
            
            Line2D.Double line = new Line2D.Double(x, y, newX, newY);
            shapes.add(new DrawableShape(line, color, 1));
            
            x = newX;
            y = newY;
        }
    }
}
