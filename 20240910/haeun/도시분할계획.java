import java.io.*;
import java.util.*;

public class 도시분할계획 {
    static int N;
    static int M;
    static boolean[] visited;
    static ArrayList<House>[] edges;
    static int costs;
    static int maxCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        costs = 0;
        maxCost = 0;
        edges = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[from].add(new House(to, cost));
            edges[to].add(new House(from, cost));
        }

        prim(1);
        System.out.println(costs - maxCost);
    }

    static void prim(int start) {
        PriorityQueue<House> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
        pq.add(new House(start, 0));

        while (!pq.isEmpty()) {
            House house = pq.poll();
            int t = house.to;
            int c = house.cost;

            if (visited[t])
                continue;
            visited[t] = true;
            costs += c;
            maxCost = Math.max(maxCost, c);

            for (House h : edges[t]) {
                if (!visited[h.to]) {
                    pq.add(h);
                }
            }

        }
    }
}

class House {
    int to;
    int cost;

    House(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}