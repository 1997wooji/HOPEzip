package Model;

import java.util.Arrays;

/** class RepeatInfo : �ݺ� ������ ��� �ִ� Ŭ���� */
public class RepeatInfo {

	/** �ݺ� ������ ���� ���� �ĺ�Ű�� �����ϴ� �ʵ� */
	private String repeatKey;
	/** �ݺ� �Ⱓ�� �����ϴ� �ʵ� */
	private byte repeatTerm;
	/** �ݺ� ������ �����ϴ� �ʵ� */
	private byte[] repeatDay;

	/** ��Ʈ��ũ�� ���� ������ */
	public RepeatInfo() {
		super();
		this.repeatDay = new byte[7];
	}

	/** �ݺ� ���� ���� �� ��� ������ �޴� ������ */
	public RepeatInfo(String repeatKey, byte repeatTerm, byte[] repeatDay) {
		setRepeatKey(repeatKey);
		setRepeatTerm(repeatTerm);
		this.repeatDay = new byte[7];
		setRepeatDay(repeatDay);
	}

	public RepeatInfo(byte repeatTerm, byte[] repeatDay) {
		generateKey();
		setRepeatTerm(repeatTerm);
		this.repeatDay = new byte[7];
		setRepeatDay(repeatDay);
	}

	/** ���� �ĺ�Ű�� ��ȯ�ϴ� �޼ҵ�. */
	public String getRepeatKey() {
		return repeatKey;
	}

	/** �ݺ� �Ⱓ�� ��ȯ�ϴ� �޼ҵ�. ����ڿ��� UI�� ���� ��ǥ ������ ������ �� ��¥ ����� �ϱ� ���� ���. */
	public byte getRepeatTerm() {
		return repeatTerm;
	}

	/** �ݺ� ������ ��ȯ�ϴ� �޼ҵ�. ����ڿ��� UI�� ���� ��ǥ ������ ������ �� ��¥ ����� �ϱ� ���� ���. */
	public byte[] getRepeatDay() {
		return repeatDay;

	}

	/** ���� �ĺ�Ű�� �����ϴ� �޼ҵ�. */
	private void setRepeatKey(String repeatKey) {
		if (repeatKey != null)
			this.repeatKey = repeatKey;
	}

	/** �ݺ� �Ⱓ�� �����ϴ� �޼ҵ�. ����ڰ� �ݺ� ���θ� ������ �ÿ� �Բ� ���. */
	public void setRepeatTerm(byte repeatTerm) {
		this.repeatTerm = repeatTerm;
	}

	/** �ݺ� ������ �����ϴ� �޼ҵ�. ����ڰ� �ݺ� ���θ� ������ �ÿ� �Բ� ���. */
	public void setRepeatDay(byte[] repeatDay) {
		if (repeatDay != null)
			this.repeatDay = repeatDay;
		else 
			for(int i = 0; i < 7; i++) {
				this.repeatDay[i] = (byte)-1;
			}
	}

	@Override
	public String toString() {
		return "RepeatInfo [repeatKey=" + repeatKey + ", repeatTerm=" + repeatTerm + ", repeatDay="
				+ Arrays.toString(repeatDay) + "]";
	}

	private void generateKey() {
		ManageCount mc = ManageCount.getInstance();
		int key = mc.getRepeatKeyCnt();
		mc.addRepeatKeyCnt();

		String repeatKey = "repeat" + key;
		setRepeatKey(repeatKey);
	}

	/*
	 * public Object clone() throws CloneNotSupportedException{
	 * 
	 * RepeatInfo cloned=(RepeatInfo)super.clone(); Arrays.copyOf(original,
	 * newLength); return null; }���߿� �� clone �ϱ�
	 */
}
