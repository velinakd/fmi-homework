import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class TileBoard {
	private int initialBoard[][] = new int[3][3];
	public static final int finalBoard[][] = {
		{1,2,3},
		{4,0,5},
		{6,7,8}
	};
	private PriorityQueue<Node> toVisit;
	private ArrayList<Node> visited;
	
	public TileBoard(int board[][]) {
		NodeComparator nc = new NodeComparator();
		toVisit = new PriorityQueue<>(10, nc);
		visited = new ArrayList<Node>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.initialBoard[i][j] = board[i][j];
			}
		}
		
		
	}
	
	public boolean moveTiles() {t
		Node startNode = new Node(0, initialBoard);
		toVisit.add(startNode);
		visited.add(startNode);
		while(!toVisit.isEmpty()) {
			Node currentNode = toVisit.poll();
			if(currentNode.getHeuristic() == 0) {
				System.out.println("Yesss we found it!");
				break;
				}
			List<Node> neighbors = currentNode.neighbors();
			for (Node n : neighbors) {
				if(!visited.contains(n))
					toVisit.add(n);
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("Starting...");
		int[][] b = {{1,2,3},{4,5,6},{7,8,0}};
		TileBoard tileBoard = new TileBoard(b);
		tileBoard.moveTiles();
	}

}
