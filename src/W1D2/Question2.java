package W1D2;

public class Question2 {
	public static int f(int x) {
		return x == 4 ? 3 : x == 3 ? 2 : 1;
	}

	public static int g(int x, int y) {
		return x * y;
	}

	public static void main(String[] args) {
		int[] ip = { 1, 6, 2, 8, 2, 9 };
		int[] map = new int[ip.length];

		int fold = 1;

		for (int i = 0; i < ip.length; i++) {
			map[i] = f(ip[i]);
		}

		for (int m : map) {
			fold = fold * m;
		}
		for (int i = 0; i < ip.length; i++) {
			System.out.print(ip[i] + ",");
		}

		System.out.print(" is " + (fold % 2 == 0 ? "" : fold == 1 ? "" : "not ") + "nice");

	}
}
