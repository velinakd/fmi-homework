import java.util.Scanner;

public class TicTacToe {

	private static int max(int A, int B, Node state) {
		if (state.isFinal()) {
			return state.evaluate();
		}
		for (int i = 0; i < 9; i++) {
			if (state.isValid(i)) {
				state.makeMove(i, 1);
				int x = min(A, B, state);
				if (A < x) {
					A = x;
				}
				if (A >= B) {
					state.revertMove(i);
					return A;
				}
				state.revertMove(i);
			}
		}
		return A;
	}

	private static int min(int A, int B, Node state) {
		if (state.isFinal()) {
			return state.evaluate();
		}
		for (int i = 0; i < 9; i++) {
			if (state.isValid(i)) {
				state.makeMove(i, 2);
				int x = max(A, B, state);
				if (B > x) {
					B = x;
				}
				if (A >= B) {
					state.revertMove(i);
					return B;
				}
				state.revertMove(i);
			}
		}
		return B;
	}

	public static int play(Node state) {
		int currentMax = -10;
		int bestMove = 0;
		for (int i = 0; i < 9; i++) {
			if (state.isValid(i)) {
				state.makeMove(i, 1);
				int current = min(currentMax, 10, state);
				if (currentMax < current) {
					currentMax = current;
					bestMove = i;
				}
				state.revertMove(i);
			}
		}
		return bestMove;
	}
	
	public static void main(String[] args) {
		Node state = new Node();
		state.makeMove(0, 2);
		state.makeMove(1, 2);
		Scanner in = new Scanner(System.in);
		state.print();
//		while (!state.isFinal()) {
			int move = -1;
//			do {
//				try {
//					move = in.nextInt();
//				} catch (Exception e) {
//					String str = in.next();
//				}
//			} while (move < 0 || move > 8 || !state.isValid(move));
//			state.makeMove(move, 2);
//			state.print();
			System.out.println("");
			if (!state.isFinal()) {
				move = play(state);
				state.makeMove(move, 1);
				state.print();
				System.out.println("");
			}
//		}
		int winner = state.findWinner();
		if (winner == 0)
			System.out.println("It's a draw");
		else
			System.out.println(winner + " wins");
		in.close();
	}

}
