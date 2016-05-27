package W1D4.Question2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CharReducer {

	private Map<Character, List<Integer[]>> reducedPairs = new HashMap<>();

	public CharReducer() {

	}

	public void addPairs(Entry<Character, Integer[]> pair) {
		List<Integer[]> existingRes = new ArrayList<>();
		if (reducedPairs.containsKey(pair.getKey())) {
			existingRes = reducedPairs.get(pair.getKey());
		}
		existingRes.add(pair.getValue());
		reducedPairs.replace(pair.getKey(), existingRes);
	}

}
