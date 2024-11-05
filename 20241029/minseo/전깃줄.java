import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class 전깃줄 {
	static int C, N, arr[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		arr = new int[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// A를 기준으로 정렬
		Arrays.sort(arr, new Comparator<int[]>() {
			
			@Override
			public int compare(int[] a, int[] b) {
				return Integer.compare(a[0], b[0]);
			}
		});
		
		// 가장 긴 증가하는 수열
		int temp[] = new int[N];
		int dp[] = new int[N+1];
		Arrays.fill(dp, 1);
		
		for(int i = 0; i < N; i++) {
			temp[i] = arr[i][1];
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(temp[j] < temp[i]) {
					dp[i] = Math.max(dp[j]+1, dp[i]);
				}
			}
		}
		
		int ans = N;
		for(int i = 0; i < N; i++) {
			ans = Math.min(ans, N-dp[i]);
		}
		System.out.println(ans);
	}
}