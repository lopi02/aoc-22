import java.util.ArrayList;
import java.util.Scanner;

public class Day06 implements AoCTask {
	
	int n = 1; // input size
	
	String[] input = new String[n];
	
	@Override
	public void readInput(Scanner scan) {
		for(int i = 0; i < n; i++) {
			input[i] = scan.nextLine();
		}
	}

	public int markerAt(String str, int pos) {
		ArrayList<Character> letters = new ArrayList<Character>();
		for(int j = 0; j < str.length(); j++) {
				char let = str.charAt(j);
				while(letters.contains(let)) {
					letters.remove(0);
				}
				letters.add(let);
				if (letters.size() == pos) return ( j+1);
		}
		return -1;
	}
	
	@Override
	public String task1() {
		/*
		 * - the first position where the four most recently received characters were all different
		 */
		String out = " ";
		for (int i = 0; i < input.length; i++) {
			String str = input[i];
				out = out + " " + markerAt(str, 4);
			};
		return out;
	}

	@Override
	public String task2() {
		String out = " ";
		for (int i = 0; i < input.length; i++) {
			String str = input[i];
				out = out + " " + markerAt(str, 14);
			};
		return out;
	}

}
