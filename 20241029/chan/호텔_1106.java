import java.io.*;
import java.util.*;

public class 호텔_1106 {
	
	static int C, N;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new int[C + 100];//
		Arrays.fill(dp, 999999); // Integer.MAX_VALUE로 값을 넣었더니 오버플로우 발생
		dp[0] = 0;
		
		// dp설명 : dp인덱스 만큼의 사람을 모으는데 드는 최소 비용
		// C + 100을 하는 이유는  문제에 "이 값은 100보다 작거나 같은 자연수이다" 라고 적혀 있어서 필요한 고객의 수는 C지만 추가 고객을 계산하기 위해 C+ 100을함
		// ex) C=1 이상의 고객이 늘리길 원할 때 50의 비용으로 100의 인원을 홍보할 수 있다고 한다면 dp[1] ~ dp[100]까지 뒤져봐야 최소값을 얻을 수 있음 따라서 33번째 줄에서 dp[C] ~ dp[C+100]탐색과 dp[C + 100]을 하는 이유
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken()); // 비용
			int customers = Integer.parseInt(st.nextToken()); // 고객들
			for(int j = customers; j < C + 100; j++) {
//				System.out.println("dp["+ j +"] | " + dp[j] + " = Math.min( "+ (cost + dp[j - customers]) +" = "+ cost + " + dp["+ (j-customers) +"] | "+ dp[j - customers] + ", " + dp[j] + ")");
				dp[j] = Math.min(cost + dp[j - customers], dp[j]);
			}
		}// 입력 완료
		
		int result = Integer.MAX_VALUE;
		for(int i = C; i < C + 100; i++){
		    result = Math.min(result, dp[i]);
		}
		System.out.println(result);
	}
}

/*

12 2
3 5
1 1

 */ 
