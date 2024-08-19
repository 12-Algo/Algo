import java.io.*;
import java.util.*;

public class Main {
	
	static String[][] boards;
	static int n;
	static boolean[][] isVisited;
	static boolean[][] isVisitedC;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(bf.readLine());
		
		boards = new String[n][n];
		
		for(int i=0;i<n;i++) {
			String line = bf.readLine();
			for(int j=0;j<n;j++) {
				boards[i][j] = String.valueOf(line.charAt(j));
			}
		};
		
		isVisited = new boolean[n][n];
		isVisitedC = new boolean[n][n];
		
		int container = 0;
		int containerC = 0;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(isVisited[i][j]==false) {
					bfs(i, j);
					container += 1;
				}
//				적록색약인 사람을 위해 색상 보정
				if(boards[i][j].equals("R")) {
					boards[i][j] = "G";
				}
			}
		}
//		isVisited 배열 초기화
		isVisited = isVisitedC;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(isVisited[i][j]==false) {
					bfs(i, j);
					containerC += 1;
				}
			}
		}
		System.out.println(container+" "+containerC);
	}
	
//	bfs 탐색 메서드
	static void bfs(int x, int y) {
//		우하좌상
		int[] dxs = {0, 1, 0, -1};
		int[] dys = {1, 0, -1, 0};
		
//		현재 색상
		String color = boards[x][y];
		Queue<int[]> queue = new LinkedList<>();
		isVisited[x][y] = true;
		queue.add(new int[] {x, y});

		while(true) {
			if(queue.isEmpty()) {
				break;
			}
			int[] board = queue.poll();
			int nowX = board[0];
			int nowY = board[1];
//		방향별로 확인 후 dfs탐새을 위해 queue에 삽입	
			for(int i=0;i<4;i++) {
				int nx = nowX+dxs[i];
				int ny = nowY+dys[i];
				
				if (inRange(nx, ny, n)){
					if(boards[nx][ny].equals(color) && isVisited[nx][ny]==false) {
						isVisited[nx][ny] = true;
						queue.add(new int[] {nx, ny});
					}
				}
			}
		}
	}
	
//	x, y 좌표 범위 확인 메서드
	static boolean inRange(int x, int y, int n) {
		return x>=0 && x<n && y>=0 && y< n;
	}
}
