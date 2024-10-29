package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1106 {

	static int C, N;
	static int[][] costCustomer;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		C = Integer.parseInt(input.nextToken());
		N = Integer.parseInt(input.nextToken());
		costCustomer = new int[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer cities = new StringTokenizer(bf.readLine());
			costCustomer[i][0] = Integer.parseInt(cities.nextToken());
			costCustomer[i][1] = Integer.parseInt(cities.nextToken());
		}
		
		dp = new int[C+101];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for(int i=0;i<N;i++) {
			int cost = costCustomer[i][0];
			int customer = costCustomer[i][1];
			for(int j=customer;j<C+101;j++) {
				if(dp[j-customer] != Integer.MAX_VALUE) {
					dp[j]= Math.min(dp[j-customer]+cost, dp[j]); 
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i=C;i<C+101;i++	) {
			answer = Math.min(answer, dp[i]);
		}
		System.out.println(answer);
	}
}
