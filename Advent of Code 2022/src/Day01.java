import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day01 implements AoCTask {

	ArrayList<String> input = new ArrayList<String>();

	@Override
	public void readInput(Scanner scan) {

		while (scan.hasNextLine()) {
			input.add(scan.nextLine());
		}
	}

	ArrayList<Integer> sums = new ArrayList<Integer>();

	@Override
	public String task1() {
		int max = 0;
		int sum = 0;
		for (int i = 0; i < input.size(); i++) {
			boolean isNumeric = isNumeric(input.get(i));
			if (!isNumeric) {
				sums.add(sum);
				sum = 0;
			}
			if (isNumeric) {
				sum += Integer.valueOf(input.get(i));
			}
			if (sum > max)
				max = sum;

		}
		;
		String out = "" + max;
		return out;

	}

	boolean isNumeric(String s) {
		if (s == null || s.equals("")) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	};

	@Override
	public String task2() {
		int out = 0;
		ArrayList<Integer> sums_top = new ArrayList<Integer>(sums);
		for (int i = 0; i < 3; i++) {
			int max = Collections.max(sums_top);
			out += max;
			sums_top.remove(sums_top.indexOf(max));
		}
		return "" + out;
	}

}
