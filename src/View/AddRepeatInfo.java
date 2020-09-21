package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AddRepeatInfo extends JFrame {
	private static AddRepeatInfo addRepeatInfoMember;
	static {
		//addRepeatInfoMember = new AddRepeatInfo();
	}
	private JPanel contentPane;
	private JTextField textField;

	public static AddRepeatInfo getInstance() {
		return addRepeatInfoMember;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddRepeatInfo frame = addRepeatInfoMember;
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
	public AddRepeatInfo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 465, 142);
		contentPane.add(panel);

		JLabel label = new JLabel("\uBC18\uBCF5 \uC815\uBCF4");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("±º∏≤", Font.BOLD, 17));

		JLabel label_1 = new JLabel("\uBC18\uBCF5 \uAC04\uACA9(\uC77C)");
		label_1.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);

		JCheckBox chckbxNewCheckBox = new JCheckBox("");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(184).addComponent(label,
								GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup().addGap(24)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxNewCheckBox)))
						.addContainerGap(184, Short.MAX_VALUE)));
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup().addGap(29)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addGap(56)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 21,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(chckbxNewCheckBox))
								.addContainerGap(115, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 196, 465, 36);
		contentPane.add(panel2);

		JLabel label_2 = new JLabel("\uC694\uC77C \uC120\uD0DD");
		label_2.setFont(new Font("±º∏≤", Font.PLAIN, 14));
		label_2.setHorizontalAlignment(SwingConstants.RIGHT);

		JCheckBox checkBox = new JCheckBox("");
		GroupLayout gl_panel2 = new GroupLayout(panel2);
		gl_panel2
				.setHorizontalGroup(
						gl_panel2.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel2.createSequentialGroup().addGap(74).addComponent(label_2)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(checkBox,
												GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
										.addContainerGap(301, Short.MAX_VALUE)));
		gl_panel2.setVerticalGroup(gl_panel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel2.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel2.createParallelGroup(Alignment.BASELINE).addComponent(label_2)
								.addComponent(checkBox, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(121, Short.MAX_VALUE)));
		panel2.setLayout(gl_panel2);

		JPanel selectTerm = new JPanel();
		selectTerm.setBounds(0, 145, 465, 41);
		contentPane.add(selectTerm);

		JLabel label_3 = new JLabel("\uC77C");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("±º∏≤", Font.PLAIN, 14));

		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_selectTerm = new GroupLayout(selectTerm);
		gl_selectTerm
				.setHorizontalGroup(gl_selectTerm.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
						gl_selectTerm.createSequentialGroup().addContainerGap(106, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addGap(73)));
		gl_selectTerm.setVerticalGroup(gl_selectTerm.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selectTerm.createSequentialGroup().addContainerGap()
						.addGroup(gl_selectTerm.createParallelGroup(Alignment.BASELINE)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(67, Short.MAX_VALUE)));
		selectTerm.setLayout(gl_selectTerm);

		JPanel selectDay = new JPanel();
		selectDay.setBounds(0, 242, 465, 81);
		contentPane.add(selectDay);

		JToggleButton toggleButton = new JToggleButton("\uC77C");
		toggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			}
		});

		JToggleButton toggleButton_1 = new JToggleButton("\uC6D4");

		JToggleButton toggleButton_2 = new JToggleButton("\uD654");

		JToggleButton toggleButton_3 = new JToggleButton("\uC218");

		JToggleButton toggleButton_4 = new JToggleButton("\uBAA9");

		JToggleButton toggleButton_5 = new JToggleButton("\uAE08");

		JToggleButton toggleButton_6 = new JToggleButton("\uD1A0");
		GroupLayout gl_selectDay = new GroupLayout(selectDay);
		gl_selectDay.setHorizontalGroup(gl_selectDay.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_selectDay.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
						.addComponent(toggleButton, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(toggleButton_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(toggleButton_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(toggleButton_3, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(toggleButton_4, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(toggleButton_5, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(toggleButton_6, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGap(21)));
		gl_selectDay.setVerticalGroup(gl_selectDay.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				gl_selectDay.createSequentialGroup().addContainerGap(30, Short.MAX_VALUE).addGroup(gl_selectDay
						.createParallelGroup(Alignment.LEADING)
						.addComponent(toggleButton_6, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(toggleButton_5, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(toggleButton_4, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(toggleButton_3, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(toggleButton_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(toggleButton_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addComponent(toggleButton, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGap(21)));
		selectDay.setLayout(gl_selectDay);

		JButton btnNewButton = new JButton("\uC800\uC7A5");
		btnNewButton.setBounds(377, 361, 76, 31);
		contentPane.add(btnNewButton);

		JButton button = new JButton("\uCDE8\uC18C");
		button.setBounds(295, 361, 76, 31);
		contentPane.add(button);
		selectTerm.setVisible(false);
		selectDay.setVisible(false);

		chckbxNewCheckBox.addItemListener(new ItemListener() { // ±‚∞£ º±≈√
			public void itemStateChanged(ItemEvent e) {

				if (e.getStateChange() == ItemEvent.SELECTED) {

					selectTerm.setVisible(true);
					checkBox.setEnabled(false);
				} else {
					selectTerm.setVisible(false);
					checkBox.setEnabled(true);
				}

			}
		});

		checkBox.addItemListener(new ItemListener() { // ø‰¿œ º±≈√
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {

					selectDay.setVisible(true);
					chckbxNewCheckBox.setEnabled(false);

				} else {
					selectDay.setVisible(false);
					chckbxNewCheckBox.setEnabled(true);

				}
			}
		});

	}
}
