package algorithm;

import java.util.*;
import java.io.*;

public class BOJ1106 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 목표 인원
		int C = Integer.parseInt(st.nextToken());
		// 도시 개수
		int N = Integer.parseInt(st.nextToken());
		
		// 최대 1000
		int[] arr = new int[1101];
		
		// 비용과 그에 따른 고객 수 저장
		List<int[]> costAndCustomers = new ArrayList<>();
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customers = Integer.parseInt(st.nextToken());
			costAndCustomers.add(new int[] {cost, customers});
		}
		
		int answer = 987654321;
		for (int i=1;i<arr.length;i++) {
			arr[i] = 987654321;
			for (int j=0;j<costAndCustomers.size();j++) {
				// 현재 인덱스 - 고객 수 < 0 이면 안됨.
				if (i - costAndCustomers.get(j)[1] < 0) {
					continue;
				}
				arr[i] = Math.min(arr[i], arr[i-costAndCustomers.get(j)[1]] + costAndCustomers.get(j)[0]);
			}
			if (i >= C) {
				answer = Math.min(answer, arr[i]);				
			}
		}
		System.out.println(answer);
	}
}