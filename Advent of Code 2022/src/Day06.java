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

	@Override
	public String task1() {
		/*
		 * - the first position where the four most recently received characters were all different
		 */
		for (int i = 0; i < input.length; i++) {
			String str = input[i];
			ArrayList<Character> letters = new ArrayList<Character>();
			for(int j = 0; j < str.length(); j++) {
					char let = str.charAt(j);
					while(letters.contains(let)) {
						letters.remove(0);
					}
					letters.add(let);
					if (letters.size() == 4) return ""+( j+1);
			}

		}
		return "";
	}

	@Override
	public String task2() {
		for (int i = 0; i < input.length; i++) {
			String str = input[i];
			ArrayList<Character> letters = new ArrayList<Character>();
			for(int j = 0; j < str.length(); j++) {
					char let = str.charAt(j);
					while(letters.contains(let)) {
						letters.remove(0);
					}
					letters.add(let);
					if (letters.size() == 14) return ""+( j+1);
			}

		}
		return "";
	}

}
