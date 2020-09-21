package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.CommonService;
import Controller.ExamService;
import Controller.GoalService;

public class AddGoal extends JFrame {
	private static AddGoal addGoalMember;
	static {
		//addGoalMember = new AddGoal();
	}
	private JPanel contentPane;
	private JTextField goalQtyTextField;
	private JTextField goalNameTextField;
	private JTextField locationTextField;
	private JTextField memoTextField;
	private JCheckBox monthGoalCheckBox;
	private JCheckBox dDayCheckBox;
	private JComboBox goalUnitComboBox;
	private JComboBox goalCategoryComboBox;
	private JComboBox examCategoryComboBox;
	private JLabel startDateLabel;
	private JLabel endDateLabel;
	private JComboBox startHourComboBox;
	private JComboBox startMinComboBox;
	private JComboBox endHourComboBox;
	private JComboBox endMinComboBox;

	private FakeDao fakeDao = FakeDao.fakeDao;
	private final Action action = new SwingAction();

	public void monthlyCheck() {
		this.monthGoalCheckBox.setSelected(true);
	}

	public static AddGoal getInstance() {
		return addGoalMember;
	}

	public void addGoalUnitCategory(String str) {
		if (((DefaultComboBoxModel) goalUnitComboBox.getModel()).getIndexOf(str) == -1) {
			int index = goalUnitComboBox.getItemCount();
			goalUnitComboBox.insertItemAt(str, index - 1);
		}
	}

	public void addExamCategory(String str) {
		if (((DefaultComboBoxModel) examCategoryComboBox.getModel()).getIndexOf(str) == -1) {
			int index = examCategoryComboBox.getItemCount();
			examCategoryComboBox.insertItemAt(str, index - 1);
		}
	}

	public void addGoalCategory(String str) {
		if (((DefaultComboBoxModel) goalCategoryComboBox.getModel()).getIndexOf(str) == -1) {
			int index = goalCategoryComboBox.getItemCount();
			goalCategoryComboBox.insertItemAt(str, index - 1);
		}
	}

	public void setStartDateLabel(String str) {
		startDateLabel.setText(str);
	}

