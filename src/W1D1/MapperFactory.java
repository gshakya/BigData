package W1D1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperFactory {

	private String fileLocation;
	private List<Pair<String, Integer>> mappedList = new ArrayList<>();

	public MapperFactory(String fileLocation) {
		this.fileLocation = fileLocation;

	}

	public MapperFactory(){
		
	}
	public static void main(String[] args) {

		MapperFactory m = new MapperFactory("testDataForW1D1.txt");
		List<Pair<String, Integer>> mappedValue = m.map();

		m.printPairs();
		m.printSortedPairs();
	}

	public List<Pair<String, Integer>> map() {
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
						mappedList.add(new Pair<String, Integer>(m.group(0)
								.replace('"', '\0').trim().toLowerCase(), 1));
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

	public void printPairs() {

		System.out.println("-------UNSORTED LIST-----------------");
		System.out.println("-------------------------------------");
		mappedList.stream().forEach(System.out::println);
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");

	}

	public void printSortedPairs() {

		System.out.println("---------SORTED LIST-----------------");
		System.out.println("-------------------------------------");
		mappedList.stream().sorted().forEach(System.out::println);
		System.out.println("-------------------------------------");
		System.out.println("-------------------------------------");

	}

	public List<Pair<String, Integer>> getMappedList() {
		return mappedList;
	}

	public void setMappedList(List<Pair<String, Integer>> mappedList) {
		this.mappedList = mappedList;
	}
	
	
}
