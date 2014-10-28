package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import observer.Observer;
import view.BlocksPanel.Block;
import controller.AbstractController;

public class WinFrame extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private AbstractController controller;
	private HashMap<Integer, Point> hashBlocks = new HashMap<Integer, Point>();
	// private Line line = new Line();
	private BlocksPanel blocks;
	private boolean isRunning = false;

	public WinFrame(AbstractController controller) {
		initComponent();
		this.controller = controller;
		this.setVisible(true);
	}

	public void update(String str) {

	}

	private void initComponent() {
		this.setTitle("MetaHeuristic");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Panel pour les blocks
		blocks = new BlocksPanel();
		// blocks.setPreferredSize(new Dimension(300, 300));
		// blocks.setBorder(BorderFactory.createBevelBorder(NORMAL));

		/*
		 * Insets insets = blocks.getInsets(); Dimension size;
		 * 
		 * int x = 0, y = 0;
		 * 
		 * 
		 * for (int i = 0; i < 25; i++) { Block block = new Block();
		 * block.setId(i); blocks.add(block);
		 * 
		 * x++;
		 * 
		 * if (i % 5 == 0) { x = 0;
		 * 
		 * if (i != 0) y++; }
		 * 
		 * size = block.getPreferredSize(); block.setBounds(60 * x +
		 * insets.left, 50 * y + insets.top, size.width, size.height);
		 * 
		 * }
		 * 
		 * 
		 * line.setPreferredSize(new Dimension(300, 200));
		 * 
		 * 
		 * blocks.add(line);
		 */

		// Panel pour la toolbox
		JPanel toolBox = new JPanel();
		toolBox.setPreferredSize(new Dimension(70, 300));

		JButton startButton = new JButton("start");
		startButton.setPreferredSize(new Dimension(70, 40));
		startButton.addActionListener(new StartListener());

		JButton stopButton = new JButton("stop");
		stopButton.setPreferredSize(new Dimension(70, 40));
		stopButton.addActionListener(new StopListener());

		toolBox.add(startButton);
		toolBox.add(stopButton);

		// slider de la temperature
		JPanel sliderPanel = new JPanel();
		JLabel sliderLabel = new JLabel("Temperature");

		JSlider temperature = new JSlider(JSlider.HORIZONTAL, 0, 25, 25);

		temperature.addChangeListener(new TemperatureListener());
		temperature.setMajorTickSpacing(10);
		temperature.setMinorTickSpacing(1);
		temperature.setPaintTicks(true);
		temperature.setPaintLabels(true);
		temperature.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

		Font font = new Font("Serif", Font.ITALIC, 15);
		temperature.setFont(font);

		sliderPanel.add(sliderLabel);
		sliderPanel.add(temperature);

		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(400, 400));

		container.add(sliderPanel, BorderLayout.NORTH);
		container.add(blocks, BorderLayout.CENTER);
		container.add(toolBox);

		this.setContentPane(container);

		this.pack();

		for (Block b : blocks.getBlocks()) {
			hashBlocks.put(b.id, new Point(b.x, b.y));
		}

	}

	// ====================== class Listener ===========
	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!isRunning) {
				// controller.setHashBlock(hashBlocks);
				// controller.setAction(((JButton) e.getSource()).getText());
				blocks.drawLine(hashBlocks.get(1), hashBlocks.get(7));
				isRunning = true;
			}
		}
	}

	class StopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.setAction(((JButton) e.getSource()).getText());
			isRunning = false;
		}
	}

	class TemperatureListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider) e.getSource();
			int temp = (int) source.getValue();

			controller.setTemperature(temp);
		}
	}
}
