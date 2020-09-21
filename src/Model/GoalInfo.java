package Model;
public class GoalInfo {
	/** ��ǥŰ */
	private String goalKey;
	/** ��ǥ ī�װ��� */
	private String goalCategory;
	/** ��ǥ �̸� */
	private String goalName;
	/** ��ǥ��*/
	private double goalQty;
	/** ��ǥ ���� */
	private String goalUnit;
	/** �ݺ�Ű */
	private String repeatKey;
	/** �޸� ���� */
	private String memo;
	/** ���� �� */
	private String startDate;
	/** ���� �� */
	private String endDate;
	/** ���� �ð� */
	private String startTime;
	/** ���� �ð� */
	private String endTime;
	/** ��ǥ �� */
	private byte goalMonth;
	/** null parameter ������ */
	
	public GoalInfo() {
		super();
	}
	/** �����ε��� ������ */
	public GoalInfo(String goalCategory, String goalName, double goalQty, String goalUnit,
		String repeatKey, String memo, String startDate, String endDate, String startTime, String endTime, byte goalMonth) {
		this.goalCategory = goalCategory;
		this.goalName = goalName;
		this.goalQty = goalQty;
		this.goalUnit = goalUnit;
		this.repeatKey = repeatKey;
		this.memo = memo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.goalMonth = goalMonth;
		this.generateKey();
	}
	public GoalInfo(String goalKey, String goalCategory, String goalName, double goalQty, String goalUnit,
			String repeatKey, String memo, String startDate, String endDate, String startTime, String endTime, byte goalMonth) {
		this.goalKey = goalKey;
		this.goalCategory = goalCategory;
		this.goalName = goalName;
		this.goalQty = goalQty;
		this.goalUnit = goalUnit;
		this.repeatKey = repeatKey;
		this.memo = memo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.goalMonth = goalMonth;
	}
	/** goalKey�� getter */
	public String getGoalKey() {
		return goalKey;
	}
	/** goalCategory�� getter */
	public String getGoalCategory() {
		return goalCategory;
	}
	/** goalName�� getter */
	public String getGoalName() {
		return goalName;
	}
	/** goalQty�� getter */
	public double getGoalQty() {
		return goalQty;
	}
	/** goalUnit�� getter */
	public String getGoalUnit() {
		return goalUnit;
	}
	/** repeatKey�� getter */
	public String getRepeatKey() {
		return repeatKey;
	}
	/** memo�� getter */
	public String getMemo() {
		return memo;
	}
	/** startDate�� getter */
	public String getStartDate() {
		return startDate;
	}
	/** endDate�� getter */
	public String getEndDate() {
		return endDate;
	}
	/** startTime�� getter */
	public String getStartTime() {
		return startTime;
	}
	/** endTime�� getter */
	public String getEndTime() {
		return endTime;
	}
	/** goalMonth�� getter */
	public byte getGoalMonth() {
		return goalMonth;
	}
	private void setGoalKey(String goalKey) {
		this.goalKey = goalKey;
	}
	private void generateKey() {
		ManageCount manageCount = ManageCount.getInstance();
		String goalKey = goalCategory + manageCount.getGoalKeyCnt();
		manageCount.addGoalKeyCnt();
		
		this.setGoalKey(goalKey);
	}
	/** goalCategory�� setter */
	public void setGoalCategory(String goalCategory) {
		this.goalCategory = goalCategory;
	}
	/** goalName�� setter */
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	/** goalQty�� setter */
	public void setGoalQty(double goalQty) {
		this.goalQty = goalQty;
	}
	/** goalUnit�� setter */
	public void setGoalUnit(String goalUnit) {
		this.goalUnit = goalUnit;
	}
	/** repeatKey�� setter */
	public void setRepeatKey(String repeatKey) {
		this.repeatKey = repeatKey;
	}
	/** memo�� setter */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/** startDate�� setter */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/** endDate�� setter */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/** startTime�� setter */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/** EndTime�� setter */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/** goalMonth�� setter */
	public void setGoalMonth(byte goalMonth) {
		this.goalMonth = goalMonth;
	}
	public String toString() {
		return "goalCategory=" + goalCategory + ", goalName=" + goalName
				+ ", goalQty=" + goalQty + ", goalUnit=" + goalUnit + ", repeatKey=" + repeatKey + ", memo=" + memo
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", startTime=" + startTime + ", endTime="
				+ endTime + ", goalMonth=" + goalMonth + "]";
	}
}
