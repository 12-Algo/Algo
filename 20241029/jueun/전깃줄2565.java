import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
//2차원 dp
public class Main {

	static final int MAX_INDEX = 500;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		List<int[]> lineList = new ArrayList<>();
		lineList.add(new int[] {-1, -1});
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			lineList.add(new int[] { a, b });
		}

		lineList.sort((l1, l2) -> l1[0] - l2[0]);
				
		
		//dp[i][j]는 i번째 전깃줄까지 봤을때, B(j) 이하에 전깃줄이 연결되어 있을 때, 연결할 수 있는 최대의 전깃줄 갯수
		int[][] dp = new int[N+1][MAX_INDEX+1];
		for(int i=1; i<=N; i++) {
			int[] line = lineList.get(i);
			
			for(int j=1; j<line[1]; j++) {
				dp[i][j] = dp[i-1][j];
			}
			
			int cnt = dp[i-1][line[1]-1] + 1; //부분최적합
			for(int j=line[1]; j<=MAX_INDEX; j++) {
				dp[i][j] = Math.max(dp[i-1][j], cnt);
			}
		}
		
		System.out.println(N-dp[N][MAX_INDEX]);
	}
}
