import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static ArrayList<Edge>[] graph;

	static int prim(int start, PriorityQueue<Edge> pq) {
		boolean[] visited = new boolean[N+1];
//		오르막길의 cost = 0이므로 추후 cnt계산을 위해 -1로 시작점 세팅
		pq.add(new Edge(start, -1));
		int temp = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if(temp == N+1) break;
			if(visited[now.w]) continue;
			
			visited[now.w] = true;
			temp++;
//			오르막길이면 cnt++
			if(now.cost == 0) cnt++;			
			
			for (Edge nxt : graph[now.w]) {
				if(visited[nxt.w]) continue;
				pq.add(nxt);
			}
		}
		return cnt;
	}
	
	static class Edge{

		int w, cost;
		
		Edge(int w, int cost){
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
		
		for(int i=0;i<M+1;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(line.nextToken());
			int to = Integer.parseInt(line.nextToken());
			int cost = Integer.parseInt(line.nextToken());
			graph[from].add(new Edge(to, cost));
			graph[to].add(new Edge(from, cost));
		}

//		최소힙(오르막길(0)부터 선택)
		PriorityQueue<Edge> minPq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
//		최대힙(내리막길(1)부터 선택)
		PriorityQueue<Edge> maxPq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.cost, o1.cost));
		
		int maxFatigue = prim(0, minPq);
		int minFatigue = prim(0, maxPq);
		System.out.println(maxFatigue*maxFatigue - minFatigue*minFatigue);
	}

}