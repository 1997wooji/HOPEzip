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
	/** ��ǥ ī�װ��� ������ �� �ִ� Map */
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

	/** parameter�� Map�� ���� �� �ִ� �����ε��� ������ */
	public GoalCategoryDao(Map<String, GoalCategory> goalCategoryMap) {
		if (goalCategoryMap == null) {
			return;
		}
		this.goalCategoryMap = goalCategoryMap;
	}

	/** goalCategoryMap �ʵ��� setter */
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

	/** �ʵ���� ���� String���� ��ȯ�� �� �ִ� �޼ҵ� */
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

	/** Map�� ũ�⸦ �����ִ� �޼ҵ� */
	public int goalCategoryCnt() {
		return goalCategoryMap.size();
	}

	/** ��ǥī�װ��� ������ �� ����ϴ� �޼ҵ� */
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

	/** ���Ͽ��� ��ǥī�װ��� �ҷ� �� ��� ��� */
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

	/** goalCategory�̸��� goalColor�� �޾� goalCategory�� �߰��� ��� ��� */
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

	/** goalCategory�� �޾� goalCategory�� �߰��� ��� ��� */
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

	/** goalCategory�� ���� goalCategory�� ������ ��� ��� */
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

	/** goalCategory�̸��� ���� goalCategory�� ������ ��� ��� */
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
	 * goalCategory�̸��� goalColor�� ��ġ�ϴ� goalCategory�� ã�� goalCategory�� ������ ��� ���
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
	 * ������ goalCategory�̸��� ���ο� goalCategory �̸��� ������ goalCategory�� �޾� ������ ��� ���
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
	 * ������ goalCategory�̸��� ���ο� goalCategory�̸�, goalColor�� �޾� ������ goalCategory��
	 * ������ ��� ���.
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

	/** goalCategory�� ���ο� goalCategory�� �����Ҷ� ���. */
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

	/** goalCategory�̸��� ���ο� goalCategory�� �����Ҷ� ���. */
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

	/** goalCategory�̸��� ���� goalCategory�� ã�� goalColor�� �����Ҷ� ��� */
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

	/** goalCategory�̸����� goalCategory�� ã�� ��� ��� */
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

	/** goalCategory�� goalCategory�� ã�� ��� ��� */
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

	/** goalCategory�̸��� goalColor�� goalCategory�� ã�� ��� ��� */
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

	/** goalColor�� goalCategory�� ã�� ��� ��� */
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
