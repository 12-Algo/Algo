import java.util.*;

class Solution {
    private static int[][] memo;
    private static int[] tops;
    private static int N;
    public int solution(int n, int[] inputtops) {
        int answer = 0;
        N = n;
        tops = inputtops;
        memo = new int[N][4];
        for (int[] me : memo) {
            Arrays.fill(me, -1);
        }
        answer = findAllCases(0, 0);
        return answer;
    }
    
    //0: 모두 삼각형, 1: 왼쪽 마름모, 2: 중간 마름모, 3: 오른쪽 마름모
    private static int findAllCases(int depth, int from) {
        if (depth == N) {
            return 1;
        }
        
        if (memo[depth][from] != -1) return memo[depth][from];
        
        //from이 0이라면 4개 다 가능.
        //1이어도 0, 1, 2, 3 가능.
        //2어어도 0, 1, 2, 3 가능.
        //3이면 0, 2, 3 가능.
        //위 모든 경우에 2 호출은 tops[depth] == 1일 때만 가능.
        //결론: 3일 때는 1로 못감.
        
        memo[depth][from] = 0;
        
        memo[depth][from] = (memo[depth][from] + findAllCases(depth + 1, 0)) % 10007;
        
        if (from != 3) memo[depth][from] = (memo[depth][from] + findAllCases(depth + 1, 1)) % 10007;
        
        if (tops[depth] == 1) memo[depth][from] = (memo[depth][from] + findAllCases(depth + 1, 2)) % 10007;
        
        memo[depth][from] = (memo[depth][from] + findAllCases(depth + 1, 3)) % 10007;
        return memo[depth][from] % 10007;
    }
}