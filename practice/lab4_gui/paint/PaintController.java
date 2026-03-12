import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;

public class PaintController {
    private PaintModel model;
    private PaintView view;
    private int prevX, prevY;

    public PaintController(PaintModel model, PaintView view) {
        this.model = model;
        this.view = view;

        view.getBlackBtn().addActionListener(e -> model.setCurrentColor(Color.BLACK));
        view.getRedBtn().addActionListener(e -> model.setCurrentColor(Color.RED));
        view.getGreenBtn().addActionListener(e -> model.setCurrentColor(Color.GREEN));
        view.getBlueBtn().addActionListener(e -> model.setCurrentColor(Color.BLUE));
        view.getYellowBtn().addActionListener(e -> model.setCurrentColor(Color.YELLOW));

        view.getDrawModeBtn().addActionListener(e -> {
            model.setDrawingMode(true);
            view.getDrawModeBtn().setSelected(true);
        });

        view.getShapeModeBtn().addActionListener(e -> {
            model.setDrawingMode(false);
            view.getShapeModeBtn().setSelected(true);
        });

        view.getClearBtn().addActionListener(e -> {
            model.clearShapes();
            view.refreshCanvas();
        });

        view.getSmallBtn().addActionListener(e -> {
            int size = 2;
            model.setCurrentBrushSize(size);
            updateSizeControls(size);
        });
        
        view.getMediumBtn().addActionListener(e -> {
            int size = 5;
            model.setCurrentBrushSize(size);
            updateSizeControls(size);
        });
        
        view.getLargeBtn().addActionListener(e -> {
            int size = 10;
            model.setCurrentBrushSize(size);
            updateSizeControls(size);
        });
        
        view.getExtraLargeBtn().addActionListener(e -> {
            int size = 20;
            model.setCurrentBrushSize(size);
            updateSizeControls(size);
        });

        view.getSizeSlider().addChangeListener(e -> {
            if (!view.getSizeSlider().getValueIsAdjusting()) {
                int size = view.getSizeSlider().getValue();
                model.setCurrentBrushSize(size);
                updateSizeButtons(size);
            }
        });

        setupMouseListeners();
    }

    private void setupMouseListeners() {
        PaintView.Canvas canvas = view.getCanvas();

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (model.isDrawingMode()) {
                    prevX = e.getX();
                    prevY = e.getY();
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!model.isDrawingMode() && e.getClickCount() == 1) {
                    view.showShapeDialog(e.getX(), e.getY(), PaintController.this);
                }
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (model.isDrawingMode()) {
                    int x = e.getX();
                    int y = e.getY();
                    
                    model.addShape(
                        new Line2D.Double(prevX, prevY, x, y),
                        model.getCurrentColor(),
                        model.getCurrentBrushSize()
                    );
                    
                    Graphics2D g2d = (Graphics2D) canvas.getGraphics();
                    g2d.setColor(model.getCurrentColor());
                    g2d.setStroke(new BasicStroke(
                        model.getCurrentBrushSize(), 
                        BasicStroke.CAP_ROUND, 
                        BasicStroke.JOIN_ROUND
                    ));
                    g2d.drawLine(prevX, prevY, x, y);
                    g2d.dispose();
                    
                    prevX = x;
                    prevY = y;
                }
            }
        });
    }

    private void updateSizeControls(int size) {
        updateSizeButtons(size);
        
        view.getSizeSlider().setValue(size);
    }

    private void updateSizeButtons(int size) {
        view.getSmallBtn().setSelected(size == 2);
        view.getMediumBtn().setSelected(size == 5);
        view.getLargeBtn().setSelected(size == 10);
        view.getExtraLargeBtn().setSelected(size == 20);
    }

    public void addPattern(int steps, double angle, int divisor, double step, 
                          int x, int y, Color color) {
        model.addPattern(steps, angle, divisor, step, x, y, color);
        view.refreshCanvas();
    }

    public Color getCurrentColor() {
        return model.getCurrentColor();
    }
}
