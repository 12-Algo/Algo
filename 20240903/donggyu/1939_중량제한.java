package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1939 {

	static int N, M, ans;
	static PriorityQueue<Bridge> q = new PriorityQueue<>();
	static int[] parents;
	
	static void findMax(int factory1, int factory2) {
		
		while(!q.isEmpty()) {
			Bridge cur = q.poll();
			union(cur.a, cur.b);
//			다리가 견딜 수 있는 중량의 최댓값 순으로 정렬했기 때문에
//			처음 두 섬이 연결된 경우가 최대
			if(findParent(factory1)==findParent(factory2)) {
				ans = cur.w;
				break;
			}
		}
	}
	
	static void union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;			
		}
	}
	
	static int findParent(int x) {
		if(parents[x]==x) return x;
		return parents[x] = findParent(parents[x]); 
	}
	
	static void makeSet() {
		for(int i=1;i<=N;i++) {
			parents[i] = i; 
		}
	}
	
	static class Bridge implements Comparable<Bridge>{
		int a;
		int b;
		int w;
		
		public Bridge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		
//		큰 거부터 정렬
		@Override
		public int compareTo(Bridge b) {
			return b.w - this.w;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		M = Integer.parseInt(input.nextToken());
		parents = new int[N+1];
		
		for(int i=0;i<M;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(line.nextToken());
			int b = Integer.parseInt(line.nextToken());
			int w = Integer.parseInt(line.nextToken());
			q.add(new Bridge(a, b, w));
		}
		StringTokenizer factories = new StringTokenizer(bf.readLine());
		int factory1 = Integer.parseInt(factories.nextToken());
		int factory2 = Integer.parseInt(factories.nextToken());
		
		ans = 0;
		makeSet();
		findMax(factory1, factory2);
		System.out.println(ans);
	}

}
