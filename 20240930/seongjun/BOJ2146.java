package algorithm;

import java.util.*;
import java.io.*;

public class BOJ2146 {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int N;
	static List<int[]> buildablePos = new ArrayList<>();
	static boolean[][] buildablePosVisit;
	static int minDistance = 987654321;
	
	private static void mark(int[][] map, int x, int y, int mark) {
		Queue<int[]> q = new LinkedList<>();
		map[y][x] = mark;
		q.add(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int i=0;i<4;i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
					continue;
				}
				if (map[ny][nx] != 1) {
					if (map[ny][nx] == 0) {
						if (!buildablePosVisit[now[0]][now[1]]) {
							buildablePosVisit[now[0]][now[1]] = true;
							buildablePos.add(new int[] {now[0], now[1]});
						}						
					}
					continue;
				}
				map[ny][nx] = mark;
				q.add(new int[] {nx, ny});
			}
		}
	}
	
	private static void buildBridge(int x, int y, int[][] map, boolean[][] visit) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y, 0});
		visit[y][x] = true;
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int distance = now[2];
			for (int i=0;i<4;i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[ny][nx]) {
					continue;
				}
				if (map[ny][nx] != 0 && map[ny][nx] != map[y][x]) {
					minDistance = Math.min(minDistance, distance);
					return;
				}
				if (map[ny][nx] != 0) {
					continue;
				}
				if (distance >= minDistance) {
					return;
				}
				
				visit[ny][nx] = true;
				q.add(new int[] {nx, ny, distance+1});
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		buildablePosVisit = new boolean[N][N];
		
		int[][] map = new int[N][N];
		int mark = 2;
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬마다 마킹
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (map[i][j] == 1) {
					mark(map, j, i, mark);
					mark++;
				}
			}
		}

		// 다리 지을 수 있는 좌표 탐색하며 가장 짧은 거리 탐색
		for (int i=0;i<buildablePos.size();i++) {
			boolean[][] visit = new boolean[N][N];
			int x = buildablePos.get(i)[0];
			int y = buildablePos.get(i)[1];
			buildBridge(x, y, map, visit);
		}
		System.out.println(buildablePos.size());
		System.out.println(minDistance);
		
	}

}
