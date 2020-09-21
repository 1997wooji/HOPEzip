package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** class RepeatInfoDao : �ݺ� �������� �����ϱ� ���� Ŭ���� */
public class RepeatInfoDao {

	/** �ݺ� �������� ���� �� �����ϴ� �ʵ�. */
	private Map<String, RepeatInfo> repeatInfoMap;
	private static RepeatInfoDao repeatInfoDao;

	static {
		repeatInfoDao = new RepeatInfoDao();
	}

	public static RepeatInfoDao getInstance() {
		return repeatInfoDao;
	}

	/** ��Ʈ��ũ�� ���� ������. */
	public RepeatInfoDao() {
		this.repeatInfoMap = new HashMap<String, RepeatInfo>();
	}

	/** �ݺ� �������� �����ϴ� Map�� �޴� ������. */
	public RepeatInfoDao(Map<String, RepeatInfo> repeatInfoMap) {
		if (this.repeatInfoMap == null)
			this.repeatInfoMap = new HashMap<String, RepeatInfo>();
		setRepeatInfoMap(repeatInfoMap);
	}

	public Object[] getRepeatInfoMap() {
		return repeatInfoMap.values().toArray();
	}

	/** �ݺ� �������� �����ϴ� Map�� �����ϴ� �޼ҵ�. */
	public void setRepeatInfoMap(Map<String, RepeatInfo> repeatInfoMap) {
		if (repeatInfoMap != null)
			this.repeatInfoMap = repeatInfoMap;
	}

	/** ���ڿ��� ��ȯ�ϴ� �޼ҵ�. */
	public String toString() {
		return "RepeatInfoDao [repeatInfoMap=" + repeatInfoMap + "]";
	}

	/** repeatInfoMap�� ����� ��ȯ�ϴ� �޼ҵ� */
	public int repeatInfoCnt() {
		return repeatInfoMap.size();
	}

	/** �ݺ� �������� ���Ͽ� �����ϱ� ���� �޼ҵ� */
	public boolean saveRepeatInfoMap() throws IOException {
		File file = new File("c:/data/RepeatInfo.txt");
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		if (fw == null || pw == null) {
			pw.close();
			fw.close();
			return false;
		}

		Iterator<RepeatInfo> iterator = repeatInfoMap.values().iterator();
		RepeatInfo repeatInfo = null;

		while (iterator.hasNext()) {
			repeatInfo = iterator.next();
			byte[] temp = repeatInfo.getRepeatDay();
			pw.println(repeatInfo.getRepeatKey() + "/" + repeatInfo.getRepeatTerm() + "/" + temp[0] + "," + temp[1] + "," + temp[2] + "," + temp[3] + "," + temp[4] + "," + temp[5] + "," + temp[6]);
		}

		pw.flush();
		pw.close();
		fw.close();

		return true;

	}

	/** �ݺ� �������� ���Ͽ��� �о���� ���� �޼ҵ� */
	public boolean loadRepeatInfoMap() throws IOException {
		File file = new File("c:/data/RepeatInfo.txt");
		if (!file.exists())
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
		String[] repeatDay = null;
		boolean flag = true;
		RepeatInfo test = null;

		while ((str = br.readLine()) != null) {
			result = str.split("/");
			repeatDay = result[2].split(",");
			byte[] repeatByteDay = new byte[7];
			for (int i = 0; i < 7; i++) {
				repeatByteDay[i] = Byte.parseByte(repeatDay[i]);
			}

			RepeatInfo repeatInfo = new RepeatInfo(result[0], Byte.parseByte(result[1]), repeatByteDay);

			test = addRepeatInfo(repeatInfo);
			if (test == null)
				flag = false;
		}

		br.close();
		fr.close();

		return flag;

	}

	/** �ݺ� ������ Map�� �߰��ϱ� ���� �̿��ϴ� �޼ҵ�. �ݺ� ���� ������ �޾� �߰��Ѵ�. */
	public RepeatInfo addRepeatInfo(byte repeatTerm, byte[] repeatDay) {
		RepeatInfo repeatInfo = new RepeatInfo(repeatTerm, repeatDay);
		return addRepeatInfo(repeatInfo);
	}

