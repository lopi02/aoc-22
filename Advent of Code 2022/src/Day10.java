import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Day10 implements AoCTask {
	
	int n = 144; // input size
	
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
		 * - addx V takes two cycles to complete, after 2 cycles the X register is increased by the value V (could be negative)
		 * - noop takes one cycle to complete
		 * 
		 */

		Task[] workload = new Task[n];

		for (int j = 0; j < workload.length; j++){
			String[] str = input[j].split(" ");
			Task task = null;
			if(str.length == 1) task = new Task(str[0]);
			if(str.length == 2) task = new Task(str[0], Integer.valueOf(str[1]));
			workload[j] = task;
		}

		int X = 1;
		int i = 0;
		int counter = 1;
		int sum = 0;
		ArrayList<Integer> stops = new ArrayList<Integer>(Arrays.asList(20, 60, 100, 140, 180, 220));
		while( i < workload.length){
			int cycles_need = workload[i].execute();
			if(cycles_need == 0) {
				X = X + workload[i].V;
				i++;
			}
			counter++;
			if (stops.contains(counter)) {
				sum = sum + X*counter;
		}
	}

		return "" + sum;
	}

	@Override
	public String task2() {
		char lit =	'#';
		char dark = ' ';
		


		Task[] workload = new Task[n];

		for (int j = 0; j < workload.length; j++){
			String[] str = input[j].split(" ");
			Task task = null;
			if(str.length == 1) task = new Task(str[0]);
			if(str.length == 2) task = new Task(str[0], Integer.valueOf(str[1]));
			workload[j] = task;
		}


		char[][] crt =  new char [6][40];
		for(char[] el : crt) {
			Arrays.fill(el, dark);
		}

		int X = 1; // = SPRITE von X bis X + 2
		int i = 0;
		int counter = 1;

		ArrayList<Integer> stops = new ArrayList<Integer>(Arrays.asList(20, 60, 100, 140, 180, 220));
		while( i < workload.length){
			ArrayList<Integer> sprite = new ArrayList<Integer>(Arrays.asList(X, X+1, X+2));

			if (sprite.contains(counter%40)) {
				int line = counter/40;
				int pos = (counter) % 40 ;
				crt[line][pos] = lit;
		}


			int cycles_need = workload[i].execute();
			if(cycles_need == 0) {
				X = X + workload[i].V;
				i++;
			}
			

		counter++;

	}

		String out = "";
		for (char[] el : crt) {
			out = out + "\n" + String.valueOf(el);
		}

		return out;
	}

	public class Task {
		String type = "";
		int V = 0;
		int cycles_needed = 0;
		Task(String type) {
			this.type = type;
			if (type.equals("noop")) cycles_needed = 1;
			if (type.equals("addx")) throw new IllegalArgumentException("addx: Integer V needed");
		}
		Task(String type, int V) {
			this.type = type;
			this.V = V;
			if (type.equals("noop")) throw new IllegalArgumentException("noop: no Integer V possible");
			if (type.equals("addx")) cycles_needed = 2;
		}

		public int execute() {
			cycles_needed--;
			return cycles_needed;
		}
	}

}
