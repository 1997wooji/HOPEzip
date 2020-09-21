package View;

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

import Controller.ExamService;

public class AddExamCategory extends JFrame {
	private static AddExamCategory addExamCategoryMember;
	static {
		addExamCategoryMember = new AddExamCategory();
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

	public static AddExamCategory getInstance() {
		return addExamCategoryMember;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddExamCategory frame = addExamCategoryMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AddExamCategory() {
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
				switch (e.getActionCommand()) {
				case "저장":
					String str = textField.getText().trim();
					setInputStr(str);
					ExamService es = new ExamService();
					if (es.addExamCategory(str)) {
						JOptionPane.showMessageDialog(contentPane, "입력이 성공되었습니다");
						AddGoal addGoal = AddGoal.getInstance();
						addGoal.addExamCategory(str);
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "중복되는 카테고리가 있습니다.");
					}
					
				case "취소":
					dispose();
					break;
				}
			}
		}

		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new BtnListener());
		JButton btnCancel = new JButton("취소");
		btnCancel.addActionListener(new BtnListener());

		JLabel titleLabel = new JLabel("\uC2DC\uD5D8 \uCE74\uD14C\uACE0\uB9AC \uCD94\uAC00");
		titleLabel.setFont(new Font("굴림", Font.BOLD, 15));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel textFieldLabel = new JLabel("\uC2DC\uD5D8 \uCE74\uD14C\uACE0\uB9AC \uBA85");
		textFieldLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldLabel.setFont(new Font("굴림", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING,
						gl_panel.createSequentialGroup().addContainerGap(253, Short.MAX_VALUE)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup().addGap(153).addComponent(titleLabel).addContainerGap(189,
						Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup().addGap(63)
						.addComponent(textFieldLabel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(85, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(29).addComponent(titleLabel).addGap(52)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldLabel))
						.addGap(94)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addGap(19)));
		panel.setLayout(gl_panel);
	}

}
