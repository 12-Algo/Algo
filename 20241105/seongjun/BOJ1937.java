package algorithm;

import java.io.*;
import java.util.*;

public class BOJ1937 {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[][] forest;
	static int[][] counts;
	static int N;
	static int answer = 0;
	static int call = 0;
	
	static int findMax(int x, int y) {
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= N || ny < 0 || ny >= N || forest[ny][nx] <= forest[y][x]) {
				continue;
			}
			if (counts[ny][nx] != 1) {
				counts[y][x] = Math.max(counts[y][x], counts[ny][nx] + 1);
			} else {
				counts[y][x] = Math.max(counts[y][x], findMax(nx, ny)+1);				
			}
		}
		return counts[y][x];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		forest = new int[N][N];
		counts = new int[N][N];
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
				counts[i][j] = 1;
			}
		}
		
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (counts[i][j] != 1) {
					continue;
				}
				findMax(j, i);
				answer = Math.max(answer, counts[i][j]);
			}
		}
		
		System.out.println(answer);
	}
}
