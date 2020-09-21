package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.SimpleMemoService;
import Model.ManageCount;


public class AddMemo extends JFrame {
	private static AddMemo addMemoMember;
	static {
		addMemoMember = new AddMemo();
	}
	private JPanel contentPane;
	private JTextField memoNameTextField;

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
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 30));
		lblNewLabel.setBounds(115, 22, 200, 35);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\uBA54\uBAA8 \uBA85");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("굴림", Font.PLAIN, 20));
		label.setBounds(29, 104, 67, 24);
		contentPane.add(label);
		
		memoNameTextField = new JTextField();
		memoNameTextField.setBounds(115, 108, 307, 21);
		contentPane.add(memoNameTextField);
		memoNameTextField.setColumns(10);
		
		HomePanel hp=HomePanel.getInstance();
		SimpleMemo sm = SimpleMemo.getInstance();
		
		JButton btnSave = new JButton("\uC800\uC7A5");
		ManageCount mCount = ManageCount.getInstance();
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(e.getActionCommand()) {
				case "저장":
					String memoName = memoNameTextField.getText();
					hp.addMemoTableItem(memoName);
					System.out.println(sm.addMemoTableItem(memoName));
					SimpleMemoService ss=new SimpleMemoService();
				if(ss.addSimpleMemo(memoName)==true)
					JOptionPane.showMessageDialog(contentPane, "입력이 성공되었습니다");
				else
					JOptionPane.showMessageDialog(contentPane, "실패");
					
					dispose();
					break;
				}
			}
		});
		//mCount
		//sm.getSimpleMemo().table.getColumnModel().getColumn(3).setCellEditor(new CheckBoxCellEditor());
		btnSave.setBounds(325, 228, 97, 23);
		contentPane.add(btnSave);
		
		JButton btnBtncancel = new JButton("\uCDE8\uC18C");
		btnBtncancel.setBounds(218, 228, 97, 23);
		contentPane.add(btnBtncancel);
	}
}
