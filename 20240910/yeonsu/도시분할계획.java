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
    static Queue<int[]> pq;
    public static void main(String[] args) throws IOException {
        init();
        int totalLength = 0;
        int maxLength = -1;
        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            if (find(node[0]) == find(node[1])) continue;
            union(node[0], node[1]);
            if (maxLength < node[2]) maxLength = node[2];
            totalLength += node[2];
        }
        System.out.println(totalLength - maxLength);
    }

    private static void init() throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        parent = new int[N + 1];
        rank = new int[N + 1];
        for(int i = 0; i < N + 1; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int a, b, c;
            token = new StringTokenizer(br.readLine());
            a = Integer.parseInt(token.nextToken());
            b = Integer.parseInt(token.nextToken());
            c = Integer.parseInt(token.nextToken());
            pq.offer(new int[]{a, b, c});
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