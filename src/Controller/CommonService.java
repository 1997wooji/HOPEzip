package Controller;

import Model.CompleteInfoDao;
import Model.ExamCategoryDao;
import Model.ExamInfoDao;
import Model.ExeDateInfo;
import Model.ExeDateInfoDao;
import Model.GoalCategoryDao;
import Model.GoalInfo;
import Model.GoalInfoDao;
import Model.GoalUnit;
import Model.GoalUnitDao;
import Model.ManageCount;
import Model.RepeatInfoDao;
import Model.SimpleMemoInfoDao;

public class CommonService {
	private GoalInfoDao goalInfoDao;
	private GoalUnitDao goalUnitDao;
	private GoalCategoryDao goalCategoryDao;
	private CompleteInfoDao completeInfoDao;
	private ExeDateInfoDao exeDateInfoDao;
	private ExamInfoDao examInfoDao;
	private ExamCategoryDao examCategoryDao;
	private RepeatInfoDao repeatInfoDao;
	private SimpleMemoInfoDao simpleMemoInfoDao;
	private ManageCount manageCount;

	public CommonService() {
		this.goalInfoDao = GoalInfoDao.getInstance();
		this.goalUnitDao = GoalUnitDao.getInstance();
		this.goalCategoryDao = GoalCategoryDao.getInstance();
		this.completeInfoDao = CompleteInfoDao.getInstance();
		this.exeDateInfoDao = ExeDateInfoDao.getInstance();
		this.examInfoDao = ExamInfoDao.getInstance();
		this.examCategoryDao = ExamCategoryDao.getInstance();
		this.repeatInfoDao = RepeatInfoDao.getInstance();
		this.simpleMemoInfoDao = SimpleMemoInfoDao.getInstance();
		this.manageCount = ManageCount.getInstance();
	}

	public boolean loadSystem() throws Exception {
		if (!goalInfoDao.loadGoalInfoMap())
			return false;
		if (!goalUnitDao.loadGoalUnitList())
			return false;
		if (!goalCategoryDao.loadGoalCategoryMap())
			return false;
		if (!completeInfoDao.loadCompleteInfoMap())
			return false;
		if (!exeDateInfoDao.loadExeDateInfoMap())
			return false;
		if (!examInfoDao.loadExamInfoMap())
			return false;
		if (!examCategoryDao.loadExamCategoryList())
			return false;
		if (!repeatInfoDao.loadRepeatInfoMap())
			return false;
		if (!simpleMemoInfoDao.loadSimpleMemoInfoList())
			return false;
		if (!manageCount.loadCount())
			return false;
		return true;
	}

	public boolean saveSystem() throws Exception {
		if (!goalInfoDao.saveGoalInfoMap())
			return false;
		if (!goalUnitDao.saveGoalUnitList())
			return false;
		if (!goalCategoryDao.saveGoalCategoryMap())
			return false;
		if (!completeInfoDao.saveCompleteInfoMap())
			return false;
		if (!exeDateInfoDao.saveExeDateInfoMap())
			return false;
		if (!examInfoDao.saveExamInfoMap())
			return false;
		if (!examCategoryDao.saveExamCategoryList())
			return false;
		if (!repeatInfoDao.saveRepeatInfoMap())
			return false;
		if (!simpleMemoInfoDao.saveSimpleMemoInfoList())
			return false;
		if(!manageCount.saveCount())
			return false;
		return true;

	}

	public String[] loadGoalUnit() {
		Object[] objs = goalUnitDao.getGoalUnitList();
		String[] goalUnits = new String[goalUnitDao.goalUnitCnt()];
		for (int i = 0; i < goalUnitDao.goalUnitCnt(); i++) {
			GoalUnit goalUnit = (GoalUnit) objs[i];
			goalUnits[i] = goalUnit.getGoalUnit();
		}
		return goalUnits;
	}

	public boolean addGoalUnit(String goalUnit) {
		if (goalUnitDao.addGoalUnit(goalUnit) != null)
			return false;
		return true;
	}

	public String[] loadGoalCategory() {
		Object[] objs = goalCategoryDao.getGoalCategoryMap();
		String[] goalCategories = new String[goalCategoryDao.goalCategoryCnt()];
		for (int i = 0; i < goalCategoryDao.goalCategoryCnt(); i++) {
			String str = (String) objs[i];
			goalCategories[i] = str;
		}
		return goalCategories;
	}

	public boolean addGoalCategory(String goalCategory, String color) {
		if (goalCategoryDao.addGoalCategory(goalCategory, color) != null)
			return true;
		return false;
	}

	public boolean saveCompleteTime(String completeTime, String goalName, String startDate, String endDate,
			String startTime, String endTime, String currentDate) {
		GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
		String goalKey = goalInfo.getGoalKey();

		ExeDateInfo exeDateInfo = exeDateInfoDao.searchExeDateInfo(goalKey);
		String completeKey = exeDateInfo.searchCompleteKey(currentDate);


		if (completeInfoDao.updateCompleteTime(completeKey, completeTime) != null)
			return false;
		return true;
	}

	public boolean saveCompleteQty(String completeQty, String goalName, String startDate, String endDate,
			String startTime, String endTime, String currentDate) {
		GoalInfo goalInfo = goalInfoDao.searchGoalInfo(goalName, startDate, endDate, startTime, endTime);
		String goalKey = goalInfo.getGoalKey();

		ExeDateInfo exeDateInfo = exeDateInfoDao.searchExeDateInfo(goalKey);
		String completeKey = exeDateInfo.searchCompleteKey(currentDate);
		
		if (completeInfoDao.updateCompleteQty(completeKey, Integer.parseInt(completeQty)) == null) {
			return false;
		}
		completeInfoDao.searchCompleteInfo(completeKey).updateCompleteRate(goalInfo.getGoalQty());
		return true;
	}

}
