package W1D5.Question1;


import java.util.ArrayList;
import java.util.List;



public class pairNumberProcess {

	private PairMapper[] mapper;
	private pairReducer[] reducer;

	public pairNumberProcess(int m, int r) {
		this.mapper = new PairMapper[m];
		this.reducer = new pairReducer[r];
	}

	public int getPartition(Integer integer) {
		return (int) integer.hashCode() % reducer.length;
	}

	public static void main(String args[]) {
		pairNumberProcess wp = new pairNumberProcess(2, 4);
		wp.processWord();
	}

	private void processWord() {
        
		final String dir = System.getProperty("user.dir");
//        System.out.println("current dir = " + dir);
        
		List<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>> mappedPairs = new ArrayList<>();
		for (int i = 0; i < mapper.length; i++) {
			mapper[i] = new PairMapper(dir+
					"/src/W1D5/Question1/words"
							+ i + ".txt");
			
			
//			mapper[i].mapValues();
//			 mappers[i].printPairs();
//			System.out.println(" I am "+i);
			//mapper[i].mapValues();
			List<keyValuePair<keyValuePair<Integer, Integer>,Integer>> list = mapper[i].inMapperPairs();
			System.out.println("\n_____________Mapper " + i
					+ " Output_____________\n");
			mappedPairs.add(list);
//			printListOfkeyValuePair(list);
		}

		// Now apply shuffle Sort

		List<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>> partitionPairs = shuffleSort(mappedPairs);

	//System.out.println(partitionPairs);

		// Combine the mapped and partioned pairs to in a single list
		List<List<groupByPair<keyValuePair<Integer, Integer>,Integer>>> reducerInputList = new ArrayList<List<groupByPair<keyValuePair<Integer, Integer>,Integer>>>();
		for (int i = 0; i < reducer.length; i++) {

			pairReducer reducer = new pairReducer();
			List<groupByPair<keyValuePair<Integer, Integer>,Integer>> reducerInput = reducer
					.groupKey(partitionPairs.get(i));
			System.out.println("\n_____________Reducer " + i
					+ " Input_____________\n");
			printListOfGroupByPair(reducerInput);

			reducerInputList.add(reducerInput);

		}

		// Reduce the pairs values
		for (int i = 0; i < reducer.length; i++) {

			pairReducer reducer = new pairReducer();
			List<groupByPair<keyValuePair<Integer, Integer>,Integer>> reducerInput = reducerInputList.get(i);
			System.out.println("\n_____________Reducer " + i
					+ " Output_____________\n");
			List<keyValuePair<keyValuePair<Integer,Integer>, Integer>> reducerOutput = reducer
					.numberReduce(reducerInput);
			printKeyValue(reducerOutput);
		}
	}



	private List<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>> shuffleSort(
			List<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>> allMappedPairs) {
		List<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>> partitionPairs = new ArrayList<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>>();
		List<List<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>>> shuffledKeysList = new ArrayList<>();

		for (int i = 0; i < this.mapper.length; i++) {
			shuffledKeysList.add(new ArrayList<List<keyValuePair<keyValuePair<Integer, Integer>,Integer>>>());
		}
		for (int i = 0; i < this.mapper.length; i++) {
			for (int j = 0; j < this.reducer.length; j++) {
				shuffledKeysList.get(i).add(new ArrayList<keyValuePair<keyValuePair<Integer, Integer>,Integer>>());
			}
		}

		for (int i = 0; i < this.reducer.length; i++) {
			partitionPairs.add(new ArrayList<keyValuePair<keyValuePair<Integer, Integer>,Integer>>());
		}

		// shuffle step
		int i = 0;
//		System.out.println(shuffledKeysList);
		for (List<keyValuePair<keyValuePair<Integer, Integer>,Integer>> list : allMappedPairs) {
			for (keyValuePair<keyValuePair<Integer, Integer>,Integer> pair : list) {
				int partitionLevel = getPartition(pair.getKey().getKey());

				shuffledKeysList.get(i).get(partitionLevel).add(pair);
				partitionPairs.get(partitionLevel).add(pair);
			}

			i++;

		}
		pairComparator<Integer, Integer,Integer> comparator = new pairComparator<>();
		for (int j = 0; j < this.mapper.length; j++) {
			for (int k = 0; k < this.reducer.length; k++) {
				System.out.println("\n________Pairs sent from Mapper " + j + " to Reducer " + k + "__________\n");
				if (shuffledKeysList.size() > j && shuffledKeysList.get(j).size() > k) {
					List<keyValuePair<keyValuePair<Integer, Integer>,Integer>> partitionedList = shuffledKeysList.get(j).get(k);
					comparator.sort(partitionedList);
					for (keyValuePair<keyValuePair<Integer, Integer>,Integer> keyVal : partitionedList)
						System.out.println("<" + keyVal.getKey() + "," + keyVal.getValue() + ">");
				}
			}
		}

		return partitionPairs;
	

	}
	private void printListOfkeyValuePair(
			List<keyValuePair<keyValuePair<Integer, Integer>,Integer>> list) {
		// TODO Auto-generated method stub
		if (list != null) {
			for (keyValuePair<keyValuePair<Integer, Integer>,Integer> word : list) {
				System.out.println("<" + word.getKey() + "," + word.getValue()
						+ ">");

			}
		}
	}

	private static void printListOfGroupByPair(
			List<groupByPair<keyValuePair<Integer, Integer>,Integer>> reducerInput) {
		if (reducerInput != null) {
			for (groupByPair<keyValuePair<Integer, Integer>,Integer> item : reducerInput) {
				System.out.println("<" + item.getKey() + "," + item.getValues()
						+ ">");

			}
		}
	}
	
	private void printKeyValue(List<keyValuePair<keyValuePair<Integer, Integer>,Integer>> reducerOutput) {
		// TODO Auto-generated method stub
		if(reducerOutput !=null)
		{
			for(keyValuePair<keyValuePair<Integer,Integer>,Integer> item : reducerOutput)
			{
				System.out.println("< "+item.getKey()+","+item.getValue()+">");
			}
		}
		
	}
}
