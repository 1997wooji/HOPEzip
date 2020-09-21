package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HopeCalendar_2 extends JFrame implements ActionListener {
	private static HopeCalendar_2 hopeCalendarMember;
	static {
		hopeCalendarMember = new HopeCalendar_2();
	}
	private JPanel contentPane;
	private GregorianCalendar cal, cal2;
	private JComboBox monthChoice; // 월 선택
	private JComboBox yearChoice; // 년도 선택
	private JButton previousButton; // 이전월
	private JButton nextButton; // 다음 달
	private int currentMonth; // 현재월
	private int currentYear; // 현재연도
	private int currentDay; // 현재 요일
	private String currentDayString; //
	private String currentDateString; // 현재 날자
	private Font helvB16 = new Font("바탕체", Font.PLAIN, 12);
	private Font btnF = new Font("바탕체", Font.BOLD, 6);
	private String days[] = { "일", "월", "화", "수", "목", "금", "토" };
	private String months[] = { "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" };
	private int daysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private JButton[][] monthButtons = new JButton[6][7]; // 날자 선택 버튼
	private JLabel[] dayLabels = new JLabel[7]; // 요일 라벨
	private int day;

	public static HopeCalendar_2 getInstance() {
		return hopeCalendarMember;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HopeCalendar_2 frame = hopeCalendarMember;
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setCurrentDateString(int year, int month, int day) {
		if (month < 10 && day < 10) {
			this.currentDateString = year + ".0" + month + ".0" + day;
		} else if (month < 10) {
			this.currentDateString = year + ".0" + month + "." + day;
		} else if (day < 10) {
			this.currentDateString = year + "." + month + ".0" + day;
		} else {
			this.currentDateString = year + "." + month + "." + day;
		}
	}

	public String getCurrentDateString() {
		return this.currentDateString;
	}

	public HopeCalendar_2() {
		setForeground(new Color(255, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(272, 156, 400, 500);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		// setContentPane(contentPane);

		cal = new GregorianCalendar();
		LocalDateTime localDateTime = LocalDateTime.now();
		int todayYear = localDateTime.getYear();
		int todayMonth = localDateTime.getMonthValue();
		int today = localDateTime.getDayOfMonth();

		cal.set(todayYear, todayMonth - 1, today);
		this.setCurrentDateString(todayYear, todayMonth, today);

		currentYear = cal.get(Calendar.YEAR);
		currentMonth = cal.get(Calendar.MONTH);

		JPanel main = new JPanel();
		main.setLayout(new BorderLayout());
		contentPane.add(main);

		JPanel up = new JPanel();
		up.setLayout(new GridLayout(1, 4));

		yearChoice = new JComboBox(); // 년도 선택 콤보박스 생성
		for (int i = 2000; i < 2050; i++) {
			yearChoice.addItem(i);
		}
		yearChoice.setSelectedIndex(currentYear - 2000);
		up.add(yearChoice);
		yearChoice.addActionListener(this);

		monthChoice = new JComboBox(); // 월 선택 콤보박스 생성
		for (int i = 0; i < months.length; i++) {
			monthChoice.addItem(months[i]);
		}
		monthChoice.setSelectedIndex(currentMonth);
		up.add(monthChoice);
		monthChoice.addActionListener(this);
		main.add("North", up);

		// 하단 달력 버튼
		JPanel down = new JPanel();
		down.setLayout(new BorderLayout());

		// dyap패널에 요일추가
		JPanel dayp = new JPanel();
		dayp.setLayout(new GridLayout(1, 7));

		for (int k = 0; k < days.length; k++) {
			dayLabels[k] = new JLabel(days[k], JLabel.CENTER);
			dayLabels[k].setFont(helvB16);
			dayp.add(dayLabels[k]);
		}
		down.add("North", dayp);

		JPanel datep = new JPanel();
		datep.setLayout(new GridLayout(6, 7));

		ActionListener dateSetter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = e.getActionCommand();
				if (!num.equals("")) {
					day = Integer.parseInt(num);
					AddGoal ad = AddGoal.getInstance();
					if (currentMonth + 1 < 10 && day < 10) {
						ad.setEndDateLabel((currentYear) + ".0" + (currentMonth + 1) + ".0" + day);
					} else if (currentMonth + 1 < 10) {
						ad.setEndDateLabel((currentYear) + ".0" + (currentMonth + 1) + "." + day);
					} else if (day < 10) {
						ad.setEndDateLabel((currentYear) + "." + (currentMonth + 1) + ".0" + day);
					} else {
						ad.setEndDateLabel((currentYear) + "." + (currentMonth + 1) + "." + day);
					}

					dispose();
				}
			}
		};

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				monthButtons[i][j] = new JButton("");
				monthButtons[i][j].setFont(btnF);
				datep.add(monthButtons[i][j]);
				monthButtons[i][j].addActionListener(dateSetter);
			}
		}

		down.add("Center", datep);
		main.add("Center", down);
		add(main);
		display_cal();
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public int getDay() {
		return day;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public void display_cal() {
		cal2 = new GregorianCalendar(currentYear, currentMonth, 1);
		currentDay = cal2.get(Calendar.DAY_OF_WEEK);// 현재 달의 첫째 요일 알아내기

		// System.out
		// .println("currentYear" + currentYear + "currentDay: " + currentDay +
		// " currentMonth: " + currentMonth);

		int MaxDate = daysInMonth[currentMonth];// 현재 달의 마지막 날짜를 알아내기
		int date_Now = 1;
		boolean ok = true;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				if (date_Now == 1 && j + 1 < currentDay) {
					monthButtons[i][j].setText("");
					monthButtons[i][j].setEnabled(false);
				} else if (date_Now > MaxDate) {
					monthButtons[i][j].setText("");
					monthButtons[i][j].setEnabled(false);
				} else {
					String today = "" + date_Now;
					monthButtons[i][j].setText(today);
					monthButtons[i][j].setEnabled(true);
					date_Now++;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stu

		int selectYear = yearChoice.getSelectedIndex();// 선택한 년도 인덱스 알아내기
		yearChoice.setSelectedIndex(selectYear);
		cal.set(Calendar.YEAR, (selectYear + 2000));// 선택한 년도를 현재 년도로

		currentYear = cal.get(Calendar.YEAR);
		if (cal.isLeapYear(selectYear + 2000)) {
			daysInMonth[1] = 29;
		}

		int selectMonth = monthChoice.getSelectedIndex();// 선택한 월의 인덱스 알아내기
		monthChoice.setSelectedIndex(selectMonth);
		cal.set(Calendar.MONTH, selectMonth); // 선택한 월을 현재 달로
		currentMonth = cal.get(Calendar.MONTH);
		display_cal();
	}

}
