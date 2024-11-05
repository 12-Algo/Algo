package algorithm;

import java.util.*;
import java.io.*;

public class BOJ2565 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] poles = new int[501];
		int[] unoverlappingCount = new int[501];
		int maxPole = 0;
		for (int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			poles[A] = B;
			maxPole = Math.max(maxPole, Math.max(A, B));
		}
		
		int maxUnoverlappingCount = 0;
		for (int i=1;i<=maxPole;i++) {
			unoverlappingCount[i] = 1;
			for (int j=1;j<i;j++) {
				if (poles[j] == 0) {
					continue;
				}
				if (poles[j] < poles[i]) {
					unoverlappingCount[i] = Math.max(unoverlappingCount[i], unoverlappingCount[j] + 1);
				}
			}
			maxUnoverlappingCount = Math.max(maxUnoverlappingCount, unoverlappingCount[i]);
		}
		
		System.out.println(N - maxUnoverlappingCount);
	}
}