	/** �ݺ� ������ Map�� �߰��ϱ� ���� �̿��ϴ� �޼ҵ�. ��ü Ÿ���� �޾� �߰��Ѵ�. */
	public RepeatInfo addRepeatInfo(RepeatInfo repeatInfo) {
		if (repeatInfo == null) {
			return null;
			
		}
		if (searchRepeatInfo(repeatInfo.getRepeatKey()) != null) {
			return null;
			
		}
		repeatInfoMap.put(repeatInfo.getRepeatKey(), repeatInfo);
		return repeatInfo;

	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. �ݺ� �ĺ� Ű�� �޾� �����Ѵ�. */
	public RepeatInfo deleteRepeatInfo(String repeatKey) {
		if (repeatKey == null)
			return null;
		if (searchRepeatInfo(repeatKey) == null)
			return null;
		RepeatInfo delRepeatInfo = repeatInfoMap.remove(repeatKey);
		if (delRepeatInfo == null)
			return null;
		return delRepeatInfo; // ���� ������ repeatInfo�� ��ȯ
	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. ��ü�� �޾� �����Ѵ�. */
	public RepeatInfo deleteRepeatInfo(RepeatInfo repeatInfo) {
		if (repeatInfo == null)
			return null;
		if (searchRepeatInfo(repeatInfo) == null)
			return null;
		RepeatInfo delRepeatInfo = repeatInfoMap.remove(repeatInfo.getRepeatKey());
		if (delRepeatInfo == null)
			return null;
		return delRepeatInfo;

	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. �ݺ� ������ �޾� ��ġ�ϴ� ���� �����Ѵ�. */
	public RepeatInfo deleteRepeatInfo(String repeatKey, byte repeatTerm, byte[] repeatDay) {
		if (repeatKey == null || repeatDay == null)
			return null;
		if (searchRepeatInfo(new RepeatInfo(repeatKey, repeatTerm, repeatDay)) == null)
			return null;
		RepeatInfo delRepeatInfo = repeatInfoMap.remove(repeatKey);
		if (delRepeatInfo == null)
			return null;
		return delRepeatInfo;

	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. �ĺ�Ű�� �ݺ� ������ ã�� �����Ѵ�. */
	public RepeatInfo updateRepeatInfo(String repeatKey, byte newRepeatTerm, byte[] newRepeatDay) {
		RepeatInfo updateRepeatInfo = null;
		if (repeatKey == null || newRepeatDay == null)
			return null;
		if ((updateRepeatInfo = searchRepeatInfo(repeatKey)) == null)
			return null;
		updateRepeatInfo.setRepeatTerm(newRepeatTerm);
		updateRepeatInfo.setRepeatDay(newRepeatDay);

		return updateRepeatInfo;

	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. �ĺ�Ű�� �ݺ� ������ ã�� �����Ѵ�. */
	public RepeatInfo updateRepeatInfo(String repeatKey, RepeatInfo newRepeatInfo) {
		if (repeatKey == null || newRepeatInfo == null)
			return null;
		if (repeatKey != newRepeatInfo.getRepeatKey())
			return null;
		if (searchRepeatInfo(repeatKey) == null)
			return null;
		if (repeatInfoMap.replace(repeatKey, newRepeatInfo) == null)
			return null;
		return newRepeatInfo;
	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. ��ü�� �ݺ� ������ ã�� �����Ѵ�. */
	public RepeatInfo updateRepeatInfo(RepeatInfo repeatInfo, RepeatInfo newRepeatInfo) {
		if (repeatInfo == null || newRepeatInfo == null)
			return null;
		if (repeatInfo.getRepeatKey() != newRepeatInfo.getRepeatKey())
			return null;
		if (searchRepeatInfo(repeatInfo) == null)
			return null;
		if (repeatInfoMap.replace(repeatInfo.getRepeatKey(), newRepeatInfo) == null)
			return null;
		return newRepeatInfo;
	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. �ĺ�Ű�� �ݺ� ������ ã�� �ݺ� �Ⱓ�� �����Ѵ�. */
	public RepeatInfo updateRepeatTerm(String repeatKey, byte newRepeatTerm) {
		RepeatInfo updateRepeatInfo = null;
		if (repeatKey == null)
			return null;
		if ((updateRepeatInfo = searchRepeatInfo(repeatKey)) == null)
			return null;
		updateRepeatInfo.setRepeatTerm(newRepeatTerm);
		return updateRepeatInfo;
	}

	/** Map���� �ݺ� ������ �����ϱ� ���� �̿��ϴ� �޼ҵ�. �ĺ�Ű�� �ݺ� ������ ã�� �ݺ� ���ϸ� �����Ѵ�. */
	public RepeatInfo updateRepeatDay(String repeatKey, byte[] newRepeatDay) {
		RepeatInfo updateRepeatInfo = null;
		if (repeatKey == null || newRepeatDay == null)
			return null;
		if ((updateRepeatInfo = searchRepeatInfo(repeatKey)) == null)
			return null;
		updateRepeatInfo.setRepeatDay(newRepeatDay);
		return updateRepeatInfo;
	}

	/** Map���� �ݺ� ������ ã������ �̿��ϴ� �޼ҵ�. �ĺ�Ű�� �ݺ� ������ ã�´�. */
	public RepeatInfo searchRepeatInfo(String repeatKey) {
		if (repeatKey == null)
			return null;
		return repeatInfoMap.get(repeatKey);
	}

	/** Map���� �ݺ� ������ ã������ �̿��ϴ� �޼ҵ�. ��ü�� �ݺ� ������ ã�´�. */
	public RepeatInfo searchRepeatInfo(RepeatInfo repeatInfo) {
		if (repeatInfo == null)
			return null;
		if (!(repeatInfoMap.containsValue(repeatInfo))) // false�̸�
			return null;
		return repeatInfoMap.get(repeatInfo.getRepeatKey());
	}

	/** Map���� �ݺ� ������ ã������ �̿��ϴ� �޼ҵ�. �ݺ� �Ⱓ���� �ݺ� �������� ã�´�. */
	public RepeatInfo[] searchRepeatTerm(byte repeatTerm) {
		List<RepeatInfo> repeatInfos = new ArrayList<RepeatInfo>();
		Iterator<RepeatInfo> iterator = repeatInfoMap.values().iterator();
		RepeatInfo repeatInfo = null;
		while (iterator.hasNext()) {
			repeatInfo = iterator.next();
			if (repeatInfo.getRepeatTerm() == repeatTerm)
				repeatInfos.add(repeatInfo);

		}
		if (repeatInfos.size() == 0)
			return null;

		Object[] arr = repeatInfos.toArray();
		RepeatInfo[] repeatArr = new RepeatInfo[arr.length];
		for (int i = 0; i < arr.length; i++) {
			repeatArr[i] = (RepeatInfo) arr[i];
		}
		return repeatArr;

	}

	/** Map���� �ݺ� ������ ã������ �̿��ϴ� �޼ҵ�. �ݺ� ���Ϸ� �ݺ� �������� ã�´�. */
	public RepeatInfo[] searchRepeatDay(byte[] repeatDay) {
		List<RepeatInfo> repeatInfos = new ArrayList<RepeatInfo>();
		Iterator<RepeatInfo> iterator = repeatInfoMap.values().iterator();
		RepeatInfo repeatInfo = null;
		while (iterator.hasNext()) {
			repeatInfo = iterator.next();
			if (Arrays.equals(repeatInfo.getRepeatDay(), repeatDay))
				repeatInfos.add(repeatInfo);

		}
		if (repeatInfos.size() == 0)
			return null;

		Object[] arr = repeatInfos.toArray();
		RepeatInfo[] repeatArr = new RepeatInfo[arr.length];
		for (int i = 0; i < arr.length; i++) {
			repeatArr[i] = (RepeatInfo) arr[i];
		}
		return repeatArr;

	}

}