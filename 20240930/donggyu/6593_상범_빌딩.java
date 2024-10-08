package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj6593 {

	static StringBuilder sb = new StringBuilder();
	static int L, R, C;
	static char[][][] board;
	static int[][] deltas = {{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
	static int[] start, end;
	
	static void bfs(int x, int y, int z) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][][] visited = new boolean[L][R][C];
		int cnt = 0;
		q.add(new int[] {x, y, z, cnt});
		visited[x][y][z] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX=now[0], nowY=now[1], nowZ=now[2];
			cnt = now[3];
			
			if(nowX==end[0] && nowY==end[1] && nowZ==end[2]) {
				sb.append("Escaped in "+cnt+" minute(s).\n");
				return;
			}
			
			for(int i=0;i<6;i++) {
				int nx = nowX+deltas[i][0], ny = nowY+deltas[i][1], nz = nowZ+deltas[i][2];
				if(nx<0 || ny<0 || nz<0 || nx>L-1 || ny>R-1 || nz>C-1) continue;				
				if(visited[nx][ny][nz]) continue;
				
				if(board[nx][ny][nz]=='.' || board[nx][ny][nz]=='E') {
					visited[nx][ny][nz] = true;
					q.add(new int[] {nx, ny, nz, cnt+1});
				}
			}
		}
		sb.append("Trapped!\n");
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer input = new StringTokenizer(bf.readLine());
			L = Integer.parseInt(input.nextToken());
			R = Integer.parseInt(input.nextToken());
			C = Integer.parseInt(input.nextToken());
			if(L==0 && R==0 && C==0) {
				System.out.println(sb.toString());
				return;
			}
			
			board = new char[L][R][C];
			for(int i=0;i<L;i++) {
				for(int j=0;j<R;j++) {
					String line = bf.readLine();
					for(int k=0;k<C;k++) {
						board[i][j][k] = line.charAt(k);
						if(board[i][j][k]=='S') {
							start = new int[] {i, j, k};
						} else if(board[i][j][k]=='E') {
							end = new int[] {i, j, k};
						}
					}
				}
				bf.readLine();
			}
			bfs(start[0], start[1], start[2]);
		}
	}
}
