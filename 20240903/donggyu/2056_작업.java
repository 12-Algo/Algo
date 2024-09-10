package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2056 {

	static int N, ans;
	static int[] edges, times, finishTime;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static Queue<Integer> q = new ArrayDeque<>();
	static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

//	위상정렬
	static void topological() {
		
		for(int i=0;i<N;i++) {
			if(edges[i]==0) {
				q.add(i);
				finishTime[i]= times[i]; 
			}
		}
		
		while(!q.isEmpty()) {
			int now = q.poll();
			
			ArrayList<Integer> g = graph.get(now);
			for(int j=0;j<g.size();j++) {
				int to = g.get(j);
				edges[to]--;
//				A작업이 끝나는 시간 = 선행 작업들이 끝나는시간+A작업에 걸리는 시간들 중 최대값 
				finishTime[to] = Math.max(finishTime[to], times[to]+finishTime[now]);
				if(edges[to]==0) q.add(to);
			}
		}
	}
	
	static void setInput() throws IOException {
		for(int i=0;i<N;i++) {
			StringTokenizer line = new StringTokenizer(bf.readLine());	
			times[i]= Integer.parseInt(line.nextToken());
			int precedingCnt = Integer.parseInt(line.nextToken());
			for(int j=0;j<precedingCnt;j++) {
				int work = Integer.parseInt(line.nextToken());
				graph.get(work-1).add(i);
				edges[i]++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(bf.readLine());
		edges = new int[N];
//		각 작업별 작업에 걸리는 시간
		times = new int[N];
		for(int i=0;i<N;i++) {
			graph.add(new ArrayList<>());
		}
//		각 작업별 종료 시간
		finishTime = new int[N];
		
		setInput();
		topological();
		
		ans = 0;
		for(int i=0;i<finishTime.length;i++) {
			if(ans<finishTime[i]) ans = finishTime[i];
		}
		System.out.println(ans);
	}
}
