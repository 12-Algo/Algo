import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int N, M;
    static List<List<int[]>> adjList;
    static List<int[]> lines = new ArrayList<>();
    private static long originalMinTime;
    private static int[] preNode;

    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        adjList = new ArrayList<>();
        preNode = new int[N + 1];
        for (int i=0;i<N+1;i++) {
            adjList.add(new ArrayList<>());
        }

        for (int m=0;m<M;m++) {
            int a, b, c;
            token = new StringTokenizer(br.readLine());
            a = Integer.parseInt(token.nextToken());
            b = Integer.parseInt(token.nextToken());
            c = Integer.parseInt(token.nextToken());
            adjList.get(a).add(new int[]{b, c});
            adjList.get(b).add(new int[]{a, c});
        }

        dijkstra();
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i=2;i<N+1;i++) {
            if (preNode[i] != 0) {
                cnt++;
                sb.append(i + " " + preNode[i] + "\n");
            }
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    private static void dijkstra() {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        pq.offer(new int[]{1, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            //방문하지 않았다면 노드를 방문합니다.
            if (dist[cur[0]] < cur[1]) continue;

            //인접한 노드중에서 갱신할 수 있는 노드를 갱신후 pq에 저장.
            for (int[] next : adjList.get(cur[0])) {
                if (dist[next[0]] > cur[1] + next[1]) {
                    dist[next[0]] = cur[1] + next[1];
                    preNode[next[0]] = cur[0];
                    pq.add(new int[]{next[0], dist[next[0]]});
                }
            }
        }
    }
}


