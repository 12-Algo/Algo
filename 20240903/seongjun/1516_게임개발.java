package algorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1516 {
	// 모든 건물 짓는 최소 시간 근사
	// 여러 개 건물 동시 짓기 가능
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] buildingTime = new int[N+1];
		int[] answer = new int[N+1];
		int[] degrees = new int[N+1];
		List<Integer>[] preBuilding = new ArrayList[N+1];
		for (int i=0;i<N+1;i++) {
			preBuilding[i] = new ArrayList<>();
		}
		Queue<Integer> q = new LinkedList<>();
		for (int n=1;n<=N;n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			buildingTime[n] = Integer.parseInt(st.nextToken());
			answer[n] = buildingTime[n];
			int input = Integer.parseInt(st.nextToken());
			while (input != -1) {
				preBuilding[input].add(n);
				degrees[n]++;
				input = Integer.parseInt(st.nextToken());
			}
			if (degrees[n] == 0) {
				q.add(n);
			}
		}
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int i=0;i<preBuilding[now].size();i++) {
				int idx = preBuilding[now].get(i);
				degrees[idx]--;
				
				answer[idx] = Math.max(answer[idx], answer[now] + buildingTime[idx]);
				if (degrees[idx] == 0) {
					q.add(idx);
				}
			}
		}
		
		for (int i=1;i<=N;i++) {
			System.out.println(answer[i]);
		}
		
		
		
		
	}
}