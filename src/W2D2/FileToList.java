package W2D2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import W1D1.Pair;

public class FileToList {

	public static ArrayList<String> getList(String fileLocation) {
		ArrayList<String> result = new ArrayList();
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
						result.add(m.group(0).replace('"', '\0').trim().toLowerCase());
					}
				}

			}
			br.close();
			return result;
		}

		catch (Exception e) {// Catch exception if any
			e.printStackTrace();
			return null;
		}
	}

}
