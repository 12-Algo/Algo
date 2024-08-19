import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
		int userNum = Integer.parseInt(st.nextToken());
		int relationNum = Integer.parseInt(st.nextToken());
		int friend1;
		int friend2;
		
//		친구 정보 저장 리스트
		List<Integer>[] relations = new ArrayList[userNum+1];
		for(int i=1;i<=userNum;i++) {
			relations[i] = new ArrayList<>();
		}
		
		for(int i=0;i<relationNum;i++) {
			st = new StringTokenizer(bf.readLine());
			friend1 = Integer.parseInt(st.nextToken());
			friend2 = Integer.parseInt(st.nextToken());
			relations[friend1].add(friend2);
			relations[friend2].add(friend1);
		}
		
		
//		케빈베이컨 계산
		int[] kbNums = new int[userNum+1];
		for(int i=1;i<=userNum;i++) {
			kbNums[i] = bfs(relations, userNum, i);
		}
		
		int minKb = Integer.MAX_VALUE;
		int ansUser = 0;
//		최소 케빈베이컨 유저
		for(int i=1;i<=userNum;i++) {
			if(kbNums[i] < minKb) {
				minKb = kbNums[i];
				ansUser = i;
			}
		}
		System.out.println(ansUser);
	}
	
//	케빈 베이컨 계산 메서드
	static int bfs(List<Integer>[] relations, int n, int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] isVisited = new boolean[n+1];
		int[] dist = new int[n+1];
		int total = 0;
		
		queue.add(start);
		isVisited[start] = true;
		
		while(true) {
			if(queue.isEmpty()) {
				break;
			}
			int cur = queue.poll();
			
			for(int friend : relations[cur]) {
				if(isVisited[friend] == false) {
					isVisited[friend] = true;
					dist[friend] = dist[cur] + 1;
					total += dist[friend];
					queue.add(friend);
				}
			}
			
		}
		return total;
	}
}
