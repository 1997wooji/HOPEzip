package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** �����Ͽ� ���� ������ ������ �ִ� Ŭ���� */
public class ExeDateInfo {
	private int completeKeyCnt;
	private String goalKey;
	/** ���뷮 0���� �ĺ� Ű 1�� �� ������ �ٿ��� �����ϴ� �ʵ� */
	private List<String[]> exeKeyList;

	/** null parameter Constructor */
	public ExeDateInfo() {
		exeKeyList = new ArrayList<String[]>();
	}

	/** ���뷮 �ĺ�Ű ���� , ��ǥŰ, ����Ű�� �Ķ���ͷ� ���� ������ */
	public ExeDateInfo(String goalKey, String[] exeDates) {
		this.exeKeyList = new ArrayList<String[]>();
		this.goalKey = goalKey;
		completeKeyCnt = exeDates.length;
		for (int i = 0; i < exeDates.length; i++) {
			CompleteInfo ci = new CompleteInfo(0, "0", 0.0);
			CompleteInfoDao.getInstance().addCompleteInfo(ci);
			addExeKey(new String[] { ci.getCompleteKey(), exeDates[i] });
		}
	}

	public ExeDateInfo(int completeKeyCnt, String goalKey, String[] exeKeys) {
		this.exeKeyList = new ArrayList<String[]>();
		setCompleteKeyCnt(completeKeyCnt);
		setGoalKey(goalKey);
		setExeKeyList(exeKeys);
	}

//	public ExeDateInfo(int completeKeyCnt, String goalKey, String[][] exeKeys) {
//		this.exeKeyList = new ArrayList<String[]>();
//		setCompleteKeyCnt(completeKeyCnt);
//		setGoalKey(goalKey);
//		List<String[]> exeKeysToList = new ArrayList<String[]>();
//		setExeKeyList(exeKeysToList);
//		for (String[] s : exeKeys) {
//			exeKeysToList.add(s);
//		}
//	}

	/*
	 * �� �� public ExeDateInfo(String goalKey, String startDate, String endDate,
	 * byte repeatTerm) {
	 * 
	 * } public ExeDateInfo(String goalKey, String startDate, String endDate,
	 * byte[] repeatDay) {
	 * 
	 * }
	 */
	/** ���뷮 �ĺ�Ű ������ ���� getter */
	public int getCompleteKeyCnt() {
		return completeKeyCnt;
	}

	/** ��ǥŰ�� ���� getter */
	public String getGoalKey() {
		return goalKey;
	}

	/** exeKey�� String�迭�� ��ȯ */
	public String[] getExeKeyList() {
		String[] returnKeyList = new String[exeKeyList.size()];

		for (int i = 0; i < exeKeyList.size(); i++) {
			returnKeyList[i] = exeKeyList.get(i)[0] + "/" + exeKeyList.get(i)[1];
		}
		return returnKeyList;

	}

	/** CompleteKeyCnt�� setter */
	public void setCompleteKeyCnt(int completeKeyCnt) {
		if (completeKeyCnt >= 0)
			this.completeKeyCnt = completeKeyCnt;
	}

	/** GoalKey�� setter */
	private void setGoalKey(String goalKey) {
		if (goalKey != null)
			this.goalKey = goalKey;
	}

	/** exeKey�� setter */
	public void setExeKeyList(String[] exeKeyList) {
		if (exeKeyList != null) {
			this.exeKeyList.clear();
			for(int i = 0; i < exeKeyList.length; i++) {
				String[] temp = exeKeyList[i].split("/");
				this.exeKeyList.add(temp);
			}
		}
	}

	@Override
	public String toString() {
		return "ExeDateInfo [completeKeyCnt=" + completeKeyCnt + ", goalKey=" + goalKey + ", exeKeyList=" + exeKeyList
				+ "]";
	}

	/** exeKey List�� ũ�⸦ ���� �� �ִ� �޼ҵ� exeKey�� getter�� ���� ������ ũ�⸦ ���� �� ���. */
	public int exeSize() {
		return exeKeyList.size();
	}

