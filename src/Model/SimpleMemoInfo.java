package Model;

/** class SimpleMemoInfo : 간단 메모 정보를 갖는 클래스 */
public class SimpleMemoInfo {
	/** 간단 메모 고유 식별키를 저장하는 필드 */
	private String simpleMemoKey;
	/** 간단 메모 명을 저장하는 필드 */
	private String simpleMemoName;
	/** 간단 메모 체크 여부를 저장하는 필드 */
	private boolean isChecked;

	/** 네트워크를 위해 만든 생성자. */
	public SimpleMemoInfo() {
	}

	public SimpleMemoInfo(String simpleMemoName, boolean isChecked) {
		ManageCount manageCount = ManageCount.getInstance();
		int key = manageCount.getSimpleMemoKeyCnt();
		manageCount.addSimpleMemoKeyCnt();

		String simpleMemoKey = "simplememo" + key;
		setSimpleMemoKey(simpleMemoKey);
		setSimpleMemoName(simpleMemoName);
		setChecked(isChecked);
	}

	/** 모든 필드를 받는 생성자. */
	public SimpleMemoInfo(String simpleMemoKey, String simpleMemoName, boolean isChecked) {
		setSimpleMemoKey(simpleMemoKey);
		setSimpleMemoName(simpleMemoName);
		setChecked(isChecked);
	}

	/** 고유 식별키를 반환하는 메소드. 특정 메모 삭제 시에 이용된다. */
	public String getSimpleMemoKey() {
		return simpleMemoKey;
	}

	/** 메모 명을 반환하는 메소드. UI에 내용을 제공하기 위해 사용된다. */
	public String getSimpleMemoName() {
		return simpleMemoName;
	}

	/** 메모 체크 여부를 반환하는 메소드. UI에 체크 여부를 제공하고, 정렬하기 위해 사용된다. */
	public boolean isChecked() {
		return isChecked;
	}

	/** 메모 고유 식별키를 설정하는 메소드. 사용자가 메모를 추가하면, 시스템이 내부적으로 메모 고유 키를 설정하기 위해 사용된다. */
	private void setSimpleMemoKey(String simpleMemoKey) {
		if (simpleMemoKey != null)
			this.simpleMemoKey = simpleMemoKey;
	}

	/** 메모 명을 설정하는 생성자. 사용자가 입력 또는 수정한 메모 명을 시스템에 저장하기 위해 사용된다. */
	public void setSimpleMemoName(String simpleMemoName) {
		if (simpleMemoName != null)
			this.simpleMemoName = simpleMemoName;
	}

	/** 메모 체크 여부를 설정하는 메소드. 사용자가 메모 체크를 클릭할 시 메모 체크 여부 저장을 위해 사용된다. */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public void changeChecked() {
		setChecked(!isChecked);
	}

	/** 문자열을 반환하는 메소드. */
	public String toString() {
		return simpleMemoKey + "/" + simpleMemoName + "/" + isChecked;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleMemoInfo other = (SimpleMemoInfo) obj;
		if (isChecked != other.isChecked)
			return false;
		if (simpleMemoKey == null) {
			if (other.simpleMemoKey != null)
				return false;
		} else if (!simpleMemoKey.equals(other.simpleMemoKey))
			return false;
		if (simpleMemoName == null) {
			if (other.simpleMemoName != null)
				return false;
		} else if (!simpleMemoName.equals(other.simpleMemoName))
			return false;
		return true;
	}
}
