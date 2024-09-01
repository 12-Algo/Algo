import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer token;
    static StringBuilder sb;
    static int N, M;
    static int[] adjList;
    private static int[] parent;
    private static int[] rank;

    public static void main(String[] args) throws IOException {
        getInput();
    }
    
    private static void getInput() throws IOException {
        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        adjList = new int[N * M];
        parent = new int[N * M];
        rank = new int[N * M];
        for (int i=0;i<N * M;i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        
        for (int i=0;i<N;i++) {
            String line = br.readLine();
            for (int j=0;j<M;j++) {
                char nextDir = line.charAt(j);
                int nextNode;
                if (nextDir == 'U') {
                    nextNode = i * M + j - M;
                } else if (nextDir == 'D') {
                    nextNode = i * M + j + M;
                } else if (nextDir == 'L') {
                    nextNode = i * M + j - 1;
                } else {
                    nextNode = i * M + j + 1;
                }
                adjList[i * M + j] = nextNode;
            }
        }
        int cnt = 0;
        // for (int a : adjList) {
        //     System.out.print(a + " ");
        //     if (++cnt % M == 0) System.out.println();
        // }
        System.out.println(countSafeZone());
    }

    private static int countSafeZone() {
        for (int i=0;i<N * M;i++) {
            if (find(i) == find(adjList[i])) continue;
            makeDisjointSet(i);
        }
        int cnt = 0;
        for (int i=0;i<N * M;i++) {
            if (i == find(i)) cnt++;
        }
        
        return cnt;
    }

    private static void makeDisjointSet(int a) {
        if (find(a) == find(adjList[a])) return ;
        union(a, adjList[a]);
        makeDisjointSet(adjList[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (rank[a] == rank[b]) {
            parent[b] = a;
            rank[a]++;
        } 
        else if (rank[a] > rank[b]) parent[b] = a;
        else parent[a] = b;
    }

    private static int find(int b) {
        if (parent[b] == b) return b;
        return find(parent[b]);
    }
}