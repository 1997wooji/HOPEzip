package Controller;

public class TestMain {
	public static void main(String[] args) throws Exception {

		
		CommonService cs = new CommonService();
		cs.loadSystem();
		SimpleMemoService sm = new SimpleMemoService();
		String[] str = sm.simpleMemoLoad();

//		GoalService gs = new GoalService();
//		gs.deleteAllGoalInfo("s", "20180511", "20180502", "0000", "0000");
//		gs.addGoalInfo("����", "���� Ǯ��", "20180501", "20180531", "1430", "1600", "40", "����", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("����", "���� Ǯ��", "20180501", "20180531", "1630", "1700", "50", "��", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("��ȸ", "��ȸ Ǯ��", "20180501", "20180531", "1730", "1800", "30", "��", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("�ѱ���", "�ѱ��� Ǯ��", "20180501", "20180531", "1830", "1900", "60", "����", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("����", "���� ���� Ǯ��", "20180501", "20180531", "1930", "2000", "80", "����", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("����", "���� ���� Ǯ��", "20180501", "20180531", "2030", "2100", "70", "����", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("��ȸ", "��ȸ ���� Ǯ��", "20180501", "20180531", "2130", "2200", "10", "����", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("����", "�ѱ��� ���� Ǯ��", "20180501", "20180531", "2230", "2300", "20", "����", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("�ڹ�", "�ڴ�", "20180501", "20180531", "1230", "1300", "30", "����", "���� Ǯ��", (byte) 1, null);
//		gs.addGoalInfo("�ڹ�", "�ڸ鼭 �ڵ�", "20180501", "20180531", "1150", "1200", "60", "����", "���� Ǯ��", (byte) 1, null);
//
//		cs.saveCompleteQty("30", "���� Ǯ��", "20180501", "20180531", "1430", "1600", "20180505");
//		cs.saveCompleteQty("40", "���� Ǯ��", "20180501", "20180531", "1630", "1700", "20180505");
//		cs.saveCompleteQty("20", "��ȸ Ǯ��", "20180501", "20180531", "1730", "1800", "20180505");
//		cs.saveCompleteQty("50", "�ѱ��� Ǯ��", "20180501", "20180531", "1830", "1900", "20180505");
//		cs.saveCompleteQty("70", "���� ���� Ǯ��", "20180501", "20180531", "1930", "2000", "20180505");
//		cs.saveCompleteQty("60", "���� ���� Ǯ��", "20180501", "20180531", "2030", "2100", "20180505");
//		cs.saveCompleteQty("5", "��ȸ ���� Ǯ��", "20180501", "20180531", "2130", "2200", "20180505");
//		cs.saveCompleteQty("10", "�ѱ��� ���� Ǯ��", "20180501", "20180531", "2230", "2300", "20180505");
//		cs.saveCompleteQty("20", "�ڴ�", "20180501", "20180531", "1230", "1300", "20180505");
//		cs.saveCompleteQty("50", "�ڸ鼭 �ڵ�", "20180501", "20180531", "1150", "1200", "20180505");
//
//		StatsService ss = new StatsService();
//		String[] str = ss.loadMonthlyStats("20180401");
//		for (int i = 0; i < str.length; i++) {
//			System.out.println(str[i]);
//		}
//
//		cs.saveSystem();
	}

}
