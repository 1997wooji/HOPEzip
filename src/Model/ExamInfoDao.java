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
	/** �÷����� �����ϱ� ���� �޼ҵ� */
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
	
	
	/** toString �޼ҵ� */
	public String toString() {
		return "ExamInfoDao [examInfoMap=" + examInfoMap + "]";
	}
	/** �÷����� ����� ��ȸ�ϱ� ���� �޼ҵ� */
	public int examInfoCnt() {
		return examInfoMap.size();
	}
	/** ���Ͽ� �÷����� �����ϱ� ���� �޼ҵ� */
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
	/** ������ ������ �÷��ǿ� �����ϱ� ���� �޼ҵ� */
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
	/** ��ü�� �����Ͽ� �÷��ǿ� �߰��ϱ� ���� �޼ҵ� */
	public ExamInfo addExamInfo(String goalKey, String examCategory,String location, boolean isDday) {
		if(searchExamInfo(goalKey) == null) {
			ExamInfo examInfo = new ExamInfo(goalKey, examCategory, location, isDday);
			this.examInfoMap.put(goalKey, examInfo);
			return examInfo;
		}
		return null;
	}
	/** ��ü�� �÷��ǿ� �߰��ϱ� ���� �޼ҵ� */
	public ExamInfo addExamInfo(ExamInfo examInfo) {
		if(searchExamInfo(examInfo) == null) {
			this.examInfoMap.put(examInfo.getGoalKey(), examInfo);
			return examInfo;
		}
		return null;
	}
	/** �÷��ǿ��� �ʵ� ���� ��� ��ġ�ϴ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamInfo deleteExamInfo(String goalKey, String examCategory,  String location, boolean isDday) {
		ExamInfo examInfo = searchExamInfo(goalKey, examCategory, location, isDday);
		if(examInfo != null)
			return examInfoMap.remove(examInfo.getGoalKey());
		return null;
	}
	/** �÷��ǿ��� ��ǥ �ĺ� Ű�� ��ġ�ϴ� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamInfo deleteExamInfo(String goalKey) {
		return examInfoMap.remove(goalKey);
	}
	/** �÷��ǿ��� �ش� ��ü�� �����ϱ� ���� �޼ҵ� */
	public ExamInfo deleteExamInfo(ExamInfo examInfo) {
		return examInfoMap.remove(examInfo.getGoalKey());
	}
	/** ��ǥ �ĺ� Ű�� �̿��Ͽ� ��� �ʵ��� �����͸� ������Ʈ�ϱ� ���� �޼ҵ� */
	public ExamInfo updateExamInfo(String goalKey, String newExamCategory, String newLocation, boolean newIsDday) {
		ExamInfo examInfo = examInfoMap.get(goalKey);
		if(examInfo != null) {
			examInfo.setExamCategory(newExamCategory);
			examInfo.setLocation(newLocation);
			examInfo.setDday(newIsDday);
		}
		return examInfo;
	}
	/** ��ǥ �ĺ� Ű�� �̿��Ͽ� �ش� ��ü�� ������Ʈ�ϱ� ���� �޼ҵ� */
	public ExamInfo updateExamInfo(String goalKey, ExamInfo examInfo) {
		if(searchExamInfo(examInfo) == null) {
			examInfoMap.remove(goalKey);
			examInfoMap.put(examInfo.getGoalKey(), examInfo);
			return examInfo;
		}
		return null;
	}
	/** �ش� ��ü�� ���ο� ��ü�� ������Ʈ�ϱ� ���� �޼ҵ� */
	public ExamInfo updateExamInfo(ExamInfo examInfo, ExamInfo newExamInfo) {
		if(searchExamInfo(newExamInfo) == null) {
			examInfoMap.remove(examInfo);
			examInfoMap.put(newExamInfo.getGoalKey(), newExamInfo);
			return newExamInfo;
		}
		return null;
	}
	/** ��ǥ �ĺ� Ű�� �̿��Ͽ� ��ǥ ī�װ� ���� �����ϱ� ���� �޼ҵ� */
	public ExamInfo updateExamCategory(String goalKey, String newExamCategory) {
		ExamInfo examInfo = searchExamInfo(goalKey);
		if(examInfo != null)
			examInfo.setExamCategory(newExamCategory);
		return examInfo;
	}

	/** ��ǥ �ĺ� Ű�� �̿��Ͽ� ���� ��Ҹ� �����ϱ� ���� �޼ҵ� */
	public ExamInfo updateLocation(String goalKey, String newLocation) {
		ExamInfo examInfo = searchExamInfo(goalKey);
		if(examInfo != null)
			examInfo.setLocation(newLocation);
		return examInfo;
	}
	/** ��ǥ �ĺ� Ű�� �̿��Ͽ� ���� ���� ���θ� �����ϱ� ���� �޼ҵ� */
	public ExamInfo updateIsDday(String goalKey, boolean newIsDday) {
		ExamInfo examInfo = searchExamInfo(goalKey);
		if(examInfo != null)
			examInfo.setDday(newIsDday);
		return examInfo;
	}
	/** �ʵ��� �����Ͱ� ��ġ�ϴ� ��ü�� ��ȸ�ϱ� ���� �޼ҵ� */
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
	/** ��ǥ �ĺ� Ű�� ��ġ�ϴ� ��ü�� ��ȸ�ϱ� ���� �޼ҵ� */
	public ExamInfo searchExamInfo(String goalKey) {
		return examInfoMap.get(goalKey);
	}
	/** �ش� ��ü�� ��ȸ�ϱ� ���� �޼ҵ� */
	public ExamInfo searchExamInfo(ExamInfo examInfo) {
		return examInfoMap.get(examInfo.getGoalKey());
	}
	/** ī�װ��� ��ġ�ϴ� ���� ������ ��ȸ�ϱ� ���� �޼ҵ� */
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
	/** ���� ��Ұ� ��ġ�ϴ� ���� ������ ��ȸ�ϱ� ���� �޼ҵ� */
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
	/** ���� ���� ���ΰ� ��ġ�ϴ� ���� ������ ��ȸ�ϱ� ���� �޼ҵ� */
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
