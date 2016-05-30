package W1D5.Question2;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StripsComparator<k, v> {

	public <k extends Comparable<? super k>> List<StripedElement<k, v>> sort(List<StripedElement<k, v>> list) {

		Collections.sort(list, new Comparator<StripedElement<k, v>>() {

			@Override
			public int compare(StripedElement<k, v> k1, StripedElement<k, v> k2) {

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