	public void setEndDateLabel(String str) {
		endDateLabel.setText(str);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoal frame = addGoalMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddGoal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 600, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		HomePanel hp = HomePanel.getInstance();
		GoalPanel gp = GoalPanel.getInstance();

		JPanel datePanel = new JPanel();
		datePanel.setBounds(12, 205, 382, 78);
		contentPane.add(datePanel);

		JLabel dateLabel = new JLabel("\uB0A0\uC9DC");
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JButton btnEndDatePicker = new JButton("endDatePicker");
//		btnEndDatePicker.setAction(action);

		HopeCalendar hc = HopeCalendar.getInstance();
		startDateLabel = new JLabel(hc.getCurrentDateString());

		endDateLabel = new JLabel(hc.getCurrentDateString());
		GroupLayout gl_datePanel = new GroupLayout(datePanel);
		gl_datePanel.setHorizontalGroup(gl_datePanel.createParallelGroup(Alignment.LEADING).addGroup(gl_datePanel
				.createSequentialGroup().addContainerGap()
				.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(gl_datePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_datePanel.createSequentialGroup().addComponent(startDateLabel).addContainerGap())
						.addGroup(gl_datePanel.createSequentialGroup().addComponent(endDateLabel)
								.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
								.addComponent(btnEndDatePicker, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addGap(62)))));
		gl_datePanel
				.setVerticalGroup(gl_datePanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_datePanel.createSequentialGroup().addContainerGap()
								.addGroup(gl_datePanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(dateLabel, GroupLayout.PREFERRED_SIZE, 33,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(startDateLabel))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_datePanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(endDateLabel).addComponent(btnEndDatePicker,
												GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		datePanel.setLayout(gl_datePanel);

		class BtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "저장":
					String goalName = goalNameTextField.getText().trim();
					String goalCategory = (String) goalCategoryComboBox.getSelectedItem();
					String examCategory = (String) examCategoryComboBox.getSelectedItem();
					String startDate = (String) startDateLabel.getText();
					String endDate = (String) endDateLabel.getText();
					String startTime = startHourComboBox.getSelectedItem()
							+ (String) startMinComboBox.getSelectedItem();
					String endTime = endHourComboBox.getSelectedItem() + (String) endMinComboBox.getSelectedItem();
					String goalQty = goalQtyTextField.getText().trim();
					String goalUnit = (String) goalUnitComboBox.getSelectedItem();
					String location = locationTextField.getText().trim();
					String memo = memoTextField.getText().trim();

					HomePanel hp = HomePanel.getInstance();
					if (!goalCategory.equals("시험")) {
						GoalService gs = new GoalService();
						if (gs.addGoalInfo(goalCategory, goalName, startDate, endDate, startTime, endTime, goalQty,
								goalUnit, memo)) {
							JOptionPane.showMessageDialog(contentPane, "입력이 성공되었습니다");
						}
						else
							JOptionPane.showMessageDialog(contentPane, "실패하였습니다");
					} else {
						ExamService es = new ExamService();
						if (es.addExamInfo(goalName, startDate, endDate, startTime, endTime, goalQty, goalUnit, memo,
								examCategory, location, true))
							JOptionPane.showMessageDialog(contentPane, "입력이 성공되었습니다");
						else
							JOptionPane.showMessageDialog(contentPane, "실패하였습니다");
					}

					dispose();
					break;
				}
			}

		}

		JPanel monthDatePanel = new JPanel();
		monthDatePanel.setBounds(12, 205, 382, 78);
		contentPane.add(monthDatePanel);
		monthDatePanel.setVisible(false);

		JLabel monthGoalLable = new JLabel("\uC6D4 \uC120\uD0DD");
		monthGoalLable.setHorizontalAlignment(SwingConstants.RIGHT);
		monthGoalLable.setFont(new Font("굴림", Font.PLAIN, 14));

		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setModel(new DefaultComboBoxModel(new String[] { "2018", "2019", "2020", "2021" }));

		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

		JLabel yearLabel = new JLabel("\uB144");
		yearLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yearLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel monthLabel = new JLabel("\uC6D4");
		monthLabel.setHorizontalAlignment(SwingConstants.LEFT);
		monthLabel.setFont(new Font("굴림", Font.PLAIN, 14));
		GroupLayout gl_monthDatePanel = new GroupLayout(monthDatePanel);
		gl_monthDatePanel.setHorizontalGroup(gl_monthDatePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_monthDatePanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_monthDatePanel.createParallelGroup(Alignment.TRAILING)
								.addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_monthDatePanel.createSequentialGroup()
										.addComponent(monthGoalLable, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(yearComboBox,
												GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)))
						.addGap(12)
						.addGroup(gl_monthDatePanel.createParallelGroup(Alignment.LEADING)
								.addComponent(monthLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(yearLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(144, Short.MAX_VALUE)));
		gl_monthDatePanel
				.setVerticalGroup(
						gl_monthDatePanel
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_monthDatePanel.createSequentialGroup()
										.addContainerGap()
										.addGroup(gl_monthDatePanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(monthLabel, GroupLayout.PREFERRED_SIZE, 30,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_monthDatePanel.createSequentialGroup()
														.addGroup(gl_monthDatePanel
																.createParallelGroup(Alignment.BASELINE)
																.addComponent(monthGoalLable,
																		GroupLayout.PREFERRED_SIZE, 33,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(yearComboBox, GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(yearLabel, GroupLayout.PREFERRED_SIZE, 25,
																		GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(monthComboBox, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		monthDatePanel.setLayout(gl_monthDatePanel);

		JPanel examPanel = new JPanel();
		examPanel.setBounds(0, 469, 572, 105);
		contentPane.add(examPanel);
		examPanel.setVisible(false);

		JLabel examCategoryLabel = new JLabel("\uC2DC\uD5D8 \uCE74\uD14C\uACE0\uB9AC");
		examCategoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		examCategoryLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel locationLabel = new JLabel("\uC7A5\uC18C");
		locationLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		locationLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel dDayLabel = new JLabel("\uB514\uB370\uC774 \uC124\uC815 \uC5EC\uBD80");
		dDayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dDayLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		dDayCheckBox = new JCheckBox("");
		dDayCheckBox.setHorizontalAlignment(SwingConstants.CENTER);

		locationTextField = new JTextField();
		locationTextField.setColumns(10);
		ExamService es = new ExamService();
		String[] categories = es.loadExamCategory();
		examCategoryComboBox = new JComboBox();
		examCategoryComboBox.setMaximumRowCount(5);
		examCategoryComboBox.setModel(new DefaultComboBoxModel(categories));
		examCategoryComboBox.addItem("사용자 추가");
		GroupLayout gl_examPanel = new GroupLayout(examPanel);
		gl_examPanel.setHorizontalGroup(gl_examPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_examPanel.createSequentialGroup().addGap(25).addGroup(gl_examPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_examPanel.createSequentialGroup()
								.addComponent(examCategoryLabel, GroupLayout.PREFERRED_SIZE, 110,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(examCategoryComboBox,
										GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_examPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(dDayLabel, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(dDayCheckBox, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_examPanel.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 110,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(locationTextField,
										GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(68, Short.MAX_VALUE)));
		gl_examPanel.setVerticalGroup(gl_examPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_examPanel
				.createSequentialGroup()
				.addGroup(gl_examPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(examCategoryLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(examCategoryComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_examPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(locationLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(locationTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_examPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(dDayLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_examPanel.createSequentialGroup()
								.addComponent(dDayCheckBox, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
								.addGap(6)))));
		examPanel.setLayout(gl_examPanel);

		examCategoryComboBox.addItemListener(new ItemListener() { // 시험 카테고리
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item.equals("사용자 추가")) {
						AddExamCategory aec = AddExamCategory.getInstance();
						JDialog jd = new JDialog(aec, true);
						jd.setBounds(700, 200, 450, 300);
						jd.setContentPane(aec.getContentPane());
						jd.setVisible(true);

					}
				}
			}
		});

		JButton btnSave = new JButton("저장");
		btnSave.setBounds(492, 620, 80, 31);
		contentPane.add(btnSave);

		btnSave.addActionListener(new BtnListener());

		JButton btnCancel = new JButton("취소");
		btnCancel.setBounds(409, 620, 80, 31);
		contentPane.add(btnCancel);

		btnCancel.addActionListener(new BtnListener());

		JPanel goalPanel = new JPanel();
		goalPanel.setBounds(0, 10, 572, 459);
		contentPane.add(goalPanel);

		JLabel titleLabel = new JLabel("\uBAA9\uD45C \uCD94\uAC00\uD558\uAE30");
		titleLabel.setFont(new Font("궁서", Font.PLAIN, 29));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel goalCategoryLabel = new JLabel("\uBAA9\uD45C \uCE74\uD14C\uACE0\uB9AC");
		goalCategoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		goalCategoryLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel goalNameLabel = new JLabel("\uBAA9\uD45C \uBA85");
		goalNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		goalNameLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel monthGoalLabel = new JLabel("\uC6D4\uBCC4 \uBAA9\uD45C");
		monthGoalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		monthGoalLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel timeLabel = new JLabel("\uC2DC\uAC04");
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel repeatLabel = new JLabel("\uBC18\uBCF5 \uC5EC\uBD80");
		repeatLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		repeatLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel goalQtyLabel = new JLabel("\uBAA9\uD45C\uB7C9");
		goalQtyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		goalQtyLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel memoLabel = new JLabel("\uBA54\uBAA8");
		memoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		memoLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		goalQtyTextField = new JTextField();
		goalQtyTextField.setColumns(10);

		CommonService cs = new CommonService();
		String[] goalUnits = cs.loadGoalUnit();
		goalUnitComboBox = new JComboBox();
		goalUnitComboBox.setMaximumRowCount(5);
		goalUnitComboBox.setModel(new DefaultComboBoxModel(goalUnits));
		goalUnitComboBox.addItem("사용자 추가");

		String[] goalCategories = cs.loadGoalCategory();
		List<String> temp = new ArrayList<String>();
		for (int i = 0; i < goalCategories.length; i++) {
			String[] tempStr = goalCategories[i].split("/");
			temp.add(tempStr[0]);
		}
		goalCategoryComboBox = new JComboBox();
		goalCategoryComboBox.setMaximumRowCount(5);
		goalCategoryComboBox.setModel(new DefaultComboBoxModel(temp.toArray()));
		goalCategoryComboBox.addItem("사용자 추가");

		goalNameTextField = new JTextField();
		goalNameTextField.setColumns(10);

		JComboBox monthGoalComboBox = new JComboBox();
		monthGoalComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "\uC6D4\uBCC4 \uBAA9\uD45C\uC5D0\uC11C \uCD94\uAC00",
						"\uC6D4\uBCC4 \uBAA9\uD45C1", "\uC6D4\uBCC4 \uBAA9\uD45C2", "\uC6D4\uBCC4 \uBAA9\uD45C3" }));

		monthGoalCheckBox = new JCheckBox("");

		monthGoalCheckBox.setHorizontalAlignment(SwingConstants.CENTER);

		startHourComboBox = new JComboBox();
		startHourComboBox
				.setModel(new DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08",
						"09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

		startMinComboBox = new JComboBox();
		startMinComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

		JCheckBox repeatCheckBox = new JCheckBox("");
		repeatCheckBox.setHorizontalAlignment(SwingConstants.CENTER);

		endHourComboBox = new JComboBox();
		endHourComboBox.setModel(new DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

		endMinComboBox = new JComboBox();
		endMinComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14",
						"15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30",
						"31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
						"47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

		memoTextField = new JTextField();
		memoTextField.setColumns(10);
		GroupLayout gl_goalPanel = new GroupLayout(goalPanel);
		gl_goalPanel.setHorizontalGroup(gl_goalPanel.createParallelGroup(
				Alignment.LEADING)
				.addGroup(gl_goalPanel.createSequentialGroup().addGroup(gl_goalPanel
						.createParallelGroup(Alignment.LEADING).addGroup(
								gl_goalPanel.createSequentialGroup().addGap(24).addGroup(gl_goalPanel
										.createParallelGroup(Alignment.LEADING).addGroup(gl_goalPanel
												.createSequentialGroup().addComponent(monthGoalLabel,
														GroupLayout.PREFERRED_SIZE,
														110, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(monthGoalCheckBox, GroupLayout.PREFERRED_SIZE, 31,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_goalPanel.createSequentialGroup().addGroup(gl_goalPanel
												.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_goalPanel.createSequentialGroup()
														.addComponent(goalCategoryLabel, GroupLayout.PREFERRED_SIZE,
																110, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(goalCategoryComboBox, 0, 222, Short.MAX_VALUE))
												.addGroup(gl_goalPanel.createSequentialGroup()
														.addComponent(goalNameLabel, GroupLayout.PREFERRED_SIZE, 110,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.UNRELATED)
														.addComponent(goalNameTextField, GroupLayout.DEFAULT_SIZE, 222,
																Short.MAX_VALUE)))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(monthGoalComboBox, 0, 156, Short.MAX_VALUE).addGap(29))
										.addGroup(gl_goalPanel.createSequentialGroup()
												.addComponent(repeatLabel, GroupLayout.PREFERRED_SIZE, 110,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(repeatCheckBox, GroupLayout.PREFERRED_SIZE, 34,
														GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_goalPanel.createSequentialGroup()
												.addGroup(gl_goalPanel.createParallelGroup(Alignment.TRAILING)
														.addComponent(memoLabel, GroupLayout.PREFERRED_SIZE, 110,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(goalQtyLabel, GroupLayout.PREFERRED_SIZE, 110,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(gl_goalPanel.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_goalPanel.createSequentialGroup()
																.addComponent(goalQtyTextField,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.RELATED)
																.addComponent(goalUnitComboBox,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(47))
														.addComponent(memoTextField, GroupLayout.DEFAULT_SIZE, 421,
																Short.MAX_VALUE)))
										.addGroup(gl_goalPanel.createSequentialGroup()
												.addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 110,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(startHourComboBox, GroupLayout.PREFERRED_SIZE, 49,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(startMinComboBox, GroupLayout.PREFERRED_SIZE, 48,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(endHourComboBox, GroupLayout.PREFERRED_SIZE, 49,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(endMinComboBox, GroupLayout.PREFERRED_SIZE, 49,
														GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_goalPanel.createSequentialGroup().addGap(194).addComponent(titleLabel)))
						.addContainerGap()));
		gl_goalPanel.setVerticalGroup(gl_goalPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_goalPanel
				.createSequentialGroup().addGap(32).addComponent(titleLabel).addGap(27)
				.addGroup(gl_goalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(goalCategoryLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(goalCategoryComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_goalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(goalNameLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(goalNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(monthGoalComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_goalPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(monthGoalCheckBox, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addComponent(monthGoalLabel, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
				.addGroup(gl_goalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(startHourComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(endHourComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(endMinComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(startMinComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(5)
				.addGroup(gl_goalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(repeatLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(repeatCheckBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_goalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(goalQtyLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(goalQtyTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(goalUnitComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_goalPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(memoLabel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(memoTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)));
		goalPanel.setLayout(gl_goalPanel);

		goalCategoryComboBox.addItemListener(new ItemListener() { // 일정
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item.equals("시험"))
						examPanel.setVisible(true);
					else if (item.equals("사용자 추가")) {
						examPanel.setVisible(false);
						AddGoalCategory agc = AddGoalCategory.getInstance();
						JDialog jd = new JDialog(agc, true);
						jd.setBounds(700, 200, 450, 300);
						jd.setContentPane(agc.getContentPane());
						jd.setVisible(true);

					} else
						examPanel.setVisible(false);
				}
			}
		});

		monthGoalComboBox.addItemListener(new ItemListener() { // 목표 명 월별에서 끌어오기
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (!item.equals("월별 목표에서 추가")) {
						goalNameTextField.setText(item);
					} else {
						goalNameTextField.setText(null);
					}
				}
			}
		});

		goalUnitComboBox.addItemListener(new ItemListener() { // 목표량 단위
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					String item = (String) event.getItem();
					if (item.equals("사용자 추가")) {
						examPanel.setVisible(false);
						AddGoalUnitCategory agu = AddGoalUnitCategory.getInstance();
						JDialog jd = new JDialog(agu, true);
						jd.setBounds(700, 200, 450, 300);
						jd.setContentPane(agu.getContentPane());
						jd.setVisible(true);

					} else
						examPanel.setVisible(false);
				}
			}
		});

		monthGoalCheckBox.addItemListener(new ItemListener() { // 월별
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) // 체크박스는 이것만 해도 됨
				{
					monthDatePanel.setVisible(true);
					datePanel.setVisible(false);
				} else {
					monthDatePanel.setVisible(false);
					datePanel.setVisible(true);
				}

			}
		});

		repeatCheckBox.addItemListener(new ItemListener() { // 반복
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					AddRepeatInfo ari = AddRepeatInfo.getInstance();
					JDialog jd = new JDialog(ari, true);
					jd.setBounds(700, 300, 481, 441);
					jd.setContentPane(ari.getContentPane());
					jd.setVisible(true);

				} else
					;

			}
		});

		dDayCheckBox.addItemListener(new ItemListener() { // 디데이
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					;
				else
					;

			}
		});

		JButton btnStartDatePicker = new JButton("startDatePicker");
//		btnStartDatePicker.setAction(action);
		datePanel.add(btnStartDatePicker);
		btnStartDatePicker.setBounds(291, 16, 30, 28);

		class DatePickerListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "startDatePicker":
					HopeCalendar test4 = HopeCalendar.getInstance();
					JDialog jd = new JDialog(test4, true);
					jd.setBounds(700, 200, 450, 300);
					jd.setContentPane(test4.getContentPane());
					jd.setVisible(true);
					break;
				case "endDatePicker":
					HopeCalendar_2 test5 = HopeCalendar_2.getInstance();
					JDialog jd2 = new JDialog(test5, true);
					jd2.setBounds(700, 200, 450, 300);
					jd2.setContentPane(test5.getContentPane());
					jd2.setVisible(true);
					break;

				}

			}

		}

		btnStartDatePicker.addActionListener(new DatePickerListener());
		btnEndDatePicker.addActionListener(new DatePickerListener());

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
