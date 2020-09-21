package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/** class SimpleMemoInfoDao : 간단 메모를 저장하고, 접근하기 위해 사용되는 클래스. */
public class SimpleMemoInfoDao {
	/** 간단 메모들을 저장하는 List */
	private List<SimpleMemoInfo> simpleMemoInfoList;
	private static SimpleMemoInfoDao simpleMemoInfoDao;

	static {
		simpleMemoInfoDao = new SimpleMemoInfoDao();
	}

	public static SimpleMemoInfoDao getInstance() {
		return simpleMemoInfoDao;
	}

	/** 네트워크를 위하여 만들어진 생성자. */
	public SimpleMemoInfoDao() {
		simpleMemoInfoList = new ArrayList<SimpleMemoInfo>();
	}

	/** Map을 받아 설정하는 생성자. */
	public SimpleMemoInfoDao(List<SimpleMemoInfo> simpleMemoInfoList) {
		setSimpleMemoInfoList(simpleMemoInfoList);
	}

	public Object[] getSimpleMemoInfoList() {
		return simpleMemoInfoList.toArray();
	}

	/** memo를 저장하는 List를 설정하는 메소드. 생성자에서 사용된다. */
	public void setSimpleMemoInfoList(List<SimpleMemoInfo> simpleMemoInfoList) {
		if (simpleMemoInfoList != null)
			this.simpleMemoInfoList = simpleMemoInfoList;
	}

	/** 문자열을 반환하는 메소드. */
	public String toString() {
		return "SimpleMemoInfoDao [simpleMemoInfoList=" + simpleMemoInfoList + "]";
	}

	/** memo를 저장하는 Map의 크기를 알기위하여 사용되는 메소드. */
	public int simpleMemoInfoCnt() {
		return simpleMemoInfoList.size();
	}

