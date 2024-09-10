import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static ArrayList<Node>[] graph;
	static int[] time, connected;
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)->Integer.compare(o1.cost, o2.cost));
		pq.add(new Node(1, 0));
		time[1] = 0;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			int nowIdx = now.w;
			int nowCost = now.cost;
			
//			기존 값이 더 작으면 패스
			if(nowCost > time[nowIdx]) continue;
			
			for (Node nxt : graph[nowIdx]) {
				if(nowCost + nxt.cost < time[nxt.w]) {
					time[nxt.w] = nowCost + nxt.cost;
					pq.add(new Node(nxt.w, time[nxt.w]));
					connected[nxt.w] = now.w;
				}
			}
		}
	}
	
	static class Node{
		int w, cost;
		
		Node(int w, int cost){
			this.w = w;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		M = Integer.parseInt(input.nextToken());
	
		graph = new ArrayList[N+1];
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>(); 
		}
		
		for(int i=0;i<M;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			int pc1 = Integer.parseInt(line.nextToken());
			int pc2 = Integer.parseInt(line.nextToken());
			int t = Integer.parseInt(line.nextToken());
			graph[pc1].add(new Node(pc2, t));
			graph[pc2].add(new Node(pc1, t));
		}
		
		connected = new int[N+1];
		time = new int[N+1];
		Arrays.fill(time, Integer.MAX_VALUE);
		
		dijkstra();
		
		sb.append(N-1).append("\n");
		for(int i=2;i<=N;i++) {
			sb.append(i).append(" ").append(connected[i]).append("\n");
		}
		System.out.println(sb.toString());
	}
}
