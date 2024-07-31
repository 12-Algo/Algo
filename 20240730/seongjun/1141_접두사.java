package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<String> words = new ArrayList<>();
		for (int i=0;i<N;i++) {
			words.add(br.readLine());
		}
		Collections.sort(words, (s1, s2) -> Integer.compare(s2.length(), s1.length()));
		
		for (int i=0;i<words.size();i++) {
			String longer = words.get(i);
			for (int j=i+1;j<words.size();j++) {
				 String shorter = words.get(j);
				 if (longer.substring(0, shorter.length()).equals(shorter)) {
					 words.remove(j);
					 j--;
				 }
			}
		}
		
		System.out.println(words.size());
	
		
	}
}
