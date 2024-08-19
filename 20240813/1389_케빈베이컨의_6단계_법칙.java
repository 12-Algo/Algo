import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer>[] relation;
    static int[][] dist;

    private static void bfs(int s) {
        Queue<int[]> q = new LinkedList<>();

        // 큐에 [현재 node, 관계의 거리] 배열로 저장
        int[] start = { s, 1 };
        q.add(start);

        while (!q.isEmpty()) {
            int[] out = q.poll();
            int now = out[0];
            int d = out[1];

            for (int f : relation[now]) {
                if (dist[s][f] == 0) {
                    dist[s][f] = d;

                    int[] next = { f, d + 1 };
                    q.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer input = new StringTokenizer(br.readLine());
        N = Integer.parseInt(input.nextToken());
        int M = Integer.parseInt(input.nextToken());
        relation = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            relation[i] = new ArrayList<Integer>();
        }

        dist = new int[N][N];

        // 인접 리스트
        for (int i = 0; i < M; i++) {
            StringTokenizer rel = new StringTokenizer(br.readLine());
            int f1 = Integer.parseInt(rel.nextToken());
            int f2 = Integer.parseInt(rel.nextToken());

            relation[f1 - 1].add(f2 - 1);
            relation[f2 - 1].add(f1 - 1);
        }

        for (int i = 0; i < N; i++) {
            bfs(i);
        }

        int res = Integer.MAX_VALUE;
        int resIdx = 0;

        // 베이컨 수 최소 구하기
        for (int i = 0; i < N; i++) {
            int temp = Math.min(res, Arrays.stream(dist[i]).sum());

            if (temp < res) {
                res = temp;
                resIdx = i;
            }
        }

        System.out.println(resIdx + 1);
    }
}