package swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];
		for (int i=0;i<n;i++) {
			arr[i] = br.readLine();
		}
		
		int max = 0;
		for (int i=0;i<arr.length;i++) {
			int temp = 0;
			for (int j=0;j<arr.length;j++) {
				boolean isFriend = false;
				if (i == j) {
					continue;
				}
				// 1. A와 B가 서로 친구
				if (arr[i].charAt(j) == 'Y' && arr[j].charAt(i) == 'Y') {
					isFriend = true;
				} else {
					for (int k=0;k<arr.length;k++) {
						if (i == k || j == k) {
							continue;
						}
						// 2. A와도 친구이고, B와도 친구인 C가 존재
						if (arr[i].charAt(k) == 'Y' && arr[j].charAt(k) == 'Y') {
							isFriend = true;
							break;
						}
					}
				}
				if (isFriend) {
					temp++;
				}
			}
			max = Math.max(max, temp);
		}
		
		System.out.println(max);
	}
}