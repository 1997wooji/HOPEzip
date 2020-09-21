import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JSpinner;
import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class ManageExam extends JFrame {
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
		setTitle("Àå¿ø±ÞÁ¦");
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
		
		JSpinner spinner = new JSpinner();
		spinner.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 16));
		spinner.setToolTipText("");
		spinner.setModel(new SpinnerNumberModel(5, 1, 12, 1));
		spinner.setBounds(619, 57, 76, 27);
		contentPane.add(spinner);
		
		JPanel panel = new JPanel();
		panel.setBounds(35, 108, 962, 556);
		contentPane.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		
		table_1 = new JTable(30,5);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"5\uC6D4 12\uC77C10:00-13:00", "\uD1A0\uC775 \uC2DC\uD5D8 22\uCC28", "\uBD09\uCC9C \uCD08\uB4F1\uD559\uAD50", "750\uC810", "-"},
				{"5\uC6D4 28\uC77C10:00-13:00", "\uC815\uBCF4\uCC98\uB9AC\uAE30\uC0AC", "\uC11C\uC6B8\uB300 \uACF5\uD559\uAD00 201\uD638", "80", "-"},
				{"5\uC6D4 2\uC77C10:00-13:00", "\uD1A0\uC775 \uC2DC\uD5D8 21\uCC28", "\uBD09\uCC9C \uCD08\uB4F1\uD559\uAD50", "800\uC810", "900\uC810"},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\uC77C\uC2DC", "\uC2DC\uD5D8 \uBA85", "\uC7A5\uC18C", "\uBAA9\uD45C\uC810\uC218", "\uC810\uC218"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class
			};
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

		
		//SET SELECTION MODE
	    table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    GroupLayout gl_panel = new GroupLayout(panel);
	    gl_panel.setHorizontalGroup(
	    	gl_panel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addContainerGap()
	    			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1131, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    gl_panel.setVerticalGroup(
	    	gl_panel.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_panel.createSequentialGroup()
	    			.addGap(5)
	    			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    panel.setLayout(gl_panel);
	    
	    JLabel lblNewLabel = new JLabel("\uC2DC\uD5D8 \uC77C\uC815");
	    lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 36));
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
	    btnNewButton.setFont(new Font("±¼¸²", Font.PLAIN, 30));
	    btnNewButton.setBounds(798, 41, 61, 57);
	    contentPane.add(btnNewButton);
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
	            //int selectedRow=lsm.getMinSelectionIndex();
	
	        	  ViewExam viewExam=new ViewExam();
	        	  JDialog jd=new JDialog(viewExam);
	        	  jd.setBounds(700, 200, 550, 650);
	        	  jd.setContentPane(viewExam.getContentPane());
	        	  jd.setVisible(true);
	        	  //jd.setEnabled(false);
	        	  jd.setAlwaysOnTop(true);
	           
	          }
	        }
	      });
	}
	
	public JPanel getContentPane() {
		return this.contentPane;
	}
}
