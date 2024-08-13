package swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int kevinBacon(Queue<Integer> q, boolean[] visit, int level, List<List<Integer>>friends) {
		int sum = 0;
		if (q.isEmpty()) {
			return 0;
		}
		
		Queue<Integer> nextLevelQ = new LinkedList<>();
		while (!q.isEmpty()) {
			int temp = q.poll();
			if (visit[temp]) {
				continue;
			}
			visit[temp] = true;
			sum += level;
			for (int i=0;i<friends.get(temp).size();i++ ) {
				nextLevelQ.add(friends.get(temp).get(i));
			}
		}
		return sum + kevinBacon(nextLevelQ, visit, level+1, friends); 
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 단계 합 저장
		int[] levelSum = new int[n+1];
		
		// 친구 관계 저장
		List<List<Integer>> friends = new ArrayList<>();
		for (int i=0;i<=n;i++) {
			friends.add(new ArrayList<>());
		}
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friends.get(a).add(b);
			friends.get(b).add(a);
		}
		
		
		
		for (int i=1;i<=n;i++) {
			Queue<Integer> q = new LinkedList<>();
			for (int j=0;j<friends.get(i).size();j++) {
				q.add(friends.get(i).get(j));
			}
			
			boolean[] visit = new boolean[n+1];
			visit[i] = true;
			levelSum[i] = kevinBacon(q,visit, 1, friends);
		}
		
		int answer = 0;
		int min = 987654321;
		for (int i=1;i<levelSum.length;i++) {
			if (min > levelSum[i]) {
				answer = i;
				min = levelSum[i];
			}
		}
		
		System.out.println(answer);
	}
}
