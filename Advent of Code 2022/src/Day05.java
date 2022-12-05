import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Day05 implements AoCTask {

	int n = 515; // input size

	String[] input = new String[n];

	@Override
	public void readInput(Scanner scan) {
		for (int i = 0; i < n; i++) {
			input[i] = scan.nextLine();
		}
	}

	public Stackes[] createStacks(String[] arr) {
		ArrayList<String> input_stacks = new ArrayList<String>();
		int i = 0;
		while (!arr[i].isBlank()) {
			input_stacks.add(arr[i]);
			i++;
		}
		;
		ArrayList<Stackes> out = new ArrayList<Stackes>();
		while (input_stacks.get(0).length() > 0) {
			out.add(new Stackes());
			for (int j = input_stacks.size() - 2; j >= 0; j--) {
				String str = input_stacks.get(j);
				Stackes current = out.get(out.size() - 1);
				if (str.charAt(0) == '[') {
					current.add(new crates(str.charAt(1)));
				}
				if (str.length() > 3)
					str = str.substring(4);
				else
					str = "";
				input_stacks.set(j, str);
			}
		}
		;
		Stackes[] output = new Stackes[out.size()];
			for (int j = 0; j < out.size(); j++) {
				output[j] = out.get(j);
			}
		return  output;
	}
	String ReturnTop (Stackes[] stack) {
		String out = "";
		for (int i = 0; i < stack.length; i++)
			out = out + (stack[i].getTop() != null ? stack[i].getTop().getValue(): " ");
		return out;
	}

	@Override
	public String task1() {
		Stackes[] stack = (Stackes[]) createStacks(input);
		int startline = 0;
		do {
			startline++;
		} while (!input[(startline == 0 ? 0 : startline - 1)].isBlank());
		for (int i = startline; i < input.length; i++) {
			String[] str = input[i].split("move | from | to ");
			//System.out.println(input[i] + ": " + Arrays.toString(str));
			int moves = Integer.valueOf(str[1]);
			int start = Integer.valueOf(str[2]) - 1;
			int goal = Integer.valueOf(str[3]) - 1;
			for (int k = 0; k < moves; k++) {
				crates crate = stack[start].crateMover_9000();
				stack[goal].add(crate);
			}
			//System.out.println(ReturnTop(stack));
		}
		return ReturnTop(stack);
	}
	@Override
	public String task2() {
		Stackes[] stack = (Stackes[]) createStacks(input);
		int startline = 0;
		do {
			startline++;
		} while (!input[(startline == 0 ? 0 : startline - 1)].isBlank());
		for (int i = startline; i < input.length; i++) {
			String[] str = input[i].split("move | from | to ");
			//System.out.println(input[i] + ": " + Arrays.toString(str));
			int height = Integer.valueOf(str[1]);
			int start = Integer.valueOf(str[2]) - 1;
			int goal = Integer.valueOf(str[3]) - 1;
			stack[start].crateMover_9001(height, stack[goal]);
			//System.out.println(ReturnTop(stack));
		}
		return ReturnTop(stack);
	}

}

class Stackes {
	crates top = null;

	void add(crates c) {
		c.below = top;
		this.top = c;
		//System.out.println("top ok");
	}

	crates crateMover_9000() {
		crates topel = top;
		this.top = top.below;
		topel.removeBelow();
		return topel;
	}
	
	void crateMover_9001(int height, Stackes goal) {
		crates currentEl = top;
		crates topold = top;
		for (int i = 1; i <height; i++) {
			currentEl = currentEl.below;
		};
		this.top = currentEl.below;

		currentEl.below = goal.getTop();
		goal.top = topold;
	}

	crates getTop() {
		return this.top;
	}

	Stackes() {
	}
}

class crates {
	Character value;
	crates below;

	crates(Character value, crates below) {
		this.value = value;
		this.below = below;
	}

	crates(Character value) {
		this.value = value;
		this.below = null;
	}

	void setBelow(crates below) {
		this.below = below;
	}

	void removeBelow() {
		this.below = null;
	}

	void setValue(Character value) {
		this.value = value;
	}

	crates getBelow() {
		return this.below;
	}

	Character getValue() {
		return this.value;
	}

}