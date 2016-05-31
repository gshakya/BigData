package W2D1.Question1;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class pairComparator<k,v, val> {

	public <K extends Comparable<? super k>, v extends Comparable<? super v>> List<keyValuePair<keyValuePair<k,v>, val>> sort(List<keyValuePair<keyValuePair<k,v>, val>> list) {
		//Collections.sort(list, new Comparator<KeyValuePair<KeyValuePair<K, V>, V1>>() {
		Collections.sort(list, new Comparator<keyValuePair<keyValuePair<k, v>, val>>() {
			@Override
			public int compare(keyValuePair<keyValuePair<k,v>, val> o1, keyValuePair<keyValuePair<k,v>, val> o2) {

				keyValuePair<k,v> firstPair = o1.getKey();
				keyValuePair<k,v> secondPair = o2.getKey();

				if (firstPair.getKey() instanceof String && secondPair.getKey() instanceof String
						&& firstPair.getValue() instanceof String && secondPair.getValue() instanceof String){
					
					int pairComparision = firstPair.getKey().toString().toLowerCase().compareTo(secondPair.getKey().toString().toLowerCase());
					if( pairComparision != 0) return pairComparision;
					
					pairComparision = firstPair.getValue().toString().toLowerCase().compareTo(secondPair.getValue().toString().toLowerCase());
					return pairComparision;
					
				}
				
				int pairComparision = ((Comparable<? super k>) firstPair.getKey()).compareTo(secondPair.getKey());
				if( pairComparision != 0) return pairComparision;
				
				pairComparision = firstPair.getValue().compareTo(secondPair.getValue());
				return pairComparision;
			}

		});

		return list;
	}
}
