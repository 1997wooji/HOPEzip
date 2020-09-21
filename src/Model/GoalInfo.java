package Model;
public class GoalInfo {
	/** 목표키 */
	private String goalKey;
	/** 목표 카테고리명 */
	private String goalCategory;
	/** 목표 이름 */
	private String goalName;
	/** 목표량*/
	private double goalQty;
	/** 목표 단위 */
	private String goalUnit;
	/** 반복키 */
	private String repeatKey;
	/** 메모 내용 */
	private String memo;
	/** 시작 일 */
	private String startDate;
	/** 종료 일 */
	private String endDate;
	/** 시작 시간 */
	private String startTime;
	/** 종료 시간 */
	private String endTime;
	/** 목표 달 */
	private byte goalMonth;
	/** null parameter 생성자 */
	
	public GoalInfo() {
		super();
	}
	/** 오버로딩된 생성자 */
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
	/** goalKey의 getter */
	public String getGoalKey() {
		return goalKey;
	}
	/** goalCategory의 getter */
	public String getGoalCategory() {
		return goalCategory;
	}
	/** goalName의 getter */
	public String getGoalName() {
		return goalName;
	}
	/** goalQty의 getter */
	public double getGoalQty() {
		return goalQty;
	}
	/** goalUnit의 getter */
	public String getGoalUnit() {
		return goalUnit;
	}
	/** repeatKey의 getter */
	public String getRepeatKey() {
		return repeatKey;
	}
	/** memo의 getter */
	public String getMemo() {
		return memo;
	}
	/** startDate의 getter */
	public String getStartDate() {
		return startDate;
	}
	/** endDate의 getter */
	public String getEndDate() {
		return endDate;
	}
	/** startTime의 getter */
	public String getStartTime() {
		return startTime;
	}
	/** endTime의 getter */
	public String getEndTime() {
		return endTime;
	}
	/** goalMonth의 getter */
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
	/** goalCategory의 setter */
	public void setGoalCategory(String goalCategory) {
		this.goalCategory = goalCategory;
	}
	/** goalName의 setter */
	public void setGoalName(String goalName) {
		this.goalName = goalName;
	}
	/** goalQty의 setter */
	public void setGoalQty(double goalQty) {
		this.goalQty = goalQty;
	}
	/** goalUnit의 setter */
	public void setGoalUnit(String goalUnit) {
		this.goalUnit = goalUnit;
	}
	/** repeatKey의 setter */
	public void setRepeatKey(String repeatKey) {
		this.repeatKey = repeatKey;
	}
	/** memo의 setter */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/** startDate의 setter */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/** endDate의 setter */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/** startTime의 setter */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/** EndTime의 setter */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/** goalMonth의 setter */
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
