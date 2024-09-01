import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] player;
    static boolean[] visited;
    static int[] order;
    static int answer;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        n = Integer.parseInt(token.nextToken());
        player = new int[n][9];
        order = new int[9];
        answer = 0;

        for (int ining=0;ining<n;ining++) {
            visited = new boolean[9];
            token = new StringTokenizer(br.readLine());
            for (int i=0;i<9;i++) {
                player[ining][i] = Integer.parseInt(token.nextToken());
            }
        }
        visited[0] = true;
        order[3] = 0;
        answer += getMaxScore(0);
        System.out.println(answer);
    }

    private static int getMaxScore(int depth) {
        int score = 0;
        if (depth == 9) {
            return getScore();
        }

        for (int i=0;i<9;i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[depth] = i;
                score = Math.max(score, getMaxScore((depth + 1) == 3 ? 4 : depth + 1));
                visited[i] = false;
            }
        }
        return score;
    }

    private static int getScore() {
        Queue<Boolean> q = new ArrayDeque<>();
        int score = 0;
        q.add(false); q.add(false); q.add(false);
        int outCount = 0;
        int i=0;
        int ining = 0;
        while(ining < n) {
            int cnt = player[ining][order[i]];
            i = (i + 1) % 9;
            //주자 아웃
            if (cnt == 0) {
                outCount++;
                //이닝 종료
                if (outCount == 3) {
                    outCount = 0;
                    ining++;
                    q = new ArrayDeque<>();
                    q.add(false); q.add(false); q.add(false);
                }
                continue;
            }
            //주자 진루
            if (cnt-- > 0) {
                q.add(true);
                if(q.poll()) score++;
            }
            //베이스 이동
            while(cnt-- > 0) {
                q.add(false);
                if (q.poll()) score++;
            }
        }
        return score;
    }

}