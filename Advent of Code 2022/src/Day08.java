import java.util.Scanner;

public class Day08 implements AoCTask {

	int n = 99; // input size

	String[] input = new String[n];

	@Override
	public void readInput(Scanner scan) {
		for (int i = 0; i < n; i++) {
			input[i] = scan.nextLine();
		}
	}

	@Override
	public String task1() {
		/*
		 * A tree is visible if all of the other trees between it and an edge of the
		 * grid are shorter than it. Only consider trees in the same row or column; that
		 * is, only look up, down, left, or right from any given tree.
		 */
		int[][] grid = new int[n][input[0].length()];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				grid[i][j] = input[i].charAt(j);
			}
		}
		
		
		boolean[][] dp = isVisible(grid, n);
		
		
		int counter = 0;
		for(int x = 0; x < dp.length; x++) {
			for(int y = 0; y < dp.length; y++){
				if (dp[x][y]) counter++;
			}
		}

		return "" + counter;
	}

	public boolean[][] isVisible(int[][] grid, int n) {
		boolean[][] dp = new boolean[n][n];

		// Base Cases
		for (int i = 0; i < n; i++) {
			dp[0][i] = true;
			dp[i][0] = true;
			dp[n-1][i] = true;
			dp[i][n-1] = true;
		}

		//

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (dp[x][y] != true) {
					// from the top
					boolean top = true;
					for (int k = 0; k < y; k++) {
						if (grid[x][k] >= grid[x][y]) {
							top = false;
							break;
						}
					}
					// from the bottom
					boolean bottom = true;
					for (int k = y + 1; k < n; k++) {
						if (grid[x][k] >= grid[x][y]) {
							bottom = false;
							break;
						}
					}
					// from the left
					boolean left = true;
					for (int k = 0; k < x; k++) {
						if (grid[k][y] >= grid[x][y]) {
							left = false;
							break;
						}
					}
					// from the right
					boolean right = true;
					for (int k = x + 1; k < n; k++) {
						if (grid[k][y] >= grid[x][y]) {
							right = false;
							break;
						}
					}
					if (top | bottom | left | right)
						dp[x][y] = true;
				}
			}

		}

		return dp;
	}

	@Override
	public String task2() {
		
		int[][] grid = new int[n][input[0].length()];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < input[i].length(); j++) {
				grid[i][j] = input[i].charAt(j);
			}
		}
		
		
		int[][] dp = getScenicScore(grid, n);
		
		
		int max = 0;
		for(int x = 0; x < dp.length; x++) {
			for(int y = 0; y < dp.length; y++){
				if (dp[x][y] > max) max = dp[x][y];
			}
		}

		return "" + max;
	}
	public int[][] getScenicScore(int[][] grid, int n) {
		int[][] dp = new int[n][n];

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				//top
				int top = 0;
				for (int k = y-1; k >= 0; k--) {
					top++;
					if (grid[x][k] >= grid[x][y]) {
						break;
					}
				}
				
				//bottom
				int bottom = 0;
				for (int k = y+1; k < n; k++) {
					bottom++;
					if (grid[x][k] >= grid[x][y]) break;
				}
				
				
				// left
				int left = 0;
				for (int k = x-1; k >= 0; k--) {
					left++;
					if (grid[k][y] >= grid[x][y]) break;
				}
				
				// right
				int right = 0;
				for (int k = x+1; k < n; k++) {
					right++;
					if (grid[k][y] >= grid[x][y]) break;
				}
				dp[x][y] = top * bottom * right * left;
				//System.out.println(x + "/" + y + ":" +dp[x][y] + "= " + top + " " + bottom + " " + right+ " " +left );
			}

		}

		return dp;
	}


}
