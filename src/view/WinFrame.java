package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
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
	private JPanel container;

	public WinFrame(AbstractController controller) {
		super("MetaHeuristic");

		initComponent();
		this.controller = controller;
		this.setSize(1000, 1000);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(container);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void update(String str) {

	}

	private void initComponent() {
		container = new JPanel();
		container.setPreferredSize(new Dimension(400, 400));

		// Panel pour les blocks
		JPanel blocks = new JPanel();
		blocks.setPreferredSize(new Dimension(300, 300));
		blocks.setBorder(BorderFactory.createBevelBorder(NORMAL));

		for (int i = 0; i < 25; i++) {
			Block block = new Block();
			blocks.add(block);
		}

		// Panel pour les toolbox
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

		JPanel sliderPanel = new JPanel();
		JLabel sliderLabel = new JLabel("Temperature");

		JSlider temperature = new JSlider(JSlider.HORIZONTAL, 0,
				25, 25);

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

		container.add(sliderPanel, BorderLayout.NORTH);
		container.add(blocks, BorderLayout.CENTER);
		container.add(toolBox);

	}

	// ====================== class Listener ===========
	class StartListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.setAction(((JButton) e.getSource()).getText());
		}
	}

	class StopListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			controller.setAction(((JButton) e.getSource()).getText());
		}
	}

	class TemperatureListener implements ChangeListener {
		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
			int temp = (int)source.getValue();
			
			controller.setTemperature(temp);
		}
	}
}

class Block extends JComponent {
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