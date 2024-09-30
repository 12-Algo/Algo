import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 오른쪽, 대각선, 아래
	static int[] dx = {1, 1, 0};
	static int[] dy = {0, 1, 1};
	
	static int answer = 0;
	
	static void checkPath(int x, int y, String state, int n, int[][] house) {
		if (x == n && y == n) {
			answer++;
			return;
		}
		
		if (state.equals("horizon")) {
			for (int i=0;i<2;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				state = setState(i);		
				if (!rangeCheck(nx, ny, n) || !movable(nx, ny, house, state)) {
					continue;
				}
				checkPath(nx, ny, state, n, house);
			}
		} else if (state.equals("diagonal")) {
			for (int i=0;i<3;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				state = setState(i);
				if (!rangeCheck(nx, ny, n) || !movable(nx, ny, house, state)) {
					continue;
				}
				checkPath(nx, ny, state, n, house);
			}
		} else if (state.equals("vertical")) {
			for (int i=1;i<3;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				state = setState(i);
				if (!rangeCheck(nx, ny, n) || !movable(nx, ny, house, state)) {
					continue;
				}
				checkPath(nx, ny, state, n, house);
			}
		}
	}
	
	static String setState(int idx) {
		if (idx == 0) {
			return "horizon";
		} else if (idx == 1) {
			return "diagonal";
		} else {
			return "vertical";
		}
	}
	
	static boolean rangeCheck(int x, int y, int n) {
		if (x < 1 || x > n || y < 1 || y > n) {
			return false;
		}
		return true;
	}
	
	static boolean movable(int x, int y ,int[][] house, String state) {
		if (state.equals("horizon") || state.equals("vertical")) {
			if (house[y][x] == 1) {
				return false;
			}
		} else if (state.equals("diagonal")) {
			if (house[y][x] == 1 || house[y-1][x] == 1 || house[y][x-1] == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] house = new int[n+1][n+1];
		for (int i=1;i<=n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1;j<=n;j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		String state = "horizon";
		
		checkPath(2, 1, state, n, house);
		System.out.println(answer);
		
	}
}