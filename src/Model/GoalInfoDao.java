package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GoalInfoDao {
	/** goalInfo를 저장하는 Map */
	private Map<String, GoalInfo> goalInfoMap;
	private static GoalInfoDao goalInfoDao;

	static {
		goalInfoDao = new GoalInfoDao();
	}

	public static GoalInfoDao getInstance() {
		return goalInfoDao;
	}

	/** null parameter 생성자 */
	public GoalInfoDao() {
		goalInfoMap = new HashMap<String, GoalInfo>();
	}

	/** 오버로딩된 생성자 */
	public GoalInfoDao(Map<String, GoalInfo> goalInfoMap) {
		if (this.goalInfoMap == null)
			this.goalInfoMap = new HashMap<String, GoalInfo>();
		setGoalInfoMap(goalInfoMap);
	}

	/** goalInfoMap의 setter */
	public void setGoalInfoMap(Map<String, GoalInfo> goalInfoMap) {
		this.goalInfoMap.clear();

		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next();
			String key = iterator.toString();
			this.goalInfoMap.put(goalInfoMap.get(key).getGoalKey(), goalInfoMap.get(key));
		}
	}

	public Object[] getGoalInfoMap() {
		List<GoalInfo> temp = new ArrayList<GoalInfo>();
		Iterator<String> iterator = this.goalInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next();
			String key = iterator.toString();
			temp.add(this.goalInfoMap.get(key));
		}
		return temp.toArray();
	}

	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		Iterator<String> keyIterator = goalInfoMap.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			GoalInfo value = goalInfoMap.get(key);
			strBuilder.append(value.toString());
		}
		return "GoalInfoDao [goalInfoMap=" + strBuilder.toString() + "]";
	}

	/** Map의 size를 구할때 사용 */
	public int goalInfoCnt() {
		return goalInfoMap.size();
	}

	/** goalInfoMap을 저장 */
	public boolean saveGoalInfoMap() throws IOException {
		File file = new File("C:/data/GoalInfo.txt");
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			GoalInfo value = goalInfoMap.get(str);

			String data = value.getGoalKey() + "/" + value.getGoalCategory() + "/" + value.getGoalName() + "/"
					+ value.getGoalQty() + "/" + value.getGoalUnit() + "/" + value.getRepeatKey() + "/"
					+ value.getMemo() + "/" + value.getStartDate() + "/" + value.getEndDate() + "/"
					+ value.getStartTime() + "/" + value.getEndTime() + "/" + value.getGoalMonth();
			pw.println(data);
		}

		fw.flush();
		fw.close();
		pw.close();
		return true;
	}

	/** goalInfoMap에 데이터를 로드 */
	public boolean loadGoalInfoMap() throws NumberFormatException, IOException, ParseException {
		File file = new File("C:/data/GoalInfo.txt");
		if (!file.exists())
			file.createNewFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		String str = null;
		while ((str = br.readLine()) != null) {
			String[] datas = str.split("/");
			addGoalInfo(datas[0], datas[1], datas[2], Double.parseDouble(datas[3]), datas[4], datas[5], datas[6],
					datas[7], datas[8], datas[9], datas[10], Byte.parseByte(datas[11]), null);
		}
		fr.close();
		br.close();
		return true;
	}

	/** 모든 정보를 파라미터로 받아 goalInfoMap에 goalInfo를 추가 */
	public GoalInfo addGoalInfo(String goalCategory, String goalName, double goalQty, String goalUnit, String repeatKey,
			String memo, String startDate, String endDate, String startTime, String endTime, byte goalMonth,
			String[] exeDates) {
		if (searchGoalInfo(goalName, startDate, endDate, startTime, endTime) == null) {
			GoalInfo goalInfo = new GoalInfo(goalCategory, goalName, goalQty, goalUnit, repeatKey, memo, startDate,
					endDate, startTime, endTime, goalMonth);

			String goalKey = goalInfo.getGoalKey();

			ExeDateInfoDao exeDateInfoDao = ExeDateInfoDao.getInstance();

			this.goalInfoMap.put(goalKey, goalInfo);

			if (exeDateInfoDao.addExeDateInfo(goalKey, exeDates) != null)
				return goalInfo;
		}
		return null;
	}

	public GoalInfo addGoalInfo(String goalKey, String goalCategory, String goalName, double goalQty, String goalUnit,
			String repeatKey, String memo, String startDate, String endDate, String startTime, String endTime,
			byte goalMonth, String[] exeDates) {
		GoalInfo goalInfo = new GoalInfo(goalKey, goalCategory, goalName, goalQty, goalUnit, repeatKey, memo, startDate,
				endDate, startTime, endTime, goalMonth);
		this.goalInfoMap.put(goalKey, goalInfo);
		return goalInfo;
	}

	/** goalInfo객체를 파라미터로 받아 goalInfoMap에 goalInfo를 추가 */
	public GoalInfo addGoalInfo(GoalInfo goalInfo) {
		if (goalInfo != null) {
			this.goalInfoMap.put(goalInfo.getGoalKey(), goalInfo);
			return goalInfo;
		}
		return null;
	}

	/** 모든 정보를 파라미터로 받아 모든 정보가 같은 객체를 goalInfoMap에서 찾아 삭제 */
	public GoalInfo deleteGoalInfo(String goalKey, String goalCategory, String goalName, double goalQty,
			String goalUnit, String repeatKey, String memo, String startDate, String endDate, String startTime,
			String endTime, byte goalMonth) {
		String[] strArray = new String[] { goalKey, goalCategory, goalName, goalUnit, repeatKey, memo, startDate,
				endDate, startTime, endTime };
		if (!errorCheck(strArray)) {
			return null;
		}

		GoalInfo goalInfo = goalInfoMap.get(goalKey);
		if (goalInfo == null) { // goalKey와 일치하는게 없는 경우
			return null;
		}

		if (goalInfo.getGoalKey().equals(goalKey) && goalInfo.getEndDate().equals(endDate)
				&& goalInfo.getGoalCategory().equals(goalCategory) && goalInfo.getGoalKey().equals(goalKey)
				&& (goalInfo.getGoalMonth() == goalMonth) && goalInfo.getGoalName().equals(goalName)
				&& (goalInfo.getGoalQty() == goalQty) && goalInfo.getGoalUnit().equals(goalUnit)
				&& goalInfo.getMemo().equals(memo) && goalInfo.getRepeatKey().equals(repeatKey)
				&& goalInfo.getStartDate().equals(startDate) && goalInfo.getStartTime().equals(startTime)) {
			goalInfoMap.remove(goalInfo.getGoalKey());
			return goalInfo;
		}
		return null;
	}

	/** goalKey를 파라미터로 받아 goalInfoMap에서 goalInfo를 삭제할 때 사용 */
	public GoalInfo deleteGoalInfo(String goalKey) {
		if (goalKey == null || goalKey == "") {
			return null;
		}
		GoalInfo flag = null;
		if (goalInfoMap.get(goalKey) == null) { // goalKey가 일치하는가 goalInfo가 없는
												// 경우
			return null;
		}
		flag = goalInfoMap.remove(goalKey);
		if (flag != null) {
			return flag;
		}
		return null;
	}

	/** 파라미터로 받은 goalInfo와 모든 정보가 같은 goalInfo를 goalInfoMap에서 찾아 삭제 */
	public GoalInfo deleteGoalInfo(GoalInfo goalInfo) {
		if (goalInfo.getGoalKey() == null) {
			return null;
		}
		GoalInfo tempInfo = goalInfoMap.get(goalInfo.getGoalKey()); // goalInfo의
																	// 객체와 맵핑되는
																	// 객체가 있는지
																	// 확인
		if (tempInfo == null) {
			return null;
		}
		if (tempInfo.getGoalKey().equals(goalInfo.getGoalKey()) && tempInfo.getEndDate().equals(goalInfo.getEndDate())
				&& tempInfo.getGoalCategory().equals(goalInfo.getGoalCategory())
				&& tempInfo.getGoalKey().equals(goalInfo.getGoalKey())
				&& (tempInfo.getGoalMonth() == goalInfo.getGoalMonth())
				&& tempInfo.getGoalName().equals(goalInfo.getGoalName())
				&& (tempInfo.getGoalQty() == goalInfo.getGoalQty())
				&& tempInfo.getGoalUnit().equals(goalInfo.getGoalUnit())
				&& tempInfo.getMemo().equals(goalInfo.getMemo())
				&& tempInfo.getRepeatKey().equals(goalInfo.getRepeatKey())
				&& tempInfo.getStartDate().equals(goalInfo.getStartDate())
				&& tempInfo.getStartTime().equals(goalInfo.getStartTime())) {
			GoalInfo deleteInfo = goalInfoMap.remove(tempInfo.getGoalKey());
			return deleteInfo;
		}
		return null;
	}

	/** goalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 새로운 정보들로 수정할 때 사용 */
	// goalQty String에서 double로 수정
	public GoalInfo updateGoalInfo(String goalKey, String newGoalCategory, String newGoalName, double newGoalQty,
			String newGoalUnit, String newRepeatKey, String newMemo, String newStartDate, String newEndDate,
			String newStartTime, String newEndTime, byte newGoalMonth) {
		String[] strArray = new String[] { goalKey, newGoalCategory, newGoalName, newGoalUnit, newRepeatKey, newMemo,
				newStartDate, newEndDate, newStartTime, newEndTime };
		if (!errorCheck(strArray)) {
			return null;
		}
		GoalInfo value = goalInfoMap.get(goalKey);
		if (value == null) { // key와 일치하는 goalInfo가 없을 경우
			return null;
		}

		value.setGoalCategory(newGoalCategory);
		value.setGoalName(newGoalName);
		value.setGoalQty(newGoalQty);
		value.setGoalUnit(newGoalUnit);
		value.setRepeatKey(newRepeatKey);
		value.setMemo(newMemo);
		value.setStartDate(newStartDate);
		value.setEndDate(newEndDate);
		value.setStartTime(newStartTime);
		value.setEndTime(newEndTime);
		value.setGoalMonth(newGoalMonth);
		// goalInfoMap.put(goalKey, value); // 이전 value가 지워지고 새로운 value로 대체

		return value;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 newGoalInfo로 수정할 때 사용 */
	public GoalInfo updateGoalInfo(String goalKey, GoalInfo newGoalInfo) {
		String[] strArray = new String[] { goalKey };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) == null) {
			return null;
		}

		goalInfoMap.put(goalKey, newGoalInfo);
		return goalInfoMap.get(goalKey);
	}

	/** golalInfoMap에서 goalInfo와 일치하는 goalInfo를 찾아 newGoalInfo로 수정할 때 사용 */
	public GoalInfo updateGoalInfo(GoalInfo goalInfo, GoalInfo newGoalInfo) {
		if (goalInfo == null || newGoalInfo == null) {
			return null;
		}

		if (goalInfoMap.get(goalInfo.getGoalKey()) == null) { // Map에 goalInfo의
																// key를 가지고 있는
																// 객체가 없으면 null
																// 리턴
			return null;
		}

		if (goalInfo.getGoalKey().equals(newGoalInfo.getGoalKey())) { // goalInfo의
																		// 키와
																		// newGoalInfo의
																		// 키가 같지
																		// 않으면
																		// null리턴
			return null;
		}

		goalInfoMap.put(goalInfo.getGoalKey(), newGoalInfo);

		return goalInfoMap.get(goalInfo.getGoalKey());
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 goalCategory를 수정할 때 사용 */
	public GoalInfo updateGoalCategory(String goalKey, String newGoalCategory) {
		String[] strArray = new String[] { goalKey, newGoalCategory };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) == null) {
			return null;
		}

		goalInfoMap.get(goalKey).setGoalCategory(newGoalCategory);

		return goalInfoMap.get(goalKey);
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 goalName을 수정할 때 사용 */
	public GoalInfo updateGoalName(String goalKey, String newGoalName) {
		String[] strArray = new String[] { goalKey, newGoalName };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setGoalName(newGoalName);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 goalQty를 수정할 때 사용 */
	public GoalInfo updateGoalQty(String goalKey, double newGoalQty) {
		if (goalKey == null || goalKey == "") {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setGoalQty(newGoalQty);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 goalUnit을 수정할 때 사용 */
	public GoalInfo updateGoalUnit(String goalKey, String newGoalUnit) {
		String[] strArray = new String[] { goalKey, newGoalUnit };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setGoalUnit(newGoalUnit);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 repeatKey를 수정할 때 사용 */
	public GoalInfo updateRepeatKey(String goalKey, String newRepeatKey) {
		String[] strArray = new String[] { goalKey, newRepeatKey };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setRepeatKey(newRepeatKey);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 memo를 수정할 때 사용 */
	public GoalInfo updateMemo(String goalKey, String newMemo) {
		String[] strArray = new String[] { goalKey, newMemo };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setMemo(newMemo);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 startDate를 수정할 때 사용 */
	public GoalInfo updateStartDate(String goalKey, String newStartDate) {
		String[] strArray = new String[] { goalKey, newStartDate };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setStartDate(newStartDate);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 endDate를 수정할 때 사용 */
	public GoalInfo updateEndDate(String goalKey, String newEndDate) {
		String[] strArray = new String[] { goalKey, newEndDate };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) == null) {
			return null;
		}

		goalInfoMap.get(goalKey).setEndDate(newEndDate);

		return goalInfoMap.get(goalKey);
	}

	/**
	 * golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 startDate와 endDate를 수정할 때 사용
	 */
	public GoalInfo updateDate(String goalKey, String newStartDate, String newEndDate) {
		String[] strArray = new String[] { goalKey, newStartDate, newEndDate };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) == null) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setStartDate(newStartDate);
			goalInfoMap.get(goalKey).setEndDate(newEndDate);

			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 startTime을 수정할 때 사용 */
	public GoalInfo updateStartTime(String goalKey, String newStartTime) {
		String[] strArray = new String[] { goalKey, newStartTime };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setStartTime(newStartTime);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 endTime을 수정할 때 사용 */
	public GoalInfo updateEndTime(String goalKey, String newEndTime) {
		String[] strArray = new String[] { goalKey, newEndTime };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setEndTime(newEndTime);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/**
	 * golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 startTime과 endTime을 수정할 때 사용
	 */
	public GoalInfo updateTime(String goalKey, String newStartTime, String newEndTime) {
		String[] strArray = new String[] { goalKey, newStartTime, newEndTime };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setStartTime(newStartTime);
			goalInfoMap.get(goalKey).setEndTime(newEndTime);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾아 goalMonth를 수정할 때 사용 */
	public GoalInfo updateGoalMonth(String goalKey, Byte newGoalMonth) {
		String[] strArray = new String[] { goalKey };
		if (!errorCheck(strArray)) {
			return null;
		}
		if (goalInfoMap.get(goalKey) != null) {
			goalInfoMap.get(goalKey).setGoalMonth(newGoalMonth);
			return goalInfoMap.get(goalKey);
		}
		return null;
	}

	/** golalInfoMap에서 goalKey와 일치하는 goalInfo를 찾을 때 사용 */
	public GoalInfo searchGoalInfo(String goalKey) {
		if (goalKey == null) {
			return null;
		}
		GoalInfo value = null;
		value = goalInfoMap.get(goalKey);

		return value;
	}

	// 단위테스트 여기까지 했음
	/** golalInfoMap에서 goalInfo와 일치하는 goalInfo를 찾을 때 사용 */
	public GoalInfo searchGoalInfo(GoalInfo goalInfo) {
		if (goalInfo == null) {
			return null;
		}
		GoalInfo value = null;
		value = goalInfoMap.get(goalInfo.getGoalKey());

		return value;
	}

	public GoalInfo searchGoalInfo(String goalName, byte goalMonth) {
		if (goalName == null || goalName == "") {
			return null;
		}
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		GoalInfo value = null;
		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getGoalName().equals(goalName) && value.getGoalMonth() == goalMonth) {
				break;
			}
		}
		return value;
	}

	/** golalInfoMap에서 goalCategory와 일치하는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchGoalCategory(String goalCategory) {
		if (goalCategory == null || goalCategory == "") {
			return null;
		}
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		GoalInfo value = null;
		GoalInfo[] values = null;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();
		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getGoalCategory().equals(goalCategory)) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	/** golalInfoMap에서 goalName과 일치하는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchGoalName(String goalName) {
		if (goalName == null || goalName == "") {
			return null;
		}
		Iterator<String> iterator = goalInfoMap.keySet().iterator();

		GoalInfo value = null;
		GoalInfo[] values = null;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();

		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getGoalName().equals(goalName)) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	public GoalInfo searchGoalInfo(String goalName, String startDate, String endDate, String startTime,
			String endTime) {
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		GoalInfo value = null;
		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getGoalName().equals(goalName) && value.getStartDate().equals(startDate)
					&& value.getEndDate().equals(endDate) && value.getStartTime().equals(startTime)
					&& value.getEndTime().equals(endTime)) {
				return value;
			}
		}
		return null;
	}

	/** golalInfoMap에서 repeatKey와 일치하는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchRepeatKey(String repeatKey) {
		if (repeatKey == null || repeatKey == "") {
			return null;
		}
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		GoalInfo value = null;
		GoalInfo[] values = null;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();

		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getRepeatKey().equals(repeatKey)) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	/** golalInfoMap에서 startDate와 일치하는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchStartDate(String startDate) {
		if (startDate == null || startDate == "") {
			return null;
		}
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		GoalInfo value = null;
		GoalInfo[] values = null;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();

		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getStartDate().equals(startDate)) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	/** golalInfoMap에서 endDate와 일치하는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchEndDate(String endDate) {
		if (endDate == null || endDate == "") {
			return null;
		}
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		GoalInfo value = null;
		GoalInfo[] values = null;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();

		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getEndDate().equals(endDate)) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	/** golalInfoMap에서 startDate와 endDate사이에 있는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchDate(String startDate, String endDate) {
		if (startDate == null || endDate == null) {
			return null;
		}
		GoalInfo[] goals;
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();
		while (iterator.hasNext()) {
			String str = iterator.next();
			GoalInfo value = goalInfoMap.get(str);

			if ((Integer.parseInt(value.getStartDate()) >= Integer.parseInt(startDate))
					&& (Integer.parseInt(value.getStartDate()) <= Integer.parseInt(endDate))
					&& (Integer.parseInt(value.getEndDate()) <= Integer.parseInt(startDate))
					&& (Integer.parseInt(value.getEndDate()) <= Integer.parseInt(endDate))) {
				tempList.add(value);
			}
		}
		goals = new GoalInfo[tempList.size()];
		int i = 0;
		for (GoalInfo info : tempList) {
			goals[i] = info;
			++i;
		}
		return goals;
	}

	/** golalInfoMap에서 startTime이후에 시작되는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchStartTime(String startTime) {
		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		if (startTime == null || startTime == "") {
			return null;
		}

		GoalInfo value = null;
		GoalInfo[] values = null;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();

		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (Integer.parseInt(value.getStartTime()) <= Integer.parseInt(startTime)) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	/** golalInfoMap에서 endTime이전까지 끝내야 하는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchEndTime(String endTime) {
		if (endTime == null || endTime == "") {
			return null;
		}

		List<GoalInfo> tempList = new ArrayList<GoalInfo>();
		GoalInfo value = null;
		GoalInfo[] values = null;

		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (Integer.parseInt(value.getEndTime()) <= Integer.parseInt(endTime)) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	// ##################
	/** golalInfoMap에서 시작 종료 시간이 startTime~endTime사이에 있는 goalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchTime(String startTime, String endTime) {
		String[] strArray = new String[] { startTime, endTime };
		if (!errorCheck(strArray)) {
			return null;
		}

		GoalInfo[] goals;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();
		Iterator<String> iterator = goalInfoMap.keySet().iterator();

		while (iterator.hasNext()) {
			String str = iterator.next();
			GoalInfo value = goalInfoMap.get(str);
			if ((Integer.parseInt(value.getStartTime()) >= Integer.parseInt(startTime))
					&& (Integer.parseInt(value.getStartTime()) <= Integer.parseInt(endTime))
					&& (Integer.parseInt(value.getEndTime()) >= Integer.parseInt(startTime))
					&& (Integer.parseInt(value.getEndTime()) <= Integer.parseInt(endTime))) {
				tempList.add(value);
			}
		}
		goals = new GoalInfo[tempList.size()];
		int i = 0;
		for (GoalInfo info : tempList) {
			goals[i] = info;
			++i;
		}
		return goals;
	}

	/**
	 * golalInfoMap에서 startDate이후 endDate이전까지 일정 중 startTime과 endTime사이에 해야하는
	 * 일정의 goalInfo들을 찾을 때 사용
	 */
	public GoalInfo[] searchDateTime(String startDate, String endDate, String startTime, String endTime) {
		String[] strArray = new String[] { startDate, endDate, startTime, endTime };
		if (!errorCheck(strArray)) {
			return null;
		}

		GoalInfo[] goals;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();

		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			GoalInfo value = goalInfoMap.get(str);
			if ((Integer.parseInt(value.getStartTime()) >= Integer.parseInt(startTime))
					&& (Integer.parseInt(value.getStartTime()) <= Integer.parseInt(endTime))
					&& (Integer.parseInt(value.getEndTime()) >= Integer.parseInt(startTime))
					&& (Integer.parseInt(value.getEndTime()) <= Integer.parseInt(endTime))
					&& (Integer.parseInt(value.getStartTime()) >= Integer.parseInt(startTime))
					&& (Integer.parseInt(value.getStartTime()) <= Integer.parseInt(endTime))
					&& (Integer.parseInt(value.getEndTime()) >= Integer.parseInt(startTime))
					&& (Integer.parseInt(value.getEndTime()) <= Integer.parseInt(endTime))) {
				tempList.add(value);
			}
		}
		goals = new GoalInfo[tempList.size()];
		int i = 0;
		for (GoalInfo info : tempList) {
			goals[i] = info;
			++i;
		}
		return goals;
	}

	/** 목표 달이 일치하는 GoalInfo들을 찾을 때 사용 */
	public GoalInfo[] searchGoalMonth(Byte goalMonth) {
		if (goalMonth <= 0) {
			return null;
		}

		GoalInfo value = null;
		GoalInfo[] values = null;
		List<GoalInfo> tempList = new ArrayList<GoalInfo>();

		Iterator<String> iterator = goalInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			value = goalInfoMap.get(str);
			if (value.getGoalMonth() == goalMonth) {
				tempList.add(value);
			}
			values = new GoalInfo[tempList.size()];
			int i = 0;
			for (GoalInfo info : tempList) {
				values[i] = info;
				++i;
			}
		}
		return values;
	}

	/** String[]에 null이나 ""이 있나 확인 */
	public boolean errorCheck(String[] check) {
		for (int i = 0; i < check.length; i++) {
			if (check[i] == null || check[i] == "") {
				return false;
			}
		}
		return true;
	}

}