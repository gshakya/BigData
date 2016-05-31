package W2D2;

import java.util.ArrayList;

public class Handler {
	private Mapper<keyValuePair<PairedKey<String>, Integer>>[] mappers;
	private Reducer<PairedKey<String>, Integer>[] reducers;

	Handler(int m, int r) {
		mappers = new Mapper[m];
		reducers = new Reducer[r];
		for (int i = 0; i < reducers.length; i++) {
			reducers[i] = new Reducer<>();
		}

	}

	public void initMapper(Mapper[] m) {
		mappers = m;
		populateReducers();
	}

	public Reducer[] getReducers() {
		return reducers;
	}

	private void populateReducers() {
		int mapIdx = 0;
		for (Mapper m : mappers) {
			ArrayList<keyValuePair<PairedKey<String>, Integer>> mapperElements = m.getElements();

			for (keyValuePair<PairedKey<String>, Integer> mapperElement : mapperElements) {
				int redIdx = getPartition(mapperElement.getKey().getFirst());
				reducers[redIdx].addElement(mapperElement.getKey(), mapperElement.getValue());
				System.out.println("Mapper " + mapIdx + " output to Reducer " + redIdx);
				System.out.println("< <"+mapperElement.getKey()+"> ,"+mapperElement.getValue()+">");
			}
			mapIdx++;
		}
	}

	public int getPartition(Object o) {
		return (int)Math.abs( o.hashCode() % reducers.length);
	}

}
