package Model;
public class GoalCategory {
	/** ��ǥ ī�װ��� ������ �� �ִ� �ʵ� */
	private String goalCategory;
	/** ��ǥ ������ ������ �� �ִ� �ʵ� */
	private String goalColor;

	/** null parameter Constructor */
	public GoalCategory() {
		super();
	}

	/** ��ǥ ī�װ��� ��ǥ ������ �޴� ������ */
	public GoalCategory(String goalCategory, String goalColor) {
		this.goalCategory = goalCategory;
		this.goalColor = goalColor;
	}

	/** ��ǥ ī�װ� getter */
	public String getGoalCategory() {
		return goalCategory;
	}

	/** ��ǥ ���� getter */
	public String getGoalColor() {
		return goalColor;
	}

	/** ��ǥ ī�װ� setter */
	public void setGoalCategory(String goalCategory) {
		this.goalCategory = goalCategory;
	}

	/** ��ǥ ���� setter */
	public void setGoalColor(String goalColor) {
		this.goalColor = goalColor;
	}

	/** �ʵ���� ���� String���� ��ȯ�� �� �ִ� �޼ҵ� */
	public String toString() {
		return "goalCategory: " + goalCategory + " goalColor: " + goalColor;
	}
}