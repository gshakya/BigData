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

		for (int i = 0; i < r; i++) {
			reducers[i] = new ReducerFactory();
		}
	
	}
	
	public void buildMappers(MapperFactory[] mappers){
		this.mappers=mappers;
		for (int i = 0; i < this.mappers.length; i++) {
			this.mappers[i].map();
		}
		
	}

	public void shuffleSort() {

		for (int i = 0; i < mappers.length; i++) {
			
			for (Pair<String, Integer> m : mappers[i].getMappedList()) {
				reducers[getPartition(m.getKey())].addMappedObj(m);
			}
		}

		
	}
	
	
public void mappedList(){
	System.out.println("------------------------------------");
	for (int m = 0; m < mappers.length; m++) {
		System.out.println("Mapped " + m + " output");
		mappers[m].getMappedList().stream().sorted()
				.forEach(System.out::println);
	}
}
	
public void mergeList(){
	System.out.println("------------------------------------");
	for (int r = 0; r < reducers.length; r++) {
		System.out.println("Reducer " + r + " merged output");
		reducers[r].mergeMappedList().stream().sorted()
				.forEach(System.out::println);
	}
}

public void reduceList(){
	System.out.println("------------------------------------");
	for (int red = 0; red < reducers.length; red++) {
		System.out.println("Reducer " + red + " reduced output");
		reducers[red].reduceMergedList().stream().sorted()
				.forEach(System.out::println);
	}
}

	public static void main(String[] args) {
		WordCount w = new WordCount(3, 4);
		MapperFactory[] mappers  = new MapperFactory[3];
		
		for (int i = 0; i < mappers.length; i++) {
			mappers[i] = new MapperFactory("mapper" + i + ".txt");
			mappers[i].map();
		}
		w.buildMappers(mappers);

		w.mappedList();
		w.shuffleSort();		
		w.mergeList();
		w.reduceList();
	}

	public int getPartition(String key) {

		return (int) Math.abs(key.hashCode() % reducers.length);
	}
}
