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
	/** ���� ī�װ� ���� ��ȸ�ϴ� �޼ҵ� */
	public String getExamCategory() {
		return examCategory;
	}
	/** ���� ī�װ� ���� �����ϴ� �޼ҵ� */
	public void setExamCategory(String examCategory) {
		this.examCategory = examCategory;
	}
	/** toString �޼ҵ� */
	public String toString() {
		return examCategory;
	}
}
