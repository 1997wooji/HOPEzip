package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Controller.SimpleMemoService;
import Model.ManageCount;
import Model.SimpleMemoInfoDao;

public class SimpleMemo extends JFrame {
	private static SimpleMemo simpleMemoMember;
	private int number;
	SimpleMemo simpleMemo;
	static {
		simpleMemoMember = new SimpleMemo();
	}
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable memoTable;
	private String memo;
	private ListSelectionModel completeSelectionCheck;
	private String completeSelectionData;

	public static SimpleMemo getInstance() {
		return simpleMemoMember;
	}

	public JPanel getContentPane() {
		return this.contentPane;
	}

	public void setCompleteSelectionData() {
		if (completeSelectionCheck.isSelectionEmpty()) {
			completeSelectionData = "false";
		} else {
			completeSelectionData = "true";
		}
	}

	public void setMemo(String str) {
		memo = str;
	}

	public JTable getMemoTable() {
		return memoTable;
	}

	public boolean addMemoTableItem(String memoName) {
		if (memoName == null) {
			return false;
		}

		ManageCount mCount = ManageCount.getInstance();
		Object[] row = { new Boolean(false), memoName };
		DefaultTableModel model = (DefaultTableModel) memoTable.getModel();
		model.addRow(row);

		return true;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SimpleMemo frame = simpleMemoMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SimpleMemo() {
		memo = null;
		completeSelectionData = "false";
		setResizable(false);
		setTitle("Àå¿ø±ÞÁ¦");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		scrollPane = new JScrollPane(memoTable);
		// JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 138, 835, 521);
		contentPane.add(scrollPane);
		scrollPane.setBackground(new Color(255, 255, 255));
		loadMemo();

		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMemo am = AddMemo.getInstance();
				JDialog jd = new JDialog(am);
				jd.setBounds(700, 400, 450, 300);
				jd.setContentPane(am.getContentPane());
				jd.setVisible(true);
				jd.setAlwaysOnTop(true);
			}
		});
		btnNewButton_1.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\\uC9C0\uC6B0\\Desktop\\fdfdfd.png"));
		btnNewButton_1.setBounds(872, 70, 68, 66);
		contentPane.add(btnNewButton_1);

		JButton btnMinus = new JButton("-");
		btnMinus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnMinus.setFont(new Font("±¼¸²", Font.PLAIN, 36));
		btnMinus.setBounds(793, 68, 68, 66);
		contentPane.add(btnMinus);

		JLabel lblNewLabel = new JLabel("\uAC04\uB2E8 \uBA54\uBAA8");
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 36));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(437, 35, 156, 42);
		contentPane.add(lblNewLabel);
		setContentPane(contentPane);
	}

	public void loadMemo() {
		SimpleMemoService ss = new SimpleMemoService();
		String[] memos = ss.simpleMemoLoad();
		Object[][] simpleMemoTable = new Object[memos.length][2];

		memoTable = new JTable();
		for (int i = 0; i < memos.length; i++) {
			String[] result = memos[i].split("/");
			simpleMemoTable[i][0] = Boolean.parseBoolean(result[2]);
			simpleMemoTable[i][1] = result[1];
		}

		memoTable.setModel(new DefaultTableModel(simpleMemoTable,
				new String[] { "\uB2EC\uC131 \uC5EC\uBD80", "\uBA54\uBAA8 \uB0B4\uC6A9" }) {
			Class[] columnTypes = new Class[] { Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		memoTable.getColumnModel().getColumn(0).setResizable(false);
		memoTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		memoTable.getColumnModel().getColumn(1).setResizable(false);
		memoTable.getColumnModel().getColumn(1).setPreferredWidth(600);
		scrollPane.setViewportView(memoTable);
		memoTable.setRowHeight(30);

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
}
