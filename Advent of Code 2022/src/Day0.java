
import java.util.Scanner;

public class Day0 implements AoCTask {
	
	int[] input = new int[2000];
	
	@Override
	public void readInput(Scanner scan) {
		for(int i = 0; i < 2000; i++) {
			input[i] = scan.nextInt();
		}
	}

	@Override
	public String task1() {
		return "test";
	}

	@Override
	public String task2() {
		return "test";
	}

}