import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, t;
	static int[] parents;
	static List<Road> roads;
	
	static int conquer() {
		int total = 0;
		for(int i=0;i<roads.size();i++) {
			Road now = roads.get(i);
			if(union(now.a, now.b)) total += now.cost;
		}
		return total;
	}
	
	static class Road{
		int a, b, cost;
		
		Road(int a, int b, int cost){
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
	}
	
	static void makeSet() {
		for(int i=1;i<N+1;i++) {
			parents[i] = i; 
		}
	}
	
	static int findParent(int x) {
		if(parents[x]==x) return x;
		
		return parents[x]= findParent(parents[x]); 
	}
	
	static boolean union(int a, int b) {
		int aRoot = findParent(a);
		int bRoot = findParent(b);
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		M = Integer.parseInt(input.nextToken());
		t = Integer.parseInt(input.nextToken());
		
		roads = new ArrayList<>();
		for(int i=0;i<M;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			int a = Integer.parseInt(line.nextToken());
			int b = Integer.parseInt(line.nextToken());
			int cost = Integer.parseInt(line.nextToken());
			roads.add(new Road(a, b, cost));
		}
		
		Collections.sort(roads, (o1, o2) -> (o1.cost-o2.cost));
		parents = new int[N+1];
		makeSet();
		int ans = conquer();
		ans += ((N-2)*(t+(t*(N-2)))/2);
		System.out.println(ans);
	}
	
}
