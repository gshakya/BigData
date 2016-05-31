package W2D2;

public class PairedKey<E extends Comparable<E>> implements Comparable<PairedKey> {
	private E first;
	private E second;

	public PairedKey(E first, E second) {
		super();
		this.first = first;
		this.second = second;
	}

	public E getFirst() {
		return first;
	}

	public E getSecond() {
		return second;
	}

	@Override
	public int compareTo(PairedKey o) {
		if (o.getFirst().compareTo(first) == 0) {
			if (second.compareTo((E) o.getSecond()) == 0) {
				return 0;
			} else {
				return second.compareTo((E) o.getSecond());
			}
		}
		return o.getFirst().compareTo(first);
	}

	@Override
	public String toString() {
		return first + " , " + second;
	}

}
