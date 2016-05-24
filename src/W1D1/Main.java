package W1D1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		collector("testDataForW1D1.txt");
	}

	public static void collector(String fileLocation) {
		try {
			List<Pair<String, Integer>> storeWordList = new ArrayList<>();
			FileInputStream fstream = new FileInputStream(fileLocation);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			String regexPattern = "\"??([A-Za-z]+-??[A-Za-z]+|[A-Za-z])\"??\\.??";
			Pattern r = Pattern.compile(regexPattern);
			Matcher m;
			
			// Read File Line By Line
			String[] tokenArray ;
			while ((strLine = br.readLine()) != null) {
				tokenArray = strLine.split(" ");
				
				for (String token : tokenArray){
					m= r.matcher(token);
					if(m.find()){
//						System.out.println(m.group(0).replace('"', '\0').trim());
						storeWordList.add(new Pair<String, Integer>(m.group(0).replace('"', '\0').trim().toLowerCase(), 1));
					}
				}
				
			}
			br.close();
			System.out.println("-------UNSORTED LIST-----------------");
			System.out.println("-------------------------------------");
			storeWordList.stream().forEach(System.out::println);
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
			
			
			System.out.println("---------SORTED LIST-----------------");
			System.out.println("-------------------------------------");
			storeWordList.stream().sorted().forEach(System.out::println);
			System.out.println("-------------------------------------");
			System.out.println("-------------------------------------");
		}

		catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}
