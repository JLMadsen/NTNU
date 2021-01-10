/**
 * EnkelApplet.java - "Programmering i Java", 4.utgave - 2009-07-01
 *
 * En applet som tegner en liten tekst i en oval, oransje bakgrunn.
 */
import javax.swing.*;
import java.awt.*;

public class EnkelApplet extends JApplet {
  public void init() {
    add(new Tegning());
  }
}
class Tegning extends JPanel {
  public void paintComponent(Graphics tegneflate) {
    super.paintComponent(tegneflate);
    setBackground(Color.ORANGE);
    tegneflate.drawString("Hei hei", 50, 50);
    tegneflate.drawOval(40, 30, 55, 40);
  }
}
