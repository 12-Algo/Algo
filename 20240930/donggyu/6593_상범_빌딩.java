import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); 
	static int[][] delta = {{1, 0, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, 1}, {0, 0, -1}};
	static int L, R, C;
	static char[][][] board;
	static int[] endPos;
	
	public static void main(String[] args) throws IOException {
		
		while(true) {
			StringTokenizer input = new StringTokenizer(bf.readLine());
			
			L = Integer.parseInt(input.nextToken());
			R = Integer.parseInt(input.nextToken());
			C = Integer.parseInt(input.nextToken());
			if(L==0 && R==0 && C==0)
				break;
			init();
			printBoard();
		}
	}
	
	static void init() throws IOException {
		board = new char[L][R][C];
		for(int i=0;i<L;i++) {
			for(int j=0;j<R;j++) {
				String line = bf.readLine();
				for(int k=0;k<C;k++) {
					if(line.charAt(k)=='E')
						endPos = new int[]{i, j, k};
					board[i][j][k] = line.charAt(k);
				}
			}
			bf.readLine();
		}			
	}
	
	static void printBoard() {
		for(int i=0;i<L;i++	) {
			for(int j=0;j<R;j++) {
				for(int k=0;k<C;k++) {
					System.out.print(board[i][j][k]);
				}
				System.out.println();
			}
			System.out.println();
		}
	}
	
	static int dfs(int depth, boolean visited[][][]) {
		
		
		return 0;
	}
}
