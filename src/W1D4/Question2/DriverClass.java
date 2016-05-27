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
	public static void main(String[] args) {
		List<String> str = getStringsForFile("W1D4/File0.txt");
		CharMapper cMapper = new CharMapper();
		str.stream().forEach(s -> cMapper.addCharacter(s));
		
		
		List<String> str1 = getStringsForFile("W1D4/File1.txt");
		CharMapper cMapper1 = new CharMapper();
		str1.stream().forEach(s -> cMapper1.addCharacter(s));
		
		
		
		CharReducer cReducer = new CharReducer();
		for (Entry<Character, Integer[]> mappedVal : cMapper.getCharMap()
				.entrySet()) {
			cReducer.addPairs(mappedVal);
		}
		
		for (Entry<Character, Integer[]> mappedVal : cMapper1.getCharMap()
				.entrySet()) {
			cReducer.addPairs(mappedVal);
		}
		
		printKeyListPair(cReducer.getMergedPairs());
		System.out.println("-------------------");
		printKeyPair(cReducer.getReducedPairs());
		
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
						mappedList.add(m.group(0).replace('"', '\0').trim()
								.toLowerCase());
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

	public static void printKeyPair(Map<Character, Integer[]> collections) {
		for (Map.Entry<Character, Integer[]> coll : collections.entrySet()) {
			System.out.println("< " + coll.getKey() + " , ["
					+ coll.getValue()[0] + ", " + coll.getValue()[1] + "] >");

		}
	}

	public static void printKeyListPair(
			Map<Character, List<Integer[]>> collections) {
		for (Map.Entry<Character, List<Integer[]>> coll : collections
				.entrySet()) {
			String pairList = "";
			for (Integer[] pair : coll.getValue()) {
				pairList += " [" + pair[0] + ", " + pair[1] + "] ";
			}
			System.out.println("< " + coll.getKey() + " , [" + pairList
					+ "] >");

		}
	}
}
