package W2D2;

public class keyValuePair<k, v> {
	private k key;
	private v value;

	public keyValuePair(k key, v value) {
		this.key = key;
		this.value = value;
	}

	public k getKey() {
		return key;
	}

	public v getValue() {
		return value;
	}

	@Override
	public String toString() {
		return   key + " , " + value ;
	}

}