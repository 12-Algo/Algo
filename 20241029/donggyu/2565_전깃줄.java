import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[][] electricLines; 
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		electricLines = new int[N][2];
		for(int i=0;i<N;i++) {
			StringTokenizer input = new StringTokenizer(bf.readLine());
			electricLines[i][0] = Integer.parseInt(input.nextToken());
			electricLines[i][1] = Integer.parseInt(input.nextToken());
		}
		
		Arrays.sort(electricLines, (o1, o2) -> {
			return o1[0] - o2[0];
		});
		
		dp = new int[N];
		
		for(int i=0;i<N;i++	) {
			dp[i] = 1;
			for(int j=0;j<i;j++	) {
				if(electricLines[i][1] > electricLines[j][1])
					dp[i] = Math.max(dp[i], dp[j]+1);
			}
		}
		
		int maxLine = 0;
		for(int i=0;i<N;i++) {
			maxLine = Math.max(maxLine, dp[i]);
		}
		System.out.println(N-maxLine);
	}
}