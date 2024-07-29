package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> heights = new PriorityQueue<>(Collections.reverseOrder());
		for (int i=0;i<N;i++) {
			heights.add(Integer.parseInt(br.readLine()));
		}
		
		int count = 0;
		boolean available = false;
		for (int i=0;i<T;i++) {
			int heightest = heights.poll();
			if (heightest == 1) {
				heights.add(heightest);
				break;
			}
			if (heightest < H) {
				available = true;
				heights.add(heightest);
				break;
			}
			
			heightest /= 2;
			heights.add(heightest);
			count++;
		}
		
		int heightest = heights.poll();
		heights.add(heightest);
		if (heightest < H) {
			available = true;
		}
		
		if (available) {
			System.out.println("YES");
			System.out.println(count);
		} else {
			System.out.println("NO");
			System.out.println(heights.poll());
		}		
	}
}
