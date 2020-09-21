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
	/** ���뷮 �ĺ� Ű�� ��ȸ�ϱ� ���� �޼ҵ� */
	public String getCompleteKey() {
		return completeKey;
	}

	/** ���뷮�� ��ȸ�ϱ� ���� �޼ҵ� */
	public int getCompleteQty() {
		return completeQty;
	}

	/** �н� �ð��� ��ȸ�ϱ� ���� �޼ҵ� */
	public String getCompleteTime() {
		return completeTime;
	}

	/** ���뵵�� ��ȸ�ϱ� ���� �޼ҵ� */
	public double getCompleteRate() {
		return completeRate;
	}

	/** ���뷮 �ĺ� Ű�� �����ϱ� ���� �޼ҵ� */
	private void setCompleteKey(String completeKey) {
		this.completeKey = completeKey;
	}

	private void generateKey() {
		ManageCount mc = ManageCount.getInstance();

		String completeKey = "complete" + mc.getCompleteKeyCnt();
		mc.addCompleteKeyCnt();

		this.setCompleteKey(completeKey);
	}

	/** ���뷮�� �����ϱ� ���� �޼ҵ� */
	public void setCompleteQty(int completeQty) {
		this.completeQty = completeQty;
	}

	public void setCompleteQty(int completeQty, double goalQty) {
		this.completeQty = completeQty;
	}

	/** �н� �ð��� �����ϱ� ���� �޼ҵ� */
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}

	/** ���뵵�� �����ϱ� ���� �޼ҵ� */
	public void setCompleteRate(double completeRate) {
		this.completeRate = completeRate;
	}

	public void updateCompleteRate(double goalQty) {
		this.completeRate = (double) this.completeQty / goalQty;
	}

	/** toString �޼ҵ� */
	public String toString() {
		return "CompleteInfo [completeKey=" + completeKey + ", completeQty=" + completeQty + ", completeTime="
				+ completeTime + ", completeRate=" + completeRate + "]";
	}



}
