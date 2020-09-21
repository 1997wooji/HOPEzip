package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GoalUnitDao {

	private List<GoalUnit> goalUnitList;
	private static GoalUnitDao goalUnitDao;
	static {
		goalUnitDao = new GoalUnitDao();
	}

	public static GoalUnitDao getInstance() {
		return goalUnitDao;
	}

	/** Constructor Summary */
	/** Null Parameter Constructor */
	public GoalUnitDao() {
		this(new ArrayList<GoalUnit>());
	}

	/** ��ü �ʵ带 �޴� Constructor */
	public GoalUnitDao(List<GoalUnit> goalUnitList) {
		this.goalUnitList = goalUnitList;
	}

	/** Method Summary */
	/** List�� ���޹޾� �ʵ忡 �����ϱ� ���� �޼ҵ� */
	public void setGoalUnitList(List<GoalUnit> goalUnitList) {
		this.goalUnitList = goalUnitList;
	}

	public Object[] getGoalUnitList() {
		return goalUnitList.toArray();
	}

	/** toString OverLoading Method */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < goalUnitList.size(); i++) {
			sb.append(i + "��° goalUnit: " + goalUnitList.get(i).getGoalUnit());
		}
		return sb.toString();
	}

	/** �ݷ����� ������ ���Ϲ޴� �޼ҵ� */
	public int goalUnitCnt() {
		return goalUnitList.size();
	}

	/** �ݷ����� ���Ͽ� �����ϴ� �޼ҵ� */
	public boolean saveGoalUnitList() throws Exception {
		File file = new File("c:/data/GoalUnit.txt");
		file.createNewFile();

		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		if (fw == null || pw == null) {
			return false;
		}
		for (int i = 0; i < goalUnitList.size(); i++) {
			pw.println(goalUnitList.get(i).getGoalUnit());
		}

		pw.flush();
		pw.close();
		fw.close();

		return true;
	}

	/** �����͸� ���Ϸκ��� �о�ͼ� �ݷ��ǿ� �����ϴ� �޼ҵ� */
	public boolean loadGoalUnitList() throws Exception {
		File file = new File("c:/data/GoalUnit.txt");
		if(!file.exists())
			file.createNewFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		if (fr == null || br == null)
			return false;
		String str = null;

		while ((str = br.readLine()) != null) {
			addGoalUnit(str);
		}
		
		br.close();
		return true;
	}

	/** GoalUnit�� �����ϰ� �ݷ��ǿ� �߰��ϴ� �޼ҵ� */
	public GoalUnit addGoalUnit(String goalUnit) {
		if (goalUnit == null || goalUnit.equals("")) {
			return null;
		}
		GoalUnit gu = searchGoalUnit(goalUnit);
		if (gu != null) {
			return null;
		}

		goalUnitList.add(new GoalUnit(goalUnit));

		return gu;
	}

	/** Overriding�Ͽ� GoalUnit�� �����ϰ� �ݷ��ǿ� �߰��ϴ� �޼ҵ� */
	public GoalUnit addGoalUnit(GoalUnit goalUnit) {
		if (goalUnit == null) {
			return null;
		}
		GoalUnit gu = searchGoalUnit(goalUnit);
		if (gu != null) {
			return null;
		}

		goalUnitList.add(goalUnit);

		return goalUnit;
	}

	/**
	 * �ݷ����� GoalUnit�� �����ϱ� ���ؼ� ���� ���� �Ķ���͸� �˻��Ͽ� ��ġ�ϴ� GoalUnit�� �ݷ��ǿ��� �����ϴ� ����ϴ�
	 * �޼ҵ�
	 */
	public GoalUnit deleteGoalUnit(String goalUnit) {
		GoalUnit gu = searchGoalUnit(goalUnit);
		if (gu == null) {
			return null;
		}
		goalUnitList.remove(goalUnitList.indexOf(gu));
		return gu;
	}

	/**
	 * �ݷ����� GoalUnit�� �����ϱ� ���ؼ� ���� ���� �Ķ���͸� �˻��Ͽ� ��ġ�ϴ� GoalUnit�� �ݷ��ǿ��� �����ϴ� ����ϴ�
	 * �޼ҵ�
	 */
	public GoalUnit deleteGoalUnit(GoalUnit goalUnit) {
		GoalUnit gu = searchGoalUnit(goalUnit);
		if (gu == null) {
			return null;
		}
		goalUnitList.remove(goalUnitList.indexOf(gu));
		return gu;
	}

	/** �ݷ����� index�� ����Ǿ��ִ� Element�� �����ϱ� ���� �޼ҵ� */
	public GoalUnit deleteGoalUnit(int index) {
		GoalUnit gu = searchGoalUnit(index);
		if (gu == null) {
			return null;
		}
		goalUnitList.remove(index);
		return null;
	}

	/** �ݷ��ǿ� goalUnit�� �����Ͽ� �ش� �ε����� Element ������ �����ϱ� ���� �޼ҵ� */
	public GoalUnit updateGoalUnit(int index, String goalUnit) {
		GoalUnit gu = searchGoalUnit(index);
		if (gu == null) {
			return null;
		}
		goalUnitList.get(index).setGoalUnit(goalUnit);
		gu = goalUnitList.get(index);
		return gu;
	}

	/** �Ķ���ͷ� ���޹��� index�� �ش��ϴ� Element�� GoalUnit���� �����ϱ� ���� �޼ҵ� */
	public GoalUnit updateGoalUnit(int index, GoalUnit goalUnit) {
		GoalUnit gu = searchGoalUnit(index);
		if (gu == null) {
			return null;
		}
		goalUnitList.add(index, goalUnit);
		gu = goalUnitList.get(index);
		return gu;
	}

	/**
	 * �Ķ���� goalUnit�� ��ġ�ϴ� �ݷ��� ���� GoalUnit�� ã�� �Ķ���� newGoalUnit���� �����ϱ� ���� �޼ҵ�
	 */
	public GoalUnit updateGoalUnit(String goalUnit, String newGoalUnit) {
		GoalUnit gu = searchGoalUnit(goalUnit);
		if (gu == null) {
			return null;
		}
		goalUnitList.get(goalUnitList.indexOf(gu)).setGoalUnit(newGoalUnit);
		return goalUnitList.get(goalUnitList.indexOf(gu));
	}

	/**
	 * �Ķ���� goalIUnit�� ��ġ�ϴ� �ݷ��� ���� GoalUnit�� ã�� �Ķ���� newGoalUnit��ü�� �����ϱ� ���� �޼ҵ�
	 */
	public GoalUnit updateGoalUnit(String goalUnit, GoalUnit newGoalUnit) {
		GoalUnit gu = searchGoalUnit(goalUnit);
		if (gu == null) {
			return null;
		}
		goalUnitList.add(goalUnitList.indexOf(gu), newGoalUnit);
		goalUnitList.remove(goalUnitList.indexOf(gu));
		return newGoalUnit;
	}

	/** �ε����� �ݷ����� GoalUnit�� ã������ �޼ҵ� */
	public GoalUnit searchGoalUnit(int index) {
		if (index < 0 || goalUnitList.size() < index) {
			return null;
		}
		if (goalUnitList.get(index) == null) {
			return null;
		}
		return goalUnitList.get(index);
	}

	/** �Ķ���� goalUnit�� ��ġ�ϴ� �ݷ��� ���� GoalUnit�� ã�� ��ü�� ��ȯ�� �ֱ� ���� �޼ҵ� */
	public GoalUnit searchGoalUnit(String goalUnit) {
		if (goalUnit == null || goalUnit.equals("")) {
			return null;
		}
		for (int i = 0; i < goalUnitList.size(); i++) {
			if (goalUnitList.get(i).getGoalUnit().equals(goalUnit)) {
				return goalUnitList.get(i);
			}
		}
		return null;
	}

	/** �Ķ���� goalUnit�� ��ġ�ϴ� �ݷ��� ���� GoalUnit�� ã�� ��ü�� ��ȯ�� �ֱ� ���� �޼ҵ� */
	public GoalUnit searchGoalUnit(GoalUnit goalUnit) {
		if (goalUnit == null) {
			return null;
		}
		for (int i = 0; i < goalUnitList.size(); i++) {
			if (goalUnitList.get(i).equals(goalUnit)) {
				return goalUnitList.get(i);
			}
		}
		return null;
	}

}
