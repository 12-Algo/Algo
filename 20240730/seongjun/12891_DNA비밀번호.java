package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static boolean passwordAvailable(int[] acgtCount, int[] minCount) {
		for (int i=0;i<4;i++) {
			if (acgtCount[i] < minCount[i]) {
				return false;
			}
		}
		return true;
	}
	
	static void subtractCount(char front, int[] acgtCount) {
		if (front == 'A') {
			acgtCount[0]--;
		} else if (front == 'C') {
			acgtCount[1]--;
		} else if (front == 'G') {
			acgtCount[2]--;
		} else if (front == 'T') {
			acgtCount[3]--;
		}
	}
	
	static void addCount(char back, int[] acgtCount) {
		if (back == 'A') {
			acgtCount[0]++;
		} else if (back == 'C') {
			acgtCount[1]++;
		} else if (back == 'G') {
			acgtCount[2]++;
		} else if (back == 'T') {
			acgtCount[3]++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		
		String dna = br.readLine();
		
		int[] minCount = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<4;i++) {
			minCount[i] = Integer.parseInt(st.nextToken());
		}
		int[] acgtCount = new int[4];
		
		int answer = 0;
		// 반복문을 순회하며 맨 앞 한 글자가 ACGT일때 count--, 맨 뒤 한 글자가 ACGT일 때 count++
		// 매 반복마다 비밀번호로 만들 수 있는지 체크.
		String subString = dna.substring(0, T);
		for (int i=0;i<subString.length();i++) {
			addCount(subString.charAt(i), acgtCount);
		}
		
		if (passwordAvailable(acgtCount, minCount)) {
			answer++;
		}
		
		for (int i=1;i<S-T+1;i++) {
			subtractCount(dna.charAt(i-1), acgtCount);
			addCount(dna.charAt(i+T-1), acgtCount);
			
			if (passwordAvailable(acgtCount, minCount)) {
				answer++;
			}
		}
		System.out.println(answer);
		
	}
}
