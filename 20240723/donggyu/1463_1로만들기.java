package javaAlgorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class To1 {
	//		dp[0] = 0
	//		dp[1] = 0
	//		dp[2] = 1
	//		dp[3] = 1
	//		dp[4] = 2 2의 배수 -> 이전 값 확인
	//		dp[5] = 3
	//		dp[6] = 2 3의 배수 -> 이전 값 확인 -> 2랑 3 둘 다 확인 해야 하나?
	//		dp[7] =  2의 배수 3의 배수 아님 -> 1빼야 하니까 dp[6]+1 (3)
	//		dp[8] =  2의 배수 -> dp[4] 확인 dp[4]+1 (3) / dp[7]+1 (4) min 값 -> 3
	//		...n까지 진행
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(bf.readLine());
		//dp 테이블
		int[] dp = new int[n+1];

		//초기 세팅값보다 작은 수가 타겟인 경우
		if(n==1) {
			System.out.println(0);
			return;
		} else if(2 ==n || n==3) {
			System.out.println(1);
			return;
		}

		//dp 테이블 초기 세팅
		dp[0] = 0;
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		int small = 0;
		for(int i=4;i<=n;i++) {
//			i가 2의 배수이면서 3의 배수이면(6의 배수이면)
			if(i%3 == 0 && i%2==0) {
				small = dp[i-1]+1;
				if(small > dp[i/3]+1) {
					small = dp[i/3]+1;
				}
				if(small > dp[i/2]+1) {
					small = dp[i/2]+1;
				}
				dp[i] = small;
			}
//			i가 3의 배수면
			else if (i%3==0) {
				dp[i] = Math.min(dp[i-1]+1, dp[i/3]+1);
			} else if(i%2 ==0) { //i가 2의 배수면
				dp[i] = Math.min(dp[i-1]+1, dp[i/2]+1);
			} else { //둘 다 아닐 경우
				dp[i] = dp[i-1]+1;
			}
		}

		System.out.println(dp[n]);
	}
}

}
