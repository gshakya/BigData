package W2D1.Question3;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Reducer<K, V> {
	private Map<K, ArrayList<V>> elements;

	public Reducer() {
		elements = new TreeMap<>();
	}

	public void addElement(K key, V value) {
		ArrayList<V> initVal = new ArrayList<>();
		initVal.add(value);
		if (elements.containsKey(key)) {
			initVal = elements.get(key);
			initVal.add(value);
			elements.remove(key);
		}
		elements.put(key, initVal);
	}

	public Map<K, ArrayList<V>> getElements() {
		return elements;
	}
}
