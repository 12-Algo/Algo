package algorithm;

import java.util.*;
import java.io.*;

public class BOJ2573 {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static boolean seperation = false;
	static int N, M;
	
	static void checkSeperation(int[][] iceberg, int x, int y, boolean[][] visit, int icebergNum) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visit[y][x] = true;
		int icebergCount = 1;
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0];
			int nowY = now[1];
			
			for (int i=0;i<4;i++) {
				int nextX = nowX + dx[i];
				int nextY = nowY + dy[i];
				if (nextX <= 0 || nextX >= M-1 || nextY <= 0 || nextY >= N-1 || visit[nextY][nextX] || iceberg[nextY][nextX] == 0) {
					continue;
				}
				visit[nextY][nextX] = true;
				icebergCount++;
				q.add(new int[] {nextX, nextY});
			}
		}
		
		if (icebergCount < icebergNum) {
			seperation = true;
		} else {
			seperation = false;
		}
		
		return;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] iceberg = new int[N][M];
		List<int[]> icebergPos = new ArrayList<>();
		int year = 0;
		
		// 빙산 입력 및 위치 저장
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<M;j++) {
				iceberg[i][j] = Integer.parseInt(st.nextToken());
				if (iceberg[i][j] != 0) {
					icebergPos.add(new int[] {j, i});
				}
			}
		}
		
		// 빙산 개수 1개 이하면 불가능
		if (icebergPos.size() <= 1) {
			System.out.println(year);
			return;
		}
		
//		for (int i=0;i<icebergPos.size();i++) {
//			System.out.println(Arrays.toString(icebergPos.get(i)));
//		}
		

		
		while (true) {
			year++;
			List<int[]> completelyMelted = new ArrayList<>();
			for (int i=0;i<icebergPos.size();i++) {

				// 주변 0 개수 파악
				int x = icebergPos.get(i)[0];
				int y = icebergPos.get(i)[1];
				int zeroNum = 0;
				for (int j=0;j<4;j++) {
					int nx = x + dx[j];
					int ny = y + dy[j];
					if (iceberg[ny][nx] == 0) {
						zeroNum++;
					}
				}
				// 주변 0 개수 빼기
				int afterMelt = Math.max(iceberg[y][x] - zeroNum, 0);
				
				if (afterMelt == 0) {
					completelyMelted.add(icebergPos.get(i));
					icebergPos.remove(i);
					i--;
				} else {
					iceberg[y][x] = afterMelt;
				}
			}
			
			for (int i=0;i<completelyMelted.size();i++) {
				int x = completelyMelted.get(i)[0];
				int y = completelyMelted.get(i)[1];
				iceberg[y][x] = 0;
			}
			
			// 빙산 개수 1 이하면 불가능
			if (icebergPos.size() <= 1) {
				System.out.println(0);
				return;
			}
			
			
			// 빙산 분리 확인
			boolean[][] visit = new boolean[N][M];
			int x = icebergPos.get(0)[0];
			int y = icebergPos.get(0)[1];
			checkSeperation(iceberg, x, y, visit, icebergPos.size());	
			if (seperation) {
				System.out.println(year);
				return;
			}
		}
	}
}