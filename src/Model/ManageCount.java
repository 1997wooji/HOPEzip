package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ManageCount {
	private static ManageCount manageCount;
	private int goalKeyCnt;
	private int repeatKeyCnt;
	private int completeKeyCnt;
	private int simpleMemoKeyCnt;

	static {
		manageCount = new ManageCount();
	}

	public ManageCount() {
		this.goalKeyCnt = 1;
		this.repeatKeyCnt = 1;
		this.completeKeyCnt = 1;
		this.simpleMemoKeyCnt = 1;
	}

	public ManageCount(int goalKeyCnt, int repeatKeyCnt, int completeKeyCnt, int simpleMemoKeyCnt) {
		this.goalKeyCnt = goalKeyCnt;
		this.repeatKeyCnt = repeatKeyCnt;
		this.completeKeyCnt = completeKeyCnt;
		this.simpleMemoKeyCnt = simpleMemoKeyCnt;
	}

	public static ManageCount getInstance() {
		return manageCount;
	}

	public boolean loadCount() throws IOException {
		File file = new File("C:/data/ManageCount.txt");
		if (!file.exists())
			file.createNewFile();
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);

		String str = br.readLine();
		if (str != null) {
			String[] datas = str.split("/");
			setGoalKeyCnt(Integer.parseInt(datas[0]));
			setRepeatKeyCnt(Integer.parseInt(datas[1]));
			setCompleteKeyCnt(Integer.parseInt(datas[2]));
			setSimpleMemoKeyCnt(Integer.parseInt(datas[3]));
		}
		fr.close();
		br.close();
		return true;
	}

	public boolean saveCount() throws IOException {
		File file = new File("C:/data/ManageCount.txt");
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file);

		String data = goalKeyCnt + "/" + repeatKeyCnt + "/" + completeKeyCnt + "/" + simpleMemoKeyCnt;
		fw.write(data);

		fw.flush();
		fw.close();
		return true;
	}

	public void addGoalKeyCnt() {
		this.goalKeyCnt++;
	}

	public void addRepeatKeyCnt() {
		this.repeatKeyCnt++;
	}

	public void addCompleteKeyCnt() {
		this.completeKeyCnt++;
	}

	public void addSimpleMemoKeyCnt() {
		this.simpleMemoKeyCnt++;
	}

	public int getGoalKeyCnt() {
		return goalKeyCnt;
	}

	public int getRepeatKeyCnt() {
		return repeatKeyCnt;
	}

	public int getCompleteKeyCnt() {
		return completeKeyCnt;
	}

	public int getSimpleMemoKeyCnt() {
		return simpleMemoKeyCnt;
	}

	private void setGoalKeyCnt(int goalKeyCnt) {
		this.goalKeyCnt = goalKeyCnt;
	}

	private void setRepeatKeyCnt(int repeatKeyCnt) {
		this.repeatKeyCnt = repeatKeyCnt;
	}

	private void setCompleteKeyCnt(int completeKeyCnt) {
		this.completeKeyCnt = completeKeyCnt;
	}

	private void setSimpleMemoKeyCnt(int simpleMemoKeyCnt) {
		this.simpleMemoKeyCnt = simpleMemoKeyCnt;
	}

	public String toString() {
		return "ManageCount [goalKeyCnt=" + goalKeyCnt + ", repeatKeyCnt=" + repeatKeyCnt + ", completeKeyCnt="
				+ completeKeyCnt + ", simpleMemoKeyCnt=" + simpleMemoKeyCnt + "]";
	}
}
