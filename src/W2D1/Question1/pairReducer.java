package W2D1.Question1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class pairReducer {
	private int total;

	public List<groupByPair<keyValuePair<Integer, Integer>, Integer>> groupKey(
			List<keyValuePair<keyValuePair<Integer, Integer>, Integer>> list) {
		List<groupByPair<keyValuePair<Integer, Integer>, Integer>> groupByPairs = new ArrayList<groupByPair<keyValuePair<Integer, Integer>, Integer>>();
		if (list != null) {

			pairComparator<Integer, Integer, Integer> comparator = new pairComparator<>();
			comparator.sort(list);

			keyValuePair<Integer, Integer> prevKey = new keyValuePair<>();
			groupByPair<keyValuePair<Integer, Integer>, Integer> groupPair = new groupByPair<keyValuePair<Integer, Integer>, Integer>();
			for (keyValuePair<keyValuePair<Integer, Integer>, Integer> keyVal : list) {

				keyValuePair<Integer, Integer> key = keyVal.getKey();
				int val = keyVal.getValue();

				if (prevKey.getKey() != null) {
					if (prevKey.getKey() == (key.getKey()) && prevKey.getValue() == (key.getValue())) {
						List<Integer> values = groupPair.getValues();
						List<Integer> listValues = new ArrayList<>(values);
						listValues.add(val);
						groupPair.setValues(listValues);
					}

					else {

						if (groupPair.getKey() != null)
							groupByPairs.add(groupPair);
						groupPair = new groupByPair<>();
						groupPair.setKey(key);
						groupPair.setValues(Arrays.asList(val));
					}
				} else {
					if (groupPair.getKey() != null)
						groupByPairs.add(groupPair);
					groupPair = new groupByPair<>();
					groupPair.setKey(key);
					groupPair.setValues(Arrays.asList(val));
				}
				prevKey = key;
			}
			if (groupPair.getKey() != null)
				groupByPairs.add(groupPair);

			return groupByPairs;
		}

		return null;
	}

	public keyValuePair<keyValuePair<Integer, Integer>, Float> reducePairs(
			groupByPair<keyValuePair<Integer, Integer>, Integer> groupByPair) {
		// keyValuePair<Integer,Integer> keyVal = new keyValuePair<>();
		if (groupByPair != null) {
			keyValuePair<Integer, Integer> key = groupByPair.getKey();
			int sum = 0;

			for (int val : groupByPair.getValues()) {
				if (key.getValue() == 0) {
					total += val;
				} else {
					sum += val;
				}
			}
			return new keyValuePair<>(key, ((float) sum / (float) total));
		}

		return null;
	}

	public List<keyValuePair<keyValuePair<Integer, Integer>, Float>> numberReduce(
			List<groupByPair<keyValuePair<Integer, Integer>, Integer>> pairs) {
		int plug = 0;
		List<keyValuePair<keyValuePair<Integer, Integer>, Float>> reducedList = new ArrayList<>();
		if (pairs != null) {
			for (groupByPair<keyValuePair<Integer, Integer>, Integer> pair : pairs) {
				
				if (plug != pair.getKey().getKey() && plug != 0) {
					total = 0;
				}
				reducedList.add(reducePairs(pair));
				plug= pair.getKey().getKey();
			}
			return reducedList;
		}

		return null;
	}
}
