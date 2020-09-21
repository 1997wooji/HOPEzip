import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddMemo extends JFrame {
	private static AddMemo addMemoMember;
	static {
		addMemoMember = new AddMemo();
	}
	private JPanel contentPane;
	private JTextField textField;

	public static AddMemo getInstance() {
		return addMemoMember;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMemo frame = addMemoMember;
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
	public AddMemo() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uAC04\uB2E8 \uBA54\uBAA8 \uCD94\uAC00");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("±¼¸²", Font.PLAIN, 30));
		lblNewLabel.setBounds(115, 22, 200, 35);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\uBA54\uBAA8 \uBA85");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		label.setBounds(29, 104, 67, 24);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(115, 108, 307, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\uD655\uC778");
		btnNewButton.setBounds(325, 228, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("\uCDE8\uC18C");
		button.setBounds(218, 228, 97, 23);
		contentPane.add(button);
	}
}
