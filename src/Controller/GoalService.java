package Controller;

import Model.CompleteInfo;
import Model.CompleteInfoDao;
import Model.ExeDateInfo;
import Model.ExeDateInfoDao;
import Model.GoalInfo;
import Model.GoalInfoDao;
import Model.RepeatInfo;
import Model.RepeatInfoDao;

public class GoalService {
	private GoalInfoDao goalInfoDao;
	private CompleteInfoDao completeInfoDao;
	private ExeDateInfoDao exeDateInfoDao;
	private RepeatInfoDao repeatInfoDao;

	public GoalService() {
		this.goalInfoDao = GoalInfoDao.getInstance();
		this.completeInfoDao = CompleteInfoDao.getInstance();
		this.exeDateInfoDao = ExeDateInfoDao.getInstance();
		this.repeatInfoDao = RepeatInfoDao.getInstance();
	}

	public String[] loadGoalInfo(String currentDate) {
		Object[] objs = exeDateInfoDao.searchExeDates(currentDate);
		String[] goalInfos = new String[objs.length];

		for (int i = 0; i < objs.length; i++) {
			String goalKey = (String)objs[i];
			
			GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalKey);
			String goalCategory = goalInfo.getGoalCategory();
			String goalName = goalInfo.getGoalName();
			double goalQty = goalInfo.getGoalQty();
			String goalUnit = goalInfo.getGoalUnit();
			String memo = goalInfo.getMemo();
			String startTime = goalInfo.getStartTime();
			String endTime = goalInfo.getEndTime();
			
			ExeDateInfo exeDateInfo = exeDateInfoDao.searchExeDateInfo(goalKey);
			String completeKey = exeDateInfo.searchCompleteKey(currentDate);
			CompleteInfo completeInfo = completeInfoDao.searchCompleteInfo(completeKey);
			int completeQty = completeInfo.getCompleteQty();
			String completeTime = completeInfo.getCompleteTime();
			double completeRate = completeInfo.getCompleteRate();

			String subKey = goalKey.substring(goalCategory.length(), goalKey.length());

			goalInfos[i] = subKey + "/" + startTime + "/" + endTime + "/" + goalCategory + "/" + goalName + "/"
					+ goalQty + "/" + goalUnit + "/" + completeQty + "/" + completeRate + "/" + completeTime + "/"
					+ memo;
		}

