package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class Block extends JComponent {
	private static final long serialVersionUID = 1L;

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(50, 50);
	}

	@Override
	public void paintComponent(Graphics g) {
		int margin = 10;
		Dimension dim = getSize();
		super.paintComponent(g);
		g.setColor(Color.blue);
		g.fillRect(margin, margin, dim.width - margin * 2, dim.height - margin
				* 2);
	}
}