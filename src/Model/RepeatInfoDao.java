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

/** class RepeatInfoDao : 반복 설정에게 접근하기 위한 클래스 */
public class RepeatInfoDao {

	/** 반복 설정들을 여러 개 저장하는 필드. */
	private Map<String, RepeatInfo> repeatInfoMap;
	private static RepeatInfoDao repeatInfoDao;

	static {
		repeatInfoDao = new RepeatInfoDao();
	}

	public static RepeatInfoDao getInstance() {
		return repeatInfoDao;
	}

	/** 네트워크를 위한 생성자. */
	public RepeatInfoDao() {
		this.repeatInfoMap = new HashMap<String, RepeatInfo>();
	}

	/** 반복 설정들을 저장하는 Map을 받는 생성자. */
	public RepeatInfoDao(Map<String, RepeatInfo> repeatInfoMap) {
		if (this.repeatInfoMap == null)
			this.repeatInfoMap = new HashMap<String, RepeatInfo>();
		setRepeatInfoMap(repeatInfoMap);
	}

	public Object[] getRepeatInfoMap() {
		return repeatInfoMap.values().toArray();
	}

	/** 반복 설정들을 저장하는 Map을 설정하는 메소드. */
	public void setRepeatInfoMap(Map<String, RepeatInfo> repeatInfoMap) {
		if (repeatInfoMap != null)
			this.repeatInfoMap = repeatInfoMap;
	}

	/** 문자열을 반환하는 메소드. */
	public String toString() {
		return "RepeatInfoDao [repeatInfoMap=" + repeatInfoMap + "]";
	}

	/** repeatInfoMap의 사이즈를 반환하는 메소드 */
	public int repeatInfoCnt() {
		return repeatInfoMap.size();
	}

	/** 반복 설정들을 파일에 저장하기 위한 메소드 */
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

	/** 반복 설정들을 파일에서 읽어오기 위한 메소드 */
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

	/** 반복 설정을 Map에 추가하기 위해 이용하는 메소드. 반복 설정 정보를 받아 추가한다. */
	public RepeatInfo addRepeatInfo(byte repeatTerm, byte[] repeatDay) {
		RepeatInfo repeatInfo = new RepeatInfo(repeatTerm, repeatDay);
		return addRepeatInfo(repeatInfo);
	}

	/** 반복 설정을 Map에 추가하기 위해 이용하는 메소드. 객체 타입을 받아 추가한다. */
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

	/** Map에서 반복 설정을 삭제하기 위해 이용하는 메소드. 반복 식별 키를 받아 삭제한다. */
	public RepeatInfo deleteRepeatInfo(String repeatKey) {
		if (repeatKey == null)
			return null;
		if (searchRepeatInfo(repeatKey) == null)
			return null;
		RepeatInfo delRepeatInfo = repeatInfoMap.remove(repeatKey);
		if (delRepeatInfo == null)
			return null;
		return delRepeatInfo; // 삭제 성공한 repeatInfo를 반환
	}

	/** Map에서 반복 설정을 삭제하기 위해 이용하는 메소드. 객체를 받아 삭제한다. */
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

	/** Map에서 반복 설정을 삭제하기 위해 이용하는 메소드. 반복 정보를 받아 일치하는 것을 삭제한다. */
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

	/** Map에서 반복 설정을 수정하기 위해 이용하는 메소드. 식별키로 반복 설정을 찾아 수정한다. */
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

	/** Map에서 반복 설정을 수정하기 위해 이용하는 메소드. 식별키로 반복 설정을 찾아 수정한다. */
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

	/** Map에서 반복 설정을 수정하기 위해 이용하는 메소드. 객체로 반복 설정을 찾아 수정한다. */
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

	/** Map에서 반복 설정을 수정하기 위해 이용하는 메소드. 식별키로 반복 설정을 찾아 반복 기간만 수정한다. */
	public RepeatInfo updateRepeatTerm(String repeatKey, byte newRepeatTerm) {
		RepeatInfo updateRepeatInfo = null;
		if (repeatKey == null)
			return null;
		if ((updateRepeatInfo = searchRepeatInfo(repeatKey)) == null)
			return null;
		updateRepeatInfo.setRepeatTerm(newRepeatTerm);
		return updateRepeatInfo;
	}

	/** Map에서 반복 설정을 수정하기 위해 이용하는 메소드. 식별키로 반복 설정을 찾아 반복 요일만 수정한다. */
	public RepeatInfo updateRepeatDay(String repeatKey, byte[] newRepeatDay) {
		RepeatInfo updateRepeatInfo = null;
		if (repeatKey == null || newRepeatDay == null)
			return null;
		if ((updateRepeatInfo = searchRepeatInfo(repeatKey)) == null)
			return null;
		updateRepeatInfo.setRepeatDay(newRepeatDay);
		return updateRepeatInfo;
	}

	/** Map에서 반복 설정을 찾기위해 이용하는 메소드. 식별키로 반복 설정을 찾는다. */
	public RepeatInfo searchRepeatInfo(String repeatKey) {
		if (repeatKey == null)
			return null;
		return repeatInfoMap.get(repeatKey);
	}

	/** Map에서 반복 설정을 찾기위해 이용하는 메소드. 객체로 반복 설정을 찾는다. */
	public RepeatInfo searchRepeatInfo(RepeatInfo repeatInfo) {
		if (repeatInfo == null)
			return null;
		if (!(repeatInfoMap.containsValue(repeatInfo))) // false이면
			return null;
		return repeatInfoMap.get(repeatInfo.getRepeatKey());
	}

	/** Map에서 반복 설정을 찾기위해 이용하는 메소드. 반복 기간으로 반복 설정들을 찾는다. */
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

	/** Map에서 반복 설정을 찾기위해 이용하는 메소드. 반복 요일로 반복 설정들을 찾는다. */
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