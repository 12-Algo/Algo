package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724 {
	
	static int count = 0;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void calculateSafeZone(char[][] map, boolean[][] visit, int x, int y) {
		visit[y][x] = true;
		int nx = 0, ny = 0;
		if (map[y][x] == 'U') {
			nx = x + dx[0];
			ny = y + dy[0];
		} else if (map[y][x] == 'D') {
			nx = x + dx[1];
			ny = y + dy[1];
		} else if (map[y][x] == 'L') {
			nx = x + dx[2];
			ny = y + dy[2];
		} else if (map[y][x] == 'R') {
			nx = x + dx[3];
			ny = y + dy[3];
		}
		
		if (visit[ny][nx]) {
			if (map[ny][nx] == 'U' || map[ny][nx] == 'D' || map[ny][nx] == 'L' || map[ny][nx] == 'R') {
				map[y][x] = 'x';
				count++;				
			} else {
				map[y][x] = map[ny][nx];
			}
			return;
		} else {
			calculateSafeZone(map, visit, nx, ny);
			map[y][x] = map[ny][nx];
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		for (int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		boolean[][] visit = new boolean[N][M];
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				if (!visit[i][j]) {
					calculateSafeZone(map, visit, j, i);					
				}
			}
		}
	
		System.out.println(count);
	}
}
