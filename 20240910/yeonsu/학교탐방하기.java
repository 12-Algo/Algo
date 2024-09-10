import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static int N, M;
    static int[] parent;
    static int[] rank;
    static Queue<int[]> pq1;        //간선 오름차순
    static Queue<int[]> pq2;        //간선 내림차순
    public static void main(String[] args) throws IOException {
        init();
        int maxPirodo = getMaxPirodo();
        initDisjointSet();
        int minPirodo = getMinPirodo();
        System.out.println(maxPirodo - minPirodo);
    }

    private static int getMinPirodo() {
        int ret = 0;
        while(!pq2.isEmpty()) {
            int[] node = pq2.poll();
            if (find(node[0]) == find(node[1])) continue;
            union(node[0], node[1]);
//            System.out.println(node[0] + " " + node[1] + " " + node[2] + " " + totalLength);
            if (node[2] == 0) ret++;
        }
//        System.out.println(ret);
        return ret * ret;
    }

    private static int getMaxPirodo() {
        int ret = 0;
        while(!pq1.isEmpty()) {
            int[] node = pq1.poll();
            if (find(node[0]) == find(node[1])) continue;
            union(node[0], node[1]);
//            System.out.println(node[0] + " " + node[1] + " " + node[2] + " " + totalLength);
            if (node[2] == 0) ret++;
        }
//        System.out.println(ret);
        return ret * ret;
    }

    private static void init() throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        pq1 = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq2 = new PriorityQueue<>(Comparator.comparingInt(a -> -a[2]));
        initDisjointSet();

        for (int i = 0; i < M + 1; i++) {
            int a, b, c;
            token = new StringTokenizer(br.readLine());
            a = Integer.parseInt(token.nextToken());
            b = Integer.parseInt(token.nextToken());
            c = Integer.parseInt(token.nextToken());
            pq1.offer(new int[]{a, b, c});
            pq2.offer(new int[]{a, b, c});
        }
    }

    private static void initDisjointSet() {
        parent = new int[N + 1];
        rank = new int[N + 1];
        for(int i = 0; i < N + 1; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (rank[a] < rank[b]) parent[a] = b;
        else if (rank[a] > rank[b]) parent[b] = a;
        else {
            parent[b] = a;
            rank[a]++;
        }
    }

    private static int find(int a) {
        if (parent[a] == a) return a;
        return find(parent[a]);
    }
}