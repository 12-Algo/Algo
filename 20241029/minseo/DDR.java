import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int C, N, dp[][][], move[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] command = br.readLine().split(" ");
		
		move = new int[command.length];
		for(int i = 0; i < command.length; i++) {
			move[i] = Integer.parseInt(command[i]);
		}
		
		// 왼발, 오른발이 움직일 수 있는 공간 & 커맨드 길이
		dp = new int[5][5][command.length];
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				for(int k = 0; k < command.length; k++) {
					dp[i][j][k] = 0;
				}
			}
		}
		
		// 완전탐색
		System.out.println(play(0, 0, 0));
		
//		for(int i = 0; i < 5; i++) {
//			for(int j = 0; j < 5; j++) {
//				System.out.println(Arrays.toString(dp[i][j]));
//			}
//		}
		
	}
	
	// 왼발 위치, 오른발 위치, 움직인 횟수
	// 하위 움직임들의 최소 움직임 비용
	static int play(int left, int right, int cnt) {
		// 마지막 0,0으로 갈 필요는 없는 듯
		if(cnt == move.length - 1) return 0;
		// System.out.println(cnt+"번 움직임: "+dp[left][right][cnt]);
		
		if(dp[left][right][cnt] != 0) {
			// 이미 다른 발이 점유하고 있는 공간이라면?
			return dp[left][right][cnt];
		}
		dp[left][right][cnt] = Math.min(play(move[cnt], right, cnt+1) + getCost(left, move[cnt]),
				play(left, move[cnt], cnt+1) + getCost(right, move[cnt]));
		
		return dp[left][right][cnt];
	}
	
	static int getCost(int origin, int dest) {
		if(origin == dest) {
			return 1;
		}
		else if(origin == 0 && dest != 0) {
			return 2;
		}
		else if(Math.abs(origin - dest) % 2 == 1) {
			return 3;
		}
		else {
			return 4;
		}
	}
}
