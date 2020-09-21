package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.ExamService;

public class ManageExam extends JFrame {
	private JScrollPane scrollPane;
	private JPanel panel;
	private static ManageExam manageExamMember;
	static {
		manageExamMember = new ManageExam();
	}
	private JPanel contentPane;
	private JTable table_1;

	public static ManageExam getInstance() {
		return manageExamMember;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageExam frame = manageExamMember;
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
	public ManageExam() {
		setResizable(false);
		setTitle("장원급제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\\uC9C0\uC6B0\\Desktop\\fdfdfd.png"));
		btnNewButton_1.setBounds(1130, 83, 52, 51);
		contentPane.add(btnNewButton_1);

		panel = new JPanel();
		panel.setBounds(35, 108, 962, 556);
		contentPane.add(panel);

		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));

		loadExam("2018", "05");

		JLabel lblNewLabel = new JLabel("\uC2DC\uD5D8 \uC77C\uC815");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(420, 41, 156, 42);
		contentPane.add(lblNewLabel);

		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddGoal ag = new AddGoal();
				JDialog jd = new JDialog(ag);
				jd.setBounds(600, 150, 600, 700);
				jd.setContentPane(ag.getContentPane());
				jd.setVisible(true);
				jd.setAlwaysOnTop(true);
			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 30));
		btnNewButton.setBounds(798, 41, 61, 57);
		contentPane.add(btnNewButton);

		JComboBox yearComboBox = new JComboBox();
		yearComboBox.setModel(new DefaultComboBoxModel(new String[] { "2000", "2001", "2002", "2003", "2004", "2005",
				"2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018",
				"2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031",
				"2032", "2033", "2034", "2035", "2036", "2037", "2038", "2039", "2040", "2041", "2042", "2043", "2044",
				"2045", "2046", "2047", "2048", "2049", "2050" }));
		yearComboBox.setBounds(603, 57, 85, 27);
		contentPane.add(yearComboBox);

		JComboBox monthComboBox = new JComboBox();
		monthComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		monthComboBox.setBounds(700, 57, 85, 27);
		contentPane.add(monthComboBox);

		class ComboBoxItemListener implements ItemListener {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(yearComboBox.getSelectedItem().toString());
				loadExam(yearComboBox.getSelectedItem().toString(), monthComboBox.getSelectedItem().toString());
			}
		}

		yearComboBox.addItemListener(new ComboBoxItemListener());
		monthComboBox.addItemListener(new ComboBoxItemListener());

	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	public void loadExam(String year, String month) {
		ExamService es = new ExamService();
		Object[] examInfos = es.loadExamInfo();
		List<String> data = new ArrayList<String>();
		for(int i = 0; i < examInfos.length; i++) {
			String examInfo = (String)examInfos[i];
			String[] temp = examInfo.split("/");
			for(int j = 0; j < Integer.parseInt(temp[0]); j++) {
				String exeYear = temp[j + 1].substring(0, 4);
				String exeMonth = temp[j + 1].substring(4, 6);
				String exeDay = temp[j + 1].substring(6, 8);
				int cnt = Integer.parseInt(temp[0]);
				if(exeYear.equals(year) && exeMonth.equals(month)) {
					String str = exeMonth + "월 " +  exeDay + "일 " + temp[2 + cnt - 1] + "-" + temp[3 + cnt - 1] + "/" + temp[4 + cnt - 1] + "/" + temp[5 + cnt - 1] + "/" +temp[6 + cnt - 1] + " 점" + "/" + temp[j + 1].substring(9);
					data.add(str);
				}				
			}
		}
		
		Object[][] examTableData = new Object[data.size()][5];
		for(int i = 0; i < data.size(); i++) {
			String[] temp = data.get(i).split("/");
			examTableData[i][0] = temp[0];
			examTableData[i][1] = temp[1];
			examTableData[i][2] = temp[2];
			examTableData[i][3] = temp[3];
			examTableData[i][4] = temp[4];
		}

		table_1 = new JTable(30, 5);
		table_1.setModel(new DefaultTableModel(examTableData, new String[] { "\uC77C\uC2DC", "\uC2DC\uD5D8 \uBA85", "\uC7A5\uC18C", "\uBAA9\uD45C\uC810\uC218",
						"\uC810\uC218" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(1).setPreferredWidth(120);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(120);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(50);
		scrollPane.setViewportView(table_1);
		table_1.setRowHeight(50);

		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// SET SELECTION MODE
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel.createSequentialGroup().addGap(5)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE).addContainerGap()));
		panel.setLayout(gl_panel);
		
		table_1.setVisible(true);
	}
}