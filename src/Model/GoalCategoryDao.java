package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GoalCategoryDao {
	/** 목표 카테고리를 저장할 수 있는 Map */
	private Map<String, GoalCategory> goalCategoryMap;
	private static GoalCategoryDao goalCategoryDao;
	
	static {
		goalCategoryDao = new GoalCategoryDao();
	}
	public static GoalCategoryDao getInstance() {
		return goalCategoryDao;
	}
	/** null parameter constructor */
	public GoalCategoryDao() {
		this(new HashMap<String, GoalCategory>());
	}

	/** parameter로 Map을 받을 수 있는 오버로딩된 생성자 */
	public GoalCategoryDao(Map<String, GoalCategory> goalCategoryMap) {
		if (goalCategoryMap == null) {
			return;
		}
		this.goalCategoryMap = goalCategoryMap;
	}

	/** goalCategoryMap 필드의 setter */
	public void setGoalCategoryMap(Map<String, GoalCategory> goalCategoryMap) {
		if (goalCategoryMap == null) {
			return;
		}
		this.goalCategoryMap = goalCategoryMap;
	}
	public Object[] getGoalCategoryMap() {
		List<String> gc = new ArrayList<String>();
		Iterator<String> iterator = goalCategoryMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			gc.add(this.goalCategoryMap.get(key).getGoalCategory() + "/" + this.goalCategoryMap.get(key).getGoalColor());
		}
		return gc.toArray();
	}

	/** 필드들의 값을 String으로 반환할 수 있는 메소드 */
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		Iterator<String> keyIterator = goalCategoryMap.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			GoalCategory value = goalCategoryMap.get(key);
			strBuilder.append(value.toString());
		}
		return "GoalCategoryDao [goalCategoryMap=" + strBuilder.toString() + "]";
	}

	/** Map의 크기를 구해주는 메소드 */
	public int goalCategoryCnt() {
		return goalCategoryMap.size();
	}

	/** 목표카테고리를 저장할 때 사용하는 메소드 */
	public boolean saveGoalCategoryMap() throws Exception {
		File file = new File("c:/data/GoalCategory.txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);

		if (fw == null || pw == null) {
			pw.close();
			fw.close();
			return false;
		}

		Iterator<GoalCategory> iterator = goalCategoryMap.values().iterator();
		GoalCategory goalCategory = null;

		while (iterator.hasNext()) {
			goalCategory = iterator.next();
			pw.println(goalCategory.getGoalCategory() + "/" + goalCategory.getGoalColor());
		}

		pw.flush();
		pw.close();
		fw.close();

		return true;
	}

	/** 파일에서 목표카테고리를 불러 올 경우 사용 */
	public boolean loadGoalCategoryMap() throws Exception {
		File file = new File("c:/data/GoalCategory.txt");
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
		boolean flag = true;

		while ((str = br.readLine()) != null) {
			result = str.split("/");
			addGoalCategory(result[0], result[1]);
		}

		br.close();
		fr.close();

		return flag;
	}

	/** goalCategory이름과 goalColor를 받아 goalCategory를 추가할 경우 사용 */
	public GoalCategory addGoalCategory(String goalCategory, String goalColor) {
		if (goalCategory == null || goalCategory.equals("") || goalColor == null || goalColor.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc != null) {
			return null;
		}

		GoalCategory gc2 = new GoalCategory(goalCategory, goalColor);
		goalCategoryMap.put(goalCategory, gc2);
		return gc2;
	}

	/** goalCategory를 받아 goalCategory를 추가할 경우 사용 */
	public GoalCategory addGoalCategory(GoalCategory goalCategory) {
		if (goalCategory == null) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc != null) {
			return null;
		}

		goalCategoryMap.put(goalCategory.getGoalCategory(), goalCategory);
		return goalCategoryMap.get(goalCategory.getGoalCategory());
	}

	/** goalCategory와 같은 goalCategory를 삭제할 경우 사용 */
	public GoalCategory deleteGoalCategory(GoalCategory goalCategory) {
		if (goalCategory == null) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		goalCategoryMap.remove(goalCategory.getGoalCategory(), goalCategory);
		return gc;
	}

	/** goalCategory이름과 같은 goalCategory를 삭제할 경우 사용 */
	public GoalCategory deleteGoalCategory(String goalCategory) {
		if (goalCategory == null || goalCategory.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		goalCategoryMap.remove(goalCategory);
		return gc;
	}

	/**
	 * goalCategory이름과 goalColor와 일치하는 goalCategory를 찾아 goalCategory를 삭제할 경우 사용
	 */
	public GoalCategory deleteGoalCategory(String goalCategory, String goalColor) {
		if (goalCategory == null || goalCategory.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		if (gc.getGoalColor().equals(goalColor)) {
			goalCategoryMap.remove(goalCategory);
			return gc;
		}
		return null;
	}

	/**
	 * 기존의 goalCategory이름과 새로운 goalCategory 이름을 기존의 goalCategory를 받아 수정할 경우 사용
	 */
	public GoalCategory updateGoalCategory(String goalCategory, String newGoalCategory) {
		if (goalCategory == null || goalCategory.equals("") || newGoalCategory == null || newGoalCategory.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		goalCategoryMap.get(goalCategory).setGoalCategory(newGoalCategory);
		return goalCategoryMap.get(goalCategory);
	}

	/**
	 * 기존의 goalCategory이름과 새로운 goalCategory이름, goalColor를 받아 기존의 goalCategory를
	 * 수정할 경우 사용.
	 */
	public GoalCategory updateGoalCategory(String goalCategory, String newGoalCategory, String newGoalColor) {
		if (goalCategory == null || goalCategory.equals("") || newGoalCategory == null || newGoalCategory.equals("")
				|| newGoalColor == null || newGoalColor.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		goalCategoryMap.get(goalCategory).setGoalCategory(newGoalCategory);
		goalCategoryMap.get(goalCategory).setGoalColor(newGoalColor);
		return goalCategoryMap.get(goalCategory);
	}

	/** goalCategory를 새로운 goalCategory로 수정할때 사용. */
	public GoalCategory updateGoalCategory(GoalCategory goalCategory, GoalCategory newGoalCategory) {
		if (goalCategory == null || goalCategory.equals("") || newGoalCategory == null || newGoalCategory.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		goalCategoryMap.get(goalCategory.getGoalCategory()).setGoalCategory(newGoalCategory.getGoalCategory());
		goalCategoryMap.get(goalCategory.getGoalCategory()).setGoalColor(newGoalCategory.getGoalColor());
		return goalCategoryMap.get(goalCategory);
	}

	/** goalCategory이름과 새로운 goalCategory로 수정할때 사용. */
	public GoalCategory updateGoalCategory(String goalCategory, GoalCategory newGoalCategory) {
		if (goalCategory == null || goalCategory.equals("") || newGoalCategory == null || newGoalCategory.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		goalCategoryMap.get(goalCategory).setGoalCategory(newGoalCategory.getGoalCategory());
		goalCategoryMap.get(goalCategory).setGoalColor(newGoalCategory.getGoalColor());
		return goalCategoryMap.get(goalCategory);
	}

	/** goalCategory이름과 같은 goalCategory를 찾아 goalColor를 변경할때 사용 */
	public GoalCategory updateGoalColor(String goalCategory, String newGoalColor) {
		if (goalCategory == null || goalCategory.equals("") || newGoalColor == null || newGoalColor.equals("")) {
			return null;
		}
		GoalCategory gc = searchGoalCategory(goalCategory);
		if (gc == null) {
			return null;
		}
		goalCategoryMap.get(goalCategory).setGoalColor(newGoalColor);
		return goalCategoryMap.get(goalCategory);
	}

	/** goalCategory이름으로 goalCategory를 찾을 경우 사용 */
	public GoalCategory searchGoalCategory(String goalCategory) {
		if (goalCategory == null) {
			return null;
		}
		Iterator<String> iterator = goalCategoryMap.keySet().iterator();
		while (iterator.hasNext()) {
			GoalCategory gc = goalCategoryMap.get(iterator.next());
			if (gc.getGoalCategory().equals(goalCategory)) {
				return gc;
			}
		}
		return null;
	}

	/** goalCategory로 goalCategory를 찾을 경우 사용 */
	public GoalCategory searchGoalCategory(GoalCategory goalCategory) {
		if (goalCategory == null) {
			return null;
		}
		Iterator<String> iterator = goalCategoryMap.keySet().iterator();
		while (iterator.hasNext()) {
			GoalCategory gc = goalCategoryMap.get(iterator.next());
			if (gc.getGoalCategory().equals(goalCategory.getGoalCategory())) {
				return gc;
			}
		}
		return null;
	}

	/** goalCategory이름과 goalColor로 goalCategory를 찾을 경우 사용 */
	public GoalCategory searchGoalCategory(String goalCategory, String goalColor) {
		if (!errorCheck(new String[] { goalCategory, goalColor })) {
			return null;
		}
		Iterator<String> iterator = goalCategoryMap.keySet().iterator();
		while (iterator.hasNext()) {
			GoalCategory gc = goalCategoryMap.get(iterator.next());
			if (gc.getGoalCategory().equals(goalCategory) && gc.getGoalColor().equals(goalColor)) {
				return gc;
			}
		}
		return null;
	}

	/** goalColor로 goalCategory를 찾을 경우 사용 */
	public GoalCategory[] searchGoalColor(String goalColor) {
		if (goalColor == null || goalColor.equals("")) {
			return null;
		}
		GoalCategory[] gcs = new GoalCategory[goalCategoryMap.size()];
		Iterator<String> iterator = goalCategoryMap.keySet().iterator();
		int i = 0;
		while (iterator.hasNext()) {
			GoalCategory gc = goalCategoryMap.get(iterator.next());
			if (gc.getGoalColor().equals(goalColor)) {
				gcs[i] = gc;
				i++;
			}
		}
		return gcs;
	}

	public boolean errorCheck(String[] check) {
		for (int i = 0; i < check.length; i++) {
			if (check[i] == null) {
				return true;
			}
		}
		return false;
	}
}
