package Model;
public class ExamInfo {
	private String goalKey;
	private String examCategory;
	private String location;
	private boolean isDday;
	
	/** constructor summary */
	public ExamInfo() {
		super();
	}
	
	public ExamInfo(String goalKey, String examCategory, String location, boolean isDday) {
		super();
		this.goalKey = goalKey;
		this.examCategory = examCategory;
		this.location = location;
		this.isDday = isDday;
	}
	
	/** method summary */
	/** ��ǥ �ĺ� Ű�� ��ȯ�ϱ� ���� �޼ҵ� */
	public String getGoalKey() {
		return goalKey;
	}
	/** ���� ī�װ��� ��ȯ�ϱ� ���� �޼ҵ� */
	public String getExamCategory() {
		return examCategory;
	}
	/** ���� ��Ҹ� ��ȯ�ϱ� ���� �޼ҵ� */
	public String getLocation() {
		return location;
	}
	/** ���� ���� ���θ� ��ȯ�ϱ� ���� �޼ҵ� */
	public boolean isDday() {
		return isDday;
	}
	/** ��ǥ �ĺ� Ű�� �����ϱ� ���� �޼ҵ� */
	public void setGoalKey(String goalKey) {
		this.goalKey = goalKey;
	}
	/** ���� ī�װ� ���� �����ϱ� ���� �޼ҵ� */
	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}
	/** ���� ��Ҹ� �����ϱ� ���� �޼ҵ� */
	public void setLocation(String location) {
		this.location = location;
	}
	/** ���� ���� ���θ� �����ϱ� ���� �޼ҵ� */
	public void setDday(boolean isDday) {
		this.isDday = isDday;
	}
	/** toString �޼ҵ� */
	public String toString() {
		return "ExamInfo [goalKey=" + goalKey + ", examCategory=" + examCategory +", location="
				+ location + ", isDday=" + isDday + "]";
	}
}
