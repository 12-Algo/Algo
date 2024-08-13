package algorithm;

import java.io.*;
import java.util.*;

public class cheeze {
	
	static int n;
	static int m;
	static int[][] boards;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		boards = new int[n][m];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j=0;j<m;j++) {
				boards[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(boards[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void bfs() {
		
	}
	
}
