package W1D1;

public class Pair<k extends Comparable<k>, v> implements Comparable<Pair<k, v>> {

	private k key;
	private v value;

	
	public Pair(){
		
	}
	
	public Pair(k key, v value) {
		this.key = key;
		this.value = value;
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
	public int compareTo(Pair<k, v> o) {
		// TODO Auto-generated method stub
		return key.compareTo(o.getKey());
	}

	@Override
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
	
	

}
