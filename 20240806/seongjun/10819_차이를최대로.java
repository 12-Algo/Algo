package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	
	static int permutation(List<Integer> input, List<Integer> perm) {
		int max = 0;
		if (input.size() == 0) {
			for (int i=0;i<perm.size()-1;i++) {
				max += Math.abs(perm.get(i) - perm.get(i+1));
			}
		} else {
			for (int i=0;i<input.size();i++) {
				List<Integer> nextPerm = new ArrayList<>(perm);
				nextPerm.add(input.get(i));
				
				List<Integer> nextInput = new ArrayList<>(input);
				nextInput.remove(input.indexOf(input.get(i)));
				
				max = Math.max(permutation(nextInput, nextPerm), max);
			}	
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Integer> A = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			A.add(Integer.parseInt(st.nextToken()));
		}
		
		int answer = permutation(A, new ArrayList<>());
		System.out.println(answer);
	}
}
