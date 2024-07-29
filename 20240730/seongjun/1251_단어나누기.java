package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String partialReverse(int midLength, String str) {
		String result = "z" + str; // 초기 상태가 사전 순서 상 가장 앞서는 단어를 제외.
		for (int i=1;i+midLength<str.length();i++) {
			StringBuilder left = new StringBuilder(str.substring(0, i)).reverse();
			StringBuilder mid = new StringBuilder(str.substring(i, i + midLength)).reverse();
			StringBuilder right = new StringBuilder(str.substring(i+midLength)).reverse();

			
			String concat = left.append(mid).append(right).toString();
			result = compare(result, concat);
		}
		
		return result;
	}
	
	static String compare(String str1, String str2) {
		if (str1.compareTo(str2) > 0) {
			return str2;
		}
		return str1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		String answer = "z" + input; // 초기 상태가 사전 순서 상 가장 앞서는 단어를 제외.
		// 1. 가운데 알파벳의 길이를 1개, 2개, 3개, ..., n-2개 까지 설정하면서 순회
		// 2. 순회하면서 알파벳의 사전 순서 비교.
		int midLength = 1;
		for (int i=0;i<input.length()-2;i++) {				
			answer = compare(answer, partialReverse(midLength+i, input));
		}
		System.out.println(answer);
	}

}