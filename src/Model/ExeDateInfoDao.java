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
	/** 실행일을 저장하는 Map */
	private Map<String, ExeDateInfo> exeDateInfoMap;
	private static ExeDateInfoDao exeDateInfoDao;
	static {
		exeDateInfoDao = new ExeDateInfoDao();
	}

	public static ExeDateInfoDao getInstance() {
		return exeDateInfoDao;
	}

	/** null parameter 생성자 */
	public ExeDateInfoDao() {
		exeDateInfoMap = new HashMap<String, ExeDateInfo>();
	}

	/** 오버로딩된 생성자 */
	public ExeDateInfoDao(Map<String, ExeDateInfo> exeDateInfoMap) {
		setExeDateInfoMap(exeDateInfoMap);
	}

	/** exeDateInfoMap의 setter */
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

	/** 필드들의 값을 String으로 반환할 수 있는 메소드 */
	public String toString() {
		return "ExeDateInfoDao [exeDateInfoMap=" + exeDateInfoMap + "]";
	}

	/** exeDateInfoMap의 size를 구할때 사용 */
	public int exeDateInfoCnt() {
		return exeDateInfoMap.size();
	}

	/** exeDateInfoMap를 저장할 때 사용 */
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

	/** 파일에서 정보를 가져와 exeDateInfoMap에 로드할 때 사용 */
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

	/** exeDateInfoMap에 ExeDateInfo를 추가할 때 사용 */
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

	/** exeDateInfoMap에 ExeDateInfo로 데이터를 추가할 때 사용 */
	public ExeDateInfo addExeDateInfo(ExeDateInfo exeDateInfo) {
		if (exeDateInfo == null)
			return null;
		if (exeDateInfoMap.containsKey(exeDateInfo.getGoalKey()))
			return null;
		if (exeDateInfoMap.put(exeDateInfo.getGoalKey(), exeDateInfo) != null)
			return null;
		return exeDateInfo;
	}

	/** exeDateInfoMap에서 데이터를 삭제할 때 사용 */
	public ExeDateInfo deleteExeDateInfo(String goalKey, String completeKey, String exeDate) {
		if (goalKey == null || completeKey == null || exeDate == null)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		String[] exeKey = { completeKey, exeDate };
		exeDateInfoMap.get(goalKey).deleteExeKey(exeKey);
		return exeDateInfoMap.get(goalKey);
	}

	/** exeDateInfoMap에서 데이터를 삭제할 때 사용 */
	public ExeDateInfo deleteExeDateInfo(String goalKey) {
		if (goalKey == null)
			return null;
		if (!exeDateInfoMap.containsKey(goalKey))
			return null;
		return exeDateInfoMap.remove(goalKey);

	}

	/** exeDateInfoMap에서 데이터를 삭제할 때 사용 */
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

	/** exeDateInfoMap에서 데이터를 수정할 때 사용 */
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

	/** exeDateInfoMap에서 데이터를 수정할 때 사용 */
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

	/** exeDateInfoMap에서 데이터를 수정할 때 사용 */
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

	/** exeDateInfoMap에서 데이터를 찾을 때 사용 */
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

	/** exeDateInfoMap에서 goalKey데이터를 찾을 때 사용 */
	public ExeDateInfo searchExeDateInfo(String goalKey) {
		if (goalKey == null)
			return null;
		return exeDateInfoMap.get(goalKey);
	}

	/** exeDateInfoMap에서 exeDateInfo로 데이터를 찾을 때 사용 */
	public ExeDateInfo searchExeDateInfo(ExeDateInfo exeDateInfo) {
		if (exeDateInfo == null)
			return null;
		if (!exeDateInfoMap.containsValue(exeDateInfo))
			return null;
		return exeDateInfoMap.get(exeDateInfo.getGoalKey());
	}

	/** exeDateInfoMap에서 exeDate로 데이터를 찾을 때 사용 */
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

	/** exeDateInfoMap에서 completeKey로 데이터를 찾을 때 사용 */
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
		// DAY_OF_WEEK 일(1) 월(2) 화(3) 수(4) 목(5) 금(6) 토(7)

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
		// 현재날짜 받아서 month만 -1 (month만 인덱스임)
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
