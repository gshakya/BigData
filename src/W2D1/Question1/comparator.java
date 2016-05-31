package W2D1.Question1;

import java.util.Collections;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import W2D1.Question3.keyValuePair;

public class comparator<k,v> {

	public <k extends Comparable<? super k>> List<keyValuePair<k,v>> sort(List<keyValuePair<k,v>> list) {

		Collections.sort(list, new Comparator<keyValuePair<k,v>>() {

			@Override
			public int compare(keyValuePair<k,v> k1, keyValuePair<k,v> k2) {

				k key1 = k1.getKey();
				k key2 = k2.getKey();
				
				if (key1 instanceof String && key2 instanceof String)
					return key1.toString().toLowerCase().compareTo(key2.toString().toLowerCase());
				return key1.compareTo(key2);
			}
		});

		return list;
	}

}
