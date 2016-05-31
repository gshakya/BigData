package W2D1.Question1;

import java.util.List;

public class groupByPair<K, V> {

	private K key;
	private List<V> values;
	
	public groupByPair(K key, List<V> values){
		this.key = key;
		this.values = values;
	}
	
	public groupByPair(){
		
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public List<V> getValues() {
		return values;
	}

	public void setValues(List<V> values) {
		this.values = values;
	}
	
	@Override
	public String toString(){
		return "[" + key + "," + values + "]";
	}

}
