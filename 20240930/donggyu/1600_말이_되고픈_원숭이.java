import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

	static int k, w, h;
	static int[][] board;
	static int[][] monkeyMoves = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	static int[][] horseMoves = {{2, 1}, {1, 2}, {2, -1}, {1, -2}, {-2, 1}, {-2, 1}, {-1, -2}, {-2, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(bf.readLine());
		StringTokenizer input = new StringTokenizer(bf.readLine());
		w = Integer.parseInt(input.nextToken());
		h = Integer.parseInt(input.nextToken());
		board = new int[h][w];
		for(int i=0;i<h;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			for(int j=0;j<w;j++) {
				board[i][j]= Integer.parseInt(line.nextToken()); 
			}
		}
	
		bfs(0, 0);
	}
	
	static void bfs(int x, int y) {
		int[][][] visited = new int[h][w][k+1];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y, 0});
		visited[x][y][0] = 1;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0], nowY = now[1], horse=now[2];
			
			if(nowX == h-1 && nowY == w-1) {
				System.out.println(visited[nowX][nowY][horse]);
				return;
			}
			
			for(int d=0;d<4;d++) {
				int nx = nowX+monkeyMoves[d][0], ny = nowY+monkeyMoves[d][1];
				if(nx<0 || ny<0 || nx>h-1 || ny>w-1) continue;
				if(board[nx][ny]==1) continue;
				if(visited[nx][ny][horse]!=0) continue;
				
				visited[nx][ny][horse] = visited[nx][ny][horse]+1;
				q.add(new int[] {nx, ny, horse});
			}
			
			if(horse<k) {
				for(int i=0;i<8;i++) {
					int nx = nowX+horseMoves[i][0], ny = nowY+horseMoves[i][1];
					if(nx<0 || ny<0 || nx>h-1 || ny>w-1) continue;
					if(board[nx][ny]==1) continue;
					if(visited[nx][ny][horse+1] != 0) continue;
					
					visited[nx][ny][horse+1] = visited[nowX][nowY][horse]+1;
					q.add(new int[] {nx, ny, horse+1});
				}
			}
			
		}
		System.out.println(-1);
	}
}
