import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int C, N, dp[], weight[], value[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		dp = new int[C+100];
		weight = new int[N];
		value = new int[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			weight[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, 1000000);
        dp[0] = 0;

        for(int i = 0; i < N; i++){
            for(int j = value[i]; j < C+100; j++){
                dp[j]=Math.min(dp[j],weight[i]+dp[j-value[i]]);
            }
        }

        int ans = Integer.MAX_VALUE;
        
        for(int i=C; i<C+100; i++){
            ans = Math.min(ans, dp[i]);
        }
        
        System.out.println(ans);
	}
}
