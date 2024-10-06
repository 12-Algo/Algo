import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<int[]>[] list;

    static int prim() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] check = new boolean[N + 1];
        check[1] = true;

        for (int[] next : list[1]) {
            pq.add(next);
        }

        int sum = 0;
        int maxDist = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (check[now[0]]) {
                continue;
            }

            check[now[0]] = true;
            sum += now[1];
            maxDist = Math.max(maxDist, now[1]);

            for (int[] next : list[now[0]]) {
                pq.add(next);
            }
        }

        return sum - maxDist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer nums = new StringTokenizer(br.readLine());
        N = Integer.parseInt(nums.nextToken());
        M = Integer.parseInt(nums.nextToken());
        list = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer input = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(input.nextToken());
            int b = Integer.parseInt(input.nextToken());
            int c = Integer.parseInt(input.nextToken());

            list[a].add(new int[] { b, c });
            list[b].add(new int[] { a, c });
        }

        System.out.println(prim());
    }
}