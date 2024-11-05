package algorithm;

import java.util.*;
import java.io.*;

public class BOJ1253 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int answer = 0;
		for (int i=N-1;i>=0;i--) {
			int target = arr[i];
			int left = 0;
			int right = N-1;
			while (left < right) {
				if (left == i) {
					left++;
					continue;
				} else if (right == i) {
					right--;
					continue;
				}
				int sum = arr[left] + arr[right];
				if (sum > target) {
					right--;
				} else if (sum < target) {
					left++;
				} else if (sum == target) {
					answer++;
					break;
				}
			}
		}
		System.out.println(answer);
		
	}
}