package View;

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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Controller.GoalService;
import Controller.SimpleMemoService;
import Model.ManageCount;
import Model.SimpleMemoInfoDao;

public class HomePanel extends JFrame {
	private static HomePanel homePanelMember;
	static {
		 homePanelMember = new HomePanel();
	}
	private JPanel contentPane;
	private JTable todayGoalTable;
	private JTable memoTable;
	private JLabel examList;
	private JLabel dateLabel;
	private JList monthGoalList;
	private String currentDate;

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

	public void setDateLabel(String currentDate) {
		this.dateLabel.setText(currentDate);
	}

	public String getDateLabel() {
		return this.dateLabel.getText();
	}

	public void setCurrentDate(String currentDate) {
		if (currentDate != null) {
			this.currentDate = currentDate;
			loadExamList(currentDate);
		}
		if (this.todayGoalTable != null)
			loadTodayGoalTable(currentDate);
	}

	public void addGoalTableItem(String goalCategory, String goalName, String goalQty, String goalUnit,
			String completeQty) {

		String completeRate = null;
		if (completeQty.equals("") || completeQty == null) {
			completeQty = "-";
			completeRate = "0";
		} else {
			Double completeRateA = Double.parseDouble(completeQty) / Double.parseDouble(goalQty);
			completeRate = completeRateA + "";
		}
		String[] row = { goalCategory, goalName, goalQty + goalUnit, completeQty + goalUnit, completeRate + "%" };
		DefaultTableModel model = (DefaultTableModel) todayGoalTable.getModel();
		model.addRow(row);
	}

	public void addMemoTableItem(String memoName) {
		if (memoName == null || memoName == "") {
			return;
		}
		ManageCount mCount = ManageCount.getInstance();
		Object[] row = { memoName, new Boolean(false) };
		DefaultTableModel model = (DefaultTableModel) memoTable.getModel();
		model.addRow(row);
	}

	public HomePanel() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		HopeCalendar_3 hc3 = HopeCalendar_3.getInstance();
		setCurrentDate(hc3.getCurrentDateString());

		JLabel examLabel = new JLabel("Ω√«Ë ¿œ¡§");
		examLabel.setHorizontalAlignment(SwingConstants.CENTER);
		examLabel.setFont(new Font("±º∏≤", Font.PLAIN, 36));
		examLabel.setBounds(63, 47, 172, 57);
		contentPane.add(examLabel);

		JScrollPane examScrollPane = new JScrollPane();
		examScrollPane.setBounds(255, 25, 740, 109);
		examScrollPane.setBackground(Color.WHITE);
		contentPane.add(examScrollPane);

		examList = new JLabel(
				"\uACF5\uBB34\uC6D0 \uC2DC\uD5D8(D-20, 05/31), \uD1A0\uC775 \uC2DC\uD5D8(D-30, 06/18), \uACF5\uBB34\uC6D0 \uC2DC\uD5D8(D-50, 06/20)");
		examList.setBackground(Color.WHITE);
		examList.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		loadExamList(currentDate);
		examScrollPane.setViewportView(examList);

		JLabel todayLabel = new JLabel("ø¿¥√¿« ¿œ¡§");
		todayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		todayLabel.setFont(new Font("±º∏≤", Font.PLAIN, 36));
		todayLabel.setBounds(397, 170, 192, 42);
		contentPane.add(todayLabel);

		dateLabel = new JLabel(hc3.getCurrentDateString());
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setFont(new Font("±º∏≤", Font.PLAIN, 20));
		dateLabel.setBounds(171, 185, 173, 24);
		contentPane.add(dateLabel);

