package bg.unisofia.fmi.ai.nqueens;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class NQueens {
	private Random rand = new Random(13331);
	private int n;
	private double MAX_ITER;
	private int[] queens;

	int[] columns;
	int[] firstDiagonal;
	int[] secondDiagonal;
	
	public NQueens(int n) {
		this.n = n;
		queens = new int[n];
		MAX_ITER = 20000;
		columns = new int[n];
		secondDiagonal = new int[2*n];
		firstDiagonal = new int[2*n];
	}

	private void initializePositions() {
		for (int i = 0; i < n; i++) {
//			queens[i] = r.nextInt(n);
//			queens[i] = (int) (Math.random() * (n - 1)) + 1;
//			queens[i] = (int) (Math.random() * n);
			queens[i] = rand.nextInt(n);
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
		Arrays.fill(columns , 0);
		Arrays.fill(secondDiagonal , 0);
		Arrays.fill(firstDiagonal , 0);
		for(int i=0;i<n;++i){
		 if(i != x) {
			 ++columns[queens[i]];
			 ++secondDiagonal[i + queens[i]];
			 ++firstDiagonal[queens[i] - i + n];
		 }
		}
		int minCount = (int)1E9;
		int currentCount = 0;

		ArrayList<Integer> arr = new ArrayList<>();
		
		for (int j = 0; j < n; j++) {
			queens[x] = j;
			currentCount = columns[queens[x]];
			currentCount += secondDiagonal[x + queens[x]];
			currentCount += firstDiagonal[queens[x] - x + n];
			//currentCount = numberOfConflicts(x);
			if (minCount > currentCount) {
				arr.clear();
	            minCount = currentCount;
			}
			if (minCount == currentCount) {
				arr.add(j);
			}
		}
		queens[x] = arr.get(rand.nextInt(arr.size()));
	}

	private boolean isSolution() {
		Arrays.fill(columns , 0);
		Arrays.fill(secondDiagonal , 0);
		Arrays.fill(firstDiagonal , 0);
		for(int i=0;i<n;++i){
			 if(++columns[queens[i]] > 1)
				 return false;
			 if(++secondDiagonal[i + queens[i]] > 1)
				 return false;
			 if(++firstDiagonal[queens[i] - i + n] > 1)
				 return false;
		}
		//for (int i = 0; i < n; i++) {
		//	if (numberOfConflicts(i) != 0)
		//		return false;
		//}
		return true;
	}
	
	private void print() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(queens[i] == j) {
					System.out.print("* ");
				} else {
					System.out.print("- ");
				}
			}
			System.out.println("");
		}
	}

	public void findSolution() {
//		Random r = new Random(System.currentTimeMillis());
		while (true) {
			int k = 0;
			initializePositions();
//			System.err.println("Init");
			int i = -1;
			int last = -1;
			while (k <= MAX_ITER) {
				while (i == last || (numberOfConflicts(i) == 0)) {
//					i = r.nextInt(n);
					i = rand.nextInt(n);
				}
				
				last = i;
//				System.out.println(k + "MIN_START");
					minConflicts(i);
//				System.out.println(k + "MIN_END");
				if (isSolution()) {
					//System.out.println(Arrays.toString(queens));
					print();
					return;
				}
//				System.out.println("FINAL CHECK");
				k++;
			}
//			System.out.println();
		}
	}

	public static void main(String[] args) {
		double beg = System.currentTimeMillis();
		NQueens nq = new NQueens(50);
		nq.findSolution();
		double end = System.currentTimeMillis();
		System.out.printf("%.3f\n", (end - beg)/1000);
	}
}
