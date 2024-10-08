package algorithm;

import java.util.*;
import java.io.*;

public class BOJ1600 {
	
	static int K, W, H;
	static int[][] map;
	static int[][][] moveCountByJump;
	
	static int[] dx = {0, 0, -1, 1, -2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
	
	static int calculateMovement(int startX, int startY) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {startX, startY, 0, 0}); // x, y, 일반이동횟수, 점프횟수
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];
			int move = now[2];
			int jump = now[3];

			if (x == W-1 && y == H-1) {
				return move + jump;
			}
			
			for (int i=0;i<dx.length;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx < 0 || nx >= W || ny < 0 || ny >= H || map[ny][nx] == 1) {
					continue;
				}
				if (i < 4) { // 일반 움직임
					if (moveCountByJump[ny][nx][jump] != 0) { // 이미 현재와 동일한 점프 횟수를 사용해 도달한 적 있음.
						continue;
					}
					moveCountByJump[ny][nx][jump] = moveCountByJump[y][x][jump] + 1;
					q.add(new int[] {nx, ny, move+1, jump});
				} else { // 점프
					if (jump == K) {
						continue;
					}
					if (moveCountByJump[ny][nx][jump+1] != 0) {
						continue;
					}
					moveCountByJump[ny][nx][jump+1] = moveCountByJump[y][x][jump] + 1;
					q.add(new int[] {nx, ny, move, jump+1});
				}
			}
		}
		
		return -1;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		moveCountByJump = new int[H][W][K+1];
		
		for (int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<W;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = calculateMovement(0, 0);
		System.out.println(answer);
	}

}
