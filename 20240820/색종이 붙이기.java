import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer token;
	static int[][] paper;
	static int answer;
	static int[] remain;
	public static void main(String[] args) throws IOException {
		getInput();
		
		findAnswer(0, 0, 0);
		if (answer == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(answer);
	}

	private static void findAnswer(int y, int x, int value) {
		if (y == 9 && x == 10) {
			answer = Math.min(answer, value);
			return;
		}
		
		if (x == 10) {
			findAnswer(y+1, 0, value);
			return;
		}
		
		if (paper[y][x] == 1) {
			for (int k=5;k>=1;k--) {
				if (check(y, x, k) && remain[k-1] > 0) {
					remove(y, x, k);
					remain[k-1]--;
					findAnswer(y, x + 1, value + 1);
					remain[k-1]++;
					repair(y, x, k);
				}
			}
		} else {
			findAnswer(y, x + 1, value);
		}
	}
	
	private static boolean check(int y, int x, int k) {
		for (int i=0;i<k;i++) {
			for (int j=0;j<k;j++) {
				if (y + i >= 10 || x + j >= 10) return false;
				if (paper[y+i][x+j] != 1) return false; 
			}
		}
		return true;
	}
	
	private static void remove(int y, int x, int k) {
		for (int i=0;i<k;i++) {
			for (int j=0;j<k;j++) {
				paper[y+i][x+j] = 0;
			}
		}
	}
	
	private static void repair(int y, int x, int k) {
		for (int i=0;i<k;i++) {
			for (int j=0;j<k;j++) {
				paper[y+i][x+j] = 1;
			}
		}
	}
	
	private static void getInput() throws IOException {
		paper = new int[10][10];
		remain = new int[]{5, 5, 5, 5, 5};
		answer = Integer.MAX_VALUE;
		for (int i=0;i<10;i++) {
			token = new StringTokenizer(br.readLine());
			for (int j=0;j<10;j++) {
				paper[i][j] = Integer.parseInt(token.nextToken());
			}
		}
	}
}