		class BtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				AddGoal ag = null;
				JDialog jd = null;
				switch (e.getActionCommand()) {
				case "todayGoalAdd":
					ag = AddGoal.getInstance();
					jd = new JDialog(ag, true);
					jd.setBounds(600, 150, 600, 700);
					jd.setContentPane(ag.getContentPane());
					jd.setVisible(true);

					break;
				case "monthGoalAdd":
					ag = AddGoal.getInstance();
					ag.monthlyCheck();
					jd = new JDialog(ag, true);
					jd.setBounds(600, 150, 600, 700);
					jd.setContentPane(ag.getContentPane());
					jd.setVisible(true);

					break;
				case "memoAdd":
					AddMemo am = AddMemo.getInstance();
					jd = new JDialog(am, true);
					jd.setBounds(700, 400, 450, 300);
					jd.setContentPane(am.getContentPane());
					jd.setVisible(true);

				}
			}
		}

		JButton btnTodayGoalAdd = new JButton("todayGoalAdd");
		btnTodayGoalAdd.addActionListener(new BtnListener());
		btnTodayGoalAdd.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		btnTodayGoalAdd.setBounds(645, 170, 69, 41);
		contentPane.add(btnTodayGoalAdd);

		JScrollPane todayScrollPane = new JScrollPane();
		todayScrollPane.setBounds(12, 229, 998, 176);
		contentPane.add(todayScrollPane);

		todayGoalTable = new JTable();
		todayGoalTable.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		todayGoalTable.setRowHeight(30);
		loadTodayGoalTable(currentDate);
		todayGoalTable.getColumnModel().getColumn(0).setPreferredWidth(46);
		todayGoalTable.getColumnModel().getColumn(1).setPreferredWidth(320);
		todayScrollPane.setViewportView(todayGoalTable);

		JLabel monthLabel = new JLabel("ø˘∫∞ ∏Ò«•");
		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthLabel.setFont(new Font("±º∏≤", Font.PLAIN, 36));
		monthLabel.setBounds(86, 454, 156, 42);
		contentPane.add(monthLabel);

		JButton btnMonthGoalAdd = new JButton("monthGoalAdd");
		btnMonthGoalAdd.setHorizontalAlignment(SwingConstants.LEFT);
		btnMonthGoalAdd.addActionListener(new BtnListener());
		btnMonthGoalAdd.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		btnMonthGoalAdd.setBounds(255, 457, 69, 41);
		contentPane.add(btnMonthGoalAdd);

		JLabel monthDateLabel = new JLabel("5\uC6D4");
		monthDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthDateLabel.setFont(new Font("±º∏≤", Font.PLAIN, 20));
		monthDateLabel.setBounds(30, 469, 31, 24);
		contentPane.add(monthDateLabel);

		JScrollPane monthScrollPane = new JScrollPane();
		monthScrollPane.setBounds(30, 503, 294, 162);
		contentPane.add(monthScrollPane);

		monthGoalList = new JList();
		monthGoalList.setFont(new Font("±º∏≤", Font.PLAIN, 25));
		loadMonthGoalList(currentDate);
		monthScrollPane.setViewportView(monthGoalList);

		JLabel memoLabel = new JLabel("∞£¥‹ ∏ﬁ∏");
		memoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memoLabel.setFont(new Font("±º∏≤", Font.PLAIN, 36));
		memoLabel.setBounds(411, 454, 156, 42);
		contentPane.add(memoLabel);

		JButton btnMemoAdd = new JButton("memoAdd");
		btnMemoAdd.setHorizontalAlignment(SwingConstants.RIGHT);
		btnMemoAdd.addActionListener(new BtnListener());
		btnMemoAdd.setFont(new Font("±º∏≤", Font.PLAIN, 30));
		btnMemoAdd.setBounds(600, 455, 69, 41);
		contentPane.add(btnMemoAdd);

		JScrollPane MemoScrollPane = new JScrollPane();
		MemoScrollPane.setBounds(359, 503, 363, 162);
		contentPane.add(MemoScrollPane);

		memoTable = new JTable();
		loadMemoTable();
		MemoScrollPane.setViewportView(memoTable);
		

		
		HopeCalendar_3 calendar = new HopeCalendar_3();
		JPanel jp = new JPanel();
		jp = calendar.getContentPane();
		jp.setBounds(735, 415, 290, 290);
		contentPane.add(jp);
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	public void loadTodayGoalTable(String currentDate) {
		if (currentDate == null)
			return;
		String[] temp = new String[3];
		temp[0] = currentDate.substring(0, 4);
		temp[1] = currentDate.substring(5, 7);
		temp[2] = currentDate.substring(8, 10);
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			str.append(temp[i]);
		}

		GoalService gs = new GoalService();
		String[] goals = gs.loadGoalInfo(str.toString());
		if (goals == null)
			return;

		Object[][] goalTable = new Object[goals.length][5];
		for (int i = 0; i < goals.length; i++) {
			String[] result = goals[i].split("/");

			goalTable[i][0] = result[3];
			goalTable[i][1] = result[4];
			goalTable[i][2] = result[5] + " " + result[6];
			goalTable[i][3] = result[7];
			goalTable[i][4] = result[8];

		}

		todayGoalTable.setModel(new DefaultTableModel(goalTable, new String[] { "∫–∑˘", "≥ªøÎ", "∏Ò«•∑Æ", "¥ﬁº∫∑Æ", "º∫√Îµµ" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		todayGoalTable.setVisible(true);
	}

	public void loadMemoTable() {
		SimpleMemoService sm = new SimpleMemoService();
		String[] simpleMemos = sm.simpleMemoLoad();
		Object[][] memoTableData = new Object[simpleMemos.length][2];
		for (int i = 0; i < simpleMemos.length; i++) {
			String[] temp = simpleMemos[i].split("/");
			memoTableData[i][0] = Boolean.parseBoolean(temp[2]);
			memoTableData[i][1] = temp[1];
		}

		memoTable.setModel(new DefaultTableModel(memoTableData, new String[] { "\uB2EC\uC131 \uC5EC\uBD80", "\uAC04\uB2E8 \uBA54\uBAA8" }) {
			Class[] columnTypes = new Class[] { Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		memoTable.getColumnModel().getColumn(0).setResizable(false);
		memoTable.getColumnModel().getColumn(1).setResizable(false);
		memoTable.getColumnModel().getColumn(1).setPreferredWidth(210);

		memoTable.setVisible(true);
		
		memoTable.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				System.out.println(e.getFirstRow());
				SimpleMemoInfoDao simpleMemoInfoDao = SimpleMemoInfoDao.getInstance();
				if (e.getFirstRow() >= simpleMemoInfoDao.simpleMemoInfoCnt()) {

				} else {
					simpleMemoInfoDao.searchSimpleMemoInfo(e.getFirstRow()).changeChecked();
				}
			}
		});
	}

	public void loadExamList(String currentDate) {
		
		
	}

	public void loadMonthGoalList(String currentDate) {
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
	}
}
