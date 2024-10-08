import java.util.*;

class Solution {
    
    static final int MOD = 10007;
    
    public int solution(int n, int[] tops) {
        int N = 2*n + 1;
        int[] dp = new int[N+1];
        
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i=2; i<=N; i++) {
            
            // 정삼각형
            if(i%2 == 1) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            // 역삼각형
            else {
                dp[i] = dp[i-1] + dp[i-2];
                
                //마름모 확인
                int j = i / 2 - 1;
                if(tops[j] == 1)
                    dp[i] += dp[i-1];
            }
            
            dp[i] %= 10007;
        }
        
        //System.out.println(Arrays.toString(dp));
        return dp[N];
    }
}
