import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {
	private static Main mainMember;
	static {
		mainMember = new Main();
	}
	private JPanel contentPane;
	private CardLayout cardLayout;
	private JPanel mainPanel;

	public static Main getInstance() {
		return mainMember;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = mainMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Àå¿ø±ÞÁ¦");
		setBounds(277, 156, 1366, 768);
		setBackground(Color.white);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 300, 729);
		menuPanel.setLayout(null);
		menuPanel.setBackground(Color.white);
		contentPane.add(menuPanel);

		JButton btnHome = new JButton("HOME");
		btnHome.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		btnHome.setBounds(12, 34, 276, 97);
		menuPanel.add(btnHome);

		JButton btnGoal = new JButton("GOAL");
		btnGoal.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		btnGoal.setBounds(12, 139, 276, 97);
		menuPanel.add(btnGoal);

		JButton btnExam = new JButton("EXAM");
		btnExam.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		btnExam.setBounds(12, 246, 276, 97);
		menuPanel.add(btnExam);

		JButton btnComplete = new JButton("COMPLETE");
		btnComplete.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		btnComplete.setBounds(12, 352, 276, 97);
		menuPanel.add(btnComplete);

		JButton btnMemo = new JButton("MEMO");
		btnMemo.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		btnMemo.setBounds(12, 459, 276, 97);
		menuPanel.add(btnMemo);

		cardLayout = new CardLayout(0, 0);

		mainPanel = new JPanel();
		mainPanel.setBounds(312, 0, 1038, 729);
		mainPanel.setLayout(cardLayout);
		mainPanel.setBackground(Color.white);

		HomePanel hp = HomePanel.getInstance();
		JPanel homePanel = hp.getContentPane();
		homePanel.setBounds(312, 0, 1038, 729);
		homePanel.setLayout(null);
		homePanel.setBackground(Color.white);

		GoalPanel gp = GoalPanel.getInstance();
		JPanel goalPanel = gp.getContentPane();
		goalPanel.setBounds(312, 0, 1038, 729);
		goalPanel.setLayout(null);
		goalPanel.setBackground(Color.white);

		ManageExam me = ManageExam.getInstance();
		JPanel examPanel = me.getContentPane();
		examPanel.setBounds(312, 0, 1038, 729);
		examPanel.setLayout(null);
		examPanel.setBackground(Color.white);

		JPanel completePanel = new JPanel();
		completePanel.setBounds(312, 0, 1038, 729);
		completePanel.setLayout(null);
		completePanel.setBackground(Color.white);

		SimpleMemo sm = SimpleMemo.getInstance();
		JPanel memoPanel = sm.getContentPane();
		memoPanel.setBounds(312, 0, 1038, 729);
		memoPanel.setLayout(null);
		memoPanel.setBackground(Color.white);

		mainPanel.add(homePanel, "HomePanel");
		mainPanel.add(goalPanel, "GoalPanel");
		mainPanel.add(examPanel, "ExamPanel");
		mainPanel.add(completePanel, "CompletePanel");
		mainPanel.add(memoPanel, "MemoPanel");
		contentPane.add(mainPanel);

		class MenuChangeListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "HOME":
					cardLayout.show(mainPanel, "HomePanel");
					break;
				case "GOAL":
					cardLayout.show(mainPanel, "GoalPanel");
					break;
				case "EXAM":
					cardLayout.show(mainPanel, "ExamPanel");
					break;
				case "COMPLETE":
					cardLayout.show(mainPanel, "CompletePanel");
					break;
				case "MEMO":
					cardLayout.show(mainPanel, "MemoPanel");
					break;
				}
				mainPanel.repaint();
			}
		}

		btnHome.addActionListener(new MenuChangeListener());
		btnGoal.addActionListener(new MenuChangeListener());
		btnExam.addActionListener(new MenuChangeListener());
		btnComplete.addActionListener(new MenuChangeListener());
		btnMemo.addActionListener(new MenuChangeListener());

		setContentPane(contentPane);
	}
}
