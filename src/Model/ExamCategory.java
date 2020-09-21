package Model;
public class ExamCategory {
	private String examCategory;

	/** constructor summary */
	public ExamCategory() {
		super();
	}
	public ExamCategory(String examCategory) {
		super();
		this.examCategory = examCategory;
	}
	
	/** method summary */
	/** 시험 카테고리 명을 조회하는 메소드 */
	public String getExamCategory() {
		return examCategory;
	}
	/** 시험 카테고리 명을 저장하는 메소드 */
	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}
	/** toString 메소드 */
	public String toString() {
		return examCategory;
	}
}
