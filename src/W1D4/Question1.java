package W1D4;

import W1D1.MapperFactory;
import W1D1.Pair;
import W1D2.ReducerFactory;

public class Question1 {
	private MapperFactory[] mappers;
	private MapperFactory[] inMappers;
	private ReducerFactory[] reducers;

	public Question1(int m, int r) {
		mappers = new MapperFactory[m];
		inMappers =  new MapperFactory[m];
		reducers = new ReducerFactory[r];

		for (int i = 0; i < r; i++) {
			reducers[i] = new ReducerFactory();
		}
		for (int j= 0; j < m ; j++) {
			inMappers[j] = new MapperFactory();
		}
		
		for (int j= 0; j < m ; j++) {
			mappers[j] = new MapperFactory();
		}

	}
	
	public void setMappers(MapperFactory[] mappers) {
		this.mappers = mappers;
	}
	
	public void localAggregation(){
		ReducerFactory[] aggregator =   new ReducerFactory[mappers.length];
		for (int i = 0; i < aggregator.length; i++) {
			aggregator[i] = new ReducerFactory(mappers[i].getMappedList());
			aggregator[i].mergeMappedList();
			inMappers[i].setMappedList(aggregator[i].reduceMergedList());
		}
		
	}
;
	

	public void shuffleSort() {
		for (int i = 0; i < inMappers.length; i++) {
			for (Pair<String, Integer> m : inMappers[i].getMappedList()) {
				reducers[getPartition(m.getKey())].addMappedObj(m);
			}
		}

	}

	public void mappedList() {
		System.out.println("------------------------------------");
		for (int m = 0; m < inMappers.length; m++) {
			System.out.println("Mapped " + m + " output");
			inMappers[m].getMappedList().stream().sorted().forEach(System.out::println);
		}
	}

	public void mergeList() {
		System.out.println("------------------------------------");
		for (int r = 0; r < reducers.length; r++) {
			System.out.println("Reducer " + r + " merged output");
			reducers[r].mergeMappedList().stream().sorted().forEach(System.out::println);
		}
	}

	public void reduceList() {
		System.out.println("------------------------------------");
		for (int red = 0; red < reducers.length; red++) {
			System.out.println("Reducer " + red + " reduced output");
			reducers[red].reduceMergedList().stream().sorted().forEach(System.out::println);
		}
	}
	
	public MapperFactory[] getMappers() {
		return mappers;
	}

	public ReducerFactory[] getReducers() {
		return reducers;
	}

	public static void main(String[] args) {
		Question1 w = new Question1(3, 4);
		MapperFactory[] mappers = new MapperFactory[3];

		for (int i = 0; i < mappers.length; i++) {
			mappers[i] = new MapperFactory("mapper" + i + ".txt");
			mappers[i].map();
		}
		w.setMappers(mappers);

		w.localAggregation();

		w.mappedList();
		w.shuffleSort();
		w.mergeList();
		w.reduceList();
	}

	public int getPartition(String key) {

		return (int) Math.abs(key.hashCode() % reducers.length);
	}
}
