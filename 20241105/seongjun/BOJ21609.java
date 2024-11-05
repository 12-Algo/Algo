import java.util.*;
import java.io.*;

public class BOJ21609 {
	
	static int N, M;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static int maxGroupSize = 0; // 그룹 크기
	static List<int[]> maxGroupPos = new ArrayList<>();; // 가장 큰 그룹 블록 좌표
	static int maxGroupRainbowBlocks = 0; // 가장 큰 그룹 무지개 블록 개수
	static int maxGroupStandardRow = 20; // 가장 큰 그룹 기준 블록 행
	static int maxGroupStandardCol = 20; // 가장 큰 그룹 기준 블록 열
	static boolean groupExist;
	
	static int[][] rotate(int[][] board) {
		int[][] rotatedBoard = new int[N][N];
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				rotatedBoard[i][j] = board[j][N-1-i];
			}
		}
		return rotatedBoard;
	}
	
	// 일단 크기를 기준으로 bfs
	static void findBiggestGroup(int[][] board, int x, int y, boolean[][] visit, int color, boolean[][] checkForNotToFind) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		visit[y][x] = true;
		// 그룹 크기
		int groupSize = 0;
		// 그룹 좌표 저장 리스트
		List<int[]> pos = new ArrayList<>();
		// 무지개 블록 수
		int rainbowBlocks = 0;
		// 기준 블록 행
		int standardRow = y;
		// 기준 블록 열
		int standardCol = x;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			groupSize++;
			pos.add(now);
			for (int i=0;i<4;i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				// 범위, 방문 여부 확인
				if (nx < 0 || nx >= N || ny < 0 || ny >= N || visit[ny][nx]) {
					continue;
				}
				// 검은색 안됨.
				if (board[ny][nx] == -1) {
					continue;
				}
				// 빈 칸 안됨.
				if (board[ny][nx] == -2) {
					continue;
				}
				// 다른 색이거나, 같은 색이지만 이미 방문했으면 안됨.
				if (board[ny][nx] > 0 && board[ny][nx] != color) {
					continue;
				}
				// 무지개 블록 수 세기
				if (board[ny][nx] == 0) {
					rainbowBlocks++;
				}
				// 기준 블록 행
				if (board[ny][nx] > 0) {
					standardRow = Math.min(standardRow, ny);
				}
				// 기준 블록 열
				if (board[ny][nx] > 0) {
					standardCol = Math.min(standardCol, nx);
				}
				checkForNotToFind[ny][nx] = true;
				visit[ny][nx] = true;
				q.add(new int[] {nx, ny});
			}
		}
		if (groupSize < 2) {
			return;
		} else {
			groupExist = true;
		}
		// 그룹 크기가 더 크면 그냥 교체하면 됨
		if (groupSize > maxGroupSize) {
			maxGroupSize = groupSize;
			maxGroupPos = pos;
			maxGroupRainbowBlocks = rainbowBlocks;
			maxGroupStandardRow = standardRow;
			maxGroupStandardCol = standardCol;
		} else if (groupSize == maxGroupSize) { // 그룹 크기가 동일하면
			if (rainbowBlocks > maxGroupRainbowBlocks) {	// 무지개 블록 수 비교
				maxGroupPos = pos;
				maxGroupRainbowBlocks = rainbowBlocks;
				maxGroupStandardRow = standardRow;
				maxGroupStandardCol = standardCol;
			} else if (rainbowBlocks == maxGroupRainbowBlocks) { // 무지개 블록 수 동일하면
				if (standardRow > maxGroupStandardRow) { // 기준 블록 행 비교
					maxGroupPos = pos;
					maxGroupStandardRow = standardRow;
					maxGroupStandardCol = standardCol;
				} else if (standardRow == maxGroupStandardRow) { // 기준 블록 행 동일하면
					if (standardCol > maxGroupStandardCol) { // 기준 블록 열 비교
						maxGroupPos = pos;
						maxGroupStandardCol = standardCol;
					}
				}
			}	
		}
	}
	
	static void removeBlockGroup(int[][] board) {
		for (int i=0;i<maxGroupPos.size();i++) {
			int x = maxGroupPos.get(i)[0];
			int y = maxGroupPos.get(i)[1];
			board[y][x] = -2; // 제거할 블록은 -2로 치환.
		}
	}
	
	static void applyGravity(int[][] board) {
		boolean[] nothingToGetDown = new boolean[N];
		for (int i=0;i<N;i++) {
			if (nothingToGetDown[i]) {
				continue;
			}
			for (int j=N-1;j>=0;j--) {
				if (board[j][i] == -2) {
					for (int k=j-1;k>=0;k--) {
						if (board[k][i] >= 0) {
							board[j][i] = board[k][i];
							board[k][i] = -2;
							break;
						} else if (board[k][i] == -1) {
							break;
						}
						if (k == 0) {
							nothingToGetDown[i] = true;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		groupExist = true;
		int answer = 0;
		int[][] board = new int[N][N];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while (groupExist) {
			groupExist = false;
			maxGroupSize = 0; // 그룹 크기
			maxGroupPos = new ArrayList<>();; // 가장 큰 그룹 블록 좌표
			maxGroupRainbowBlocks = 0; // 가장 큰 그룹 무지개 블록 개수
			maxGroupStandardRow = 21; // 가장 큰 그룹 기준 블록 행
			maxGroupStandardCol = 21; // 가장 큰 그룹 기준 블록 열
			boolean[][] checkForNotToFind = new boolean[N][N];
			// 블록 그룹에는 일반 블록이 적어도 하나는 있어야 함 -> 일반 블록 위치에서만 블록 그룹을 찾는다.
			for (int i=0;i<N;i++) {
				for (int j=0;j<N;j++) {
					if (!checkForNotToFind[i][j] && board[i][j] > 0) {
						checkForNotToFind[i][j] = true;
						boolean[][] visit = new boolean[N][N];
						findBiggestGroup(board, j, i, visit, board[i][j], checkForNotToFind);
					}
				}
			}
			
			// 블록 그룹 제거
			removeBlockGroup(board);
			
			// 점수 획득
			answer += Math.pow(maxGroupSize, 2);
			
			// 중력 작용
			applyGravity(board);

			// 회전
			board = rotate(board);
			
			// 중력 작용
			applyGravity(board);
		}
		
		System.out.println(answer);
	}
}
