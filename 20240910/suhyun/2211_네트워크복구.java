import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<int[]>[] list;

    static class NodeStatus {
        int dist;
        ArrayList<int[]> trace;

        NodeStatus(int dist, ArrayList<int[]> trace) {
            this.dist = dist;
            this.trace = trace; // 현재까지의 경로 저장
        }
    }

    static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.add(new int[] { 1, 0 });
        NodeStatus[] distance = new NodeStatus[N + 1];

        for (int i = 0; i <= N; i++) {
            distance[i] = new NodeStatus(Integer.MAX_VALUE, new ArrayList<>());
        }

        distance[1].dist = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();

            if (distance[now[0]].dist > now[1]) {
                continue;
            }

            for (int[] next : list[now[0]]) {
                int cost = now[1] + next[1];

                if (cost < distance[next[0]].dist) {
                    distance[next[0]].dist = cost;
                    distance[next[0]].trace = new ArrayList<>(distance[now[0]].trace); // 이전 경로 가져와서 갱신
                    distance[next[0]].trace.add(new int[] { now[0], next[0] }); // + 현재 간선
                    pq.add(new int[] { next[0], cost });
                }
            }
        }

        Set<int[]> visitedNode = new HashSet<>();

        for (int i = 1; i <= N; i++) {
            for (int[] t : distance[i].trace) {
                visitedNode.add(t);
            }
        }

        System.out.println(visitedNode.size());

        for (int[] t : visitedNode) {
            System.out.println(t[0] + " " + t[1]);
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/algorithm/input.txt"));
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
        dijkstra();
    }
}
