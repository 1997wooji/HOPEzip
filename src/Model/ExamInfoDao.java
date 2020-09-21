package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExamInfoDao {
	private Map<String, ExamInfo> examInfoMap;
	private static ExamInfoDao examInfoDao;
	/** constructor summary */
	static{
		examInfoDao = new ExamInfoDao();
	}
	public static ExamInfoDao getInstance()
	{
		return examInfoDao;
	}
	public ExamInfoDao() {
		super();
		examInfoMap = new HashMap<String, ExamInfo>();
	}
	public ExamInfoDao(Map<String, ExamInfo> examInfoMap) {
		super();
		if(this.examInfoMap == null)
			this.examInfoMap = new HashMap<String, ExamInfo> ();
		this.setExamInfoMap(examInfoMap);
	}
	
	/** method summary */
	/** 컬렉션을 저장하기 위한 메소드 */
	public void setExamInfoMap(Map<String, ExamInfo> examInfoMap) {
		if(this.examInfoMap == null || examInfoMap == null)
			return;
		if(this.examInfoMap.size() != 0)
			this.examInfoMap.clear();
		
		Iterator<String> iterator = examInfoMap.keySet().iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			this.examInfoMap.put(str, examInfoMap.get(str));
		}
	}	
	public Object[] getExamInfoMap()
	{
		List<ExamInfo> list = new ArrayList<ExamInfo>();
		Iterator<String> iterator = examInfoMap.keySet().iterator();
		while(iterator.hasNext())
		{
			list.add(examInfoMap.get(iterator.next()));
		}
		
		return list.toArray();
	}
	
	
	/** toString 메소드 */
	public String toString() {
		return "ExamInfoDao [examInfoMap=" + examInfoMap + "]";
	}
	/** 컬렉션의 사이즈를 조회하기 위한 메소드 */
	public int examInfoCnt() {
		return examInfoMap.size();
	}
	/** 파일에 컬렉션을 저장하기 위한 메소드 */
	public boolean saveExamInfoMap() throws IOException {
		File file = new File("C:/data/ExamInfo.txt");
		if(!file.exists())
			file.createNewFile();
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		
		Iterator<String> iterator = examInfoMap.keySet().iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			ExamInfo examInfo = examInfoMap.get(str);
			
			String data = examInfo.getGoalKey() + "/" + examInfo.getExamCategory() + "/" + examInfo.getLocation() + "/" + examInfo.isDday();
			pw.println(data);
		}
		
		fw.flush();
		fw.close();
		pw.close();
		return true;
	}
	/** 파일의 내용을 컬렉션에 저장하기 위한 메소드 */
	public boolean loadExamInfoMap() throws IOException {
		File file = new File("C:/data/ExamInfo.txt");
		if(!file.exists())
			file.createNewFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String str = null;
		while((str = br.readLine()) != null) {
			String[] datas = str.split("/");
			addExamInfo(datas[0], datas[1], datas[2], Boolean.parseBoolean(datas[3]));
		}
		
		fr.close();
		br.close();
		return true;		
	}
	/** 객체를 생성하여 컬렉션에 추가하기 위한 메소드 */
	public ExamInfo addExamInfo(String goalKey, String examCategory,String location, boolean isDday) {
		if(searchExamInfo(goalKey) == null) {
			ExamInfo examInfo = new ExamInfo(goalKey, examCategory, location, isDday);
			this.examInfoMap.put(goalKey, examInfo);
			return examInfo;
		}
		return null;
	}
	/** 객체를 컬렉션에 추가하기 위한 메소드 */
	public ExamInfo addExamInfo(ExamInfo examInfo) {
		if(searchExamInfo(examInfo) == null) {
			this.examInfoMap.put(examInfo.getGoalKey(), examInfo);
			return examInfo;
		}
		return null;
	}
	/** 컬렉션에서 필드 명이 모두 일치하는 객체를 삭제하기 위한 메소드 */
	public ExamInfo deleteExamInfo(String goalKey, String examCategory,  String location, boolean isDday) {
		ExamInfo examInfo = searchExamInfo(goalKey, examCategory, location, isDday);
		if(examInfo != null)
			return examInfoMap.remove(examInfo.getGoalKey());
		return null;
	}
	/** 컬렉션에서 목표 식별 키와 일치하는 객체를 삭제하기 위한 메소드 */
	public ExamInfo deleteExamInfo(String goalKey) {
		return examInfoMap.remove(goalKey);
	}
	/** 컬렉션에서 해당 객체를 삭제하기 위한 메소드 */
	public ExamInfo deleteExamInfo(ExamInfo examInfo) {
		return examInfoMap.remove(examInfo.getGoalKey());
	}
	/** 목표 식별 키를 이용하여 모든 필드의 데이터를 업데이트하기 위한 메소드 */
	public ExamInfo updateExamInfo(String goalKey, String newExamCategory, String newLocation, boolean newIsDday) {
		ExamInfo examInfo = examInfoMap.get(goalKey);
		if(examInfo != null) {
			examInfo.setExamCategory(newExamCategory);
			examInfo.setLocation(newLocation);
			examInfo.setDday(newIsDday);
		}
		return examInfo;
	}
	/** 목표 식별 키를 이용하여 해당 객체를 업데이트하기 위한 메소드 */
	public ExamInfo updateExamInfo(String goalKey, ExamInfo examInfo) {
		if(searchExamInfo(examInfo) == null) {
			examInfoMap.remove(goalKey);
			examInfoMap.put(examInfo.getGoalKey(), examInfo);
			return examInfo;
		}
		return null;
	}
	/** 해당 객체를 새로운 객체로 업데이트하기 위한 메소드 */
	public ExamInfo updateExamInfo(ExamInfo examInfo, ExamInfo newExamInfo) {
		if(searchExamInfo(newExamInfo) == null) {
			examInfoMap.remove(examInfo);
			examInfoMap.put(newExamInfo.getGoalKey(), newExamInfo);
			return newExamInfo;
		}
		return null;
	}
	/** 목표 식별 키를 이용하여 목표 카테고리 명을 수정하기 위한 메소드 */
	public ExamInfo updateExamCategory(String goalKey, String newExamCategory) {
		ExamInfo examInfo = searchExamInfo(goalKey);
		if(examInfo != null)
			examInfo.setExamCategory(newExamCategory);
		return examInfo;
	}

	/** 목표 식별 키를 이용하여 시험 장소를 수정하기 위한 메소드 */
	public ExamInfo updateLocation(String goalKey, String newLocation) {
		ExamInfo examInfo = searchExamInfo(goalKey);
		if(examInfo != null)
			examInfo.setLocation(newLocation);
		return examInfo;
	}
	/** 목표 식별 키를 이용하여 디데이 설정 여부를 수정하기 위한 메소드 */
	public ExamInfo updateIsDday(String goalKey, boolean newIsDday) {
		ExamInfo examInfo = searchExamInfo(goalKey);
		if(examInfo != null)
			examInfo.setDday(newIsDday);
		return examInfo;
	}
	/** 필드의 데이터가 일치하는 객체를 조회하기 위한 메소드 */
	public ExamInfo searchExamInfo(String goalKey, String examCategory, String location, boolean isDday) {
		ExamInfo examInfo = examInfoMap.get(goalKey);
		if(examInfo != null) {
			if(examInfo.getExamCategory().equals(examCategory))
					if(examInfo.getLocation().equals(location))
						if(examInfo.isDday() == isDday)
							return examInfo;
		}
		return null;
	}
	/** 목표 식별 키가 일치하는 객체를 조회하기 위한 메소드 */
	public ExamInfo searchExamInfo(String goalKey) {
		return examInfoMap.get(goalKey);
	}
	/** 해당 객체를 조회하기 위한 메소드 */
	public ExamInfo searchExamInfo(ExamInfo examInfo) {
		return examInfoMap.get(examInfo.getGoalKey());
	}
	/** 카테고리가 일치하는 시험 정보를 조회하기 위한 메소드 */
	public Object[] searchExamCategory(String examCategory) {
		List<ExamInfo> tempList = new ArrayList<ExamInfo>();
		
		Iterator<String> iterator = examInfoMap.keySet().iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			if(examInfoMap.get(str).getExamCategory().equals(examCategory))
				tempList.add(examInfoMap.get(str));
		}
		
		return tempList.toArray();
	}
	/** 시험 장소가 일치하는 시험 정보를 조회하기 위한 메소드 */
	public Object[] searchLocation(String location) {
		List<ExamInfo> tempList = new ArrayList<ExamInfo>();
		
		Iterator<String> iterator = examInfoMap.keySet().iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			if(examInfoMap.get(str).getLocation().equals(location))
				tempList.add(examInfoMap.get(str));
		}
		
		return tempList.toArray();
	}
	/** 디데이 설정 여부가 일치하는 시험 정보를 조회하기 위한 메소드 */
	public Object[] searchIsDday(boolean isDday) {
		List<ExamInfo> tempList = new ArrayList<ExamInfo>();
		
		Iterator<String> iterator = examInfoMap.keySet().iterator();
		while(iterator.hasNext()) {
			String str = iterator.next();
			if(examInfoMap.get(str).isDday() == isDday)
				tempList.add(examInfoMap.get(str));
		}
		
		return tempList.toArray();
	}
}
