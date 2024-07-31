package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static boolean checkAvailable(int[] alphabetCount) {
		int oddCount = 0;
		for (int i=0;i<alphabetCount.length;i++) {
			if (alphabetCount[i] % 2 == 1) {
				oddCount++;
			}
		}
		
		if (oddCount < 2) {
			return true;
		}
		return false;
	}
	
	static int[] countAlphabets(char[] alphabets) {
		int[] alphabetCount = new int[26];
		for (int i=0;i<26;i++) {
			for (int j=0;j<alphabets.length;j++) {
				if (alphabets[j] == (char)('A' +i)) {
					alphabetCount[i]++;
				}
			}
		}
		
		return alphabetCount;
	}
	
	static StringBuilder palindrome(int[] alphabetCount) {
		// alphabetCount를 순회하며 기록된 숫자만큼 알파벳을 추가한다.
		// 조건 1. 항상 문자열의 중간에 추가한다.
		// 조건 2. 홀수번 등장하는 알파벳은 따로 기록해두고 알파벳을 추가한다.
		StringBuilder sb = new StringBuilder();
		char mid = ' ';
		for (int i=0;i<alphabetCount.length;i++) {
			if (alphabetCount[i] % 2 == 1) {
				mid = (char) ('A'+i);
				for (int j=0;j<alphabetCount[i]-1;j++) {
					sb.insert(sb.length()/2, (char)('A'+i));
				}
			} else {
				for (int j=0;j<alphabetCount[i];j++) {
					sb.insert(sb.length()/2, (char)('A' +i));						
				}
			}
		}
		if (mid != ' ') {
			sb.insert(sb.length()/2, mid);
		}
		return sb;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] alphabets = br.readLine().toCharArray();
		
		// 모든 알파벳이 짝수번 등장하거나, 단 한개만 홀수번 등장해야 팰린드롬 가능.
		int[] alphabetCount = countAlphabets(alphabets);
		
		if (!checkAvailable(alphabetCount)) {
			System.out.println("I'm Sorry Hansoo");
		} else {
			System.out.println(palindrome(alphabetCount).toString());
		}
	}

}
