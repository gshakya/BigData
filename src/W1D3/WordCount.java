package W1D3;

import W1D1.MapperFactory;
import W1D1.Pair;
import W1D2.ReducerFactory;

public class WordCount {
	private MapperFactory[] mappers;
	private ReducerFactory[] reducers;

	public WordCount(int m, int r) {
		mappers = new MapperFactory[m];
		reducers = new ReducerFactory[r];
	}

	public void shuffleSort() {
		for (int r =0 ; r< reducers.length; r++){
			reducers[r] = new ReducerFactory();
		}
		
		for (int i = 0; i < mappers.length; i++) {
//			System.out.println("Mapper "+i +" output");
			
			mappers[i] = new MapperFactory("mapper"+i+".txt");
			mappers[i].map();
//			mappers[i].printPairs();
			
			for (Pair<String, Integer> m: mappers[i].map()){
				reducers[getPartition(m.getKey())].addMappedObj(m);
			}
		
		}
		
		for (int r =0 ; r< reducers.length; r++){
			System.out.println("Reducer "+r +" output");
			reducers[r].mergeMappedList().stream().sorted()
			.forEach(System.out::println);
		}
	}
	
	public static void main(String[] args) {
		WordCount w = new WordCount(3, 4);
		w.shuffleSort();
	}
	
	public int getPartition(String key){
		
		return (int) Math.abs(key.hashCode() % reducers.length);
	}
}
