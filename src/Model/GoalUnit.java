package Model;
public class GoalUnit {

	private String goalUnit;

	/** Constructor Summary */
	/** Null Parameter Constructor */
	public GoalUnit() {
		this("사용자정의");
	}

	/** 전체 필드를 받는 Constructor */
	public GoalUnit(String goalUnit) {
		this.goalUnit = goalUnit;
	}

	/** Method Summary */
	/** goalUnit의 정보를 반환하기 위한 메소드 */
	public String getGoalUnit() {
		return goalUnit;
	}

	/** 필드에 goalUnit을 저장하기 위한 메소드 */
	public void setGoalUnit(String goalUnit) {
		this.goalUnit = goalUnit;
	}

	/** overloading Method */
	public String toString() {
		return "goalUnit: " + goalUnit;
	}
}
