package view;

import java.awt.BorderLayout;
import java.awt.Component;
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
import controller.AbstractController;

public class WinFrame extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private AbstractController controller;
	private HashMap<Integer, Point> hashBlocks = new HashMap<Integer, Point>();
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
		this.setSize(new Dimension(1000, 1000));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		// Panel pour les blocks
		JPanel blocks = new JPanel();
		blocks.setPreferredSize(new Dimension(300, 300));
		blocks.setBorder(BorderFactory.createBevelBorder(NORMAL));
		
		for (int i = 0; i < 25; i++) {
			Block block = new Block();
			blocks.add(block);
		}

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
		int i = 0;

		for (Component b : blocks.getComponents()) {
			if (b instanceof Block) {
				hashBlocks.put(i, ((Block) b).getLocation());
				i++;
			}
		}
	}

	// ====================== class Listener ===========
	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (!isRunning) {
				// controller.setHashBlock(hashBlocks);
				// controller.setAction(((JButton) e.getSource()).getText());
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
