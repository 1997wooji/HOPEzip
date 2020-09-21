import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
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

public class SimpleMemo extends JFrame {
	private static SimpleMemo simpleMemoMember;
	static {
		simpleMemoMember = new SimpleMemo();
	}
	private JPanel contentPane;
	private JTable table_1;

	public static SimpleMemo getInstance() {
		return simpleMemoMember;
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
		setResizable(false);
		setTitle("Àå¿ø±ÞÁ¦");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 729);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 138, 835, 521);
		contentPane.add(scrollPane);
		scrollPane.setBackground(new Color(255, 255, 255));
		
		table_1 = new JTable(30,2);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{Boolean.FALSE, "\uBE68\uAC04\uD39C \uC0AC\uAE30! \uCC44\uC810\uC2DC \uD544\uC694!"},
				{Boolean.FALSE, "\uC9C0\uAC01\uD558\uC9C0 \uC54A\uAE30"},
				{Boolean.FALSE, "\uACFC\uC2DDXX"},
				{Boolean.TRUE, "\uBA39\uACE0 \uC8FD\uC790~~"},
				{Boolean.TRUE, "\uBA39\uACE0 \uB450\uBC88 \uC8FD\uC790~~"},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"\uB2EC\uC131 \uC5EC\uBD80", "\uBA54\uBAA8 \uB0B4\uC6A9"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(40);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(600);
		scrollPane.setViewportView(table_1);
		table_1.setRowHeight(30);
		
		//SET SELECTION MODE
	    table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    ListSelectionModel model=table_1.getSelectionModel();
	    model.addListSelectionListener(new ListSelectionListener() {

	        @Override
	        public void valueChanged(ListSelectionEvent e) {

	          // JUST IGNORE WHEN USER HAS ATLEAST ONE SELECTION
	          if(e.getValueIsAdjusting())
	          {
	            return;
	          }
	          ListSelectionModel lsm=(ListSelectionModel) e.getSource();

	          if(lsm.isSelectionEmpty())
	          {
	            JOptionPane.showMessageDialog(null, "No selection");
	          }else
	          {
	            int selectedRow=lsm.getMinSelectionIndex();
	            JOptionPane.showMessageDialog(null, "Selected Row "+selectedRow);
	          }
	        }
	      });
		
		JButton btnNewButton_1 = new JButton("+");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMemo am = new AddMemo();
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
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
}
