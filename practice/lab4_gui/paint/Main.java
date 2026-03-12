import javax.swing.*;

public class Main {
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaintModel model = new PaintModel();
            PaintView view = new PaintView(model);
            PaintController controller = new PaintController(model, view);
            view.setVisible(true);
        });
    }
}
