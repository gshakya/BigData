package W1D5.Question1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class pairReducer {

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

	public keyValuePair<keyValuePair<Integer, Integer>, Integer> reducePairs(
			groupByPair<keyValuePair<Integer, Integer>, Integer> groupByPair) {

		// keyValuePair<Integer,Integer> keyVal = new keyValuePair<>();
		if (groupByPair != null) {
			keyValuePair<Integer, Integer> key = groupByPair.getKey();
			int sum = 0;
			for (int val : groupByPair.getValues()) {
				sum += val;
			}

			return new keyValuePair<>(key, sum);
		}

		return null;
	}

	public List<keyValuePair<keyValuePair<Integer, Integer>, Integer>> numberReduce(
			List<groupByPair<keyValuePair<Integer, Integer>, Integer>> pairs) {

		List<keyValuePair<keyValuePair<Integer, Integer>, Integer>> reducedList = new ArrayList<>();
		if (pairs != null) {

			for (groupByPair<keyValuePair<Integer, Integer>, Integer> pair : pairs) {

				reducedList.add(reducePairs(pair));
			}

			return reducedList;
		}

		return null;
	}
}
