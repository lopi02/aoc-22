import java.util.ArrayList;
import java.util.Scanner;

public class Day04 implements AoCTask {

	int n = 1000; // input size

	int[][] input = new int[n][4];

	@Override
	public void readInput(Scanner scan) {
		for (int i = 0; i < n; i++) {
			String str = scan.nextLine();
			String[] pair = str.split(",");
			input[i][0] = Integer.valueOf(pair[0].split("-")[0]);
			input[i][1] = Integer.valueOf(pair[0].split("-")[1]);
			input[i][2] = Integer.valueOf(pair[1].split("-")[0]);
			input[i][3] = Integer.valueOf(pair[1].split("-")[1]);

		}
	}

	@Override
	public String task1() {
		int counter = 0;
		for (int i = 0; i < input.length; i++) {
			int elf1start = input[i][0];
			int elf1end = input[i][1];
			int elf2start = input[i][2];
			int elf2end = input[i][3];
			if ((elf1start <= elf2start && elf1end >= elf2end) || (elf1start >= elf2start && elf1end <= elf2end))
				counter++;
		}
		return "" + counter;
	}

	@Override
	public String task2() {
		int counter = 0;
		for (int i = 0; i < input.length; i++) {
			int elf1start = input[i][0];
			int elf1end = input[i][1];
			int elf2start = input[i][2];
			int elf2end = input[i][3];
			boolean overlap = false;

			ArrayList<Integer> section1 = new ArrayList<Integer>();
			for (int k = elf1start; k <= elf1end; k++) {
				section1.add(k);
			}
			for (int k = elf2start; k <= elf2end; k++) {
				if (section1.indexOf(k) != -1) overlap =true;
			}
			if(overlap) counter++;
		}
		return "" + counter;
	}

}