	/** exeKey list�� ���� �߰��� ��� ��� */
	public String[] addExeKey(String[] exeKey) {
		if (exeKey == null || exeKey.length != 2)
			return null;
		if (searchCompleteKey(exeKey[0]) != null)
			return null;
		this.exeKeyList.add(exeKey);
		return exeKey;

	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� exeKey�� �̿��� ������ ��� ��� */
	public String[] deleteExeKey(String[] exeKey) {

		if (exeKey == null || exeKey.length != 2)
			return null;
		for (int i = 0; i < exeKeyList.size(); i++) {
			if (Arrays.equals(exeKey, exeKeyList.get(i))) {
				completeKeyCnt--;
				return exeKeyList.remove(i);
			}
		}
		return null;
	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� index�� �̿��� ������ ��� ��� */
	public String[] deleteExeKey(int index) {
		if (index < 0)
			return null;
		completeKeyCnt--;
		return exeKeyList.remove(index);
	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� index�� exeKey�� �̿��� ������ ��� ��� */
	public String[] deleteExeKey(int index, String[] exeKey) {

		if (index < 0 || exeKey == null || exeKey.length != 2)
			return null;
		if (index > exeKeyList.size())
			return null;
		if (Arrays.equals(exeKey, exeKeyList.get(index))) {
			completeKeyCnt--;
			return exeKeyList.remove(index);
		}
		return null;
	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� index�� newExeKey�� �̿��� ������ ��� ��� */
	public String[] updateExeKey(int index, String[] newExeKey) {

		if (index < 0 || newExeKey == null || newExeKey.length != 2)
			return null;
		if (index > exeKeyList.size())
			return null;
		exeKeyList.set(index, newExeKey);
		return newExeKey;
	}

	public String[] updateExeKey(String completeKey, String[] newExeKey) {

		if (newExeKey == null || completeKey == null || newExeKey.length != 2)
			return null;
		if (newExeKey[0] != completeKey)
			return null;
		for (int i = 0; i < exeKeyList.size(); i++) {
			if (exeKeyList.get(i)[0] == completeKey) {
				exeKeyList.set(i, newExeKey);
				return newExeKey;
			}
		}
		return null;
	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� exeKey�� newExeKey�� �̿��� ������ ��� ��� */
	public String[] updateExeKey(String[] exeKey, String[] newExeKey) {

		if (exeKey == null || newExeKey == null || exeKey.length != 2 || newExeKey.length != 2)
			return null;

		int index = exeKeyList.indexOf(exeKey);
		if (index == -1)
			return null;

		exeKeyList.set(index, newExeKey);
		return newExeKey;
	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� index�� �̿��� �˻��� ��� ��� */
	public String[] searchExeKey(int index) {
		if (index < 0 || index > exeKeyList.size())
			return null;
		return exeKeyList.get(index);

	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� exeKey�� �̿��� �˻��� ��� ��� */
	public String[] searchExeKey(String[] exeKey) {
		if (exeKey == null || exeKey.length != 2)
			return null;
		for (int i = 0; i < exeKeyList.size(); i++) {
			if (Arrays.equals(exeKey, exeKeyList.get(i)))
				return exeKeyList.get(i);
		}

		return null;
	}
	
	public String searchExeKey(String exeKey) {
		for(int i = 0; i < this.exeKeyList.size(); i++) {
			if(this.exeKeyList.get(i)[1].equals(exeKey)) {
				return this.goalKey;
			}
		}
		return null;		
	}

	/** exeKey list�� ��ϵǾ� �ִ� exeKey�� index�� exeKey�� �̿��� �˻��� ��� ��� */
	public String[] searchExeKey(int index, String[] exeKey) {
		if (index < 0 || index > exeKeyList.size() || exeKey == null || exeKey.length != 2)
			return null;
		if (!(Arrays.equals(exeKey, exeKeyList.get(index))))
			return null;
		return exeKey;
	}

	/** exeKeyList�� ��ϵǾ� �ִ� exeKey���� �Ķ���Ϳ� ������ completeKey�� �ִ��� �˻��� ��� ��� */
	public String searchCompleteKey(String exeDate) {
		for(int i = 0; i < exeKeyList.size(); i++) {
			String[] str = this.exeKeyList.get(i);
			if(str[1].equals(exeDate))
				return str[0];
		}
		return null;
	}

	/** exeKeyList�� ��ϵǾ� �ִ� exeKey���� �Ķ���Ϳ� ������ exeDate�� �ִ��� �˻��� ��� ��� */
	public String[] searchExeDate(String exeDate) {
		for(int i = 0; i < this.exeKeyList.size(); i++) {
			String[] searchData = this.exeKeyList.get(i);
			if(searchData[1].equals(exeDate))
				return searchData;
		}
		return null;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExeDateInfo other = (ExeDateInfo) obj;
		if (completeKeyCnt != other.completeKeyCnt)
			return false;

		if (exeKeyList == null) {
			if (other.exeKeyList != null)
				return false;
		}
		if (goalKey == null) {
			if (other.goalKey != null)
				return false;
		} else if (!goalKey.equals(other.goalKey))
			return false;

		if (other.exeKeyList.size() != exeKeyList.size())
			return false;

		for (int i = 0; i < exeKeyList.size(); i++) {
			if (!(Arrays.equals(exeKeyList.get(i), other.exeKeyList.get(i))))
				return false;
		}

		return true;
	}

}
