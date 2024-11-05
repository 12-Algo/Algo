package algorithm;

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
	
	
	static int[][] rotate(int[][] board) {
		int[][] rotatedBoard = new int[N][N];
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				rotatedBoard[i][j] = board[j][N-1-i];
			}
		}
		return rotatedBoard;
	}
	
	// 가장 큰 블록을 찾으려면 모든 좌표에서 탐색(visit 처리하면서)
	// list에 좌표를 add하면서, list.size()가 가장 큰 경우를 찾아냄.
	// 가장 큰 list에 들어있는 모든 좌표들을 블록에서 제거 -> -1 ~ 5 외의 숫자로 치환?
	// 또한 list.size()^2만큼 정답에 더함.
	// 단, 가장 큰 그룹을 찾을 때 크기, 무지개 블록 수, 기준 블록 행, 열을 구해야함.
	
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
		int standardRow = N;
		// 기준 블록 열
		int standardCol = N;
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
				// 다른 색이거나, 같은 색이지만 이미 방문했으면 안됨.
				if (board[ny][nx] > 0 && board[ny][nx] != color) {
					continue;
				}
				// 무지개 블록 수 세기
				if (board[ny][nx] == 0) {
					rainbowBlocks++;
				}
				// 기준 블록 행
				if (board[ny][nx] > 0 && standardRow < 0) {
					standardRow = Math.min(N, Math.min(y, ny));
				}
				// 기준 블록 열
				if (board[ny][nx] > 0 && standardCol < 0) {
					standardCol = Math.min(N, Math.min(x, nx));
				}
				checkForNotToFind[ny][nx] = true;
				visit[ny][nx] = true;
				q.add(new int[] {nx, ny});
			}
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

	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][N];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
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
		
	}
}
