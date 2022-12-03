import java.util.Scanner;

public class Day03 implements AoCTask {
	int n = 300; // # input

	String[] input = new String[n];

	@Override
	public void readInput(Scanner scan) {
		for (int i = 0; i < n; i++) {
			input[i] = scan.next();
		}
	}

	@Override
	public String task1() {
		int x = 0;
		for (int i = 0; i < input.length; i++) {
			String str = input[i];
			String firstHalf = str.substring(0, str.length() / 2);
			String secondHalf = str.substring(str.length() / 2, str.length());
			char c = getSameofTwo(firstHalf, secondHalf);
			int prio = getPriority(c);
			x = x + prio;
		}

		return "" + x;
	}

	public char getSameofTwo(String x, String y) {
		for (int i = 0; i < x.length(); i++) {
			for (int j = 0; j < y.length(); j++) {
				if (x.charAt(i) == y.charAt(j))
					return x.charAt(i);
			}
		}
		return (char) 0;
	}

	public int getPriority(char l) {
		int x = 0;
		char[] arr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
				'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == l)
				return i + 1;
		}
		return x;
	}

	@Override
	public String task2() {
		int x = 0;
		for (int i = 0; i < input.length; i = i+3) {
			char c = getSameofThree(input[i], input[i + 1], input[i + 2]);
			int prio = getPriority(c);
			x = x + prio;
			//System.out.println(c + " " + prio + " x =" + x);

		}
		return "" + x;
	}

	public char getSameofThree(String x, String y, String z) {
		for (int i = 0; i < x.length(); i++) {
			for (int j = 0; j < y.length(); j++) {
				for (int k = 0; k < z.length(); k++) {
					if (
							x.charAt(i) == y.charAt(j) 
							&& y.charAt(j) == z.charAt(k) 
							&& x.charAt(i) == z.charAt(k))
						return x.charAt(i);
				}
			}
		}
		return (char) 0;
	}

}