		return goalInfos;
	}

	public boolean addGoalInfo(String goalCategory, String goalName, String startDate, String endDate, String startTime,
			String endTime, String goalQty, String goalUnit, String memo) {
		return this.addGoalInfo(goalCategory, goalName, startDate, endDate, startTime, endTime, goalQty, goalUnit, memo,
				(byte) 1, null);
	}

	public boolean addGoalInfo(String goalCategory, String goalName, String startDate, String endDate, String startTime,
			String endTime, String goalQty, String goalUnit, String memo, byte repeatTerm, byte[] repeatDay) {
		String[] exeDates = null;
		if (repeatTerm == -1) {
			exeDates = exeDateInfoDao.generateExeDate(startDate, endDate, repeatDay);
		} else {
			exeDates = exeDateInfoDao.generateExeDate(startDate, endDate, repeatTerm);
		}

		for (int i = 0; i < exeDates.length; i++) {
			String exeDate = exeDates[i];
			Object[] objs = exeDateInfoDao.searchExeDates(exeDate);
			if (objs != null) {
				for (int j = 0; j < objs.length; j++) {
					String goalKey = (String) objs[j];
					GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalKey);

					int oldStartTime = Integer.parseInt(goalInfo.getStartTime());
					int oldEndTime = Integer.parseInt(goalInfo.getEndTime());
					int newStartTime = Integer.parseInt(startTime);
					int newEndTime = Integer.parseInt(endTime);
					if (newStartTime >= oldStartTime && newEndTime <= oldEndTime)
						return false;
					if (newEndTime >= oldStartTime && newEndTime <= oldEndTime)
						return false;
					if (newStartTime >= oldStartTime && newStartTime <= oldEndTime)
						return false;
					if (newStartTime <= oldStartTime && newEndTime >= oldEndTime)
						return false;
				}
			}
		}

		RepeatInfo repeatInfo = repeatInfoDao.addRepeatInfo(repeatTerm, repeatDay);
		String repeatKey = repeatInfo.getRepeatKey();
		if (goalInfoDao.addGoalInfo(goalCategory, goalName, Double.parseDouble(goalQty), goalUnit, repeatKey, memo,
				startDate, endDate, startTime, endTime, (byte) -1, exeDates) == null)
			return false;
		return true;
	}

	public boolean addGoalInfo(String goalKey, String goalCategory, String goalName, String startDate, String endDate,
			String startTime, String endTime, String goalQty, String goalUnit, String memo, byte repeatTerm,
			byte[] repeatDay) {
		String[] exeDates = null;
		if (repeatTerm == -1)
			exeDates = exeDateInfoDao.generateExeDate(startDate, endDate, repeatDay);
		else
			exeDates = exeDateInfoDao.generateExeDate(startDate, endDate, repeatTerm);

		for (int i = 0; i < exeDates.length; i++) {
			String exeDate = exeDates[i];
			Object[] objs = exeDateInfoDao.searchExeDates(exeDate);
			for (int j = 0; j < objs.length; j++) {
				GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalKey);

				int oldStartTime = Integer.parseInt(goalInfo.getStartTime());
				int oldEndTime = Integer.parseInt(goalInfo.getEndTime());
				int newStartTime = Integer.parseInt(startTime);
				int newEndTime = Integer.parseInt(endTime);
				if (newStartTime >= oldStartTime && newEndTime <= oldEndTime)
					return false;
				if (newEndTime >= oldStartTime && newEndTime <= oldEndTime)
					return false;
				if (newStartTime >= oldStartTime && newStartTime <= oldEndTime)
					return false;
				if (newStartTime <= oldStartTime && newEndTime >= oldEndTime)
					return false;
			}
		}

		RepeatInfo repeatInfo = repeatInfoDao.addRepeatInfo(repeatTerm, null);
		String repeatKey = repeatInfo.getRepeatKey();

		if (goalInfoDao.addGoalInfo(goalKey, goalCategory, goalName, Double.parseDouble(goalQty), goalUnit, repeatKey,
				memo, startDate, endDate, startTime, endTime, (byte) -1, exeDates) == null)
			return false;
		return true;
	}

	public boolean deleteGoalInfo(String goalName, String startDate, String endDate, String startTime, String endTime,
			String currentDate) {
		GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
		if (goalInfo == null)
			return false;

		String goalKey = goalInfo.getGoalKey();

		ExeDateInfo exeDateInfo = exeDateInfoDao.searchExeDateInfo(goalKey);
		if (exeDateInfo == null)
			return false;

		String completeKey = exeDateInfo.searchCompleteKey(currentDate);
		if (completeKey == null)
			return false;

		String[] exeKey = new String[] { completeKey, currentDate };

		if (exeDateInfoDao.deleteExeKey(goalKey, exeKey) == null)
			return false;

		if (completeInfoDao.deleteCompleteInfo(completeKey) == null)
			return false;

		return true;
	}

	public boolean deleteAllGoalInfo(String goalName, String startDate, String endDate, String startTime,
			String endTime) {
		GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
		if (goalInfo == null)
			return false;

		String goalKey = goalInfo.getGoalKey();

		ExeDateInfo exeDateInfo = exeDateInfoDao.searchExeDateInfo(goalKey);
		if (exeDateInfo == null)
			return false;
		exeDateInfoDao.deleteExeDateInfo(exeDateInfo);

		String[] exeKey = exeDateInfo.getExeKeyList();
		for (int i = 0; i < exeKey.length; i++) {
			String[] exeKeys = exeKey[i].split("/");
			String completeKey = exeKeys[0];
			completeInfoDao.deleteCompleteInfo(completeKey);
		}

		if (goalInfoDao.deleteGoalInfo(goalKey) == null)
			return false;
		return true;
	}

//	public boolean updateGoalInfo(String goalName, String startDate, String endDate, String startTime, String endTime,
//			String newGoalCategory, String newGoalName, String newGoalQty, String newGoalUnit, String newMemo,
//			String newStartDate, String newEndDate, String newStartTime, String newEndTime, byte newGoalMonth,
//			byte repeatTerm, byte[] repeatDay) {
//		GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
//		goalInfoDao.deleteGoalInfo(goalInfo.getGoalKey());
//
//		return this.addGoalInfo(goalInfo.getGoalKey(), goalInfo.getGoalCategory(), newGoalName, newStartDate,
//				newEndDate, newStartTime, newEndTime, newGoalQty, newGoalUnit, newMemo, repeatTerm, repeatDay);
//	}

}
