import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int N;
    static int memo[];
    static int array[];
    static int answer;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        memo = new int[501];
        array = new int[501];
        Arrays.fill(memo, -1);
        Arrays.fill(array, -1);
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            array[a] = b;
        }

        for (int i=1;i<501;i++) {
            if (array[i] == -1) continue;
            memo[i] = 1;
            for (int j=1;j<i;j++) {
                if (array[j] == -1) continue;
                if (array[i] > array[j] && memo[i] < memo[j] + 1) {
                    memo[i] = memo[j] + 1;
                    answer = Math.max(memo[i], answer);
                }
            }
        }

        System.out.println(N - answer);

    }
}
