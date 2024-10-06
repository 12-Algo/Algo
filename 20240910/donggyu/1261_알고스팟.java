import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] board;
	static int[][] dist;
	static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1.cost, o2.cost));
		pq.add(new Node(0, 0, 0));
		dist[0][0] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			for (int[] d : delta) {
				int nx = now.x+d[0];
				int ny = now.y+d[1];
				
				if(!inRange(nx, ny)) continue;
				
				int ncost = now.cost;
				if(board[nx][ny]==1) {
					ncost++;					
				} 
				
				if(dist[nx][ny]>ncost) {
					dist[nx][ny] = ncost;
					pq.add(new Node(nx, ny, ncost));
				}
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<M;
	}
	
	static class Node{
		int x, y, cost;
		
		Node(int x, int y, int cost){
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(input.nextToken());
		N = Integer.parseInt(input.nextToken());
		board = new int[N][M];
		dist = new int[N][M];
		
		for(int i=0;i<N;i++) { 
			String s = bf.readLine();
			for(int j=0;j<M;j++) {
				board[i][j] = s.charAt(j)-'0';
				dist[i][j] = Integer.MAX_VALUE; 
			}
		}

		dijkstra();
		System.out.println(dist[N-1][M-1]);
	}
}

