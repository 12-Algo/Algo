import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	//최악의 경우: 홍보비 100원에 1명의 고객유치 C=1000 일 때, 100*1000;
	static final int MAX = 100_000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] cost = new int[N + 1];
		int[] customer = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			cost[i] = Integer.parseInt(st.nextToken());
			customer[i] = Integer.parseInt(st.nextToken());
		}

		// dp[i] i비용을 들여서 얻을 수 있는 최대 고객 수
		int[] dp = new int[MAX+1];

		for (int i = 1; i <= MAX; i++) {
			for (int j = 1; j <= N; j++) {
				if (i >= cost[j]) {
					dp[i] = Math.max(dp[i], dp[i - cost[j]] + customer[j]);
				}
			}
			
			if(dp[i] >= C) {
				System.out.println(i);
				break;
			}
		}
	}
}
