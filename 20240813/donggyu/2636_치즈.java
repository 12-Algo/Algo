import java.io.*;
import java.util.*;

public class Main {
	
	static int n;
	static int m;
	static int time;
	static int[][] boards;
//	총 치즈 수
	static int cheeseCnt = 0;
//	이미 녹은 치즈 수
	static int MeltedSum = 0;
	
//	우하좌상
	static int[] dxs = {0, 1, 0, -1};
	static int[] dys = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		boards = new int[n][m];
		
		
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<m;j++) {
				boards[i][j] = Integer.parseInt(st.nextToken());
				if(boards[i][j] == 1) {
					cheeseCnt += 1;
				}
			}
		}
		
		bfs(0, 0);
	}
	
	static void bfs(int x, int y) {
		boolean[][] isVisited = new boolean[n][m];
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});		
		isVisited[x][y] = true;
		
//		현재 단계에서 녹는 치즈 수
		int cheeseMelted = 0;
		time += 1;
		
		while(true) {
			if(q.isEmpty()) {
				break;
			}
			int[] board = q.poll();
			x = board[0];
			y = board[1];
			for(int i=0;i<4;i++) {
				int nx = x+dxs[i];
				int ny = y+dys[i];
				if(inRange(nx, ny, n, m)==false || isVisited[nx][ny]) {
					continue;
				}
				
				isVisited[nx][ny] = true;
				if(boards[nx][ny] == 0) {
					q.add(new int[] {nx, ny});					
				}
				else {
					cheeseMelted += 1;
					boards[nx][ny] = 0;
				}
			}
		}
		MeltedSum += cheeseMelted;
		if(MeltedSum == cheeseCnt) {
			System.out.println(time);
			System.out.println(cheeseMelted);
		}
		else {
			bfs(0, 0);
		}
	}
	
	static boolean inRange(int x, int y, int n, int m) {
		return x>=0 && x<n && y>=0 && y<m;
	}
}
