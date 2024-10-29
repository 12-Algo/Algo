package algo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2342 {
	
	static List<Integer> order = new ArrayList<>();
	static int[][][] dp;

	static int calc(int l, int r, int c) {
		if(c == order.size()) return 0;
		
		if(dp[l][r][c] != -1)
			return dp[l][r][c];
		
		int next = order.get(c);
		int leftCost =  move(l, next) + calc(next, r, c+1);
		int rightCost = move(r, next) + calc(l, next, c+1);
		dp[l][r][c] = Math.min(leftCost, rightCost);
		return dp[l][r][c];
	}
	
	static int move(int from, int to) {
		int num = Math.abs(from-to);
		if(from == 0)
			return 2;
		else if(num == 0)
			return 1;
		else if(num==1 || num==3)
			return 3;
		else
			return 4;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer orders = new StringTokenizer(bf.readLine());
		
		while(true) {
			int temp = Integer.parseInt(orders.nextToken());
			if(temp == 0) 
				break;
			order.add(temp);
		}
		
		dp = new int[5][5][order.size()];
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		System.out.println(calc(0, 0, 0));
	}
}
