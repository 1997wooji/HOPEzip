package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExeDateInfoDao {
	/** �������� �����ϴ� Map */
	private Map<String, ExeDateInfo> exeDateInfoMap;
	private static ExeDateInfoDao exeDateInfoDao;
	static {
		exeDateInfoDao = new ExeDateInfoDao();
	}

	public static ExeDateInfoDao getInstance() {
		return exeDateInfoDao;
	}

	/** null parameter ������ */
	public ExeDateInfoDao() {
		exeDateInfoMap = new HashMap<String, ExeDateInfo>();
	}

	/** �����ε��� ������ */
	public ExeDateInfoDao(Map<String, ExeDateInfo> exeDateInfoMap) {
		setExeDateInfoMap(exeDateInfoMap);
	}

	/** exeDateInfoMap�� setter */
	public void setExeDateInfoMap(Map<String, ExeDateInfo> exeDateInfoMap) {
		if (exeDateInfoMap != null)
			this.exeDateInfoMap = exeDateInfoMap;
	}

	public Object[] getExeDateInfoMap() {
		List<ExeDateInfo> list = new ArrayList<ExeDateInfo>();
		Iterator<String> iterator = exeDateInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			list.add(exeDateInfoMap.get(iterator.next()));
		}
		return list.toArray();
	}

	/** �ʵ���� ���� String���� ��ȯ�� �� �ִ� �޼ҵ� */
	public String toString() {
		return "ExeDateInfoDao [exeDateInfoMap=" + exeDateInfoMap + "]";
	}

	/** exeDateInfoMap�� size�� ���Ҷ� ��� */
	public int exeDateInfoCnt() {
		return exeDateInfoMap.size();
	}

	/** exeDateInfoMap�� ������ �� ��� */
	public boolean saveExeDateInfoMap() throws IOException {
		File file = new File("C:/data/ExeDateInfo.txt");
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		if (fw == null || pw == null) {
			pw.close();
			fw.close();
			return false;
		}

		Iterator<ExeDateInfo> iterator = exeDateInfoMap.values().iterator();
		ExeDateInfo exeDateInfo = null;
		while (iterator.hasNext()) {
			exeDateInfo = iterator.next();
			pw.println(exeDateInfo.getCompleteKeyCnt() + "/" + exeDateInfo.getGoalKey());
			for (int i = 0; i < exeDateInfo.getCompleteKeyCnt(); i++) {
				pw.println(exeDateInfo.getExeKeyList()[i]);
			}
		}
		pw.close();
		fw.close();
		return true;
	}

	/** ���Ͽ��� ������ ������ exeDateInfoMap�� �ε��� �� ��� */
	public boolean loadExeDateInfoMap() throws IOException {
		File file = new File("C:/data/ExeDateInfo.txt");
		if (!file.exists())
			file.createNewFile();

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		if (br == null || fr == null) {
			br.close();
			fr.close();
			return false;
		}

		String str = null;
		String[] result = null;
		int cnt = 0;

		while ((str = br.readLine()) != null) {
			if (str.equals(""))
				continue;
			result = str.split("/");
			cnt = Integer.parseInt(result[0]);
			String goalKey = result[1];
			String[] exeKeys = new String[cnt];
			for (int i = 0; i < cnt; i++) {
				str = br.readLine();
				exeKeys[i] = str;
			}
			ExeDateInfo exeDateInfo = new ExeDateInfo(cnt, goalKey, exeKeys);
			this.exeDateInfoMap.put(goalKey, exeDateInfo);
		}
		br.close();
		fr.close();
		return true;
	}

	/** exeDateInfoMap�� ExeDateInfo�� �߰��� �� ��� */
	public ExeDateInfo addExeDateInfo(String goalKey, String completeKey, String exeDate) {
		if (goalKey == null || completeKey == null || exeDate == null)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		String[] exeKey = { completeKey, exeDate };
		if (exeDateInfoMap.get(goalKey).addExeKey(exeKey) == null)
			return null;
		return exeDateInfoMap.get(goalKey);
	}

	public ExeDateInfo addExeDateInfo(String goalKey, String[] exeDates) {
		if (exeDateInfoMap.get(goalKey) != null) {
			return null;
		}
		exeDateInfoMap.put(goalKey, new ExeDateInfo(goalKey, exeDates));
		return exeDateInfoMap.get(goalKey);
	}

	public ExeDateInfo addExeDateInfo(int completeKeyCnt, String goalKey, String[] completeKeys, String[] exeDates) {

		if (goalKey == null || completeKeys == null || exeDates == null || completeKeyCnt < 0)
			return null;
		if (completeKeyCnt != completeKeys.length || completeKeyCnt != exeDates.length)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;

		String[] exeKey = new String[2];
		List<String> exeDateList = new ArrayList<String>();
		for (int i = 0; i < completeKeyCnt; i++) {
			exeKey[0] = completeKeys[i];
			exeKey[1] = exeDates[i];
			exeDateList.add(exeKey[0] + exeKey[1]);
		}

		String[] exeKeys = new String[exeDateList.size()];
		for (int i = 0; i < exeDateList.size(); i++) {
			exeKeys[i] = exeDateList.get(i);
		}
		ExeDateInfo exeDateInfo = new ExeDateInfo(goalKey, exeKeys);
		if (searchExeDateInfo(goalKey) == null) {
			return null;
		}
		exeDateInfoMap.put(goalKey, exeDateInfo);

		return exeDateInfo;

	}

	/** exeDateInfoMap�� ExeDateInfo�� �����͸� �߰��� �� ��� */
	public ExeDateInfo addExeDateInfo(ExeDateInfo exeDateInfo) {
		if (exeDateInfo == null)
			return null;
		if (exeDateInfoMap.containsKey(exeDateInfo.getGoalKey()))
			return null;
		if (exeDateInfoMap.put(exeDateInfo.getGoalKey(), exeDateInfo) != null)
			return null;
		return exeDateInfo;
	}

	/** exeDateInfoMap���� �����͸� ������ �� ��� */
	public ExeDateInfo deleteExeDateInfo(String goalKey, String completeKey, String exeDate) {
		if (goalKey == null || completeKey == null || exeDate == null)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		String[] exeKey = { completeKey, exeDate };
		exeDateInfoMap.get(goalKey).deleteExeKey(exeKey);
		return exeDateInfoMap.get(goalKey);
	}

	/** exeDateInfoMap���� �����͸� ������ �� ��� */
	public ExeDateInfo deleteExeDateInfo(String goalKey) {
		if (goalKey == null)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		return exeDateInfoMap.remove(goalKey);

	}

	/** exeDateInfoMap���� �����͸� ������ �� ��� */
	public ExeDateInfo deleteExeDateInfo(ExeDateInfo exeDateInfo) {
		if (exeDateInfo == null)
			return null;
		if (!exeDateInfoMap.containsValue(exeDateInfo))
			return null;
		return exeDateInfoMap.remove(exeDateInfo.getGoalKey());
	}

	public String[] deleteExeKey(String goalKey, String[] exeKey) {
		ExeDateInfo exeDateInfo = this.exeDateInfoMap.get(goalKey);
		if (exeDateInfo.deleteExeKey(exeKey) == null)
			return null;
		return exeKey;
	}

	/** exeDateInfoMap���� �����͸� ������ �� ��� */
	public ExeDateInfo updateExeDateInfo(String goalKey, ExeDateInfo newExeDateInfo) {
		if (goalKey == null || newExeDateInfo == null)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		if (newExeDateInfo.getGoalKey() != goalKey)
			return null;
		if (exeDateInfoMap.replace(goalKey, newExeDateInfo) == null)
			return null;
		return newExeDateInfo;
	}

	/** exeDateInfoMap���� �����͸� ������ �� ��� */
	public ExeDateInfo updateExeDateInfo(ExeDateInfo exeDateInfo, ExeDateInfo newExeDateInfo) {
		if (exeDateInfo == null || newExeDateInfo == null)
			return null;
		if (!exeDateInfoMap.containsValue(exeDateInfo))
			return null;
		if (exeDateInfo.getGoalKey() != newExeDateInfo.getGoalKey())
			return null;
		if (exeDateInfoMap.replace(exeDateInfo.getGoalKey(), newExeDateInfo) == null)
			return null;
		return newExeDateInfo;
	}

	/** exeDateInfoMap���� �����͸� ������ �� ��� */
	public ExeDateInfo updateExeDate(String goalKey, String completeKey, String newExeDate) {
		if (goalKey == null || completeKey == null || newExeDate == null)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		String[] exeKey = { completeKey, newExeDate };
		if (exeDateInfoMap.get(goalKey).updateExeKey(completeKey, exeKey) == null)
			return null;
		return exeDateInfoMap.get(goalKey);
	}

	/** exeDateInfoMap���� �����͸� ã�� �� ��� */
	public ExeDateInfo searchExeDateInfo(int completeKeyCnt, String goalKey, String completeKey, String exeDate) {
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		if (exeDateInfoMap.get(goalKey).getCompleteKeyCnt() != completeKeyCnt)
			return null;
		String[] exeKey = { completeKey, exeDate };
		if (exeDateInfoMap.get(goalKey).searchExeKey(exeKey) == null)
			return null;
		return exeDateInfoMap.get(goalKey);
	}

	/** exeDateInfoMap���� goalKey�����͸� ã�� �� ��� */
	public ExeDateInfo searchExeDateInfo(String goalKey) {
		if (goalKey == null)
			return null;
		return exeDateInfoMap.get(goalKey);
	}

	/** exeDateInfoMap���� exeDateInfo�� �����͸� ã�� �� ��� */
	public ExeDateInfo searchExeDateInfo(ExeDateInfo exeDateInfo) {
		if (exeDateInfo == null)
			return null;
		if (!exeDateInfoMap.containsValue(exeDateInfo))
			return null;
		return exeDateInfoMap.get(exeDateInfo.getGoalKey());
	}

	/** exeDateInfoMap���� exeDate�� �����͸� ã�� �� ��� */
	public Object[] searchExeDates(String exeDate) {
		Iterator<String> iterator = this.exeDateInfoMap.keySet().iterator();
		List<String> temp = new ArrayList<String>();
		while (iterator.hasNext()) {
			String key = iterator.next();
			ExeDateInfo exeDateInfo = this.exeDateInfoMap.get(key);
			String str = exeDateInfo.searchExeKey(exeDate);
			if (str != null) {
				temp.add(str);
			}
		}
		return temp.toArray();

	}

	public Object[] searchExeInfo(String exeDate) {
		if (exeDate == null)
			return null;
		ExeDateInfo searchData = null;

		List<ExeDateInfo> list = new ArrayList<ExeDateInfo>();
		Iterator<ExeDateInfo> iterator = exeDateInfoMap.values().iterator();
		while (iterator.hasNext()) {
			searchData = iterator.next();
			if (searchData.searchExeDate(exeDate) != null) {
				list.add(searchData);
			}

		}
		if (list.size() == 0) {
			return null;
		}

		return list.toArray();
	}

	/** exeDateInfoMap���� completeKey�� �����͸� ã�� �� ��� */
	public Object[] searchCompleteKey(String completeKey) throws IOException {
		if (completeKey == null)
			return null;
		ExeDateInfo searchData = null;
		List<ExeDateInfo> list = new ArrayList<ExeDateInfo>();
		Iterator<ExeDateInfo> iterator = exeDateInfoMap.values().iterator();
		while (iterator.hasNext()) {
			searchData = iterator.next();
			if (searchData.searchCompleteKey(completeKey) != null)
				list.add(searchData);
		}
		if (list.size() == 0)
			return null;
		return list.toArray();
	}

	public String[] generateExeDate(String startDate, String endDate, byte repeatTerm) {
		int[] startDateInt = new int[3];
		int[] endDateInt = new int[3];

		List<String> exeDates = new ArrayList<String>();
		startDateInt[0] = Integer.parseInt(startDate.substring(0, 4));
		startDateInt[1] = Integer.parseInt(startDate.substring(5, 7));
		startDateInt[2] = Integer.parseInt(startDate.substring(8, 10));

		endDateInt[0] = Integer.parseInt(endDate.substring(0, 4));
		endDateInt[1] = Integer.parseInt(endDate.substring(5, 7));
		endDateInt[2] = Integer.parseInt(endDate.substring(8, 10));

		GregorianCalendar c = new GregorianCalendar();
		c.set(startDateInt[0], startDateInt[1] - 1, startDateInt[2]);

		while ((c.get(GregorianCalendar.YEAR) * 1000 + (c.get(GregorianCalendar.MONTH) + 1) * 100
				+ c.get(GregorianCalendar.DATE)) <= (endDateInt[0] * 1000 + endDateInt[1] * 100 + endDateInt[2])) {
			StringBuilder sb = new StringBuilder();

			sb.append(c.get(GregorianCalendar.YEAR));

			if ((c.get(GregorianCalendar.MONTH) + 1) < 10)
				sb.append("0" + (c.get(GregorianCalendar.MONTH) + 1));
			else
				sb.append((c.get(GregorianCalendar.MONTH) + 1));

			if ((c.get(GregorianCalendar.DATE)) < 10)
				sb.append("0" + c.get(GregorianCalendar.DATE));
			else
				sb.append(c.get(GregorianCalendar.DATE));

			exeDates.add(sb.toString());
			c.add(GregorianCalendar.DATE, repeatTerm);
		}
		String[] exeDate = new String[exeDates.size()];
		for (int i = 0; i < exeDates.size(); i++) {
			exeDate[i] = exeDates.get(i);
		}
		return exeDate;
	}

	public String[] generateExeDate(String startDate, String endDate, byte[] repeatDay) {

		GregorianCalendar c = new GregorianCalendar();
		List<String> exeDates = new ArrayList<String>();
		Set<Integer> repeatDayInt = new HashSet<Integer>();

		int[] startDateInt = new int[3];
		int[] endDateInt = new int[3];
		// DAY_OF_WEEK ��(1) ��(2) ȭ(3) ��(4) ��(5) ��(6) ��(7)

		startDateInt[0] = Integer.parseInt(startDate.substring(0, 4));
		startDateInt[1] = Integer.parseInt(startDate.substring(4, 6));
		startDateInt[2] = Integer.parseInt(startDate.substring(6, 8));

		endDateInt[0] = Integer.parseInt(endDate.substring(0, 4));
		endDateInt[1] = Integer.parseInt(endDate.substring(4, 6));
		endDateInt[2] = Integer.parseInt(endDate.substring(6, 8));

		for (int i = 0; i < 7; i++) {
			if (repeatDay[i] == 1)
				repeatDayInt.add(i + 1);

		}

		c.set(startDateInt[0], startDateInt[1] - 1, startDateInt[2]);
		// ���糯¥ �޾Ƽ� month�� -1 (month�� �ε�����)
		while ((c.get(GregorianCalendar.YEAR) * 1000 + (c.get(GregorianCalendar.MONTH) + 1) * 100
				+ c.get(GregorianCalendar.DATE)) <= (endDateInt[0] * 1000 + endDateInt[1] * 100 + endDateInt[2])) {

			if (repeatDayInt.contains(c.get(GregorianCalendar.DAY_OF_WEEK))) {
				StringBuilder sb = new StringBuilder();

				sb.append(c.get(GregorianCalendar.YEAR));

				if ((c.get(GregorianCalendar.MONTH) + 1) < 10)
					sb.append("0" + (c.get(GregorianCalendar.MONTH) + 1));
				else
					sb.append((c.get(GregorianCalendar.MONTH) + 1));

				if ((c.get(GregorianCalendar.DATE)) < 10)
					sb.append("0" + c.get(GregorianCalendar.DATE));
				else
					sb.append(c.get(GregorianCalendar.DATE));

				exeDates.add(sb.toString());
			}

			c.add(GregorianCalendar.DATE, 1);

		}
		Object[] arr = exeDates.toArray();
		String[] exeDateString = new String[exeDates.size()];
		for (int i = 0; i < exeDates.size(); i++) {
			exeDateString[i] = (String) arr[i];
		}

		return exeDateString;
	}

}
