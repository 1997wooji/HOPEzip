
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

public class AddGoalCategory extends JFrame {
	private static AddGoalCategory addGoalCategoryMember;
	static {
		addGoalCategoryMember = new AddGoalCategory();
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
	public static AddGoalCategory getInstance() {
		return addGoalCategoryMember;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddGoalCategory frame = addGoalCategoryMember;
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
	public AddGoalCategory() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		
		class BtnListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()) {
				case "저장":
					String str = textField.getText().trim();
					setInputStr(str);
					JOptionPane.showMessageDialog(contentPane, "입력이 성공되었습니다");
					AddGoal addGoal=AddGoal.getInstance();
					addGoal.addGoalCategory(str);
				case "취소":
					dispose();
					break;
				}
			}
		}
		

		JButton btnNewButton = new JButton("저장");
		JButton button = new JButton("취소");
		
		button.addActionListener(new BtnListener());
		btnNewButton.addActionListener(new BtnListener());
		
		JLabel label = new JLabel("\uBAA9\uD45C \uCE74\uD14C\uACE0\uB9AC \uCD94\uAC00");
		label.setFont(new Font("굴림", Font.BOLD, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel label_1 = new JLabel("\uBAA9\uD45C \uCE74\uD14C\uACE0\uB9AC \uBA85");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("굴림", Font.PLAIN, 14));

		JLabel label_2 = new JLabel("\uBAA9\uD45C \uCE74\uD14C\uACE0\uB9AC \uC0C9");
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);
		label_2.setFont(new Font("굴림", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,
						gl_panel.createSequentialGroup().addContainerGap(253, Short.MAX_VALUE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(label_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(label_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 134,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
								.addComponent(label))
						.addContainerGap(149, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addGap(29).addComponent(label).addGap(37)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(label_1).addComponent(textField,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE).addGap(82)
				.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
				.addGap(19)));
		panel.setLayout(gl_panel);
	}

}
