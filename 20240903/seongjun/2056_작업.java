package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2056 {

	// 작업 N개
	// 1 <= 시간 <= 100
	// K번 작업에 선행되어야 하는 작업 -> 1 ~ (K-1)
	// 최소 시간
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] workTime = new int[N+1];
		int[] degree = new int[N+1];
		int[][] preWork = new int[N+1][];
		Queue<Integer> q = new LinkedList<>();
		for (int n=1;n<=N;n++) {
			// 작업 시간, 선행 작업 개수(진입 차수), 선행 작업 번호
			StringTokenizer st = new StringTokenizer(br.readLine());
			workTime[n] = Integer.parseInt(st.nextToken());
			degree[n] = Integer.parseInt(st.nextToken());
			if (degree[n] == 0) {
				q.add(n);
			}
			preWork[n] = new int[degree[n]];
			for (int i=0;i<preWork[n].length;i++) {
				preWork[n][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] times = new int[N+1];
		for (int i=0;i<N+1;i++) {
			times[i] = workTime[i];
		}
		int answer = 0;
		boolean[] visit = new boolean[N+1];
		while (!q.isEmpty()) {
			int now = q.poll();
			visit[now] = true;
			
			for (int i=1;i<N+1;i++) {
				if (i == now) {
					continue;
				}
				for (int j=0;j<preWork[i].length;j++) {
					if (preWork[i][j] == now) {
						degree[i]--;
						times[i] = Math.max(workTime[i] + times[now], times[i]);
						if (degree[i] == 0) {
							if (!visit[i]) {
								q.add(i);								
							}
						}
					}
				}
			}
			
			answer = Math.max(answer, times[now]);
		}
		
		System.out.println(answer);
	}
}
