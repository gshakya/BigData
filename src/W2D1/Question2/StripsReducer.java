package W2D1.Question2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import W1D5.Question1.groupByPair;

public class StripsReducer {

	public List<groupByPair<Integer, HashMap<Integer, Integer>>> groupKey(
			List<StripedElement<Integer, HashMap<Integer, Integer>>> list) {
		List<groupByPair<Integer, HashMap<Integer, Integer>>> groupByPairs = new ArrayList<groupByPair<Integer, HashMap<Integer, Integer>>>();
		if (list != null) {

			StripsComparator<Integer, HashMap<Integer, Integer>> comparator = new StripsComparator<>();
			comparator.sort(list);

			int prevKey = 0;
			groupByPair<Integer, HashMap<Integer, Integer>> groupPair = new groupByPair<Integer, HashMap<Integer, Integer>>();
			for (StripedElement<Integer, HashMap<Integer, Integer>> keyVal : list) {

				int key = keyVal.getKey();
				HashMap<Integer, Integer> val = keyVal.getValue();

				if (prevKey == key) {
					List<HashMap<Integer, Integer>> values = groupPair.getValues();
					List<HashMap<Integer, Integer>> listValues = new ArrayList<>(values);
					listValues.add(val);
					groupPair.setValues(listValues);
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

	public StripedElement<Integer, HashMap<Integer, Integer>> reducePairs(
			groupByPair<Integer, HashMap<Integer, Integer>> groupByPair) {

		// StripedElement<Integer,Integer> keyVal = new StripedElement<>();
		if (groupByPair != null) {
			int key = groupByPair.getKey();
			HashMap<Integer, Integer> finalHashValue = new HashMap<>();

			for (HashMap<Integer, Integer> val : groupByPair.getValues()) {
				if (finalHashValue.isEmpty()) {
					finalHashValue.putAll(val);
				} else {
					for (Entry<Integer, Integer> item : val.entrySet()) {
						// System.out.println("****Checking Item********");
						// System.out.println(item);
						// System.out.println("Key "+item.getKey());
						// System.out.println("Key "+item.getValue());
						// System.out.println("finalHashValue Key
						// "+finalHashValue.get(item.getKey()));

						if (finalHashValue.get(item.getKey()) == null) {
							finalHashValue.put(item.getKey(), item.getValue());
						} else {
							finalHashValue.put(item.getKey(), finalHashValue.get(item.getKey()) + item.getValue());
						}
						// System.out.println("hashValue "+finalHashValue);
					}
				}

				// System.out.println(" End ");
			}

			return new StripedElement<>(key, finalHashValue);
		}

		return null;
	}

	public List<StripedElement<Integer, HashMap<Integer, Float>>> numberReduce(
			List<groupByPair<Integer, HashMap<Integer, Integer>>> pairs) {

		List<StripedElement<Integer, HashMap<Integer, Integer>>> reducedList = new ArrayList<>();
		List<StripedElement<Integer, HashMap<Integer, Float>>> finalReducedList = new ArrayList<>();
		
		if (pairs != null) {

			for (groupByPair<Integer, HashMap<Integer, Integer>> pair : pairs) {
				reducedList.add(reducePairs(pair));
			}
			for(int i=0; i<reducedList.size(); i++)
			{
				int sum=0;
				HashMap<Integer, Float> finalHashValue = new HashMap<>();
				for (Entry<Integer, Integer> item : reducedList.get(i).getValue().entrySet())
				{
					sum +=item.getValue();
				}
//				
				for(Entry<Integer, Integer> item : reducedList.get(i).getValue().entrySet())
				{
					finalHashValue.put(item.getKey(), (float) item.getValue()/sum);
				}
				
				finalReducedList.add( new StripedElement<Integer,HashMap<Integer,Float>> (reducedList.get(i).getKey(),finalHashValue));
				
			}
			return finalReducedList;
		}

		return null;
	}
}