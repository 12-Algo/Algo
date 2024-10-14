import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] housePos = new int[N];
		for (int i=0;i<N;i++) {
			housePos[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(housePos);
		
		int left = 0;
		int right = housePos[N-1];
		int max = 0;
		while (left <= right) {
			int distance = (left + right) / 2;
			int count = 1;
			int previouseHouse = 0;
			for (int i=1;i<housePos.length;i++) {
				if (housePos[i] >= housePos[previouseHouse] + distance) {
					previouseHouse = i;
					count++;
				}
				if (count == C) {
					break;
				}
			}
			
			if (count == C) {
				left = distance + 1;
				max = Math.max(max, distance);
			} else {
				right = distance - 1;
			}
		}
		
		System.out.println(max);
	}
}