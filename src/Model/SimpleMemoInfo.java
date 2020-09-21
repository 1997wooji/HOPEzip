package Model;

/** class SimpleMemoInfo : ���� �޸� ������ ���� Ŭ���� */
public class SimpleMemoInfo {
	/** ���� �޸� ���� �ĺ�Ű�� �����ϴ� �ʵ� */
	private String simpleMemoKey;
	/** ���� �޸� ���� �����ϴ� �ʵ� */
	private String simpleMemoName;
	/** ���� �޸� üũ ���θ� �����ϴ� �ʵ� */
	private boolean isChecked;

	/** ��Ʈ��ũ�� ���� ���� ������. */
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

	/** ��� �ʵ带 �޴� ������. */
	public SimpleMemoInfo(String simpleMemoKey, String simpleMemoName, boolean isChecked) {
		setSimpleMemoKey(simpleMemoKey);
		setSimpleMemoName(simpleMemoName);
		setChecked(isChecked);
	}

	/** ���� �ĺ�Ű�� ��ȯ�ϴ� �޼ҵ�. Ư�� �޸� ���� �ÿ� �̿�ȴ�. */
	public String getSimpleMemoKey() {
		return simpleMemoKey;
	}

	/** �޸� ���� ��ȯ�ϴ� �޼ҵ�. UI�� ������ �����ϱ� ���� ���ȴ�. */
	public String getSimpleMemoName() {
		return simpleMemoName;
	}

	/** �޸� üũ ���θ� ��ȯ�ϴ� �޼ҵ�. UI�� üũ ���θ� �����ϰ�, �����ϱ� ���� ���ȴ�. */
	public boolean isChecked() {
		return isChecked;
	}

	/** �޸� ���� �ĺ�Ű�� �����ϴ� �޼ҵ�. ����ڰ� �޸� �߰��ϸ�, �ý����� ���������� �޸� ���� Ű�� �����ϱ� ���� ���ȴ�. */
	private void setSimpleMemoKey(String simpleMemoKey) {
		if (simpleMemoKey != null)
			this.simpleMemoKey = simpleMemoKey;
	}

	/** �޸� ���� �����ϴ� ������. ����ڰ� �Է� �Ǵ� ������ �޸� ���� �ý��ۿ� �����ϱ� ���� ���ȴ�. */
	public void setSimpleMemoName(String simpleMemoName) {
		if (simpleMemoName != null)
			this.simpleMemoName = simpleMemoName;
	}

	/** �޸� üũ ���θ� �����ϴ� �޼ҵ�. ����ڰ� �޸� üũ�� Ŭ���� �� �޸� üũ ���� ������ ���� ���ȴ�. */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public void changeChecked() {
		setChecked(!isChecked);
	}

	/** ���ڿ��� ��ȯ�ϴ� �޼ҵ�. */
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
