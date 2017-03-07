import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.Math;

public class Node implements Comparable<Node> {
	private int path;
	private int heuristic;
	public int board[][];
	public List<Pair> coords = Arrays.asList(new Pair(1,1), 
			new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0,0), 
			new Pair(0,0), new Pair(0,0), new Pair(0,0), new Pair(0, 0));
	private static final List<Pair> finalCoords = Arrays.asList(new Pair(1,1), 
			new Pair(0,0), new Pair(0,1), new Pair(0,2), new Pair(1,0), new Pair(1,2),
			new Pair(2,0), new Pair(2,1), new Pair(2,2));
	
	public Node(int path, int board[][]) {
		this.path = path;
		this.board = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.board[i][j] = board[i][j];
				coords.set(this.board[i][j], new Pair(i,j));
			}
		}
		this.heuristic = this.calculateHeuristic();
	}
	
	public int getPath() {
		return path;
	}
	
	public int getHeuristic() {
		return heuristic;
	}
	
	private int[][] move(int x, int y) {
		int[][] newBoard = new int[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newBoard[i][j] = this.board[i][j];
			}
		}
		int zeroPosx = coords.get(0).getX();
		int zeroPosy = coords.get(0).getY();
		newBoard[zeroPosx + x][zeroPosy + y] = 0;
		newBoard[zeroPosx][zeroPosy] = this.board[zeroPosx + x][zeroPosy + y];	
		return newBoard;
	}
	
	public List<Node> neighbors() {
		List<Node> neighbors = new ArrayList<Node>(4);
		if(coords.get(0).getX() > 0) {
			neighbors.add(new Node(this.path + 1, move(-1,0)));
		}
		if(coords.get(0).getX() < 2) {
			neighbors.add(new Node(this.path + 1, move(1,0)));
		}
		if(coords.get(0).getY() > 0) {
			neighbors.add(new Node(this.path + 1, move(0,-1)));
		}
		if(coords.get(0).getY() < 2) {
			neighbors.add(new Node(this.path + 1, move(0,1)));
		}
		return neighbors;
	}
	
	public int calculateHeuristic() {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			sum+=Math.abs(coords.get(i).getX()+ coords.get(i).getY() -
							finalCoords.get(i).getX() - coords.get(i).getY());
		}
		return sum;
	}

	@Override
	public int compareTo(Node other) {
		int result = 0;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (this.board[i][j] != other.board[i][j])
					result = 1;
			}
		}
		return result;
	}
	
	public boolean equals(Node other) {
		boolean result = true;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				if (this.board[i][j] != other.board[i][j])
					result = false;
			}
		}
		return result;
	}
	
	public int compareHeuristics(Node other) {
		int result = 0;
        if((this.getHeuristic() + this.getPath()) <= (other.getHeuristic() + other.getPath())) {
        	result = -1;	        
        } else {
        	result = ((this.getHeuristic() + this.getPath()) >= (other.getHeuristic() + other.getPath())) ? 1 : 0;
        }
        return result;
	}
	
}
