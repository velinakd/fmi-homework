package bg.unisofia.fmi.ai.nqueens;

import java.lang.Math;
import java.util.Arrays;

public class NQueens {
	private int n;
	private double MAX_ITER;
	private char[][] queens;

	public NQueens(int n) {
		this.n = n;
		queens = new char[n][n];
		Arrays.fill(queens, "_");
		MAX_ITER = 2*n;
	}

	private void initializePositions() {
		int j=0;
		for (int i = 0; i < n; i++) {
			j = (int) (Math.random() * (n - 1)) + 1;
			queens[i][j] = '*';
		}
	}

	private boolean inConflict(int x, int y) {
		if (x == y)
			return false;
		if (queens[x] == queens[y] || (x - queens[x]) == (y - queens[y])
				|| (x + queens[x]) == (y + queens[y]))
			return true;
		return false;
	}

	private int numberOfConflicts(int x) {
		int number = 0;
		for (int i = 0; i < n; i++) {
			if (inConflict(x, i))
				number++;
		}
		return number;
	}

	private void minConflicts(int x) {
		int minCount = 0;
		int rowWithMinCount = 0;
		int currentCount = 0;

		for (int j = 0; j < n; j++) {
			queens[x] = j;
			currentCount = numberOfConflicts(x);
			if (minCount > currentCount) {
				minCount = currentCount;
				rowWithMinCount = j;
			}
		}
		queens[x] = rowWithMinCount;
	}

	private boolean isSolution() {
		for (int i = 0; i < n; i++) {
			if (numberOfConflicts(i) != 0)
				return false;
		}
		return true;
	}

	public void findSolution() {
		while (true) {
			int k = 0;
			initializePositions();
			System.err.println("Init");
			int i = -1;
			int last = -1;
			while (k <= MAX_ITER) {
				while (i == last) {
					i = (int) (Math.random() * (n - 1));
				}
				last = i;
				System.out.println(k + "MIN_START");
				minConflicts(i);
				System.out.println(k + "MIN_END");
				if (isSolution()) {
					System.out.println(Arrays.toString(queens));
					return;
				}
				System.out.println("FINAL CHECK");
				k++;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		NQueens nq = new NQueens(8);
		nq.findSolution();
	}
}
