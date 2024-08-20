import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] board;
	static int ans;
	
	static void dfs(int x, int y, int dir) {
//		0:가로 1:세로 2:대각
		if(x == N-1 && y==N-1) {
			ans++;
			return;
		}
		
//		현재 : 가로
		if(dir == 0) {
//			가로로 이동
			if(inRange(x, y+1) && board[x][y+1]==0) {
				dfs(x, y+1, 0);
			}
//			대각으로 이동
			if(inRange(x+1, y+1) && board[x+1][y]==0 && board[x][y+1]==0 && board[x+1][y+1]==0) {
				dfs(x+1, y+1, 2);
			}
		}
//		현재 : 세로
		else if(dir == 1) {
//			세로
			if(inRange(x+1, y) && board[x+1][y]==0) {
				dfs(x+1, y, 1);
			}
//			대각
			if(inRange(x+1, y+1) && board[x+1][y]==0 && board[x][y+1]==0 && board[x+1][y+1]==0) {
				dfs(x+1, y+1, 2);
			}
		}
//		현재 : 대각
		else {
//			가로
			if(inRange(x, y+1) && board[x][y+1]==0) {
				dfs(x, y+1, 0);
			}
//			세로
			if(inRange(x+1, y) && board[x+1][y]==0) {
				dfs(x+1, y, 1);
			}
//			대각
			if(inRange(x+1, y+1) && board[x][y+1]==0 && board[x+1][y]==0 && board[x+1][y+1]==0) {
				dfs(x+1, y+1, 2);
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return x>=0 && x<N && y>=0 && y<N;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		board = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());	
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(line.nextToken());
			}
		}
		
		dfs(0, 1, 0);
		System.out.println(ans);
	}
}