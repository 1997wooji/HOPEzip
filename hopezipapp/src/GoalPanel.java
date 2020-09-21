import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GoalPanel extends JFrame {
	private static GoalPanel goalPanelMember;
	static {
		goalPanelMember = new GoalPanel();
	}
	private JPanel contentPane;
	private JTable table;

	public static GoalPanel getInstance() {
		return goalPanelMember;
	}

	public void addGoalTableItem(String startTime, String endTime, String goalCategory, String goalName, String goalQty, String goalUnit,
			String completeQty) {
		String completeRate=null;
		String studyTime = "";
		if (completeQty.equals("") || completeQty == null) {
			completeQty = "-";
			completeRate = "0";
		} else {
			Integer completeRateA = Integer.parseInt(completeQty) / Integer.parseInt(goalQty);
			completeRate = completeRateA + "-";
		}
		String[] row = { goalCategory, goalName, goalQty + goalUnit, completeQty + goalUnit, completeRate,"-" };
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.addRow(row);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GoalPanel frame = goalPanelMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GoalPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		JLabel lblNewLabel = new JLabel("\uC624\uB298\uC758 \uBAA9\uD45C");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(417, 40, 192, 42);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("2018-05-18 \uAE08\uC694\uC77C");
		btnNewButton.setBounds(131, 58, 133, 23);
		contentPane.add(btnNewButton);

		JButton button = new JButton("+");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoal ag = AddGoal.getInstance();
				JDialog jd = new JDialog(ag);
				jd.setBounds(600, 150, 600, 700);
				jd.setContentPane(ag.getContentPane());
				jd.setVisible(true);
				jd.setAlwaysOnTop(true);
			}
		});
		button.setFont(new Font("굴림", Font.PLAIN, 36));
		button.setBounds(752, 28, 66, 59);
		contentPane.add(button);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 99, 963, 554);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("굴림", Font.PLAIN, 20));
		table.setRowHeight(30);
		table.setModel(new DefaultTableModel(
				new Object[][] { { "08:00-09:00", "민법", "모의고사 풀기", "2회", "1회", "50%", "00:57:03" },
						{ "09:00-10:03", "토익", "실전 TOEIC 풀기", "50문제", "32문제", "64%", "00:52:42" },
						{ "10:00-09:00", "민법", "모의고사 풀기", "2회", "1회", "50%", "00:57:03" },
						{ "08:00-09:00", "민법", "모의고사 풀기", "2회", "1회", "50%", "00:57:03" },
						{ "09:00-10:03", "토익", "실전 TOEIC 풀기", "50문제", "32문제", "64%", "00:52:42" },
						{ "10:00-09:00", "민법", "모의고사 풀기", "2회", "1회", "50%", "00:57:03" }},
				new String[] { "\uC2DC\uAC04", "\uBD84\uB958", "\uBAA9\uD45C \uBA85", "\uBAA9\uD45C\uB7C9",
						"\uD559\uC2B5\uB7C9", "\uC131\uCDE8\uB3C4", "\uD559\uC2B5 \uC2DC\uAC04" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(85);
		table.getColumnModel().getColumn(2).setPreferredWidth(168);
		scrollPane.setViewportView(table);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel model = table.getSelectionModel();
		model.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				// JUST IGNORE WHEN USER HAS ATLEAST ONE SELECTION
				if (e.getValueIsAdjusting()) {
					return;
				}
				ListSelectionModel lsm = (ListSelectionModel) e.getSource();

				if (lsm.isSelectionEmpty()) {
					JOptionPane.showMessageDialog(null, "No selection");
				} else {
					int selectedRow = lsm.getMinSelectionIndex();
					GoalInfoUI gi = GoalInfoUI.getInstance();
					JDialog jd = new JDialog(gi);
					jd.setBounds(700, 400, 812, 407);
					jd.setContentPane(gi.getContentPane());
					jd.setVisible(true);
					jd.setAlwaysOnTop(true);
				}
			}
		});
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}
}
