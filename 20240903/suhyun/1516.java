import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] time, res, indegree;
    static ArrayList<Integer>[] list;

    static void getMinTime() {
        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : list[now]) {
                res[next] = Math.max(res[next], res[now] + time[next]);
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N];
        res = new int[N];
        indegree = new int[N];
        list = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(input.nextToken());
            res[i] = time[i];

            while (true) {
                int node = Integer.parseInt(input.nextToken());

                if (node == -1) {
                    break;
                }

                list[node - 1].add(i);
                indegree[i]++;
            }
        }

        getMinTime();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(res[i]).append("\n");
        }
        System.out.println(sb);
    }
}