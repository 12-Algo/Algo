import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int N, M, T;
    static List<List<int[]>> adjList;
    public static void main(String[] args) throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        T = Integer.parseInt(token.nextToken());
        adjList = new ArrayList<>();
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
        System.out.println(findMinCost());
    }

    private static long findMinCost() {
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        boolean[] visited = new boolean[N+1];
        pq.offer(new int[]{1, 0});
        int ret = T;
        int cost = -T;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            //방문하지 않았다면 노드를 방문합니다.
            if (visited[cur[0]]) continue;
            visited[cur[0]] = true;
            ret += cur[1] + cost;
            cost += T;
            //인접한 노드중에서 갱신할 수 있는 노드를 갱신후 pq에 저장.
            for (int[] next : adjList.get(cur[0])) {
                if (!visited[next[0]]) {
                    pq.offer(new int[]{next[0], next[1]});
                }
            }
        }
        return ret;
    }
}
