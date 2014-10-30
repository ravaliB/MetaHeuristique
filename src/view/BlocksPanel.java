package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class BlocksPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static int DIST = 50;
	final static int MAX_ROW = 6;
	final static int MAX_COL = 6;

	private List<Block> blocks = new ArrayList<Block>();
	private List<Line2D> lines = new ArrayList<Line2D>();
	private boolean isDrawingLine = false;
	
	class Block {
		int x, y, id;

		public Block(int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
	}

	public BlocksPanel()
	{
		initComponent();
	}

	public void drawLine(Point pt1, Point pt2)
	{
		lines.add(new Line2D.Double(pt1.x, pt1.y, pt2.x, pt2.y));
		isDrawingLine = true;
		repaint();
	}
	
	public void clearLines()
	{
		lines.clear();
	//	isDrawingLine = false;
	//	repaint();
	}
	
	public List<Block> getBlocks()
	{
		return blocks;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension dimBlock = new Dimension(50, 50);
		g.setColor(Color.BLUE);

		for (Block block : blocks) {
			g.fillRect(block.x, block.y, dimBlock.width - 10 * 2,
					dimBlock.height - 10 * 2);
			g.drawString(Integer.toString(block.id), block.x, block.y);
		}
		
		if (isDrawingLine)
		{
			Graphics2D g2d = (Graphics2D)g;
			g2d.setColor(Color.RED);
			
			for (Line2D line : lines)
				g2d.draw(line);
			
			isDrawingLine = false;
		}
	}

	private void initComponent() {
		int x = 0, y = 0, i = 0;

		for (int row = 1; row < MAX_ROW; row++) {
			y = row * DIST;

			for (int col = 1; col < MAX_COL; col++) {
				x = col * DIST;
				blocks.add(new Block(x, y, i));
				i++;
			}
		}
	}
}
