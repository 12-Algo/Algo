package algorithm;

import java.io.*;
import java.util.*;

public class BOJ6593 {

	static int L, R, C;
	static char[][][] building;
	static boolean[][][] visit;
	
	static int[] dx = {0, 0, -1, 1, 0, 0};
	static int[] dy = {-1, 1, 0, 0, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	static int minTime;

	
//	static void escape(int x, int y, int z, int time) {
//		if (building[z][y][x] == 'E') {
//			minTime = Math.min(minTime, time);
//			return;
//		}
//		
//		if (minTime <= time) {
//			return;
//		}
//
//		for (int i=0;i<6;i++) {
//			int nx = x + dx[i];
//			int ny = y + dy[i];
//			int nz = z + dz[i];
//			
//			if (nx < 0 || nx >= C || ny < 0 || ny >= R || nz < 0 || nz >= L || visit[nz][ny][nx] || building[nz][ny][nx] == '#') {
//				continue;
//			}
//			
//			visit[nz][ny][nx] = true;
//			escape(nx, ny, nz, time+1);
//			visit[nz][ny][nx] = false;
//		}
//	}
	
	static void escape(Queue<int[]> q) {
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int i=0;i<6;i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				int nz = now[2] + dz[i];
				int nTime = now[3] + 1;
				
				if (nx < 0 || nx >= C || ny < 0 || ny >= R || nz < 0 || nz >= L || visit[nz][ny][nx] || building[nz][ny][nx] == '#') {
					continue;
				}
				
				if (building[nz][ny][nx] == 'E') {
					minTime = Math.min(minTime, nTime);
					return;
				}
				
				visit[nz][ny][nx] = true;
				q.add(new int[] {nx, ny, nz, nTime});				
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<int[]> q = new LinkedList<>();
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			
			minTime = Integer.MAX_VALUE;
			building = new char[L][R][C];
			visit = new boolean[L][R][C];
			int[] startPos = new int[4];
			
			boolean startFound = false;
			
			for (int l=0;l<L;l++) {
				for (int r=0;r<R;r++) {
					building[l][r] = br.readLine().toCharArray();
					if (!startFound) {
						for (int c=0;c<C;c++) {
							if (building[l][r][c] == 'S') {
								startPos[0] = c;
								startPos[1] = r;
								startPos[2] = l;
								startFound = true;
								break;
							}
						}						
					}
				}
				br.readLine();
			}
			
			visit[startPos[2]][startPos[1]][startPos[0]] = true;
			q.add(startPos);
//			escape(startPos[0], startPos[1], startPos[2], 0);
			escape(q);
			
			if (minTime != Integer.MAX_VALUE) {
				sb.append("Escaped in ").append(minTime).append(" minute(s).\n");
			} else {
				sb.append("Trapped!\n");
			}
		}
		
		System.out.print(sb);
	}
}