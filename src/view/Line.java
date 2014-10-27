package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JComponent;

public class Line extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static class DrawLine {
		int x1;
		int y1;
		int x2;
		int y2;
		Color color;

		public DrawLine(int x1, int y1, int x2, int y2, Color color) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.color = color;
		}
	}

	private final LinkedList<DrawLine> lines = new LinkedList<DrawLine>();

	public void addLine(int x1, int x2, int x3, int x4) {
		addLine(x1, x2, x3, x4, Color.black);
	}

	public void addLine(int x1, int x2, int x3, int x4, Color color) {
		lines.add(new DrawLine(x1, x2, x3, x4, color));
		repaint();
	}

	public void clearLines() {
		lines.clear();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (DrawLine line : lines) {
			g.setColor(line.color);
			g.drawLine(line.x1, line.y1, line.x2, line.y2);
			System.out.println(line.x1 + "," + line.y1 + "," + line.x2 + "," + line.y2);
		}
		
		System.out.println("draw Ok");
	}
}
