

import java.util.Scanner;

public class Day02 implements AoCTask {

	String[][] input = new String[2500][2];

	/*
	 * A for Rock B for Paper C for Scissors
	 * 
	 * 
	 * rock 1 paper 2 scissor3s
	 */
	@Override
	public void readInput(Scanner scan) {
		for (int i = 0; i < 2500; i++) {
			input[i][0] = scan.next();
			input[i][1] = scan.next();
		}
	}

	@Override
	public String task1() {
		/*
		 * I X for Rock Y for Paper Z for Scissors
		 */
		int score = 0;
		for (int i = 0; i < input.length; i++) {
			String me = input[i][1];
			String elve = input[i][0];
			if (me.equals("X")) {
				score = score + 1; // rock
			} else if (me.equals("Y")) {
				score = score + 2; // paper
			} else if (me.equals("Z")) {
				score = score + 3; // scissors
			}
			;
			if ((me.equals("X") && elve.equals("C")) || (me.equals("Y") && elve.equals("A"))
					|| (me.equals("Z") && elve.equals("B")))
				score = score + 6;
			if ((me.equals("X") && elve.equals("A")) || (me.equals("Y") && elve.equals("B"))
					|| (me.equals("Z") && elve.equals("C")))
				score = score + 3;
		}
		return "" + score;
	}

	@Override
	public String task2() {
		/*
		 * I X for lose Y for draw Z for win
		 */

		int score = 0;
		for (int i = 0; i < input.length; i++) {
			String me = input[i][1];
			String elve = input[i][0];
			if (me.equals("X")) { // lose
				if (elve.equals("A")) { // rock
					score += 3; // scissor
				}
				if (elve.equals("B")) { // paper
					score += 1; // stone
				}
				if (elve.equals("C")) { // scissor
					score += 2; // paper
				}
			}
			if (me.equals("Y")) { // draw
				score += 3;
				if (elve.equals("A")) { // stone
					score += 1; // stone
				}
				if (elve.equals("B")) { // paper
					score += 2; // paper
				}
				if (elve.equals("C")) { // scissor
					score += 3; // scissor
				}
			}
			if (me.equals("Z")) { // win
				score += 6;
				if (elve.equals("A")) { // rock
					score += 2; // paper

				}
				if (elve.equals("B")) { // paper
					score += 3; // scissor

				}
				if (elve.equals("C")) { // scissor
					score += 1; // stone

				}

			}
			;
		}
		return "" + score;
	}

}
