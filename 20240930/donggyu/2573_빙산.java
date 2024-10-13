package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573 {

	static int N, M;
	static int[][] board;
	static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		M = Integer.parseInt(input.nextToken());
		board = new int[N][M];
		for(int i=0;i<N;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			for(int j=0;j<M;j++) {
				board[i][j]= Integer.parseInt(line.nextToken());
			}
		}
		int cnt = 0;
		while(true) {
//			System.out.println(Arrays.deepToString(board));
			int startX = 0, startY = 0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(board[i][j]!=0) {
						startX = i;
						startY = j;
					}
				}
			}
			
			if(startX==0 && startY==0) {
				System.out.println(0);
				return;
			}
			
			if(bfs(startX, startY)) cnt++;
			else {
				System.out.println(cnt);
				return;
			}
		}
	}
	
	static boolean bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][M];
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0], nowY = now[1];
			
			for(int d=0;d<4;d++) {
				int nx = nowX+deltas[d][0], ny = nowY+deltas[d][1];
				if(nx<0 || ny<0 || nx>N-1 || ny>M-1) continue;
				if(visited[nx][ny]) continue;
				
				if(board[nx][ny]!=0) {
					visited[nx][ny] = true;
					q.add(new int[] {nx, ny});
				}
				
				if(board[nx][ny]==0 && board[nowX][nowY]>0) {
					board[nowX][nowY] -= 1;
				}
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(board[i][j]!=0 && !visited[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
