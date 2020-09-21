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

/** class SimpleMemoInfoDao : ���� �޸� �����ϰ�, �����ϱ� ���� ���Ǵ� Ŭ����. */
public class SimpleMemoInfoDao {
	/** ���� �޸���� �����ϴ� List */
	private List<SimpleMemoInfo> simpleMemoInfoList;
	private static SimpleMemoInfoDao simpleMemoInfoDao;

	static {
		simpleMemoInfoDao = new SimpleMemoInfoDao();
	}

	public static SimpleMemoInfoDao getInstance() {
		return simpleMemoInfoDao;
	}

	/** ��Ʈ��ũ�� ���Ͽ� ������� ������. */
	public SimpleMemoInfoDao() {
		simpleMemoInfoList = new ArrayList<SimpleMemoInfo>();
	}

	/** Map�� �޾� �����ϴ� ������. */
	public SimpleMemoInfoDao(List<SimpleMemoInfo> simpleMemoInfoList) {
		setSimpleMemoInfoList(simpleMemoInfoList);
	}

	public Object[] getSimpleMemoInfoList() {
		return simpleMemoInfoList.toArray();
	}

	/** memo�� �����ϴ� List�� �����ϴ� �޼ҵ�. �����ڿ��� ���ȴ�. */
	public void setSimpleMemoInfoList(List<SimpleMemoInfo> simpleMemoInfoList) {
		if (simpleMemoInfoList != null)
			this.simpleMemoInfoList = simpleMemoInfoList;
	}

	/** ���ڿ��� ��ȯ�ϴ� �޼ҵ�. */
	public String toString() {
		return "SimpleMemoInfoDao [simpleMemoInfoList=" + simpleMemoInfoList + "]";
	}

	/** memo�� �����ϴ� Map�� ũ�⸦ �˱����Ͽ� ���Ǵ� �޼ҵ�. */
	public int simpleMemoInfoCnt() {
		return simpleMemoInfoList.size();
	}

	/** ����� �޸���� ���Ͽ� �Ű� ���� ���� �޼ҵ�. */
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

	/** ���Ͽ� ����� �޸���� �о���� ���� �޼ҵ�. */
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

	/** �޸� ������ �߰��ϱ� ���� ����ϴ� �޼ҵ�. �޸� ������ �޾ƿ´�. */
	public boolean addSimpleMemoInfo(String simpleMemoName, boolean isChecked) {
		if (simpleMemoName == null)
			return false;

		SimpleMemoInfo simpleMemoInfo = new SimpleMemoInfo(simpleMemoName, isChecked);
		if(addSimpleMemoInfo(simpleMemoInfo) != null)
			return true;
		return false;
	}

	/** �޸� ������ �߰��ϱ� ���� ����ϴ� �޼ҵ�. �޸� ��ü�� �޾ƿ´�. */
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

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. �޸� �ĺ�Ű�� �޾ƿ´�. */
	public SimpleMemoInfo deleteSimpleMemoInfo(String simpleMemoKey) {
		if (searchSimpleMemoInfo(simpleMemoKey) == null)
			return null;

		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).getSimpleMemoKey().equals(simpleMemoKey))
				return simpleMemoInfoList.remove(i);
		}
		return null;
	}

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. �޸� ��ü�� �޾ƿ´�. */
	public SimpleMemoInfo deleteSimpleMemoInfo(SimpleMemoInfo simpleMemoInfo) {
		if (searchSimpleMemoInfo(simpleMemoInfo) == null)
			return null;

		for (int i = 0; i < simpleMemoInfoList.size(); i++) {
			if (simpleMemoInfoList.get(i).equals(simpleMemoInfo))
				return simpleMemoInfoList.remove(i);
		}

		return null;

	}

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. ��� �������� �޾ƿ� ���ϰ� ��ġ�Ѵٸ� �����Ѵ�. */
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

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. �ĺ�Ű�� �����ϴ� �������� �����Ѵ�. */
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

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. �ĺ�Ű�� �����ϴ� ��ü�� parameter�� �޾ƿ� ��ü�� �����Ѵ�. */
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

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. */
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

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. �ĺ� Ű�� �����Ǵ� �޸� �̸��� �����Ѵ�. */
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

	/** �޸� ������ �����ϱ� ���� ����ϴ� �޼ҵ�. �ĺ� Ű�� �����Ǵ� �޸� üũ ���θ� �����Ѵ�. */
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

	/** �޸� ������ ã�� ���� ����ϴ� �޼ҵ�. �ĺ� Ű�� �����Ǵ� �޸� ������ ��ȯ�Ѵ�. */
	public SimpleMemoInfo searchSimpleMemoInfo(String simpleMemoKey) {
		if (simpleMemoKey == null)
			return null;

		for (SimpleMemoInfo sm : simpleMemoInfoList) {
			if (sm.getSimpleMemoKey() == simpleMemoKey)
				return sm;
		}
		return null;
	}

	/** �޸� ������ ã�� ���� ����ϴ� �޼ҵ�. parameter�� ���� ��ü�� �����Ǵ� �޸� ������ ��ȯ�Ѵ�. */
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

	/** �޸� ������ ã�� ���� ����ϴ� �޼ҵ�. �޸� ��� �����Ǵ� �޸� �������� ��ȯ�Ѵ�. */
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

	/** �޸� ������ ã�� ���� ����ϴ� �޼ҵ�. �޸� �޼� ���ο� �����Ǵ� �޸� �������� ��ȯ�Ѵ�. */
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
