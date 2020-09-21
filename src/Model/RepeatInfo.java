package Model;

import java.util.Arrays;

/** class RepeatInfo : 반복 정보를 담고 있는 클래스 */
public class RepeatInfo {

	/** 반복 정보에 대해 고유 식별키를 저장하는 필드 */
	private String repeatKey;
	/** 반복 기간을 저장하는 필드 */
	private byte repeatTerm;
	/** 반복 요일을 저장하는 필드 */
	private byte[] repeatDay;

	/** 네트워크를 위한 생성자 */
	public RepeatInfo() {
		super();
		this.repeatDay = new byte[7];
	}

	/** 반복 정보 생성 시 모든 정보를 받는 생성자 */
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

	/** 고유 식별키를 반환하는 메소드. */
	public String getRepeatKey() {
		return repeatKey;
	}

	/** 반복 기간을 반환하는 메소드. 사용자에게 UI를 통해 목표 정보를 제공할 때 날짜 계산을 하기 위해 사용. */
	public byte getRepeatTerm() {
		return repeatTerm;
	}

	/** 반복 요일을 반환하는 메소드. 사용자에게 UI를 통해 목표 정보를 제공할 때 날짜 계산을 하기 위해 사용. */
	public byte[] getRepeatDay() {
		return repeatDay;

	}

	/** 고유 식별키를 설정하는 메소드. */
	private void setRepeatKey(String repeatKey) {
		if (repeatKey != null)
			this.repeatKey = repeatKey;
	}

	/** 반복 기간을 설정하는 메소드. 사용자가 반복 여부를 설정할 시에 함께 사용. */
	public void setRepeatTerm(byte repeatTerm) {
		this.repeatTerm = repeatTerm;
	}

	/** 반복 요일을 설정하는 메소드. 사용자가 반복 여부를 설정할 시에 함께 사용. */
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
	 * newLength); return null; }나중에 꼭 clone 하기
	 */
}
