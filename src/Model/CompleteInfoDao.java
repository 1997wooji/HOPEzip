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

public class CompleteInfoDao {
	private Map<String, CompleteInfo> completeInfoMap;
	private static CompleteInfoDao completeInfoDao;

	static {
		completeInfoDao = new CompleteInfoDao();
	}

	public static CompleteInfoDao getInstance() {
		return completeInfoDao;
	}

	/** constructor summary */
	public CompleteInfoDao() {
		super();
		this.completeInfoMap = new HashMap<String, CompleteInfo>();
	}

	public CompleteInfoDao(Map<String, CompleteInfo> completeInfoMap) {
		super();
		if (this.completeInfoMap == null)
			this.completeInfoMap = new HashMap<String, CompleteInfo>();
		setCompleteInfoMap(completeInfoMap);
	}

	/** method summary */
	public void setCompleteInfoMap(Map<String, CompleteInfo> completeInfoMap) {
		if (this.completeInfoMap.size() != 0)
			completeInfoMap.clear();

		Iterator<String> iterator = completeInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			this.completeInfoMap.put(str, completeInfoMap.get(str));
		}
	}

	public Object[] getCompleteInfoMap() {
		List<CompleteInfo> temp = new ArrayList<CompleteInfo>();

		Iterator<String> iterator = this.completeInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			iterator.next();
			String key = iterator.toString();
			temp.add(this.completeInfoMap.get(key));
		}

		return temp.toArray();
	}

	/** toString �޼ҵ� */
	public String toString() {
		return "CompleteInfoDao [completeInfoMap=" + completeInfoMap + "]";
	}

	/** �÷����� ����� ��ȸ�ϴ� �޼ҵ� */
	public int completeInfoCnt() {
		return this.completeInfoMap.size();
	}

	/** ���Ͽ� �÷����� ������ �����ϱ� ���� �޼ҵ� */
	public boolean saveCompleteInfoMap() throws IOException {
		File file = new File("C:/data/CompleteInfo.txt");
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		Iterator<String> iterator = completeInfoMap.keySet().iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			CompleteInfo completeInfo = completeInfoMap.get(str);

			String data = completeInfo.getCompleteKey() + "/" + completeInfo.getCompleteQty() + "/"
					+ completeInfo.getCompleteTime() + "/" + completeInfo.getCompleteRate();
			pw.println(data);
		}

		fw.flush();
		fw.close();
		pw.close();
		return true;
	}

	/** ������ ������ �÷��ǿ� �����ϱ� ���� �޼ҵ� */
	public boolean loadCompleteInfoMap() throws IOException {
		File file = new File("C:/data/CompleteInfo.txt");
		if(!file.exists())
			file.createNewFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		String str = null;
		while ((str = br.readLine()) != null) {
			String[] datas = str.split("/");
			addCompleteInfo(datas[0], Integer.parseInt(datas[1]), datas[2], Double.parseDouble(datas[3]));
		}

		fr.close();
		br.close();
		return true;
	}

	public CompleteInfo addCompleteInfo() {
		return this.addCompleteInfo(0, null, 0.0);
	}
	/** ��ü�� �����Ͽ� �÷��ǿ� �߰��ϱ� ���� �޼ҵ� */
	public CompleteInfo addCompleteInfo(int completeQty, String completeTime, double completeRate) {
		CompleteInfo completeInfo = new CompleteInfo(completeQty, completeTime, completeRate);
		this.completeInfoMap.put(completeInfo.getCompleteKey(), completeInfo);
		return completeInfo;
	}
	public CompleteInfo addCompleteInfo(String completeKey, int completeQty, String completeTime, double completeRate) {
		CompleteInfo completeInfo = new CompleteInfo(completeKey, completeQty, completeTime, completeRate);
		this.completeInfoMap.put(completeKey, completeInfo);
		return completeInfo;
	}

	/** ��ü�� �÷��ǿ� �߰��ϱ� ���� �޼ҵ� */
	public CompleteInfo addCompleteInfo(CompleteInfo completeInfo) {
		if (searchCompleteInfo(completeInfo) == null) {
			this.completeInfoMap.put(completeInfo.getCompleteKey(), completeInfo);
			return completeInfo;
		}
		return null;
	}

	/** ���뷮 �ĺ� Ű�� ���� ��ü�� �����ϴ� �޼ҵ� */
	public CompleteInfo deleteCompleteInfo(String completeKey) {
		if (searchCompleteInfo(completeKey) != null) {
			CompleteInfo temp = this.completeInfoMap.get(completeKey);
			this.completeInfoMap.remove(completeKey);
			return temp;
		}
		return null;
	}

	/** �ش� �ʵ�� ��ġ�ϴ� ��ü�� �����ϴ� �޼ҵ� */
	public CompleteInfo deleteCompleteInfo(String completeKey, int completeQty, String completeTime,
			double completeRate) {
		if (searchCompleteInfo(completeKey) != null) {
			CompleteInfo temp = this.completeInfoMap.get(completeKey);
			if (temp.getCompleteQty() == completeQty)
				if (temp.getCompleteTime().equals(completeTime))
					if (temp.getCompleteRate() == completeRate) {
						this.completeInfoMap.remove(completeKey);
						return temp;
					}
		}
		return null;
	}

	/** �ش� ��ü�� �����ϱ� ���� �޼ҵ� */
	public CompleteInfo deleteCompleteInfo(CompleteInfo completeInfo) {
		if (searchCompleteInfo(completeInfo) != null) {
			this.completeInfoMap.remove(completeInfo.getCompleteKey());
			return completeInfo;
		}
		return null;
	}

	/** ���뷮 �ĺ� Ű�� �̿��Ͽ� ���ο� ��ü�� �����ϱ� ���� �޼ҵ� */
	public CompleteInfo updateCompleteInfo(String completeKey, CompleteInfo newCompleteInfo) {
		if (searchCompleteInfo(newCompleteInfo) == null) {
			this.completeInfoMap.remove(completeKey);
			this.completeInfoMap.put(newCompleteInfo.getCompleteKey(), newCompleteInfo);
			return newCompleteInfo;
		}
		return null;
	}

	/** ��� �ʵ带 �����ϱ� ���� �޼ҵ� */
	public CompleteInfo updateCompleteInfo(String completeKey, int newCompleteQty, String newCompleteTime,
			double newCompleteRate) {
		if (searchCompleteInfo(completeKey) == null) {
			CompleteInfo completeInfo = this.completeInfoMap.get(completeKey);
			completeInfo.setCompleteQty(newCompleteQty);
			completeInfo.setCompleteTime(newCompleteTime);
			completeInfo.setCompleteRate(newCompleteRate);
			return completeInfo;
		}
		return null;
	}

	/** �ش� ��ü�� ���ο� ��ü�� �����ϱ� ���� �޼ҵ� */
	public CompleteInfo updateCompleteInfo(CompleteInfo completeInfo, CompleteInfo newCompleteInfo) {
		if (searchCompleteInfo(newCompleteInfo) == null) {
			this.completeInfoMap.remove(completeInfo.getCompleteKey());
			this.completeInfoMap.put(newCompleteInfo.getCompleteKey(), newCompleteInfo);
			return newCompleteInfo;
		}
		return null;
	}

	/** ���뷮 �ĺ� Ű ���� ���뷮�� �����ϱ� ���� �޼ҵ� */
	public CompleteInfo updateCompleteQty(String completeKey, int newCompleteQty) {
		CompleteInfo completeInfo = this.completeInfoMap.get(completeKey);
		if (completeInfo != null) {
			completeInfo.setCompleteQty(newCompleteQty);
			return completeInfo;
		}
		return null;
	}

	/** ���뷮 �ĺ� Ű�� ���� �н� �ð��� �����ϱ� ���� �޼ҵ� */
	public CompleteInfo updateCompleteTime(String completeKey, String newCompleteTime) {
		CompleteInfo completeInfo = this.completeInfoMap.get(completeKey);
		if (completeInfo != null) {
			completeInfo.setCompleteTime(newCompleteTime);
			return completeInfo;
		}
		return null;
	}

	/** ���뷮 �ĺ� Ű�� ���� ���뵵�� �����ϱ� ���� �޼ҵ� */
	public CompleteInfo updateCompleteRate(String completeKey, double newCompleteRate) {
		CompleteInfo completeInfo = this.completeInfoMap.get(completeKey);
		if (completeInfo != null) {
			completeInfo.setCompleteRate(newCompleteRate);
			return completeInfo;
		}
		return null;
	}

	/** ���뷮 �ĺ� Ű�� ���� �ش� ���뷮 ������ ��ȸ�ϱ� ���� �޼ҵ� */
	public CompleteInfo searchCompleteInfo(String completeKey) {
		return this.completeInfoMap.get(completeKey);
	}

	/** ���뷮 ������ ��ȸ�ϱ� ���� �޼ҵ� */
	public CompleteInfo searchCompleteInfo(CompleteInfo completeInfo) {
		return this.completeInfoMap.get(completeInfo.getCompleteKey());
	}
}
