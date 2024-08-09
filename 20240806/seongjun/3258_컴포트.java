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
	
	static boolean obstacleCheck(int[] obstaclePos, int pos) {
		for (int i=0;i<obstaclePos.length;i++) {
			if (obstaclePos[i] == pos) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Z = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] obstaclePos = new int[M];
		for (int i=0;i<M;i++) {
			obstaclePos[i] = Integer.parseInt(st.nextToken());
		}

		int pos = 1;
		int K = 1;
		int[] visit = new int[N+1];
		while (true) {
			if ((pos + K) > N) {
				pos = (pos + K) % N;
			} else {
				pos = (pos + K);
			}
			
			if (pos == Z) {
				System.out.println(K);
				return;
			} else if (visit[pos] == 1) {
				pos = 1;
				K++;
				visit = new int[N+1];
			} else {
				if (!obstacleCheck(obstaclePos, pos)) {
					pos = 1;
					K++;
					visit = new int[N+1];
				}
				visit[pos] = 1;
			}
		}
	}
}
