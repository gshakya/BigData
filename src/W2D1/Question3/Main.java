package W2D1.Question3;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) {
		Handler h = new Handler(3, 2);
		 
		Mapper<keyValuePair<PairedKey<String>, String>> mapper1 = new Mapper<>();
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m1","t1"),"r11"));
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m2","t1"),"r12"));
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m3","t1"),"r13"));
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m4","t1"),"r14"));
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m1","t4"),"r41"));
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m2","t4"),"r42"));
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m3","t4"),"r43"));
		mapper1.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m4","t4"),"r44"));
		
		
		Mapper<keyValuePair<PairedKey<String>, String>> mapper2 = new Mapper<>();
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m1","t0"),"r01"));
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m2","t0"),"r02"));
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m3","t0"),"r03"));
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m4","t0"),"r04"));
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m1","t3"),"r31"));
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m2","t3"),"r32"));
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m3","t3"),"r33"));
		mapper2.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m4","t3"),"r34"));
		
		Mapper<keyValuePair<PairedKey<String>, String>> mapper3 = new Mapper<>();
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m1","t2"),"r21"));
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m2","t2"),"r22"));
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m3","t2"),"r23"));
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m4","t2"),"r24"));
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m1","t5"),"r51"));
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m2","t5"),"r52"));
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m3","t5"),"r53"));
		mapper3.addElement(new keyValuePair<PairedKey<String>, String>(new PairedKey<String>("m4","t5"),"r54"));
			
		Mapper[] mappers = {mapper1,mapper2,mapper3};
		
		h.initMapper(mappers);
		int reducerCnt =0;
		for (Reducer<PairedKey<String>, String> reducer: h.getReducers()){
			Map<PairedKey<String>,ArrayList<String>> reducerElements = reducer.getElements();
			System.out.println("\nReducer "+reducerCnt + " outputs");
			for(Entry<PairedKey<String>,ArrayList<String>> e :reducerElements.entrySet()){
				
				System.out.print( e.getKey()+", ");
				e.getValue().stream().forEach(System.out::print);
				
				System.out.println();
			}
			reducerCnt ++;
		}
		
		
	}
}
