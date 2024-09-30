package algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class test {
	
	static int[] root;
	
	static int find(int x) {
		if (root[x] == x) {
			return x;
		}
		return root[x] = find(root[x]);
	}
	
	static void union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		if (xRoot == yRoot) {
			return;
		}
		root[yRoot] = xRoot;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		root = new int[N+1];
		for (int i=0;i<N+1;i++) {
			root[i] = i;
		}
		int[][] island = new int[M][3];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			island[i][0] = Integer.parseInt(st.nextToken());
			island[i][1] = Integer.parseInt(st.nextToken());
			island[i][2] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		Arrays.sort(island, (o1, o2) -> Integer.compare(o2[2], o1[2]));

		for (int i=0;i<M;i++) {
			union(island[i][0], island[i][1]);
			if (find(start) == find(end)) {
				System.out.println(island[i][2]);
				return;
			}
		}
		
	}
}