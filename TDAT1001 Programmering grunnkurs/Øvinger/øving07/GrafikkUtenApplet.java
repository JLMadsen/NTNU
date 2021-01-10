// Øving 7 oppg 10.9.1

import javax.swing.*;
import java.awt.*;

class Vindu extends JFrame {
	public Vindu(String tittel) {
		setTitle(tittel);
		setSize(500, 500); // Bredde, Høyde
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tegning tegningen = new Tegning();
		add(tegningen);
	}
}

class Tegning extends JPanel {
	public void paintComponent(Graphics tegneflate) {
		super.paintComponent(tegneflate);
		setBackground(Color.GRAY);
		tegneflate.setColor(Color.YELLOW);
		tegneflate.fillOval(125, 125, 220, 220);
		tegneflate.setColor(Color.WHITE);
		tegneflate.fillOval(150, 175, 70, 70);
		tegneflate.fillOval(250, 175, 70, 70);
		tegneflate.setColor(Color.BLACK);
		tegneflate.drawOval(125, 125, 220, 220);		
		tegneflate.drawOval(150, 175, 70, 70);	
		tegneflate.drawOval(250, 175, 70, 70);
		tegneflate.fillArc(160, 190, 150, 125, 0, -180);
		tegneflate.fillOval(150, 175, 40, 40);
		tegneflate.fillOval(275, 175, 40, 40);
		tegneflate.setColor(Color.RED);
		tegneflate.fillRect(215, 252, 40, 40);
	}
}
class GrafikkUtenApplet{
	public static void main(String[] args) {
		Vindu etVindu = new Vindu("Smilefjes");
		etVindu.setVisible(true);
	}
}