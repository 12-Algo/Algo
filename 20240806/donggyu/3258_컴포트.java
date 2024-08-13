package algo;

import java.io.*;
import java.util.*;

public class Comfort {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
//		필드의 수(n), 도착해야하는 필드 번호(endpoint), 정수 개수(m)
		int n = Integer.parseInt(st.nextToken());
		int endpoint = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		List<Integer> obstacles = new ArrayList<>();
		
		st = new StringTokenizer(bf.readLine());
		for(int i=0;i<m;i++) {
			obstacles.add(Integer.parseInt(st.nextToken())-1);
		}
		
		for(int k=1;k<n;k++) {
			if (isFinish(obstacles, n, endpoint, k)) {
				System.out.println(k);;
				break;
			}
		}

	}
	
//	이동 간격이 k일 때 장애물 필드 없이 도착점에 도달하는지 확인하는 메서드
	static boolean isFinish(List<Integer> ob, int n, int endpoint, int k) {
		int[] isVisited = new int[n];
		int now = 0;
		while (true) {
			if (ob.contains(now)==true || isVisited[now]!=0) break;
			else if(now == endpoint-1) return true;
			isVisited[now] = 1;
			now = (now+k)%n;

		}
		return false;
	}
}
