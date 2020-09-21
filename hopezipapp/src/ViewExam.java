
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
import javax.swing.JList;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewExam extends JFrame {
	private static ViewExam viewExamMember;
	static {
		viewExamMember = new ViewExam();
	}
	private JPanel contentPane;
	private JTextField textField;
	JPanel panel_1;
	JPanel panel_2;
	JPanel panel;
	JButton button_3;
	JButton button_4;
	
	public static ViewExam getInstance() {
		return viewExamMember;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewExam frame = viewExamMember;
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
	public ViewExam() {
		setResizable(false);
		setTitle("½ÃÇè Á¶È¸");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 200, 550, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 543, 385);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("½ÃÇè Á¶È¸");
		lblNewLabel.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("\uC2DC\uD5D8 \uBA85");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("\uB0A0\uC9DC");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_2 = new JLabel("\uC2DC\uD5D8 \uCE74\uD14C\uACE0\uB9AC");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_3 = new JLabel("\uC2DC\uAC04");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_4 = new JLabel("\uBAA9\uD45C \uC810\uC218");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_5 = new JLabel("\uC7A5\uC18C");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_6 = new JLabel("\uBA54\uBAA8");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_8 = new JLabel("\uC815\uBCF4\uCC98\uB9AC\uAE30\uC0AC");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_9 = new JLabel("\uC790\uACA9\uC99D");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_10 = new JLabel("5\uC6D4 28\uC77C");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_11 = new JLabel("10:00~13:00");
		label_11.setHorizontalAlignment(SwingConstants.LEFT);
		label_11.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_12 = new JLabel("\uC11C\uC6B8\uB300\uD559\uAD50 \uACF5\uD559\uAD00 201\uD638");
		label_12.setHorizontalAlignment(SwingConstants.LEFT);
		label_12.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_13 = new JLabel("\uC78A\uC9C0\uB9D0\uC790! \uD615\uAD00\uD39C!");
		label_13.setHorizontalAlignment(SwingConstants.LEFT);
		label_13.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_14 = new JLabel("80 \uC810");
		label_14.setHorizontalAlignment(SwingConstants.LEFT);
		label_14.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(99)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_4)
						.addComponent(label)
						.addComponent(label_2)
						.addComponent(label_1)
						.addComponent(label_3)
						.addComponent(label_5)
						.addComponent(label_6))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label_9)
						.addComponent(label_10)
						.addComponent(label_11)
						.addComponent(label_12)
						.addComponent(label_13)
						.addComponent(label_14)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblNewLabel)
							.addComponent(label_8)))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(83)
					.addComponent(lblNewLabel)
					.addGap(52)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label)
						.addComponent(label_8))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(label_9))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(label_10))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(label_11))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(label_12))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(label_13))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(label_14))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(106, 385, 391, 33);
		contentPane.add(panel_2);
		panel_2.setVisible(false);
		
		JLabel label_7 = new JLabel("\uC810\uC218");
		label_7.setHorizontalAlignment(SwingConstants.RIGHT);
		label_7.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		button_4 = new JButton("\uC810\uC218 \uB4F1\uB85D");
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(67)
					.addComponent(label_7)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button_4)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button_4)
						.addComponent(label_7)))
		);
		panel_2.setLayout(gl_panel_2);
		
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(106, 385, 391, 33);
		contentPane.add(panel);
		
		JLabel label_16 = new JLabel("\uC810\uC218");
		label_16.setHorizontalAlignment(SwingConstants.RIGHT);
		label_16.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		JLabel label_15 = new JLabel("\uB4F1\uB85D\uB41C \uC810\uC218\uAC00 \uC5C6\uC2B5\uB2C8\uB2E4.");
		label_15.setForeground(Color.GRAY);
		label_15.setHorizontalAlignment(SwingConstants.LEFT);
		label_15.setFont(new Font("³ª´®½ºÄù¾î¶ó¿îµå Bold", Font.PLAIN, 18));
		
		button_3 = new JButton("\uC810\uC218 \uB4F1\uB85D");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(55)
					.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(label_15)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_3)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(button_3)
						.addComponent(label_15)
						.addComponent(label_16))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		button_3.addMouseListener(new MouseAdapter() {
	@Override
	public void mouseClicked(MouseEvent e) {
		panel.setVisible(false);
		panel_2.setVisible(true);
	}
});
		
	button_4.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			panel_2.setVisible(false);
			panel.setVisible(true);
		}
	});
		
		panel.setLayout(gl_panel);
		panel.setVisible(true);

		
		JButton button = new JButton("\uC218\uC815");
		button.setBounds(328, 506, 66, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\uC0AD\uC81C");
		button_1.setBounds(397, 506, 66, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\uD655\uC778");
		button_2.setBounds(466, 506, 66, 23);
		contentPane.add(button_2);
	}

}
