public class Node {
	private int[][] board;
	
	public Node() {
		board = new int[3][3];
	}

	public int evaluate() {
		int emptySlots = emptyCount();
		int winner = findWinner();
		int result = 0;
		if(winner == 1) {
			result = emptySlots + 1;
		} else if (winner == 2) {
			result = -1 - emptySlots;
		}
		return result;
	}

	public boolean isValid(int move) {
		int x = move / 3;
		int y = move % 3;
		return board[x][y] == 0;
	}
	
	public void makeMove(int move, int player) {
		doMakeMove(move, player);
	}
	
	private void doMakeMove(int move, int player) {
		int x = move / 3;
		int y = move % 3;
		board[x][y] = player;
	}
	
	public void revertMove(int move) {
		doMakeMove(move, 0);
	}
	
	private int emptyCount() {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == 0)
					count++;
			}
		}
		return count;
	}

	public int findWinner() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != 0 && board[i][0] == board[i][1]
					&& board[i][1] == board[i][2])
				return board[i][0];
			if (board[0][i] != 0 && board[0][i] == board[1][i]
					&& board[1][i] == board[2][i])
				return board[0][i];
		}
		if (board[1][1] != 0
				&& ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) || (board[0][2] == board[1][1] && board[1][1] == board[2][0])))
			return board[1][1];
		return 0;
	}

	public boolean isFinal() {
		if (findWinner() != 0)
			return true;
		if (emptyCount() == 0)
			return true;
		return false;
	}
	
	public void print() {
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
