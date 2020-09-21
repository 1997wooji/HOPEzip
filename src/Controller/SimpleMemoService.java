package Controller;

import Model.SimpleMemoInfo;
import Model.SimpleMemoInfoDao;

public class SimpleMemoService {
	private SimpleMemoInfoDao simpleMemoInfoDao;

	public SimpleMemoService() {
		this.simpleMemoInfoDao = SimpleMemoInfoDao.getInstance();
	}

	public String[] simpleMemoLoad() {
		Object[] simpleMemoList = simpleMemoInfoDao.getSimpleMemoInfoList();
		String[] str = new String[simpleMemoList.length];
		for (int i = 0; i < simpleMemoList.length; i++) {
			SimpleMemoInfo simpleMemoInfo = (SimpleMemoInfo) simpleMemoList[i];
			// 객체에서 정보 빼서 스트링 배열에 넣기(/ 구분하여 합쳐서)
			str[i] = simpleMemoInfo.getSimpleMemoKey() + "/" + simpleMemoInfo.getSimpleMemoName() + "/"
					+ simpleMemoInfo.isChecked();
		}
		return str;
	}

	public boolean addSimpleMemo(String simpleMemoName) {
		if (simpleMemoName == null || simpleMemoName == "") {
			return false;
		}
		boolean flag = false;
		flag = simpleMemoInfoDao.addSimpleMemoInfo(simpleMemoName, false);
		return flag;
	}

	/*
	 * public boolean addSimpleMemo(String simpleMemoNumber, String
	 * simpleMemoName){ simpleMemoInfoDao.addSimpleMemoInfo(simpleMemoName,
	 * false); }
	 */
	public boolean deleteSimpleMemo(int index, String simpleMemoName) {
		if (index < 0 || simpleMemoName == null || simpleMemoName == "") {
			return false;
		}
		SimpleMemoInfo simpleMoemoInfo = simpleMemoInfoDao.deleteSimpleMemoInfo(index, simpleMemoName);
		if (simpleMoemoInfo == null) {
			return false;
		}
		return true;
	}

	public boolean updateSimpleMemo(int index, String simpleMemoName, String newSimpleMemoName) {
		if (index < 0 || simpleMemoName == null || simpleMemoName == "" || newSimpleMemoName == null
				|| newSimpleMemoName == "") {
			return false;
		}
		SimpleMemoInfo flag = simpleMemoInfoDao.updateSimpleMemoName(index, simpleMemoName, newSimpleMemoName);
		if (flag == null) {
			return false;
		}
		return true;
	}

	public boolean checkSimpleMemo(int index, String simpleMemoName) {
		if (index < 0 || simpleMemoName == null || simpleMemoName == "") {
			return false;
		}
		SimpleMemoInfo flag = simpleMemoInfoDao.updateIsChecked(index, simpleMemoName);
		if (flag == null) {
			return false;
		}
		return true;
	}

}
