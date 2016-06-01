package W2D2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) {
		ArrayList<String> res = FileToList.getList("W2D2/112.txt");

		Mapper<keyValuePair<PairedKey<String>, Integer>> m1 = new Mapper<keyValuePair<PairedKey<String>, Integer>>();
		for (String txt : res) {
			m1.addElement(new keyValuePair<PairedKey<String>, Integer>(new PairedKey<String>(txt, "112"), 1));
		}

		res = FileToList.getList("W2D2/234.txt");
		Mapper<keyValuePair<PairedKey<String>, Integer>> m2 = new Mapper<keyValuePair<PairedKey<String>, Integer>>();
		for (String txt : res) {
			m1.addElement(new keyValuePair<PairedKey<String>, Integer>(new PairedKey<String>(txt, "234"), 1));
		}

		res = FileToList.getList("W2D2/356.txt");
		Mapper<keyValuePair<PairedKey<String>, Integer>> m3 = new Mapper<keyValuePair<PairedKey<String>, Integer>>();
		for (String txt : res) {
			m1.addElement(new keyValuePair<PairedKey<String>, Integer>(new PairedKey<String>(txt, "356"), 1));
		}

		res = FileToList.getList("W2D2/679.txt");
		Mapper<keyValuePair<PairedKey<String>, Integer>> m4 = new Mapper<keyValuePair<PairedKey<String>, Integer>>();
		for (String txt : res) {
			m1.addElement(new keyValuePair<PairedKey<String>, Integer>(new PairedKey<String>(txt, "679"), 1));
		}

		Handler h = new Handler(1, 4);
		Mapper[] mappers = { m1, m2, m3, m4 };
		h.initMapper(mappers);
		int redIdx = 0;
		for (Reducer<PairedKey<String>, Integer> r : h.getReducers()) {
			System.out.println("Reducer " + redIdx + " output");
			for (Entry<PairedKey<String>, ArrayList<Integer>> ele : r.getElements().entrySet()) {
				System.out.print("< < " + ele.getKey() + "> [ ");
				ele.getValue().stream().forEach(System.out::print);
				System.out.print(" ] > \n");
			}
			redIdx++;
		}

		int normRedIdx = 0;

		for (TreeMap<String, LinkedList<keyValuePair<String, Integer>>> normEnities : h.getNormalizedReducers()) {
			System.out.println("\nReducer Final " + normRedIdx + " output");
			for (Entry<String, LinkedList<keyValuePair<String, Integer>>> normEntity : normEnities.entrySet()) {
				System.out.print(normEntity.getKey() + "-->");
				normEntity.getValue().stream().forEach(k -> System.out.print(" ( " + k + " ) "));
				System.out.println();
			}
			normRedIdx++;
		}

	}
}