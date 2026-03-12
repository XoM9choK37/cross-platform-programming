import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShapeDialog extends JDialog {
    private JTextField stepsField = new JTextField("20000", 10);
    private JTextField angleField = new JTextField("10", 10);
    private JTextField divisorField = new JTextField("11", 10);
    private JTextField stepField = new JTextField("5", 10);
    private JTextField redField, greenField, blueField;
    private JPanel colorCanvas;
    private JSlider brightnessSlider;
    private JPanel colorPreview;
    private Color selectedColor;
    @SuppressWarnings("unused")
    private int clickX, clickY;
    @SuppressWarnings("unused")
    private PaintController controller;

    public ShapeDialog(JFrame parent, int clickX, int clickY, PaintController controller) {
        super(parent, "Параметры фигуры", true);
        this.clickX = clickX;
        this.clickY = clickY;
        this.controller = controller;
        this.selectedColor = controller.getCurrentColor();

        setLayout(new BorderLayout());

        JPanel paramsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0;
        paramsPanel.add(new JLabel("Шаги:"), gbc);
        gbc.gridx = 1;
        paramsPanel.add(stepsField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        paramsPanel.add(new JLabel("Угол:"), gbc);
        gbc.gridx = 1;
        paramsPanel.add(angleField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        paramsPanel.add(new JLabel("Делитель:"), gbc);
        gbc.gridx = 1;
        paramsPanel.add(divisorField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        paramsPanel.add(new JLabel("Шаг:"), gbc);
        gbc.gridx = 1;
        paramsPanel.add(stepField, gbc);

        JPanel colorPickerPanel = new JPanel();
        colorPickerPanel.setLayout(new BoxLayout(colorPickerPanel, BoxLayout.Y_AXIS));
        colorPickerPanel.setBorder(BorderFactory.createTitledBorder("Выбери цвет"));

        colorCanvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                for (int x = 0; x < getWidth(); x++) {
                    float hue = (float) x / getWidth();
                    for (int y = 0; y < getHeight(); y++) {
                        float saturation = 1.0f - (float) y / getHeight();
                        g2d.setColor(Color.getHSBColor(hue, saturation, 1.0f));
                        g2d.fillRect(x, y, 1, 1);
                    }
                }
            }
        };
        colorCanvas.setPreferredSize(new Dimension(300, 150));
        colorCanvas.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        brightnessSlider = new JSlider(JSlider.VERTICAL, 0, 100, 100);
        brightnessSlider.setPreferredSize(new Dimension(30, 150));

        colorPreview = new JPanel();
        colorPreview.setPreferredSize(new Dimension(50, 50));
        colorPreview.setBackground(selectedColor);
        colorPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        redField = new JTextField(String.valueOf(selectedColor.getRed()), 3);
        greenField = new JTextField(String.valueOf(selectedColor.getGreen()), 3);
        blueField = new JTextField(String.valueOf(selectedColor.getBlue()), 3);

        setupColorListeners();

        JPanel pickerTop = new JPanel(new BorderLayout());
        pickerTop.add(colorCanvas, BorderLayout.CENTER);
        pickerTop.add(brightnessSlider, BorderLayout.EAST);

        JPanel pickerBottom = new JPanel(new FlowLayout());
        pickerBottom.add(new JLabel("R:"));
        pickerBottom.add(redField);
        pickerBottom.add(new JLabel("G:"));
        pickerBottom.add(greenField);
        pickerBottom.add(new JLabel("B:"));
        pickerBottom.add(blueField);
        pickerBottom.add(colorPreview);

        colorPickerPanel.add(pickerTop);
        colorPickerPanel.add(pickerBottom);

        JPanel buttonPanel = new JPanel();
        JButton okButton = new JButton("Построить");
        JButton cancelButton = new JButton("Отмена");

        okButton.addActionListener(e -> {
            try {
                int steps = Integer.parseInt(stepsField.getText());
                double angle = Double.parseDouble(angleField.getText());
                int divisor = Integer.parseInt(divisorField.getText());
                double step = Double.parseDouble(stepField.getText());
                
                controller.addPattern(steps, angle, divisor, step, clickX, clickY, selectedColor);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Неверный формат числа!");
            }
        });

        cancelButton.addActionListener(e -> dispose());

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(paramsPanel, BorderLayout.NORTH);
        mainPanel.add(colorPickerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(parent);
    }

    private void setupColorListeners() {
        colorCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                updateColorFromCanvas(e.getX(), e.getY(), brightnessSlider.getValue());
            }
        });

        colorCanvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = Math.max(0, Math.min(e.getX(), colorCanvas.getWidth() - 1));
                int y = Math.max(0, Math.min(e.getY(), colorCanvas.getHeight() - 1));
                updateColorFromCanvas(x, y, brightnessSlider.getValue());
            }
        });

        brightnessSlider.addChangeListener(e -> {
            if (selectedColor != null) {
                float[] hsb = Color.RGBtoHSB(
                    selectedColor.getRed(),
                    selectedColor.getGreen(),
                    selectedColor.getBlue(),
                    null
                );
                float b = brightnessSlider.getValue() / 100f;
                selectedColor = Color.getHSBColor(hsb[0], hsb[1], b);
                updateColorFields();
            }
        });
    }

    private void updateColorFromCanvas(int x, int y, int brightness) {
        float hue = (float) x / colorCanvas.getWidth();
        float saturation = 1.0f - (float) y / colorCanvas.getHeight();
        float b = brightness / 100f;
        
        selectedColor = Color.getHSBColor(hue, saturation, b);
        updateColorFields();
    }

    private void updateColorFields() {
        colorPreview.setBackground(selectedColor);
        redField.setText(String.valueOf(selectedColor.getRed()));
        greenField.setText(String.valueOf(selectedColor.getGreen()));
        blueField.setText(String.valueOf(selectedColor.getBlue()));
    }
}
