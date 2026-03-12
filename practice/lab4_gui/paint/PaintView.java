import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PaintView extends JFrame {
    private PaintModel model;
    private Canvas canvas;
    private JButton blackBtn, redBtn, greenBtn, blueBtn, yellowBtn;
    private JToggleButton drawModeBtn, shapeModeBtn;
    private JButton clearBtn;
    private JRadioButton smallBtn, mediumBtn, largeBtn, extraLargeBtn;
    private JSlider sizeSlider;

    public PaintView(PaintModel model) {
        this.model = model;
        setTitle("Рисовалка с параметрическими фигурами");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        blackBtn = new JButton("Черный");
        blackBtn.setBackground(Color.BLACK);
        blackBtn.setForeground(Color.WHITE);

        redBtn = new JButton("Красный");
        redBtn.setBackground(Color.RED);

        greenBtn = new JButton("Зеленый");
        greenBtn.setBackground(Color.GREEN);

        blueBtn = new JButton("Синий");
        blueBtn.setBackground(Color.BLUE);
        blueBtn.setForeground(Color.WHITE);

        yellowBtn = new JButton("Желтый");
        yellowBtn.setBackground(Color.YELLOW);

        drawModeBtn = new JToggleButton("Рисование", true);
        shapeModeBtn = new JToggleButton("Фигуры", false);

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(drawModeBtn);
        modeGroup.add(shapeModeBtn);

        clearBtn = new JButton("Очистить");

        controlPanel.add(blackBtn);
        controlPanel.add(redBtn);
        controlPanel.add(greenBtn);
        controlPanel.add(blueBtn);
        controlPanel.add(yellowBtn);
        controlPanel.add(drawModeBtn);
        controlPanel.add(shapeModeBtn);
        controlPanel.add(clearBtn);

        JPanel sizePanel = createSizePanel();

        topPanel.add(controlPanel, BorderLayout.NORTH);
        topPanel.add(sizePanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    private JPanel createSizePanel() {
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.X_AXIS));
        sizePanel.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));

        JLabel sizeLabel = new JLabel("Размер: ");
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        smallBtn = new JRadioButton("2");
        smallBtn.setFont(new Font("Arial", Font.PLAIN, 11));
        smallBtn.setToolTipText("Маленький (2px)");

        mediumBtn = new JRadioButton("5");
        mediumBtn.setFont(new Font("Arial", Font.PLAIN, 11));
        mediumBtn.setToolTipText("Средний (5px)");
        mediumBtn.setSelected(true);

        largeBtn = new JRadioButton("10");
        largeBtn.setFont(new Font("Arial", Font.PLAIN, 11));
        largeBtn.setToolTipText("Большой (10px)");

        extraLargeBtn = new JRadioButton("20");
        extraLargeBtn.setFont(new Font("Arial", Font.PLAIN, 11));
        extraLargeBtn.setToolTipText("Очень большой (20px)");

        ButtonGroup sizeGroup = new ButtonGroup();
        sizeGroup.add(smallBtn);
        sizeGroup.add(mediumBtn);
        sizeGroup.add(largeBtn);
        sizeGroup.add(extraLargeBtn);

        sizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 30, 5);
        sizeSlider.setPreferredSize(new Dimension(120, 30));
        sizeSlider.setMajorTickSpacing(10);
        sizeSlider.setMinorTickSpacing(5);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setFont(new Font("Arial", Font.PLAIN, 9));

        sizePanel.add(sizeLabel);
        sizePanel.add(Box.createHorizontalStrut(5));
        sizePanel.add(smallBtn);
        sizePanel.add(mediumBtn);
        sizePanel.add(largeBtn);
        sizePanel.add(extraLargeBtn);
        sizePanel.add(Box.createHorizontalStrut(10));
        sizePanel.add(sizeSlider);

        return sizePanel;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JButton getBlackBtn() { return blackBtn; }
    public JButton getRedBtn() { return redBtn; }
    public JButton getGreenBtn() { return greenBtn; }
    public JButton getBlueBtn() { return blueBtn; }
    public JButton getYellowBtn() { return yellowBtn; }
    public JToggleButton getDrawModeBtn() { return drawModeBtn; }
    public JToggleButton getShapeModeBtn() { return shapeModeBtn; }
    public JButton getClearBtn() { return clearBtn; }
    public JRadioButton getSmallBtn() { return smallBtn; }
    public JRadioButton getMediumBtn() { return mediumBtn; }
    public JRadioButton getLargeBtn() { return largeBtn; }
    public JRadioButton getExtraLargeBtn() { return extraLargeBtn; }
    public JSlider getSizeSlider() { return sizeSlider; }

    public void refreshCanvas() {
        canvas.repaint();
    }

    public void showShapeDialog(int x, int y, PaintController controller) {
        new ShapeDialog(this, x, y, controller).setVisible(true);
    }

    class Canvas extends JPanel {
        public Canvas() {
            setPreferredSize(new Dimension(1000, 700));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            List<DrawableShape> shapes = model.getShapes();
            for (DrawableShape drawable : shapes) {
                drawable.draw(g2d);
            }
        }
    }
}
