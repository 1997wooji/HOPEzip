import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class HomePanel extends JFrame {
	private static HomePanel homePanelMember;
	static {
		homePanelMember = new HomePanel();
	}
	private JPanel contentPane;
	private JTable todayGoalTable;
	private JTable memoTable;
	private JLabel examList;

	public static HomePanel getInstance() {
		return homePanelMember;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePanel frame = homePanelMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addGoalTableItem(String goalCategory,String goalName, String goalQty, String goalUnit, String completeQty){
		
		String completeRate=null;
		if(completeQty.equals("")||completeQty==null)
			{completeQty="-"; completeRate="0";}
		else{
				Integer completeRateA=Integer.parseInt(completeQty)/Integer.parseInt(goalQty);
				completeRate=completeRateA+"";
			}
		String[] row={goalCategory,goalName,goalQty+goalUnit,completeQty+goalUnit,completeRate};
		DefaultTableModel model = (DefaultTableModel) todayGoalTable.getModel();
	    model.addRow(row);
	}

	public HomePanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		JLabel examLabel = new JLabel("시험 일정");
		examLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examLabel.setFont(new Font("굴림", Font.PLAIN, 36));
		examLabel.setBounds(63, 47, 172, 57);
		contentPane.add(examLabel);

		JScrollPane examScrollPane = new JScrollPane();
		examScrollPane.setBounds(255, 25, 740, 109);
		examScrollPane.setBackground(Color.WHITE);
		contentPane.add(examScrollPane);

		examList = new JLabel(
				"\uACF5\uBB34\uC6D0 \uC2DC\uD5D8(D-20, 05/31), \uD1A0\uC775 \uC2DC\uD5D8(D-30, 06/18), \uACF5\uBB34\uC6D0 \uC2DC\uD5D8(D-50, 06/20)");
		examList.setBackground(Color.WHITE);
		examList.setFont(new Font("굴림", Font.PLAIN, 30));
		examScrollPane.setViewportView(examList);

		JLabel todayLabel = new JLabel("오늘의 일정");
		todayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		todayLabel.setFont(new Font("굴림", Font.PLAIN, 36));
		todayLabel.setBounds(397, 170, 192, 42);
		contentPane.add(todayLabel);

		JLabel dateLabel = new JLabel("2018/05/18 금요일");
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		dateLabel.setBounds(171, 185, 173, 24);
		contentPane.add(dateLabel);

		class BtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				AddGoal ag = null;
				JDialog jd = null;
				switch (e.getActionCommand()) {
				case "todayGoalAdd":
					ag = AddGoal.getInstance();
					jd = new JDialog(ag);
					jd.setBounds(600, 150, 600, 700);
					jd.setContentPane(ag.getContentPane());
					jd.setVisible(true);
					jd.setAlwaysOnTop(true);
					break;
				case "monthGoalAdd":
					ag = AddGoal.getInstance();
					ag.monthlyCheck();
					jd = new JDialog(ag);
					jd.setBounds(600, 150, 600, 700);
					jd.setContentPane(ag.getContentPane());
					jd.setVisible(true);
					jd.setAlwaysOnTop(true);
					break;
				case "memoAdd":
					 AddMemo am = AddMemo.getInstance();
					jd = new JDialog(am);
					jd.setBounds(700, 400, 450, 300);
					jd.setContentPane(am.getContentPane());
					jd.setVisible(true);
					jd.setAlwaysOnTop(true);
				}
			}
		}

		JButton btnTodayGoalAdd = new JButton("todayGoalAdd");
		btnTodayGoalAdd.addActionListener(new BtnListener());
		btnTodayGoalAdd.setFont(new Font("굴림", Font.PLAIN, 30));
		btnTodayGoalAdd.setBounds(645, 170, 69, 41);
		contentPane.add(btnTodayGoalAdd);

		JScrollPane todayScrollPane = new JScrollPane();
		todayScrollPane.setBounds(12, 229, 998, 176);
		contentPane.add(todayScrollPane);

		todayGoalTable = new JTable();
		todayGoalTable.setFont(new Font("굴림", Font.PLAIN, 30));
		todayGoalTable.setRowHeight(30);
		todayGoalTable.setModel(new DefaultTableModel(new Object[][] { { "수학", "수학 문제 풀기", "250 문제", "120 문제", "48%" },
				{ "토익", "실전 토익 풀기", "50 문제", "0 문제", "0%" } },
				new String[] { "분류", "내용", "목표량", "달성량", "성취도" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		todayGoalTable.getColumnModel().getColumn(0).setPreferredWidth(46);
		todayGoalTable.getColumnModel().getColumn(1).setPreferredWidth(320);
		todayScrollPane.setViewportView(todayGoalTable);

		JLabel monthLabel = new JLabel("월별 목표");
		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthLabel.setFont(new Font("굴림", Font.PLAIN, 36));
		monthLabel.setBounds(86, 454, 156, 42);
		contentPane.add(monthLabel);

		JButton btnMonthGoalAdd = new JButton("monthGoalAdd");
		btnMonthGoalAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnMonthGoalAdd.addActionListener(new BtnListener());
		btnMonthGoalAdd.setFont(new Font("굴림", Font.PLAIN, 30));
		btnMonthGoalAdd.setBounds(255, 457, 69, 41);
		contentPane.add(btnMonthGoalAdd);

		JLabel monthDateLabel = new JLabel("5\uC6D4");
		monthDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthDateLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		monthDateLabel.setBounds(30, 469, 31, 24);
		contentPane.add(monthDateLabel);

		JScrollPane monthScrollPane = new JScrollPane();
		monthScrollPane.setBounds(30, 503, 294, 162);
		contentPane.add(monthScrollPane);

		JList monthGoalList = new JList();
		monthGoalList.setFont(new Font("굴림", Font.PLAIN, 25));
		monthGoalList.setModel(new AbstractListModel() {
			String[] values = new String[] { "EBS \uC644\uAC15\uD558\uAE30",
					"\uD1A0\uC775 \uC2E4\uC804\uBB38\uC81C \uB2E4 \uD480\uAE30",
					"\uD589\uC815\uBC95 \uBAA8\uC758\uACE0\uC0AC \uB2E4 \uD480\uAE30",
					"\uC218\uD559 \uBB38\uC81C \uB2E4 \uD480\uAE30",
					"\uBBFC\uBC95 \uBAA8\uC758\uACE0\uC0AC \uB2E4 \uD480\uAE30",
					"\uAD6D\uC5B4 5\uCC55\uD130\uAE4C\uC9C0 \uACF5\uBD80\uD558\uAE30",
					"\uD589\uC815\uD559 \uC778\uAC15 \uB2E4 \uB4E3\uAE30" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		monthScrollPane.setViewportView(monthGoalList);

		JLabel memoLabel = new JLabel("간단 메모");
		memoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memoLabel.setFont(new Font("굴림", Font.PLAIN, 36));
		memoLabel.setBounds(411, 454, 156, 42);
		contentPane.add(memoLabel);

		JButton btnMemoAdd = new JButton("memoAdd");
		btnMemoAdd.setHorizontalAlignment(SwingConstants.RIGHT);
		btnMemoAdd.addActionListener(new BtnListener());
		btnMemoAdd.setFont(new Font("굴림", Font.PLAIN, 30));
		btnMemoAdd.setBounds(600, 455, 69, 41);
		contentPane.add(btnMemoAdd);

		JScrollPane MemoScrollPane = new JScrollPane();
		MemoScrollPane.setBounds(359, 503, 363, 162);
		contentPane.add(MemoScrollPane);

		memoTable = new JTable();
		memoTable.setModel(new DefaultTableModel(
				new Object[][] { { "\uD615\uAD11\uD39C \uC0AC\uAE30", null },
						{ "\uC601\uAD11\uC774\uD615 \uACB0\uD63C\uC2DD \uAC00\uAE30", null },
						{ "\uC9C0\uC6B0 \uBC25 \uC0AC\uC8FC\uAE30", null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, { null, null }, },
				new String[] { "\uAC04\uB2E8 \uBA54\uBAA8", "\uB2EC\uC131 \uC5EC\uBD80" }) {
			Class[] columnTypes = new Class[] { String.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		memoTable.getColumnModel().getColumn(0).setPreferredWidth(210);
		MemoScrollPane.setViewportView(memoTable);

		HopeCalendar calendar = new HopeCalendar();
		JPanel jp = new JPanel();
		jp = calendar.getContentPane();
		jp.setBounds(735, 415, 290, 290);
		contentPane.add(jp);
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}
}
