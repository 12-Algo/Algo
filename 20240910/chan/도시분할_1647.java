package Algo_0910;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class 도시분할_1647 {    
 
    static int N, M, result;
    static PriorityQueue<Node> q;
    static int[] parent;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = 0;
        
        q = new PriorityQueue<>();
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int s = Integer.parseInt(st.nextToken());
        	int e = Integer.parseInt(st.nextToken());
        	int cost = Integer.parseInt(st.nextToken());
            q.offer(new Node(s, e, cost));
        }
        
        parent = new int[N + 1];
        kruskal();
        System.out.println(result);
    }
    
    public static void kruskal() {
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        
        int count = 0;
        while(count < N - 2) { // n - 2개의 간선을 선택한다.
            Node node = q.poll();
            int a = find(node.s);
            int b = find(node.e);
            
            if(a != b) {
                union(a, b);
                result += node.cost;
                count++; // 싸이클이 발생되지 않아 최소 간선으로 선택된 경우에만 count++를 해준다.
            }
        }
        return;
    }
    
    public static void union(int a, int b){
        parent[a] = b;
    }
    
    public static int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    
    public static class Node implements Comparable<Node> {
        int s;
        int e;
        int cost;
        
        public Node(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n1) {
            return this.cost - n1.cost;
        }
    }
}
