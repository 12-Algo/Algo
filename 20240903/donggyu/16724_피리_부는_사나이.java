package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ16724 {

	static int N, M, routeCnt;
	static char[][] board;
	static int[][] inRoute;
//	상우하좌
	static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static final HashMap<Character, Integer> map = new HashMap<Character,Integer>() {
		{
			put('U', 0);
			put('R', 1);
			put('D', 2);
			put('L', 3);
		}
	};
	
	static void calcRoute(int x, int y, int mark) {
		
		while(true) {
			int dirNum = map.get(board[x][y]);
			x += delta[dirNum][0];
			y += delta[dirNum][1];
			if(inRoute[x][y] != 0) {
//				이번 싸이클에서 마킹된 게 아닐 경우
				if(inRoute[x][y]!= mark) return;
				else if(inRoute[x][y]== mark) break;
			}
			inRoute[x][y] = mark; 
		}
		routeCnt++;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer input = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(input.nextToken());
		M = Integer.parseInt(input.nextToken());
		board = new char[N][M];
		inRoute = new int[N][M];
		
		routeCnt = 0;
		for(int i=0;i<N;i++) {
			String line = bf.readLine();
			for(int j=0;j<M;j++) {
				board[i][j] = line.charAt(j); 
			}
		}
		
		int mark = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(inRoute[i][j]==0) {
					calcRoute(i, j, ++mark);
				}
			}
		}
		System.out.println(routeCnt);
	}
}
