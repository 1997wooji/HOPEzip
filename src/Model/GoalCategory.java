package Model;
public class GoalCategory {
	/** 목표 카테고리를 저장할 수 있는 필드 */
	private String goalCategory;
	/** 목표 색상을 저장할 수 있는 필드 */
	private String goalColor;

	/** null parameter Constructor */
	public GoalCategory() {
		super();
	}

	/** 목표 카테고리와 목표 색상을 받는 생성자 */
	public GoalCategory(String goalCategory, String goalColor) {
		this.goalCategory = goalCategory;
		this.goalColor = goalColor;
	}

	/** 목표 카테고리 getter */
	public String getGoalCategory() {
		return goalCategory;
	}

	/** 목표 색상 getter */
	public String getGoalColor() {
		return goalColor;
	}

	/** 목표 카테고리 setter */
	public void setGoalCategory(String goalCategory) {
		this.goalCategory = goalCategory;
	}

	/** 목표 색상 setter */
	public void setGoalColor(String goalColor) {
		this.goalColor = goalColor;
	}

	/** 필드들의 값을 String으로 반환할 수 있는 메소드 */
	public String toString() {
		return "goalCategory: " + goalCategory + " goalColor: " + goalColor;
	}
}