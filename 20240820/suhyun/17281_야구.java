import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] output;
    static int maxScore = 0;

    static void calcScore(int[] arr) {
        int now = 0; // 현재 타자 순서
        int out = 0;
        int score = 0;
        // 큐를 사용해 베이스 표현
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < 3; i++) {
            q.add(0);
        }

        for (int i = 0; i < N; i++) {
            while (out < 3) {
                int res = output[i][arr[now]];

                // 베이스 이동하는 경우
                if (res > 0) {
                    score += q.poll();
                    q.add(1); // 사람이자 점수

                    // 베이스 돌도록 0 추가
                    for (int k = 0; k < res - 1; k++) {
                        score += q.poll();
                        q.add(0);
                    }
                } else {
                    out++;
                }

                now = (now + 1) % 9;
            }

            // 아웃인 경우 out과 베이스 초기화
            out = 0;
            for (int k = 0; k < 3; k++) {
                q.poll();
                q.add(0);
            }
        }
        maxScore = Math.max(maxScore, score);
    }

    static void permutation(int[] arr, int idx, int check) {
        // 4번 타자 고정
        if (idx == 3) {
            permutation(arr, idx + 1, check);
            return;
        }

        if (idx == 9) {
            calcScore(arr);
            return;
        }

        for (int i = 0; i < 9; i++) {
            if ((check & (1 << i)) == 0) {
                arr[idx] = i;
                permutation(arr, idx + 1, check | (1 << i));
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        output = new int[N][9];

        for (int i = 0; i < N; i++) {
            StringTokenizer line = new StringTokenizer(br.readLine());

            for (int j = 0; j < 9; j++) {
                output[i][j] = Integer.parseInt(line.nextToken());
            }
        }

        int[] arr = new int[9];
        arr[3] = 0;
        permutation(arr, 0, 1);
        System.out.println(maxScore);
    }
}