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
	/** 목표 식별 키를 반환하기 위한 메소드 */
	public String getGoalKey() {
		return goalKey;
	}
	/** 시험 카테고리를 반환하기 위한 메소드 */
	public String getExamCategory() {
		return examCategory;
	}
	/** 시험 장소를 반환하기 위한 메소드 */
	public String getLocation() {
		return location;
	}
	/** 디데이 설정 여부를 반환하기 위한 메소드 */
	public boolean isDday() {
		return isDday;
	}
	/** 목표 식별 키를 저장하기 위한 메소드 */
	public void setGoalKey(String goalKey) {
		this.goalKey = goalKey;
	}
	/** 시험 카테고리 명을 저장하기 위한 메소드 */
	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}
	/** 시험 장소를 저장하기 위한 메소드 */
	public void setLocation(String location) {
		this.location = location;
	}
	/** 디데이 설정 여부를 저장하기 위한 메소드 */
	public void setDday(boolean isDday) {
		this.isDday = isDday;
	}
	/** toString 메소드 */
	public String toString() {
		return "ExamInfo [goalKey=" + goalKey + ", examCategory=" + examCategory +", location="
				+ location + ", isDday=" + isDday + "]";
	}
}
