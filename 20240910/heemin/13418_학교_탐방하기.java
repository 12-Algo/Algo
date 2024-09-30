import java.io.*;
import java.util.*;

public class BOJ13418 {
    static int N, M;
    static int[] parent;
    static int best = 0;
    static int worst = 0;

    static void init() {
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rx = find(x);
        int ry = find(y);

        if (rx < ry) {
            parent[ry] = rx;
        }
        else {
            parent[rx] = ry;
        }
    }

    static class Road implements Comparable<Road>{
        int a;
        int b;
        int w;

        public Road(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }

        @Override
        public int compareTo(Road r) {
            return this.w - r.w;
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];

        PriorityQueue<Road> pq = new PriorityQueue<>(); // 오르막길부터 탐색 ( 0 -> 1 )
        PriorityQueue<Road> pq2 = new PriorityQueue<>(Collections.reverseOrder()); // 내리막길부터 탐색 ( 1 -> 0 )

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.add(new Road(a, b, c));
            pq2.add(new Road(a, b, c));
        }

        // 크루스칼 1
        init();
        while (!pq.isEmpty()) {
            Road r = pq.poll();

            if (find(r.a) == find(r.b)) {
                continue;
            }
            union(r.a, r.b);
            if (r.w == 0) {
                worst++;
            }
        }
        
        // 크루스칼 2
        init();
        while (!pq2.isEmpty()) {
            Road r = pq2.poll();

            if (find(r.a) == find(r.b)) {
                continue;
            }
            union(r.a, r.b);
            if (r.w == 0) {
                best++;
            }
        }
        System.out.println(worst*worst-best*best);
    }
}