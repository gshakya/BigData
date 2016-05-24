package W1D2;

import java.util.ArrayList;
import java.util.List;

import W1D1.MapperOperator;
import W1D1.Pair;

public class Reducer {
	private List<Pair<String, List<Integer>>> mergedList = new ArrayList<>();

	

	public static void main(String[] args) {
		List<Pair<String, Integer>> mappedObj = MapperOperator
				.collector("testDataForW1D1.txt");
		
		 List<Pair<String, List<Integer>>>  mergedObjs = mergeMappedList(mappedObj);
		
//		 mergedObjs.stream().forEach(System.out::println);
		 
		 reduceMergedList(mergedObjs).stream().sorted().forEach(System.out::println);
		
	}
	
	public static List <Pair<String, Integer>> reduceMergedList(List<Pair<String, List<Integer>>> mergedList){
		List<Pair<String, Integer>> reducedList = new ArrayList<>();
		
		for (Pair mergedObj : mergedList){
			int initVal=0;
			for(Integer val:(List<Integer>)mergedObj.getValue()){
				initVal+= val;
			}
			reducedList.add(new Pair(mergedObj.getKey(),initVal));
			
		}
		return reducedList;
	}
	
	public static List<Pair<String, List<Integer>>> mergeMappedList(
			List<Pair<String, Integer>> mappedObjs) {
		
		List<Pair<String, List<Integer>>> mergedList = new ArrayList<>();

		for (Pair mappedObj : mappedObjs) {
			Pair mergedobj = getPair((String) mappedObj.getKey(), mergedList);
			if (mergedobj == null) {
				mergedobj = new Pair((String) mappedObj.getKey(), new ArrayList<>());
				mergedList.add(mergedobj);
			}
			((List)mergedobj.getValue()).add(mappedObj.getValue());
		}
		
		return mergedList;
	}

	public static Pair getPair(String key,
			List<Pair<String, List<Integer>>> mergedList) {
		for (Pair p : mergedList) {
			if (p.getKey().equals(key)) {
				return p;
			}
		}
		return null;
	}

}
