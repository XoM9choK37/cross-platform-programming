import java.awt.*;
import java.awt.event.*;

class GraphTest01 extends Frame {
  GraphTest01(String s) {
    super(s);
    setBounds(0, 0, 500, 300);
    setVisible(true);
  }
  public void paint(Graphics g) {
    Dimension d = getSize();
    int dx = d.width / 20, dy = d.height / 20;
    g.drawLine(60, 60, 150, 250);
    g.drawLine(30, 30, 100, 200);
    g.drawRect(20, 20, 300, 400);
    g.drawOval(50, 50, 300, 400);
    g.fillRoundRect(60, 60, 30, 30, 3, 3);
    setBackground(Color.blue);
    setForeground(Color.red);
  }
  public static void main(String[] args) {
    GraphTest01 f = new GraphTest01(" Пример рисования");
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent ev) {
        System.exit(0);
      }
    });
  }
}

