
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddGoalUnitCategory extends JFrame {
	private static AddGoalUnitCategory addGoalUnitCategoryMember;
	static {
		addGoalUnitCategoryMember = new AddGoalUnitCategory();
	}
	private JPanel contentPane;
	private JTextField textField;
	private String inputStr;
	
	public void setInputStr(String inputStr) {
		this.inputStr = inputStr;
	}
	public String getInputStr() {
		return inputStr;
	}

	public static AddGoalUnitCategory getInstance() {
		return addGoalUnitCategoryMember;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoalUnitCategory frame = addGoalUnitCategoryMember;
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
	public AddGoalUnitCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		
		class BtnListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "저장":
					String str = textField.getText().trim();
					setInputStr(str);
					JOptionPane.showMessageDialog(contentPane, "입력이 성공되었습니다");
					AddGoal addGoal=AddGoal.getInstance();
					addGoal.addGoalUnitCategory(str);
				case "취소":
					dispose();
					break;
				}
			}
			
		}

		JButton button = new JButton("취소");
		JButton button_1 = new JButton("저장");
		
		button.addActionListener(new BtnListener());
		button_1.addActionListener(new BtnListener());

		JLabel label_1 = new JLabel("\uBAA9\uD45C\uB7C9 \uB2E8\uC704 \uCE74\uD14C\uACE0\uB9AC \uBA85");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("굴림", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setColumns(10);

		JLabel label_2 = new JLabel("\uBAA9\uD45C\uB7C9 \uB2E8\uC704 \uCE74\uD14C\uACE0\uB9AC \uCD94\uAC00");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("굴림", Font.BOLD, 15));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(241)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addGap(47)
						.addComponent(label_2, GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE).addGap(47))
				.addGroup(gl_panel.createSequentialGroup().addGap(32)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(47, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addGap(25)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(82)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 32,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(21)));
		panel.setLayout(gl_panel);
	}

}
