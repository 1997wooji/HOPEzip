import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class StopWatch extends JFrame {
	private static StopWatch stopWatchMember;
	static {
		stopWatchMember = new StopWatch();
	}
	private JPanel contentPane;
	private JLabel label;
	private long lastTickTime;
	private Timer timer;
	private long runningTime;

	public static StopWatch getInstance() {
		return stopWatchMember;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StopWatch frame = stopWatchMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StopWatch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 181);
		contentPane = new JPanel();
		contentPane.setBounds(0,0, 375, 181);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane);
		
		label = new JLabel(String.format("%02d:%02d:%02d", 0, 0, 0));
		label.setBounds(150,71,71,33);
		contentPane.add(label);
		timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				runningTime = System.currentTimeMillis() - lastTickTime;
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

		contentPane.setLayout(null);
		
		JButton start = new JButton("시작");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(start.getText().equals("시작")) {
					start.setText("중지");
					lastTickTime = System.currentTimeMillis();
					timer.start();		
				}
				else {
					start.setText("시작");
					timer.stop();
					System.out.println(runningTime);
				}
					
			}
		});
		start.setBounds(74, 114, 97, 23);
		contentPane.add(start);
		
		JButton save = new JButton("저장");
		save.setBounds(183, 114, 97, 23);
		contentPane.add(save);
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}

}
