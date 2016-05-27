package W1D2;

import java.util.ArrayList;
import java.util.List;

import W1D1.MapperFactory;
import W1D1.Pair;

public class ReducerFactory {
	private List<Pair<String, Integer>> mappedList;

	private List<Pair<String, List<Integer>>> mergedList;

	public static void main(String[] args) {
		MapperFactory m = new MapperFactory("testDataForW1D1.txt");

		List<Pair<String, Integer>> mappedObj = m.map();

		ReducerFactory r = new ReducerFactory(mappedObj);

		List<Pair<String, List<Integer>>> mergedObjs = r.mergeMappedList();

		// mergedObjs.stream().forEach(System.out::println);

		r.reduceMergedList().stream().sorted().forEach(System.out::println);

	}

	public ReducerFactory() {
		mappedList = new ArrayList<>();
		mergedList = new ArrayList<Pair<String, List<Integer>>>();

	}

	public ReducerFactory(List<Pair<String, Integer>> mappedList) {
		this.mappedList = mappedList;
		mergedList = new ArrayList<Pair<String, List<Integer>>>();

	}

	public void addMappedObj(Pair<String, Integer> p) {
		mappedList.add(p);
	}

	public List<Pair<String, Integer>> getMappedList() {
		return mappedList;
	}

	public List<Pair<String, Integer>> reduceMergedList() {
		List<Pair<String, Integer>> reducedList = new ArrayList<>();

		for (Pair mergedObj : mergedList) {
			int initVal = 0;
			for (Integer val : (List<Integer>) mergedObj.getValue()) {
				initVal += val;
			}
			reducedList.add(new Pair(mergedObj.getKey(), initVal));

		}
		return reducedList;
	}

	public List<Pair<String, List<Integer>>> mergeMappedList() {

		for (Pair mappedObj : mappedList) {
			Pair mergedobj = getPair((String) mappedObj.getKey());
			if (mergedobj == null) {
				mergedobj = new Pair((String) mappedObj.getKey(), new ArrayList<>());
				mergedList.add(mergedobj);
			}
			((List) mergedobj.getValue()).add(mappedObj.getValue());
		}

		return mergedList;
	}

	public Pair getPair(String key) {
		for (Pair p : mergedList) {
			if (p.getKey().equals(key)) {
				return p;
			}
		}
		return null;
	}

}
