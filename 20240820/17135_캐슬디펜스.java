package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17135 {
	
	static int N;
	static int M;
	static int D;
	static int[][] board;
	static int max = 0;
	
	// 좌, 상, 우
	static int[] dx = {-1, 0, 1};
	static int[] dy = {0, -1, 0};
	
	static void combination(List<Integer> c, int[] numForC, int idx) {
		if (c.size() == 3) {
			// 궁수 배치 조합마다 처치할 수 있는 적의 수를 계산해야 함.
			int[][] copyBoard = new int[N][];
			for (int i=0;i<N;i++) {
				copyBoard[i] = Arrays.copyOf(board[i], board[i].length);
			}
			calculateEnemy(new ArrayList<>(c), copyBoard);
			return;
		}
		for (int i=idx;i<numForC.length;i++) {
			c.add(numForC[i]);
			combination(c, numForC, i+1);
			c.remove(c.size()-1);
		}
	}
	
	static void calculateEnemy(List<Integer> c, int[][] copyBoard) {
		int count = 0;
		for (int archerRow = N; archerRow>0;archerRow--) {
//			System.out.println("archerRow : " + archerRow);
			boolean[][] mark = new boolean[N][M];
//			System.out.println(c.toString());
			for (int j=0;j<c.size();j++) {
				
				boolean[][] visit = new boolean[N][M];
				int archerCol = c.get(j);
//				System.out.println("시작 : " + archerCol);
				// 먼저, 궁수 바로 위 좌표가 1인지 확인
				if (copyBoard[archerRow-1][archerCol] == 1) {
					mark[archerRow-1][archerCol] = true;
					continue;
				} else {
					// 바로 위가 0이면 좌, 상, 우 순서로 큐를 통해 탐색
					Queue<int[]> q = new LinkedList<>();
					// 0 : 행, 1 : 열
					q.add(new int[] {archerRow-1, archerCol});
					while (!q.isEmpty()) {
						boolean findEnemy = false;
						int[] xy = q.poll();
						int x = xy[1];
						int y = xy[0];
						visit[y][x] = true;
//						System.out.println("시작 : " + y + " " + x);
						for (int i=0;i<3;i++) {
							int nx = x+dx[i];
							int ny = y+dy[i];
//							System.out.println("ny : " + ny + " nx : " + nx);
							// 범위.
							if (nx < 0 || nx >= M || ny < 0 || ny >= archerRow) {
//								System.out.println("범위 초과");
								continue;
							}
							// 사거리 체크
							if (Math.abs(nx - archerCol) + Math.abs(ny - archerRow) > D) {
//								System.out.println("사거리 초과");
								break;
							}
							if (copyBoard[ny][nx] == 1) {
//								System.out.println("적 식별");
								mark[ny][nx] = true;
								findEnemy = true;
								break;
							}
							if (!visit[ny][nx] && copyBoard[ny][nx] == 0) {
//								System.out.println("적도 없고, 방문도 안한 새 지점 확인");
								q.add(new int[] {ny, nx});
							}
						}	
						if (findEnemy) {
							break;
						}
					}
				}
			}
			for (int i=0;i<N;i++) {
				for (int j=0;j<M;j++) {
					if (mark[i][j]) {
						copyBoard[i][j] = 0;
//						System.out.println("적 제거 : " + i + " " + j);
						count++;
					}
				}
			}
			
//			System.out.println("적 제거 후");
//			for (int i=0;i<N;i++) {
//				for (int j=0;j<M;j++) {
//					System.out.print(copyBoard[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println("count : " + count);
//			System.out.println();
		}

		max = Math.max(max, count);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// M개의 열에 궁수를 3명 배치하는 조합 생성.
		int[] numForC = new int[M];
		for (int i=0;i<M;i++) {
			numForC[i] = i;
		}
		combination(new ArrayList<>(), numForC, 0);
		System.out.println(max);
	}
}
