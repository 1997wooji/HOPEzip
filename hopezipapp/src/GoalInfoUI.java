import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GoalInfoUI extends JFrame {
	private static GoalInfoUI goalInfoUIMember;
	static {
		goalInfoUIMember = new GoalInfoUI();
	}
	private JPanel contentPane;
	private long lastTickTime;

	public static GoalInfoUI getInstance() {
		return goalInfoUIMember;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoalInfoUI frame = goalInfoUIMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GoalInfoUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 407);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane.setLayout(null);

		JLabel label = new JLabel("\uC2DC\uAC04");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		label.setBounds(71, 54, 38, 24);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		label_1.setBounds(177, 57, 76, 24);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\uBAA9\uD45C");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		label_2.setBounds(336, 55, 38, 24);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\uBAA9\uD45C\uB7C9");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		label_3.setBounds(496, 61, 57, 24);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("\uD559\uC2B5\uB7C9");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		label_4.setBounds(590, 61, 57, 24);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\uC131\uCDE8\uB3C4");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		label_5.setBounds(676, 60, 57, 24);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("16:00 - 17:00");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.DARK_GRAY);
		label_6.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		label_6.setBounds(42, 93, 95, 18);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("\uD589\uC815\uBC95");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.DARK_GRAY);
		label_7.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		label_7.setBounds(165, 92, 95, 18);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("\uC2E4\uC804 \uBB38\uC81C \uD480\uAE30");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(Color.DARK_GRAY);
		label_8.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		label_8.setBounds(309, 95, 95, 18);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("30 \uBB38\uC81C");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setForeground(Color.DARK_GRAY);
		label_9.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		label_9.setBounds(478, 97, 95, 18);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("0 \uBB38\uC81C");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setForeground(Color.DARK_GRAY);
		label_10.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		label_10.setBounds(573, 97, 95, 18);
		contentPane.add(label_10);

		JLabel label_11 = new JLabel("0%");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setForeground(Color.DARK_GRAY);
		label_11.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		label_11.setBounds(658, 98, 95, 18);
		contentPane.add(label_11);

		JLabel label_12 = new JLabel("\uBA54\uBAA8");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 20));
		label_12.setBounds(243, 143, 38, 24);
		contentPane.add(label_12);

		JLabel label_13 = new JLabel(
				"\uD589\uC815\uBC95 \uC2E4\uC804 100\uC81C 2\uBC88\uBD80\uD130 31\uBC88\uAE4C\uC9C0 \uD480\uAE30!");
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setForeground(Color.DARK_GRAY);
		label_13.setFont(new Font("³ª´®°íµñ ExtraBold", Font.PLAIN, 15));
		label_13.setBounds(309, 144, 276, 18);
		contentPane.add(label_13);

		JButton button = new JButton("\uBAA9\uD45C \uC218\uC815");
		button.setFont(new Font("±¼¸²", Font.PLAIN, 10));
		button.setBounds(696, 315, 76, 43);
		contentPane.add(button);

		JButton button_1 = new JButton("\uBAA9\uD45C \uC0AD\uC81C");
		button_1.setFont(new Font("±¼¸²", Font.PLAIN, 10));
		button_1.setBounds(600, 315, 76, 43);
		contentPane.add(button_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(208, 177, 375, 181);
		panel.setLayout(null);
		contentPane.add(panel);
		
		
		StopWatch stopWatch = StopWatch.getInstance();
		panel.add(stopWatch.getContentPane());
		
		
		setContentPane(contentPane);
	}

}
