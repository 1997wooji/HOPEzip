import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.SwingConstants;

public class StopWatchPane extends JPanel {
	private JLabel label;
	private long lastTickTime;
	private Timer timer;

	public StopWatchPane() {
		setLayout(null);

		JButton start = new JButton("\uC2DC\uC791");
		start.setBounds(103, 224, 108, 66);
		add(start);

		JButton stop = new JButton("\uC911\uC9C0");
		stop.setBounds(227, 224, 108, 66);
		add(stop);

		label = new JLabel(String.format("%02d:%02d:%02d", 0, 0, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(103, 149, 101, 41);
		add(label);
		
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long runningTime = System.currentTimeMillis() - lastTickTime;
				Duration duration = Duration.ofMillis(runningTime);
				long hours = duration.toHours();
				duration = duration.minusHours(hours);
				long minutes = duration.toMinutes();
				duration = duration.minusMinutes(minutes);
				long millis = duration.toMillis();
				long seconds = millis / 1000;
				label.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
			}
		});

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!timer.isRunning()) {
					lastTickTime = System.currentTimeMillis();
					timer.start();
				}
			}
		});

		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});

	}

}
