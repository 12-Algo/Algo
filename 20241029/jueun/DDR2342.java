import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

   static final int DISABLE = 500_000;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());

      int N = st.countTokens();

      //dp[i][j][k]는 i번째 지시사항을 만족하면서, 왼발은 j 오른발은 k인 상태의 최소비용
      int[][][] dp = new int[N][5][5];
      for(int i=0; i<N; i++) {
         for(int j=0; j<5; j++) {
            Arrays.fill(dp[i][j], DISABLE);
         }
      }
      dp[0][0][0] = 0;

      for(int i=1; i<N; i++) {
         int next = Integer.parseInt(st.nextToken());

         for(int j=0; j<5; j++) {
            for(int k=0; k<5; k++) {
               if(dp[i-1][j][k] == DISABLE)
                  continue;

               if(j == next || k == next) {
                  dp[i][j][k] = Math.min(dp[i][j][k], dp[i-1][j][k] + 1);
                  continue;
               }

               dp[i][next][k] = Math.min(dp[i][next][k], dp[i-1][j][k] + getCost(j, next));
               dp[i][j][next] = Math.min(dp[i][j][next], dp[i-1][j][k] + getCost(k, next));
            }
         }
      }

      int min = DISABLE;
      for(int i=0; i<5; i++) {
         for(int j=0; j<5; j++) {
            min = Math.min(min, dp[N-1][i][j]);
         }
      }

      System.out.println(min);
   }

   private static int getCost(int from, int to) {
      if(from == 0) {
         return 2;
      }

      from %= 2;
      to %= 2;

      if(from != to) {
         return 3;
      }
      else {
         return 4;
      }
   }
}
