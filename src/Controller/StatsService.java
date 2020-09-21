package Controller;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import Model.CompleteInfo;
import Model.CompleteInfoDao;
import Model.ExeDateInfo;
import Model.ExeDateInfoDao;
import Model.GoalInfoDao;

public class StatsService {
	GoalInfoDao goalInfoDao;
	CompleteInfoDao completeInfoDao;
	ExeDateInfoDao exeDateInfoDao;

	public StatsService() {
		goalInfoDao = GoalInfoDao.getInstance();
		completeInfoDao = CompleteInfoDao.getInstance();
		exeDateInfoDao = ExeDateInfoDao.getInstance();
	}

	// 한 주의 일요일 날짜를 전달 받아 한주의 데이터를 넘겨준다.
	public String[] loadWeeklyStats(String exeDate) {
		List<String> list = new ArrayList<String>();
		int year, month, day;
		year = Integer.parseInt(exeDate.substring(0, 4));
		month = Integer.parseInt(exeDate.substring(5, 6));
		day = Integer.parseInt(exeDate.substring(7, 8));

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(year, month, day);
		String dateInfo;
		Object[] obj;
		String[] completeKey;

		double completeQty = 0.0;

		for (int m = 0; m < 7; m++) {
			if (gc.get(GregorianCalendar.MONTH) + 1 < 10 && gc.get(GregorianCalendar.DATE) < 10) {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "0" + (gc.get(GregorianCalendar.MONTH)) + "0"
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			} else if ((gc.get(GregorianCalendar.MONTH) + 1) < 10) {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "0" + (gc.get(GregorianCalendar.MONTH)) + ""
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			} else if (gc.get(GregorianCalendar.DATE) < 10) {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "" + (gc.get(GregorianCalendar.MONTH)) + "0"
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			} else {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "" + (gc.get(GregorianCalendar.MONTH)) + ""
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			}
			if (obj != null) {
				for (int j = 0; j < obj.length; j++) {
					ExeDateInfo ed = (ExeDateInfo) obj[j];
					completeKey = ed.getExeKeyList();
					for (int k = 0; k < completeKey.length; k++) {
						String[] key = completeKey[k].split("/");
						if (key[1].equals(dateInfo)) {
							CompleteInfo cp = completeInfoDao.searchCompleteInfo(key[0]);
							if (cp != null)
								completeQty += cp.getCompleteRate();
						}
					}

				}
				completeQty = completeQty / obj.length * 100;
				list.add(dateInfo + "/" + completeQty);

			}
			gc.add(GregorianCalendar.DATE, 1);
			completeQty = 0;
		}

		String[] dateList = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				dateList[i] = list.get(i);
			}
		}
		return dateList;
	}

	public String[] loadMonthlyStats(String exeDate) {
		List<String> list = new ArrayList<String>();
		int[] daysInMonth = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int year, month, day;
		year = Integer.parseInt(exeDate.substring(0, 4));
		month = Integer.parseInt(exeDate.substring(5, 6));
		day = Integer.parseInt(exeDate.substring(7, 8));

		GregorianCalendar gc = new GregorianCalendar();
		gc.set(year, month - 1, day);

		if (gc.isLeapYear(year))
			daysInMonth[1] = 29;

		String dateInfo;
		Object[] obj;
		String[] completeKey;

		double completeQty = 0.0;
		for (int m = 0; m < daysInMonth[month - 1]; m++) {
			if (gc.get(GregorianCalendar.MONTH) + 1 < 10 && gc.get(GregorianCalendar.DATE) < 10) {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "0" + (gc.get(GregorianCalendar.MONTH) + 1) + "0"
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			} else if ((gc.get(GregorianCalendar.MONTH) + 1) < 10) {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "0" + (gc.get(GregorianCalendar.MONTH) + 1) + ""
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			} else if (gc.get(GregorianCalendar.DATE) < 10) {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "" + (gc.get(GregorianCalendar.MONTH) + 1) + "0"
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			} else {
				dateInfo = gc.get(GregorianCalendar.YEAR) + "" + (gc.get(GregorianCalendar.MONTH) + 1) + ""
						+ gc.get(GregorianCalendar.DATE);
				obj = exeDateInfoDao.searchExeInfo(dateInfo);
			}
			if (obj != null) {
				for (int j = 0; j < obj.length; j++) {
					ExeDateInfo ed = (ExeDateInfo) obj[j];
					completeKey = ed.getExeKeyList();
					for (int k = 0; k < completeKey.length; k++) {
						String[] key = completeKey[k].split("/");
						if (key[1].equals(dateInfo)) {
							CompleteInfo cp = completeInfoDao.searchCompleteInfo(key[0]);
							if (cp != null)
								completeQty += cp.getCompleteRate();
						}
					}

				}
				completeQty = completeQty / obj.length * 100;
				list.add(dateInfo + "/" + completeQty);

			}
			gc.add(GregorianCalendar.DATE, 1);
			completeQty = 0;
		}

		String[] dateList = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				dateList[i] = list.get(i);
			}
		}
		return dateList;
	}
}
