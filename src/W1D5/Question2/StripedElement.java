package W1D5.Question2;

public class StripedElement<k, v> {
	private k key;
	private v value;

	public StripedElement(k key, v value) {
		this.key = key;
		this.value = value;
	}

	public StripedElement() {

	}

	public k getKey() {
		return key;
	}

	public v getValue() {
		return value;
	}

	public void setKey(k key) {
		this.key = key;
	}

	public void setValue(v value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "<" + key + " , " + value + " >";
	}

}