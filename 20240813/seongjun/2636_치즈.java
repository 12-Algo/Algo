package swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	// 상하좌우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static boolean checkAir(int[][] board, int x, int y, boolean[][] visit) {
		visit[y][x] = true;
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx == 0 || nx == board[i].length-1 || ny == 0 || ny == board.length-1) {
				return true;
			}
			if (board[ny][nx] == 1 || visit[ny][nx]) {
				continue;
			}
			if (checkAir(board, nx, ny, visit)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int row = Integer.parseInt(st.nextToken());
		int col = Integer.parseInt(st.nextToken());
		
		int[][] board = new int[row][col];
		List<Integer> cheesePosX = new ArrayList<>();
		List<Integer> cheesePosY = new ArrayList<>();
		for (int i=0;i<row;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<col;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 1) {
					cheesePosX.add(j);
					cheesePosY.add(i);
				}
			}
		}
		
		// 상하좌우에 0이 있으면 녹음.
		// 치즈의 위치를 리스트에 저장
		// 리스트를 순회하며 상하좌우에 0이 있는지 확인. -> 틀린 판단.
		// 0이 있다면 리스트에서 해당 원소 제거. board의 해당 좌표 0으로 설정 -> 틀린 판단
		// 리스트를 순회하며 각 좌표의 치즈가 공기로 뻗어나갈 때 범위를 벗어나는지 확인
		// 범위를 벗어난다면 치즈에 둘러쌓이지 않고 공기와 닿아있으므로 해당 치즈 녹음(제거)
		// 위 과정 반복.
		int hour = 0;
		int lastCheese = 0;
		while (!cheesePosX.isEmpty()) {
			lastCheese = 0;
			hour++;
			List<Integer> willBeMeltedPosX = new ArrayList<>();
			List<Integer> willBeMeltedPosY = new ArrayList<>();
			for (int i=0;i<cheesePosX.size();i++) {
				if (checkAir(board, cheesePosX.get(i), cheesePosY.get(i), new boolean[row][col])) {
					willBeMeltedPosX.add(cheesePosX.get(i));
					willBeMeltedPosY.add(cheesePosY.get(i));
					cheesePosX.remove(i);
					cheesePosY.remove(i);
					i--;
				}
			}
			lastCheese = willBeMeltedPosX.size();
			for (int i=0;i<willBeMeltedPosX.size();i++) {
				board[willBeMeltedPosY.get(i)][willBeMeltedPosX.get(i)] = 0;
			}
		}
		
		System.out.println(hour);
		System.out.println(lastCheese);
		
	}
}
