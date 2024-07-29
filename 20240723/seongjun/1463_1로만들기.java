import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		dp[1] = 0;
		
		
		for (int i=2;i<=n;i++) {
			if (i < 4) {
				dp[i] = 1;
			}
			int div3 = 987654321;
			int div2 = 987654321;
			int sub1 = 987654321;
			if (i % 3 == 0) {
				div3 = dp[i/3] + 1;
			}
			if (i % 2 == 0) {
				div2 = dp[i/2] + 1;
			}
			sub1 = dp[i-1] + 1;
			
			dp[i] = Math.min(div3, Math.min(div2, sub1));
		}
		System.out.println(dp[n]);
		
	}

}
