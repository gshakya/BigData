package W1D4.Question2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverClass {
	CharMapper[] mappers;
	CharReducer[] reducers;

	public DriverClass(CharMapper[] mappers, int noOfReducers) {
		this.mappers = mappers;
		reducers = new CharReducer[noOfReducers];
		for (int i = 0; i<reducers.length; i++) {
			reducers[i] = new CharReducer();
		}
	}

	public CharReducer[] getReducers() {
		shuffleSort();
		return reducers;

	}

	private void shuffleSort() {
		for (CharMapper c : mappers) {
			for (Entry<Character, Integer[]> e : c.getCharMap().entrySet()) {
				reducers[getPartition(e.getKey())].addPairs(e);
			}

		}
	}

	public static void main(String[] args) {
		CharMapper[] inputMappers = new CharMapper[4];

		for (int i = 0; i < inputMappers.length; i++) {
			List<String> str = getStringsForFile("W1D4/File" + i + ".txt");
			inputMappers[i] = new CharMapper();

			for (String s : str) {
				inputMappers[i].addCharacter(s);
			}
		}

		DriverClass d = new DriverClass(inputMappers, 3);

		CharReducer[] reducers = d.getReducers();
		

		for (int i = 0; i < inputMappers.length; i++) {
			System.out.println("----------Mapper" + i + "-------");
			printKeyPair(inputMappers[i].getCharMap());
		
		}
		

		for (int i = 0; i < reducers.length; i++) {
			System.out.println("----------Reducer " + i + "-------");
			printKeyListPair(reducers[i].getMergedPairs());
		
		}
		for (int i = 0; i < reducers.length; i++) {
			System.out.println("----------Reducer " + i + " merged-------");
			printKeyPair(reducers[i].getReducedPairs());
		}
		
		for (int i = 0; i < reducers.length; i++) {
			System.out.println("----------Reducer " + i + " Average character count-------");
			printAverageStringLength(reducers[i].getReducedPairs());
		}
		
	}

	public static List<String> getStringsForFile(String fileLocation) {
		List<String> mappedList = new ArrayList<>();
		try {

			FileInputStream fstream = new FileInputStream(fileLocation);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			String regexPattern = "\"??([A-Za-z]+-??[A-Za-z]+|[A-Za-z])\"??\\.??";
			Pattern r = Pattern.compile(regexPattern);
			Matcher m;

			// Read File Line By Line
			String[] tokenArray;
			while ((strLine = br.readLine()) != null) {
				tokenArray = strLine.split(" ");

				for (String token : tokenArray) {
					m = r.matcher(token);
					if (m.find()) {
						// System.out.println(m.group(0).replace('"',
						// '\0').trim());
						mappedList.add(m.group(0).replace('"', '\0').trim().toLowerCase());
					}
				}
			}
			br.close();
			return mappedList;
		}

		catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
			return null;
		}
	}
	
	public static  void printAverageStringLength(Map<Character, Integer[] >reducer)
	{
		for (Entry<Character, Integer[] > e: reducer.entrySet()){
			System.out.println("< "+e.getKey()+" , "+(float)e.getValue()[0]/e.getValue()[1]+" >");
		}
	}

	public static void printKeyPair(Map<Character, Integer[]> collections) {
		for (Map.Entry<Character, Integer[]> coll : collections.entrySet()) {
			System.out.println("< " + coll.getKey() + " , [" + coll.getValue()[0] + ", " + coll.getValue()[1] + "] >");

		}
	}

	public static void printKeyListPair(Map<Character, List<Integer[]>> collections) {
		for (Map.Entry<Character, List<Integer[]>> coll : collections.entrySet()) {
			String pairList = "";
			for (Integer[] pair : coll.getValue()) {
				pairList += " [" + pair[0] + ", " + pair[1] + "] ";
			}
			System.out.println("< " + coll.getKey() + " , [" + pairList + "] >");

		}
	}

	public int getPartition(Character key) {

		return (int) Math.abs(key.hashCode() % reducers.length);
	}
}
