package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExamCategoryDao {
	private List<ExamCategory> examCategoryList;
	private static ExamCategoryDao examCategoryDao;
	
	static {
		examCategoryDao = new ExamCategoryDao();
	}
	
	public static ExamCategoryDao getInstance() {
		return examCategoryDao;
	}
	
	/** constructor summary */
	public ExamCategoryDao() {
		super();
		examCategoryList = new ArrayList<ExamCategory>();
	}
	public ExamCategoryDao(List<ExamCategory> examCategoryList) {
		super();
		if(this.examCategoryList == null)
			this.examCategoryList = new ArrayList<ExamCategory>();
		setExamCategoryList(examCategoryList);
	}
	
	/** method summary */
	/** �÷����� �����ϱ� ���� �޼ҵ� */
	public void setExamCategoryList(List<ExamCategory> examCategoryList) {
		if(this.examCategoryList.size() != 0)
			this.examCategoryList.clear();
		for(int i = 0; i < examCategoryList.size(); i++)
			this.examCategoryList.add(examCategoryList.get(i));
	}
	public Object[] getExamCategoryList() {
		return this.examCategoryList.toArray();
	}
	/** toString �޼ҵ� */
	public String toString() {
		return "ExamCategoryDao [examCategoryList=" + examCategoryList + "]";
	}
	/** �÷����� ����� ��ȸ�ϱ� ���� �޼ҵ� */
	public int examCategoryCnt() {
		return this.examCategoryList.size();
	}
	/** ���Ͽ� �÷����� �����ϱ� ���� �޼ҵ� */
	public boolean saveExamCategoryList() throws IOException {
		File file = new File("C:/data/ExamCategory.txt");
		if(!file.exists())
			file.createNewFile();
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		for(int i = 0; i < examCategoryList.size(); i++) {
			pw.println(examCategoryList.get(i));
		}
		
		fw.flush();
		fw.close();
		pw.close();
		return true;
	}
	/** �÷��ǿ� ������ ������ �����ϱ� ���� �޼ҵ� */
	public boolean loadExamCategoryList() throws IOException {
		File file = new File("C:/data/ExamCategory.txt");
		if(!file.exists())
			file.createNewFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String str = null;
		while((str = br.readLine()) != null) {
			addExamCategory(str);
		}
		
		fr.close();
		br.close();
		return true;
	}
	/** ���� ī�װ��� �߰��ϱ� ���� �޼ҵ� */
	public ExamCategory addExamCategory(String examCategory) {
		if(searchExamCategory(examCategory) == null) {
			ExamCategory temp = new ExamCategory(examCategory);			
			return addExamCategory(temp);
		}
		return null;
	}
	/** ���� ī�װ� ��ü�� �߰��ϱ� ���� �޼ҵ� */
	public ExamCategory addExamCategory(ExamCategory examCategory) {
		if(searchExamCategory(examCategory) == null) {
			examCategoryList.add(examCategory);			
			return examCategory;
		}
		return null;
	}
	/** �ش� ������ ���� ī�װ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamCategory deleteExamCategory(int index) {
		ExamCategory temp = examCategoryList.get(index);
		this.examCategoryList.remove(index);
		return temp;		
	}
	/** ���� ī�װ� �� �ش��ϴ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamCategory deleteExamCategory(String examCategory) {
		ExamCategory temp = searchExamCategory(examCategory);
		this.examCategoryList.remove(temp);
		return temp;
	}
	/** ���� ī�װ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamCategory deleteExamCategory(ExamCategory examCategory) {
		this.examCategoryList.remove(examCategory);
		return examCategory;
	}
	/** ������ ���� ī�װ� ���� ���� ��ü�� �����ϱ� ���� �޼ҵ�
	 * �ϴ� ����� �ξ���*/
	public ExamCategory deleteExamCategory(int index, String examCategory) {
		ExamCategory temp = this.examCategoryList.get(index);
		if(temp.getExamCategory().equals(examCategory))
			this.examCategoryList.remove(index);
		return temp;
	}
	/** �ش� ������ ���� ī�װ� ���� ���ο� ���� ī�װ� ������ �����ϱ� ���� �޼ҵ� */
	public ExamCategory updateExamCategory(int index, String newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = this.examCategoryList.get(index);
			temp.setExamCategory(newExamCategory);
			return temp;			
		}
		return null;
	}
	/** �ش� ������ ���� ī�װ� ��ü�� ���ο� ���� ī�װ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamCategory updateExamCategory(int index, ExamCategory newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = this.examCategoryList.get(index);
			temp.setExamCategory(newExamCategory.getExamCategory());
			return temp;			
		}
		return null;
	}
	/** �ش� ���� ī�װ� ��ü�� ���ο� ���� ī�װ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamCategory updateExamCategory(ExamCategory examCategory, ExamCategory newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = searchExamCategory(examCategory);
			temp = newExamCategory;
			return temp;			
		}
		return null;
	}
	/** ���� ī�װ� �� �ش��ϴ� ���� ī�װ� ��ü�� ���ο� ���� ī�װ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamCategory updateExamCategory(String examCategory, ExamCategory newExamCategory) {
		if(searchExamCategory(newExamCategory) == null ) {
			ExamCategory temp = searchExamCategory(examCategory);
			temp = newExamCategory;
			return temp;			
		}
		return null;
	}
	/** ���� ī�װ� ���� ���ο� ���� ī�װ� ������ �����ϱ� ���� �޼ҵ� */
	public ExamCategory updateExamCategory(String examCategory, String newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = searchExamCategory(examCategory);
			temp.setExamCategory(newExamCategory);
			return temp;			
		}
		return null;
	}
	/** ������ ���� ���� ī�װ��� ��ȸ�ϴ� �޼ҵ� */
	public ExamCategory searchExamCategory(int index) {
		return examCategoryList.get(index);
	}
	/** ���� ī�װ� ���� ���� �ش� ���� ī�װ��� ��ȸ�ϴ� �޼ҵ� */
	public ExamCategory searchExamCategory(String examCategory) {
		ExamCategory temp = new ExamCategory(examCategory);
		return searchExamCategory(temp);
	}
	/** �ش� ���� ī�װ��� ��ȸ�ϴ� �޼ҵ� */
	public ExamCategory searchExamCategory(ExamCategory examCategory) {
		for(int i = 0; i < this.examCategoryList.size(); i++) {
			if(this.examCategoryList.get(i).getExamCategory().equals(examCategory.getExamCategory()))
				return this.examCategoryList.get(i);
		}
		return null;
	}
}
