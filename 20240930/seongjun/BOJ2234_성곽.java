package algorithm;

import java.util.*;
import java.io.*;

public class BOJ2234 {
	
	static int N, M;
	static int mark = 1;
	static int[][] castle;
	static int[][][] room;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int[] wallCheck = {2, 8, 1, 4};
	
	static int countRoomArea(boolean[][] visit, int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visit[y][x] = true;
		
		List<int[]> posForMark = new ArrayList<>();
		posForMark.add(new int[] {x, y});
		
		int area = 1;
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			for (int i=0;i<4;i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M || visit[nextY][nextX]) {
					continue;
				}
				// 벽 체크
				if ((castle[nowY][nowX] & wallCheck[i]) == wallCheck[i]) {
					continue;
				}
				
				visit[nextY][nextX] = true;
				q.add(new int[] {nextX, nextY});
				posForMark.add(new int[] {nextX, nextY});
				area++;
			}
		}
		
		for (int i=0;i<posForMark.size();i++) {
			room[posForMark.get(i)[1]][posForMark.get(i)[0]][0] = mark;
			room[posForMark.get(i)[1]][posForMark.get(i)[0]][1] = area;
		}
		
		return area;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		castle = new int[M][N];
		room = new int[M][N][2];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int roomCount = 0;
		int maxRoomArea = 0;
		int maxRoomAreaAfterRemoveWall = 0;
		
		boolean[][] visit = new boolean[M][N];
		// 방 개수 세기
		for (int i=0;i<M;i++) {
			for (int j=0;j<N;j++) {
				// 가장 넓은 방 넓이 찾기
				if (!visit[i][j]) {
					roomCount++;
					maxRoomArea = Math.max(maxRoomArea, countRoomArea(visit, j, i));
					mark++;
				}
				// 벽 제거 후 가장 넓은 방 넓이 찾기
				for (int k=0;k<4;k++) {
					int nx = j + dx[k];
					int ny = i + dy[k];
					if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
						continue;
					}
					if ((castle[i][j] & wallCheck[k]) == wallCheck[k]) {
						if (room[i][j][0] != room[ny][nx][0]) {
							maxRoomAreaAfterRemoveWall = Math.max(maxRoomAreaAfterRemoveWall, room[i][j][1] + room[ny][nx][1]);							
						}
					}
				}
			}
		}
		
		System.out.println(roomCount);
		System.out.println(maxRoomArea);
		System.out.println(maxRoomAreaAfterRemoveWall);
	}
}