package Model;

public class CompleteInfo {
	private String completeKey;
	private int completeQty;
	private String completeTime;
	private double completeRate;

	/** constructor summary */
	public CompleteInfo() {
		super();
	}

	public CompleteInfo(int completeQty, String completeTime, double completeRate) {
		super();
		this.completeQty = completeQty;
		this.completeTime = completeTime;
		this.completeRate = completeRate;
		this.generateKey();
	}

	public CompleteInfo(String completeKey, int completeQty, String completeTime, double completeRate) {
		super();
		this.completeKey = completeKey;
		this.completeQty = completeQty;
		this.completeTime = completeTime;
		this.completeRate = completeRate;
	}

	/** method summary */
	/** 성취량 식별 키를 조회하기 위한 메소드 */
	public String getCompleteKey() {
		return completeKey;
	}

	/** 성취량을 조회하기 위한 메소드 */
	public int getCompleteQty() {
		return completeQty;
	}

	/** 학습 시간을 조회하기 위한 메소드 */
	public String getCompleteTime() {
		return completeTime;
	}

	/** 성취도를 조회하기 위한 메소드 */
	public double getCompleteRate() {
		return completeRate;
	}

	/** 성취량 식별 키를 저장하기 위한 메소드 */
	private void setCompleteKey(String completeKey) {
		this.completeKey = completeKey;
	}

	private void generateKey() {
		ManageCount mc = ManageCount.getInstance();

		String completeKey = "complete" + mc.getCompleteKeyCnt();
		mc.addCompleteKeyCnt();

		this.setCompleteKey(completeKey);
	}

	/** 성취량을 저장하기 위한 메소드 */
	public void setCompleteQty(int completeQty) {
		this.completeQty = completeQty;
	}

	public void setCompleteQty(int completeQty, double goalQty) {
		this.completeQty = completeQty;
	}

	/** 학습 시간을 저장하기 위한 메소드 */
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	/** 성취도를 저장하기 위한 메소드 */
	public void setCompleteRate(double completeRate) {
		this.completeRate = completeRate;
	}

	public void updateCompleteRate(double goalQty) {
		this.completeRate = (double) this.completeQty / goalQty;
	}

	/** toString 메소드 */
	public String toString() {
		return "CompleteInfo [completeKey=" + completeKey + ", completeQty=" + completeQty + ", completeTime="
				+ completeTime + ", completeRate=" + completeRate + "]";
	}



}
