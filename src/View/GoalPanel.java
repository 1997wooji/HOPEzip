package View;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

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

import Controller.GoalService;

public class GoalPanel extends JFrame {
	private static GoalPanel goalPanelMember;
	static {
		goalPanelMember = new GoalPanel();
	}
	private JPanel contentPane;
	private JTable todayGoalTable;
	private GoalService goalService;
	private String currentDate;

	public static GoalPanel getInstance() {
		return goalPanelMember;
	}
	
	public void setCurrentDate(String currentDate)
	{
		if(currentDate!=null)
			this.currentDate=currentDate;

	}

	/*public void addGoalTableItem(String startTime, String endTime, String goalCategory, String goalName, String goalQty, String goalUnit,
			String completeQty) {
		String completeRate=null;
		String studyTime = "";
		if (completeQty.equals("") || completeQty == null) {
			completeQty = "-";
			completeRate = "0";
		} else {
			Integer completeRateA = Integer.parseInt(completeQty) / Integer.parseInt(goalQty);
			completeRate = completeRateA + "";
		}
		String[] row = { goalCategory, goalName, goalQty + goalUnit, completeQty + goalUnit, completeRate,studyTime };
		DefaultTableModel model = (DefaultTableModel) todayGoalTable.getModel();
		model.addRow(row);

	}*/

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
		
		goalService=new GoalService();		
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);

		JLabel lblNewLabel = new JLabel("\uC624\uB298\uC758 \uBAA9\uD45C");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(417, 40, 192, 42);
		contentPane.add(lblNewLabel);

		
		
		class BtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				switch(e.getActionCommand())
				{case "btnAddGoal":
					AddGoal ag = AddGoal.getInstance();
					JDialog jd = new JDialog(ag,true);
					jd.setBounds(600, 150, 600, 700);
					jd.setContentPane(ag.getContentPane());
					jd.setVisible(true);
					;break;
				case "btnDate":
					HopeCalendar test4 = new HopeCalendar();
					JDialog jd2 = new JDialog(test4,true);
					jd2.setBounds(700, 200, 450, 300);
					jd2.setContentPane(test4.getContentPane());
					jd2.setVisible(true);
					;break;
				}
			}
		}
		
		JButton btnDate = new JButton("btnDate");
		btnDate.setBounds(131, 58, 133, 23);
		contentPane.add(btnDate);
		btnDate.addActionListener(new BtnListener());
		
		JButton btnAddGoal = new JButton("btnAddGoal");
		btnAddGoal.setFont(new Font("±¼¸²", Font.PLAIN, 36));
		btnAddGoal.setBounds(752, 28, 66, 59);
		contentPane.add(btnAddGoal);
		btnAddGoal.addActionListener(new BtnListener());

		JScrollPane goalPane = new JScrollPane();
		goalPane.setBounds(29, 99, 963, 554);
		contentPane.add(goalPane);
		
		todayGoalTable = new JTable();
		todayGoalTable.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		todayGoalTable.setRowHeight(30);
		loadTodayGoalTable(currentDate);
		todayGoalTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		todayGoalTable.getColumnModel().getColumn(1).setPreferredWidth(85);
		todayGoalTable.getColumnModel().getColumn(2).setPreferredWidth(168);
		goalPane.setViewportView(todayGoalTable);

		todayGoalTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel model = todayGoalTable.getSelectionModel();
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
					JDialog jd = new JDialog(gi,true);
					jd.setBounds(700, 400, 812, 407);
					jd.setContentPane(gi.getContentPane());
					jd.setVisible(true);

				}
			}
		});
		
		String[] currentDates=LocalDate.now().toString().split("-");
		setCurrentDate(currentDates[0]+currentDates[1]+currentDates[2]);
	}
	
	public void loadTodayGoalTable(String currentDate)
	{
		String[] goals=goalService.loadGoalInfo(currentDate);
		Object[][] tableLoad=new Object[goals.length][7];
		
		for(int i=0; i<goals.length;i++)
		{
			String[] temp=goals[i].split("/");
			
			tableLoad[i][0]=temp[1]+"~"+temp[2];
			tableLoad[i][1]=temp[3];
			tableLoad[i][2]=temp[4];
			tableLoad[i][3]=temp[5]+temp[6];
			tableLoad[i][4]=temp[7]+temp[6];
			tableLoad[i][5]=temp[8];
			tableLoad[i][6]=temp[9];

		}
		
		todayGoalTable.setModel(new DefaultTableModel(
				tableLoad,
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
		
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}
}
