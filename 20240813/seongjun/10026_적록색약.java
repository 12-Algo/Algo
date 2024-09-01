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
	// 상 하 좌 우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static void checkForBlind(boolean[][] visit, char[][] colors, int x, int y) {
		visit[y][x] = true;
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= colors.length || ny < 0 || ny >= colors.length || visit[ny][nx]) {
				continue;
			}
			if ((colors[y][x] == 'R' || colors[y][x] == 'G') && (colors[ny][nx] == 'R' || colors[ny][nx] == 'G') ) {
				checkForBlind(visit, colors, nx, ny);	
			} else if (colors[y][x] == 'B' && colors[ny][nx] == 'B') {
				checkForBlind(visit, colors, nx, ny);
			}
		}
	}
	
	static void checkForNonBlind(boolean[][] visit, char[][] colors, int x, int y) {
		visit[y][x] = true;
		for (int i=0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= colors.length || ny < 0 || ny >= colors.length || visit[ny][nx]) {
				continue;
			}
			if (colors[y][x] == colors[ny][nx]) {
				checkForNonBlind(visit, colors, nx, ny);	
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[][] colors = new char[n][n];
		for (int i=0;i<n;i++) {
			colors[i] = br.readLine().toCharArray();
		}
		
		// 인접 구역에 같은 글자 방문 처리.
		// 반복문 돌며 방문 안한 곳에 도달하면 다시 인접 구역 같은 글자 방문 처리.
		// 위 과정 반복.
		boolean[][] colorBlindVisit = new boolean[n][n];
		boolean[][] nonBlindVisit = new boolean[n][n];
		
		int colorBlindSection = 0;
		int nonBlindSection = 0;
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				if (!colorBlindVisit[i][j]) {
					checkForBlind(colorBlindVisit, colors, j, i);
					colorBlindSection++;
				}
				if (!nonBlindVisit[i][j]) {
					checkForNonBlind(nonBlindVisit, colors, j, i);
					nonBlindSection++;
				}
			}
		}
		
		System.out.println(nonBlindSection + " " + colorBlindSection);
	}
}
