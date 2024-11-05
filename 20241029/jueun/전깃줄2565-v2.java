import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//1차원 dp
public class Main {

	static final int MAX_INDEX = 500;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		List<int[]> lineList = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			lineList.add(new int[] { a, b });
		}

		lineList.sort((l1, l2) -> l1[0] - l2[0]);
				
		
		//dp[j]는 B(j) 이하에 전깃줄이 연결되어 있을 때, 연결할 수 있는 최대의 전깃줄 갯수
		int[] dp = new int[MAX_INDEX+1];
		for(int i=0; i<N; i++) {
			int[] line = lineList.get(i);
			
			int cnt = dp[line[1]-1] + 1; //부분최적합
			for(int j=line[1]; j<=MAX_INDEX; j++) {
				dp[j] = Math.max(dp[j], cnt);
			}
		}
		
		System.out.println(N-dp[MAX_INDEX]);
	}
}
