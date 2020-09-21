package Controller;

public class TestMain {
	public static void main(String[] args) throws Exception {

		
		CommonService cs = new CommonService();
		cs.loadSystem();
		SimpleMemoService sm = new SimpleMemoService();
		String[] str = sm.simpleMemoLoad();

//		GoalService gs = new GoalService();
//		gs.deleteAllGoalInfo("s", "20180511", "20180502", "0000", "0000");
//		gs.addGoalInfo("수학", "수학 풀기", "20180501", "20180531", "1430", "1600", "40", "문제", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("국어", "국어 풀기", "20180501", "20180531", "1630", "1700", "50", "쪽", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("사회", "사회 풀기", "20180501", "20180531", "1730", "1800", "30", "개", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("한국사", "한국사 풀기", "20180501", "20180531", "1830", "1900", "60", "문제", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("수학", "수학 많이 풀기", "20180501", "20180531", "1930", "2000", "80", "문제", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("국어", "국어 많이 풀기", "20180501", "20180531", "2030", "2100", "70", "문제", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("사회", "사회 많이 풀기", "20180501", "20180531", "2130", "2200", "10", "문제", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("수학", "한국사 많이 풀기", "20180501", "20180531", "2230", "2300", "20", "문제", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("자바", "코더", "20180501", "20180531", "1230", "1300", "30", "문제", "많이 풀자", (byte) 1, null);
//		gs.addGoalInfo("자바", "자면서 코딩", "20180501", "20180531", "1150", "1200", "60", "문제", "많이 풀자", (byte) 1, null);
//
//		cs.saveCompleteQty("30", "수학 풀기", "20180501", "20180531", "1430", "1600", "20180505");
//		cs.saveCompleteQty("40", "국어 풀기", "20180501", "20180531", "1630", "1700", "20180505");
//		cs.saveCompleteQty("20", "사회 풀기", "20180501", "20180531", "1730", "1800", "20180505");
//		cs.saveCompleteQty("50", "한국사 풀기", "20180501", "20180531", "1830", "1900", "20180505");
//		cs.saveCompleteQty("70", "수학 많이 풀기", "20180501", "20180531", "1930", "2000", "20180505");
//		cs.saveCompleteQty("60", "국어 많이 풀기", "20180501", "20180531", "2030", "2100", "20180505");
//		cs.saveCompleteQty("5", "사회 많이 풀기", "20180501", "20180531", "2130", "2200", "20180505");
//		cs.saveCompleteQty("10", "한국사 많이 풀기", "20180501", "20180531", "2230", "2300", "20180505");
//		cs.saveCompleteQty("20", "코더", "20180501", "20180531", "1230", "1300", "20180505");
//		cs.saveCompleteQty("50", "자면서 코딩", "20180501", "20180531", "1150", "1200", "20180505");
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
