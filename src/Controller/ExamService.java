package Controller;

import java.util.ArrayList;
import java.util.List;

import Model.CompleteInfo;
import Model.CompleteInfoDao;
import Model.ExamCategory;
import Model.ExamCategoryDao;
import Model.ExamInfo;
import Model.ExamInfoDao;
import Model.ExeDateInfo;
import Model.ExeDateInfoDao;
import Model.GoalInfo;
import Model.GoalInfoDao;

public class ExamService {
	private GoalInfoDao goalInfoDao;
	private CompleteInfoDao completeInfoDao;
	private ExeDateInfoDao exeDateInfoDao;
	private ExamInfoDao examInfoDao;

	public ExamService() {
		goalInfoDao = GoalInfoDao.getInstance();
		completeInfoDao = CompleteInfoDao.getInstance();
		exeDateInfoDao = ExeDateInfoDao.getInstance();
		examInfoDao = ExamInfoDao.getInstance();
	}

	public Object[] loadExamInfo() {
		goalInfoDao = GoalInfoDao.getInstance();
		completeInfoDao = CompleteInfoDao.getInstance();
		examInfoDao = ExamInfoDao.getInstance();
		exeDateInfoDao = ExeDateInfoDao.getInstance();

		List<String> examInfoList = new ArrayList<String>();
		GoalInfo[] goalInfos = goalInfoDao.searchGoalCategory("시험");
		
		for(int i = 0; i < goalInfos.length; i++) {
			StringBuilder sb = new StringBuilder();
			String goalKey = goalInfos[i].getGoalKey();
			ExeDateInfo exeDateInfo = exeDateInfoDao.searchExeDateInfo(goalKey);
			String completeKeyCnt = exeDateInfo.getCompleteKeyCnt() + "";
			sb.append(completeKeyCnt + "/");			
			String[] exeKeys = exeDateInfo.getExeKeyList();
			for(int j = 0; j < exeKeys.length; j++) {
				String[] temp = exeKeys[j].split("/");
				String completeKey = temp[0];
				String exeDate = temp[1];
				CompleteInfo completeInfo = completeInfoDao.searchCompleteInfo(completeKey);
				String completeQty = completeInfo.getCompleteQty() + "";
				sb.append(exeDate + completeQty + "/");
			}
			sb.append(goalInfos[i].getStartTime() + "/");
			sb.append(goalInfos[i].getEndTime() + "/");
			sb.append(goalInfos[i].getGoalName() + "/");
			ExamInfo examInfo = examInfoDao.searchExamInfo(goalKey);
			sb.append(examInfo.getLocation() + "/");
			sb.append(goalInfos[i].getGoalQty() + "/");
			sb.append(examInfo.getExamCategory() + "/");
			sb.append(goalInfos[i].getMemo());
			examInfoList.add(sb.toString());
		}
		// 성취키개수/실행일+성취량/시작시간/종료시간/목표명/장소/목표점수/시험카테고리/메모
		return examInfoList.toArray();
	}

	public boolean addExamInfo(String goalName, String startDate, String endDate, String startTime, String endTime,
			String goalQty, String goalUnit, String memo, byte repeatTime, byte[] repeatDay, String location,
			String examCategory, boolean isDday) {
		GoalService gs = new GoalService();
		if (!gs.addGoalInfo("시험", goalName, startDate, endDate, startTime, endTime, goalQty, goalUnit, memo, repeatTime,
				repeatDay)) // 골 생성하고
		{
			return false;
		}
		GoalInfo gd = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime); // 골키를
																									// 몰라서
																									// 찾고
		if (examInfoDao.addExamInfo(gd.getGoalKey(), examCategory, location, isDday) == null) // 시험
																								// 정보
																								// 생성하기.
		{
			return false;
		}
		return true;
	}

	public boolean addExamInfo(String goalName, String startDate, String endDate, String startTime, String endTime,
			String goalQty, String goalUnit, String memo, String examCategory, String location, boolean isDday) {
		GoalService gs = new GoalService();
		if (!gs.addGoalInfo("시험", goalName, startDate, endDate, startTime, endTime, goalQty, goalUnit, memo)) // 골
																												// 생성하고
		{
			return false;
		}
		GoalInfo gd = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime); // 골키를
																									// 몰라서
																									// 찾고
		if (examInfoDao.addExamInfo(gd.getGoalKey(), examCategory, location, isDday) == null) // 시험
																								// 정보
																								// 생성하기.
		{
			return false;
		}
		return true;
	}

	public boolean deleteAllExamInfo(String goalName, String startDate, String endDate, String startTime,
			String endTime) {
		GoalService gs = new GoalService();
		if (!gs.deleteAllGoalInfo(goalName, startDate, endDate, startTime, endTime)) {
			return false;
		}
		GoalInfo gi = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
		if (gi == null) {
			return false;
		}
		if (examInfoDao.deleteExamInfo(gi.getGoalKey()) == null) {
			return false;
		}

		return true;
	}

	public boolean deleteExamInfo(String goalName, String startDate, String endDate, String startTime, String endTime,
			String currentDate) {
		GoalService gs = new GoalService();
		if (!gs.deleteGoalInfo(goalName, startDate, endDate, startTime, endTime, currentDate)) {
			return false;
		}
		GoalInfo gi = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
		if (gi == null) {
			return false;
		}
		if (examInfoDao.deleteExamInfo(gi.getGoalKey()) == null) {
			return false;
		}
		return true;
	}

	public boolean addExamScore(String goalName, String startDate, String endDate, String startTime, String endTime,
			double score, String exeDate) {
		if (score < 0) {
			return false;
		}
		GoalInfo gi = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
		if (gi == null) {
			return false;

		}
		ExeDateInfo edi = exeDateInfoDao.searchExeDateInfo(gi.getGoalKey());
		if (edi == null) {
			return false;
		}
		String completeKey = edi.searchCompleteKey(exeDate);
		CompleteInfo ci = completeInfoDao.searchCompleteInfo(completeKey);
		if (ci == null) {
			return false;
		}
		ci.setCompleteQty((int) score);

		return true;

	}

	public String[] loadExamCategory() {
		ExamCategoryDao ecd = ExamCategoryDao.getInstance();
		Object[] obj = ecd.getExamCategoryList();
		if (obj == null) {
			return null;
		}
		String[] examCategory = new String[obj.length];

		for (int i = 0; i < obj.length; i++) {
			examCategory[i] = ((ExamCategory) obj[i]).getExamCategory();
		}

		return examCategory;

	}

	public boolean addExamCategory(String examCategory) {
		ExamCategoryDao examCategoryDao = ExamCategoryDao.getInstance();
		if (examCategoryDao.addExamCategory(examCategory) == null) {
			return false;
		}
		return true;
	}

}
