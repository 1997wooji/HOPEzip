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

	/** 전체 필드를 받는 Constructor */
	public GoalUnitDao(List<GoalUnit> goalUnitList) {
		this.goalUnitList = goalUnitList;
	}

	/** Method Summary */
	/** List를 전달받아 필드에 저장하기 위한 메소드 */
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
			sb.append(i + "번째 goalUnit: " + goalUnitList.get(i).getGoalUnit());
		}
		return sb.toString();
	}

	/** 콜렉션의 개수를 리턴받는 메소드 */
	public int goalUnitCnt() {
		return goalUnitList.size();
	}

	/** 콜렉션을 파일에 저장하는 메소드 */
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

	/** 데이터를 파일로부터 읽어와서 콜렉션에 저장하는 메소드 */
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

	/** GoalUnit을 생성하고 콜렉션에 추가하는 메소드 */
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

	/** Overriding하여 GoalUnit을 생성하고 콜렉션에 추가하는 메소드 */
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
	 * 콜렉션의 GoalUnit을 제거하기 위해서 전달 받은 파라미터를 검사하여 일치하는 GoalUnit을 콜렉션에서 제거하는 사용하는
	 * 메소드
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
	 * 콜렉션의 GoalUnit을 제거하기 위해서 전달 받은 파라미터를 검사하여 일치하는 GoalUnit을 콜렉션에서 제거하는 사용하는
	 * 메소드
	 */
	public GoalUnit deleteGoalUnit(GoalUnit goalUnit) {
		GoalUnit gu = searchGoalUnit(goalUnit);
		if (gu == null) {
			return null;
		}
		goalUnitList.remove(goalUnitList.indexOf(gu));
		return gu;
	}

	/** 콜렉션의 index에 저장되어있는 Element를 제거하기 위한 메소드 */
	public GoalUnit deleteGoalUnit(int index) {
		GoalUnit gu = searchGoalUnit(index);
		if (gu == null) {
			return null;
		}
		goalUnitList.remove(index);
		return null;
	}

	/** 콜렉션에 goalUnit을 생성하여 해당 인덱스의 Element 정보를 수정하기 위한 메소드 */
	public GoalUnit updateGoalUnit(int index, String goalUnit) {
		GoalUnit gu = searchGoalUnit(index);
		if (gu == null) {
			return null;
		}
		goalUnitList.get(index).setGoalUnit(goalUnit);
		gu = goalUnitList.get(index);
		return gu;
	}

	/** 파라미터로 전달받은 index에 해당하는 Element를 GoalUnit으로 수정하기 위한 메소드 */
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
	 * 파라미터 goalUnit과 일치하는 콜렉션 내의 GoalUnit을 찾아 파라미터 newGoalUnit으로 수정하기 위한 메소드
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
	 * 파라미터 goalIUnit과 일치하는 콜렉션 내의 GoalUnit을 찾아 파라미터 newGoalUnit객체로 수정하기 위한 메소드
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

	/** 인덱스로 콜렉션의 GoalUnit을 찾기위한 메소드 */
	public GoalUnit searchGoalUnit(int index) {
		if (index < 0 || goalUnitList.size() < index) {
			return null;
		}
		if (goalUnitList.get(index) == null) {
			return null;
		}
		return goalUnitList.get(index);
	}

	/** 파라미터 goalUnit과 일치하는 콜렉션 내의 GoalUnit을 찾아 객체를 반환해 주기 위한 메소드 */
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

	/** 파라미터 goalUnit과 일치하는 콜렉션 내의 GoalUnit을 찾아 객체를 반환해 주기 위한 메소드 */
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
