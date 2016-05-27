package W1D4.Question2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CharReducer {

	private Map<Character, List<Integer[]>> mergedPairs = new HashMap<>();
	private Map<Character, Integer[]> reducedPairs = new HashMap<>();

	public CharReducer() {

	}

	public void addPairs(Entry<Character, Integer[]> pair) {
		List<Integer[]> existingRes = new ArrayList<>();
		if (mergedPairs.containsKey(pair.getKey())) {
			existingRes = mergedPairs.get(pair.getKey());
			existingRes.add(pair.getValue());
			mergedPairs.replace(pair.getKey(), existingRes);
		} else {
			existingRes.add(pair.getValue());
			mergedPairs.put(pair.getKey(), existingRes);
		}
	}

	public Map<Character, Integer[]> getReducedPairs() {

		for (Entry<Character, List<Integer[]>> mergedEntry : mergedPairs
				.entrySet()) {
			Integer[] reducedValue = { 0, 0 };
			for (Integer[] val : mergedEntry.getValue()) {
				reducedValue[0] += val[0];
				reducedValue[1] += val[1];

			}
			reducedPairs.put(mergedEntry.getKey(), reducedValue);
		}
		return reducedPairs;
	}

	public Map<Character, List<Integer[]>> getMergedPairs() {
		return mergedPairs;
	}
}
