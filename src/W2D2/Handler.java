package W2D2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Handler {
	private Mapper<keyValuePair<PairedKey<String>, Integer>>[] mappers;
	private Reducer<PairedKey<String>, Integer>[] reducers;
	private TreeMap<String, LinkedList<keyValuePair<String, Integer>>>[] normalizedReducers;

	Handler(int m, int r) {
		mappers = new Mapper[m];
		reducers = new Reducer[r];
		normalizedReducers = new TreeMap[r];

		for (int i = 0; i < reducers.length; i++) {
			reducers[i] = new Reducer<>();
		}

		for (int i = 0; i < normalizedReducers.length; i++) {
			normalizedReducers[i] = new TreeMap<>();
		}

	}

	public void initMapper(Mapper[] m) {
		mappers = m;
		populateReducers();
	}

	public Reducer[] getReducers() {
		return reducers;
	}
	
	public TreeMap<String, LinkedList<keyValuePair<String, Integer>>>[] getNormalizedReducers(){
		populateNormalizedReducer();
		return normalizedReducers;
	}

	private void populateReducers() {
		int mapIdx = 0;
		for (Mapper m : mappers) {
			ArrayList<keyValuePair<PairedKey<String>, Integer>> mapperElements = m.getElements();

			for (keyValuePair<PairedKey<String>, Integer> mapperElement : mapperElements) {
				int redIdx = getPartition(mapperElement.getKey().getFirst());
				reducers[redIdx].addElement(mapperElement.getKey(), mapperElement.getValue());
				System.out.println("Mapper " + mapIdx + " output to Reducer " + redIdx);
				System.out.println("< <" + mapperElement.getKey() + "> ," + mapperElement.getValue() + ">");
			}
			mapIdx++;
		}
	}

	private void populateNormalizedReducer() {
		int redIdx = 0;
		for ( Reducer<PairedKey<String>, Integer> reducer : reducers) {
			for(Entry<PairedKey<String>, ArrayList<Integer>> redEntry : reducer.getElements().entrySet()){
				
				String key = redEntry.getKey().getFirst();
				
				keyValuePair<String, Integer> value = new keyValuePair<String, Integer>
				( redEntry.getKey().getSecond(), 
						redEntry.getValue().stream().mapToInt(Integer::intValue).sum()) ;
				
				LinkedList<keyValuePair<String, Integer>> resultList = new LinkedList<>();
				
				if(normalizedReducers[redIdx].containsKey(key)){
					resultList = normalizedReducers[redIdx].get(key);
					normalizedReducers[redIdx].remove(key);
				}
				resultList.add(value);
				normalizedReducers[redIdx].put(key, resultList);
				
			}
			redIdx++;
		}
	}

	public int getPartition(Object o) {
		return (int) Math.abs(o.hashCode() % reducers.length);
	}

}
