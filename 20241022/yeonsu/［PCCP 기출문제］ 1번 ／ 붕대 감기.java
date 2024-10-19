class Solution {
    static int N, M, limit;
    public int solution(int[] bandage, int health, int[][] attacks) {
        int answer = health;
        N = bandage.length;
        M = attacks.length;
        for (int i=0;i<M;i++) {
            if (i == 0) answer -= attacks[i][1];
            else {
                int term = attacks[i][0] - attacks[i-1][0];
                answer += bandage[1] * (term - 1);
                while (term - bandage[0] > 0) {
                    term -= bandage[0];
                    answer += bandage[2];
                }
                if (answer > health) answer = health;
                answer -= attacks[i][1];
            }
            System.out.println(answer);
            if (answer <= 0) return -1;
        }
        return answer;
    }
}