	/** 저장된 메모들을 파일에 옮겨 쓰기 위한 메소드. */
	public boolean saveSimpleMemoInfoList() throws IOException {
		File file = new File("c:/data/SimpleMemoInfo.txt");
		if(!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		if (fw == null || pw == null) {
			pw.close();
			fw.close();
			return false;
		}

		Iterator<SimpleMemoInfo> iterator = simpleMemoInfoList.iterator();
		SimpleMemoInfo simpleMemoInfo = null;

		while (iterator.hasNext()) {
			simpleMemoInfo = iterator.next();
			pw.println(simpleMemoInfo.toString());
		}

		pw.flush();
		pw.close();
		fw.close();

		return true;

	}

	/** 파일에 저장된 메모들을 읽어오기 위한 메소드. */
	public boolean loadSimpleMemoInfoList() throws IOException {
		File file = new File("c:/data/SimpleMemoInfo.txt");
		if(!file.exists())
			file.createNewFile();

		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		if (fr == null || br == null) {
			br.close();
			fr.close();
			return false;
		}
		
		String str = null;
		String[] result = null;
		SimpleMemoInfo test = null;
		boolean flag = true;

		while ((str = br.readLine()) != null) {
			result = str.split("/");
			SimpleMemoInfo simpleMemoInfo = new SimpleMemoInfo(result[0], result[1], false);
			if (result[2].equals("true"))
				simpleMemoInfo.setChecked(true);
			else
				simpleMemoInfo.setChecked(false);
			test = addSimpleMemoInfo(simpleMemoInfo);
			if (test == null)
				flag = false;
		}

		br.close();
		fr.close();

		return flag;

	}

	/** 메모 정보를 추가하기 위해 사용하는 메소드. 메모 정보를 받아온다. */
	public boolean addSimpleMemoInfo(String simpleMemoName, boolean isChecked) {
		if (simpleMemoName == null)
			return false;

		SimpleMemoInfo simpleMemoInfo = new SimpleMemoInfo(simpleMemoName, isChecked);
		if(addSimpleMemoInfo(simpleMemoInfo) != null)
			return true;
		return false;
	}

	/** 메모 정보를 추가하기 위해 사용하는 메소드. 메모 객체를 받아온다. */
	public SimpleMemoInfo addSimpleMemoInfo(SimpleMemoInfo simpleMemoInfo) {
		if (simpleMemoInfo == null)
			return null;
		if (searchSimpleMemoInfo(simpleMemoInfo.getSimpleMemoKey()) != null) {
			return null;
		}
		simpleMemoInfoList.add(simpleMemoInfo);
		return simpleMemoInfo;
	}

	public SimpleMemoInfo deleteSimpleMemoInfo(String simpleMemoKey, String simpleMemoName) {
		if (simpleMemoKey == null || simpleMemoName == null)
			return null;

		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).getSimpleMemoName().equals(simpleMemoName)
					&& simpleMemoInfoList.get(i).getSimpleMemoKey().equals("simplememo" + simpleMemoKey)) {
				return simpleMemoInfoList.remove(i);
			}
		}
		return null;
	}

	public SimpleMemoInfo deleteSimpleMemoInfo(int index, String simpleMemoName) {
		if (simpleMemoName == null)
			return null;

		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).getSimpleMemoName() == simpleMemoName)
				return simpleMemoInfoList.remove(i);
		}
		return null;
	}

	/** 메모 정보를 삭제하기 위해 사용하는 메소드. 메모 식별키를 받아온다. */
	public SimpleMemoInfo deleteSimpleMemoInfo(String simpleMemoKey) {
		if (searchSimpleMemoInfo(simpleMemoKey) == null)
			return null;

		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).getSimpleMemoKey().equals(simpleMemoKey))
				return simpleMemoInfoList.remove(i);
		}
		return null;
	}

	/** 메모 정보를 삭제하기 위해 사용하는 메소드. 메모 객체를 받아온다. */
	public SimpleMemoInfo deleteSimpleMemoInfo(SimpleMemoInfo simpleMemoInfo) {
		if (searchSimpleMemoInfo(simpleMemoInfo) == null)
			return null;

		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).equals(simpleMemoInfo))
				return simpleMemoInfoList.remove(i);
		}

		return null;

	}

	/** 메모 정보를 삭제하기 위해 사용하는 메소드. 모든 정보들을 받아와 비교하고 일치한다면 삭제한다. */
	public SimpleMemoInfo deleteSimpleMemoInfo(String simpleMemoKey, String simpleMemoName, boolean isChecked) {
		SimpleMemoInfo simpleMemoInfo = new SimpleMemoInfo(simpleMemoKey, simpleMemoName, isChecked);
		if (searchSimpleMemoInfo(simpleMemoInfo) == null)
			return null;
		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).getSimpleMemoName() == simpleMemoName
					&& simpleMemoInfoList.get(i).isChecked() == isChecked) {
				return simpleMemoInfoList.remove(i);
			}

		}
		return null;
	}

	/** 메모 정보를 수정하기 위해 사용하는 메소드. 식별키에 대응하는 정보들을 수정한다. */
	public SimpleMemoInfo updateSimpleMemoInfo(String simpleMemoKey, String newSimpleMemoName, boolean newIsChecked) {
		if (simpleMemoKey == null || newSimpleMemoName == null)
			return null;
		SimpleMemoInfo updateSimpleMemoInfo = searchSimpleMemoInfo(simpleMemoKey);

		if (updateSimpleMemoInfo == null)
			return null;

		updateSimpleMemoInfo.setSimpleMemoName(newSimpleMemoName);
		updateSimpleMemoInfo.setChecked(newIsChecked);

		return updateSimpleMemoInfo;
	}

	/** 메모 정보를 수정하기 위해 사용하는 메소드. 식별키에 대응하는 객체를 parameter로 받아온 객체로 수정한다. */
	public SimpleMemoInfo updateSimpleMemoInfo(String simpleMemoKey, SimpleMemoInfo newSimpleMemoInfo) {

		if (simpleMemoKey == null || newSimpleMemoInfo == null)
			return null;
		if (simpleMemoKey != newSimpleMemoInfo.getSimpleMemoKey())
			return null;
		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).getSimpleMemoKey().equals(simpleMemoKey)) {
				simpleMemoInfoList.set(i, newSimpleMemoInfo);
				return newSimpleMemoInfo;
			}
		}
		return null;
	}

	/** 메모 정보를 수정하기 위해 사용하는 메소드. */
	public SimpleMemoInfo updateSimpleMemoInfo(SimpleMemoInfo simpleMemoInfo, SimpleMemoInfo newSimpleMemoInfo) {
		if (simpleMemoInfo == null || newSimpleMemoInfo == null)
			return null;
		if (simpleMemoInfo.getSimpleMemoKey() != newSimpleMemoInfo.getSimpleMemoKey())
			return null;
		if (searchSimpleMemoInfo(simpleMemoInfo) == null)
			return null;
		int index = simpleMemoInfoList.indexOf(simpleMemoInfo);
		simpleMemoInfoList.set(index, newSimpleMemoInfo);
		return newSimpleMemoInfo;
	}

	/** 메모 정보를 수정하기 위해 사용하는 메소드. 식별 키에 대응되는 메모 이름을 수정한다. */
	public SimpleMemoInfo updateSimpleMemoName(String simpleMemoKey, String newSimpleMemoName) {

		SimpleMemoInfo updateSimpleMemoInfo = null;
		if (simpleMemoKey == null || newSimpleMemoName == null)
			return null;
		if ((updateSimpleMemoInfo = searchSimpleMemoInfo("simplememo" + simpleMemoKey)) == null)
			return null;
		updateSimpleMemoInfo.setSimpleMemoName(newSimpleMemoName);
		return updateSimpleMemoInfo;

	}

	public SimpleMemoInfo updateSimpleMemoName(int index, String simpleMemoName, String newSimpleMemoName) {

		if (index < 0 || simpleMemoName == null || newSimpleMemoName == null)
			return null;

		if(this.simpleMemoInfoList.get(index) != null)
			if(this.simpleMemoInfoList.get(index).getSimpleMemoName().equals(simpleMemoName)) {
				this.simpleMemoInfoList.get(index).setSimpleMemoName(newSimpleMemoName);
				return this.simpleMemoInfoList.get(index);
			}
		return null;
	}

	public SimpleMemoInfo updateIsChecked(int index, String simpleMemoName) {
		if (simpleMemoName == null)
			return null;

		SimpleMemoInfo simpleMemoInfo = null;
		String key = (simpleMemoInfoList.get(index)).getSimpleMemoKey();
		// String cvtKey = "simpleMemo"+key;
		if ((simpleMemoInfo = searchSimpleMemoInfo(key)) != null) {
			simpleMemoInfoList.get(index).changeChecked();
			return simpleMemoInfo;
		}
		return null;

	}

	/** 메모 정보를 수정하기 위해 사용하는 메소드. 식별 키에 대응되는 메모 체크 여부를 수정한다. */
	public SimpleMemoInfo updateIsChecked(String simpleMemoKey, boolean newIsChecked) {
		SimpleMemoInfo updateSimpleMemoInfo = null;
		if (simpleMemoKey == null)
			return null;
		if ((updateSimpleMemoInfo = searchSimpleMemoInfo(simpleMemoKey)) == null)
			return null;
		updateSimpleMemoInfo.setChecked(newIsChecked);
		return updateSimpleMemoInfo;
	}

	public SimpleMemoInfo searchSimpleMemoInfo(String simpleMemoKey, String simpleMemoName) {

		if (simpleMemoKey == null || simpleMemoName == null)
			return null;

		for (SimpleMemoInfo sm : simpleMemoInfoList) {
			if (sm.getSimpleMemoKey() == simpleMemoKey && sm.getSimpleMemoName() == simpleMemoName)
				return sm;
		}
		return null;

	}

	/** 메모 정보를 찾기 위해 사용하는 메소드. 식별 키에 대응되는 메모 정보를 반환한다. */
	public SimpleMemoInfo searchSimpleMemoInfo(String simpleMemoKey) {
		if (simpleMemoKey == null)
			return null;

		for (SimpleMemoInfo sm : simpleMemoInfoList) {
			if (sm.getSimpleMemoKey() == simpleMemoKey)
				return sm;
		}
		return null;
	}

	/** 메모 정보를 찾기 위해 사용하는 메소드. parameter로 받은 객체와 대응되는 메모 정보를 반환한다. */
	public SimpleMemoInfo searchSimpleMemoInfo(SimpleMemoInfo simpleMemoInfo) {
		if (simpleMemoInfo == null)
			return null;
		int index = 0;
		if ((index = simpleMemoInfoList.indexOf(simpleMemoInfo)) == -1)
			return null;
		return simpleMemoInfoList.get(index);
	}

	public SimpleMemoInfo searchSimpleMemoInfo(int index, String simpleMemoName) {

		SimpleMemoInfo simpleMemoInfo = null;

		if ((simpleMemoInfo = simpleMemoInfoList.get(index)).getSimpleMemoName() == simpleMemoName)
			return simpleMemoInfo;

		return null;

	}
	
	public SimpleMemoInfo searchSimpleMemoInfo(int index) {
		return this.simpleMemoInfoList.get(index);
	}

	/** 메모 정보를 찾기 위해 사용하는 메소드. 메모 명과 대응되는 메모 정보들을 반환한다. */
	public SimpleMemoInfo[] searchSimpleMemoName(String simpleMemoName) {

		List<SimpleMemoInfo> simpleMemoInfos = new ArrayList<SimpleMemoInfo>();
		Iterator<SimpleMemoInfo> iterator = simpleMemoInfoList.iterator();
		SimpleMemoInfo simpleMemoInfo = null;
		while (iterator.hasNext()) {
			simpleMemoInfo = iterator.next();
			if (simpleMemoInfo.getSimpleMemoName().equals(simpleMemoName))
				simpleMemoInfos.add(simpleMemoInfo);

		}
		if (simpleMemoInfos.size() == 0)
			return null;

		Object[] arr = simpleMemoInfos.toArray();
		SimpleMemoInfo[] simpleMemoArr = new SimpleMemoInfo[arr.length];

		for (int i = 0; i < arr.length; i++) {
			simpleMemoArr[i] = (SimpleMemoInfo) arr[i];
		}

		return simpleMemoArr;
	}

	/** 메모 정보를 찾기 위해 사용하는 메소드. 메모 달성 여부와 대응되는 메모 정보들을 반환한다. */
	public SimpleMemoInfo[] searchIsChecked(boolean isChecked) {
		List<SimpleMemoInfo> simpleMemoInfos = new ArrayList<SimpleMemoInfo>();
		Iterator<SimpleMemoInfo> iterator = simpleMemoInfoList.iterator();
		SimpleMemoInfo simpleMemoInfo = null;
		while (iterator.hasNext()) {
			simpleMemoInfo = iterator.next();
			if (simpleMemoInfo.isChecked() == isChecked)
				simpleMemoInfos.add(simpleMemoInfo);

		}
		if (simpleMemoInfos.size() == 0)
			return null;

		Object[] arr = simpleMemoInfos.toArray();
		SimpleMemoInfo[] simpleMemoArr = new SimpleMemoInfo[arr.length];

		for (int i = 0; i < arr.length; i++) {
			simpleMemoArr[i] = (SimpleMemoInfo) arr[i];
		}

		return simpleMemoArr;
	}

}
