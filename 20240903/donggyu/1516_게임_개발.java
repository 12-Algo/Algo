package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {

	static int N;
	static ArrayList<ArrayList<Integer>> building = new ArrayList<>();
	static int[] times, edges, finishTime;
	static Queue<Integer> q = new ArrayDeque<>();

	static void topological() {
		for(int i=0;i<N;i++) {
			if(edges[i]== 0) {
				q.add(i);
				finishTime[i] = times[i]; 
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();

			ArrayList<Integer> g = building.get(now);
			for(int j=0;j<g.size();j++) {
				int to = g.get(j);
				edges[to]--;
				finishTime[to] = Math.max(finishTime[to], times[to]+finishTime[now]);
				if(edges[to]==0) q.add(to);
			}
		}			
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		for(int i=0;i<N;i++) {
			building.add(new ArrayList<>());
		}
		
		times = new int[N];
		edges = new int[N];
		finishTime = new int[N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());
			times[i] = Integer.parseInt(line.nextToken());
			while(true) {
				int pre = Integer.parseInt(line.nextToken());
				if (pre==-1) break;
				building.get(pre-1).add(i);
				edges[i]++;
			}
		} 
		topological();
		for(int i=0;i<finishTime.length;i++) {
			System.out.println(finishTime[i]);
		}
	}
}
