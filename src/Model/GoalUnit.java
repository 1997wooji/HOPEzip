package Model;
public class GoalUnit {

	private String goalUnit;

	/** Constructor Summary */
	/** Null Parameter Constructor */
	public GoalUnit() {
		this("���������");
	}

	/** ��ü �ʵ带 �޴� Constructor */
	public GoalUnit(String goalUnit) {
		this.goalUnit = goalUnit;
	}

	/** Method Summary */
	/** goalUnit�� ������ ��ȯ�ϱ� ���� �޼ҵ� */
	public String getGoalUnit() {
		return goalUnit;
	}

	/** �ʵ忡 goalUnit�� �����ϱ� ���� �޼ҵ� */
	public void setGoalUnit(String goalUnit) {
		this.goalUnit = goalUnit;
	}

	/** overloading Method */
	public String toString() {
		return "goalUnit: " + goalUnit;
	}
}
