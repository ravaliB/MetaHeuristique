package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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
	private BlocksPanel blocks;
	private boolean isRunning = false;
	private JSlider temperature;

	public WinFrame(AbstractController controller) {
		initComponent();
		this.controller = controller;
		this.setVisible(true);
	}

	public void update(HashMap<Integer, Point> map, HashMap<Point, Point> linksMap, double temperature) {
		Set<Integer> keys = map.keySet();
		Set<Point> keysLinks = linksMap.keySet();
		Iterator<Integer> it = keys.iterator();
		Iterator<Point> itLink = keysLinks.iterator();
		int i = 0;
		
		//mettre à jour les positions des blocks
		while (it.hasNext())
		{
			Block block = blocks.getBlocks().get(i);
			block.id = it.next();
			block.x = map.get(block.id).x;
			block.y = map.get(block.id).y;
			i++;
		}

		blocks.clearLines();
		
		//mettre à jour le linkage
		while (itLink.hasNext())
		{
			Point src = itLink.next();
			blocks.drawLine(src, linksMap.get(src));
		}
		
		hashBlocks = map;
		
		this.temperature.setValue((int)temperature);
		this.temperature.revalidate();
	}

	private void initComponent() {
		this.setTitle("MetaHeuristic");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Panel pour les blocks
		blocks = new BlocksPanel();

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

		temperature = new JSlider(JSlider.HORIZONTAL, 0, 25, 25);

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
				controller.setHashBlock(hashBlocks);
				controller.setTemperature(25);
				controller.setAction(((JButton) e.getSource()).getText());
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
			double temp = (double) source.getValue();
			
			controller.setTemperature(temp);
		}
	}
}
