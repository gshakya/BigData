package W1D4;

import W1D1.MapperFactory;
import W1D2.ReducerFactory;
import W1D3.WordCount;

public class Question1 {
	private MapperFactory[] mappers;
	private WordCount InMapper;
	private ReducerFactory[] reducedMapper;
	private ReducerFactory[] reducers;
	

	public Question1(int m, int r) {
			mappers = new MapperFactory[m];
			reducedMapper = new ReducerFactory[m];
			reducers = new ReducerFactory[r];

			for (int i = 0; i < r; i++) {
				reducers[i] = new ReducerFactory();
			}

			for (int i = 0; i < mappers.length; i++) {
				// System.out.println("Mapper " + i + " output");
				mappers[i] = new MapperFactory("mapper" + i + ".txt");
				mappers[i].map();
			}

		}
	
	
	
	
	
}
