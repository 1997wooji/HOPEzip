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
	/** 컬렉션을 저장하기 위한 메소드 */
	public void setExamCategoryList(List<ExamCategory> examCategoryList) {
		if(this.examCategoryList.size() != 0)
			this.examCategoryList.clear();
		for(int i = 0; i < examCategoryList.size(); i++)
			this.examCategoryList.add(examCategoryList.get(i));
	}
	public Object[] getExamCategoryList() {
		return this.examCategoryList.toArray();
	}
	/** toString 메소드 */
	public String toString() {
		return "ExamCategoryDao [examCategoryList=" + examCategoryList + "]";
	}
	/** 컬렉션의 사이즈를 조회하기 위한 메소드 */
	public int examCategoryCnt() {
		return this.examCategoryList.size();
	}
	/** 파일에 컬렉션을 저장하기 위한 메소드 */
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
	/** 컬렉션에 파일의 내용을 저장하기 위한 메소드 */
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
	/** 시험 카테고리를 추가하기 위한 메소드 */
	public ExamCategory addExamCategory(String examCategory) {
		if(searchExamCategory(examCategory) == null) {
			ExamCategory temp = new ExamCategory(examCategory);			
			return addExamCategory(temp);
		}
		return null;
	}
	/** 시험 카테고리 객체를 추가하기 위한 메소드 */
	public ExamCategory addExamCategory(ExamCategory examCategory) {
		if(searchExamCategory(examCategory) == null) {
			examCategoryList.add(examCategory);			
			return examCategory;
		}
		return null;
	}
	/** 해당 순번의 시험 카테고리 객체를 삭제하기 위한 메소드 */
	public ExamCategory deleteExamCategory(int index) {
		ExamCategory temp = examCategoryList.get(index);
		this.examCategoryList.remove(index);
		return temp;		
	}
	/** 시험 카테고리 명에 해당하는 객체를 삭제하기 위한 메소드 */
	public ExamCategory deleteExamCategory(String examCategory) {
		ExamCategory temp = searchExamCategory(examCategory);
		this.examCategoryList.remove(temp);
		return temp;
	}
	/** 시험 카테고리 객체를 삭제하기 위한 메소드 */
	public ExamCategory deleteExamCategory(ExamCategory examCategory) {
		this.examCategoryList.remove(examCategory);
		return examCategory;
	}
	/** 순번과 시험 카테고리 명을 통해 객체를 삭제하기 위한 메소드
	 * 일단 만들어 두었음*/
	public ExamCategory deleteExamCategory(int index, String examCategory) {
		ExamCategory temp = this.examCategoryList.get(index);
		if(temp.getExamCategory().equals(examCategory))
			this.examCategoryList.remove(index);
		return temp;
	}
	/** 해당 순번의 시험 카테고리 명을 새로운 시험 카테고리 명으로 수정하기 위한 메소드 */
	public ExamCategory updateExamCategory(int index, String newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = this.examCategoryList.get(index);
			temp.setExamCategory(newExamCategory);
			return temp;			
		}
		return null;
	}
	/** 해당 순번의 시험 카테고리 객체를 새로운 시험 카테고리 객체로 수정하기 위한 메소드 */
	public ExamCategory updateExamCategory(int index, ExamCategory newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = this.examCategoryList.get(index);
			temp.setExamCategory(newExamCategory.getExamCategory());
			return temp;			
		}
		return null;
	}
	/** 해당 시험 카테고리 객체를 새로운 시험 카테고리 객체로 수정하기 위한 메소드 */
	public ExamCategory updateExamCategory(ExamCategory examCategory, ExamCategory newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = searchExamCategory(examCategory);
			temp = newExamCategory;
			return temp;			
		}
		return null;
	}
	/** 시험 카테고리 명에 해당하는 시험 카테고리 객체를 새로운 시험 카테고리 객체로 수정하기 위한 메소드 */
	public ExamCategory updateExamCategory(String examCategory, ExamCategory newExamCategory) {
		if(searchExamCategory(newExamCategory) == null ) {
			ExamCategory temp = searchExamCategory(examCategory);
			temp = newExamCategory;
			return temp;			
		}
		return null;
	}
	/** 시험 카테고리 명을 새로운 시험 카테고리 명으로 수정하기 위한 메소드 */
	public ExamCategory updateExamCategory(String examCategory, String newExamCategory) {
		if(searchExamCategory(newExamCategory) == null) {
			ExamCategory temp = searchExamCategory(examCategory);
			temp.setExamCategory(newExamCategory);
			return temp;			
		}
		return null;
	}
	/** 순번을 통해 시험 카테고리를 조회하는 메소드 */
	public ExamCategory searchExamCategory(int index) {
		return examCategoryList.get(index);
	}
	/** 시험 카테고리 명을 통해 해당 시험 카테고리를 조회하는 메소드 */
	public ExamCategory searchExamCategory(String examCategory) {
		ExamCategory temp = new ExamCategory(examCategory);
		return searchExamCategory(temp);
	}
	/** 해당 시험 카테고리를 조회하는 메소드 */
	public ExamCategory searchExamCategory(ExamCategory examCategory) {
		for(int i = 0; i < this.examCategoryList.size(); i++) {
			if(this.examCategoryList.get(i).getExamCategory().equals(examCategory.getExamCategory()))
				return this.examCategoryList.get(i);
		}
		return null;
	}
}
