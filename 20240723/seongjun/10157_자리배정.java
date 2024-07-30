package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[c][r];
		int[][] visit = new int[c][r];
		visit[0][0] = 1;
		
		boolean increaseX = true;
		boolean increaseY = false;
		boolean decreaseX = false;
		boolean decreaseY = false;
		
		int x = 0;
		int y = 0;
		
		if (k > r*c) {
			System.out.println(0);
		} else {
			for (int i=1;i<k;i++) {
				if (increaseX) {
					x++;
					if ((x+1) == r || visit[y][x+1] == 1) {
						increaseX = false;
						increaseY = true;
					}
				} else if (increaseY) {
					y++;
					if ((y+1) == c || visit[y+1][x] == 1) {
						increaseY = false;
						decreaseX = true;
					}
				} else if (decreaseX) {
					x--;
					if ((x-1) == -1 || visit[y][x-1] == 1) {
						decreaseX = false;
						decreaseY = true;
					}
				} else if (decreaseY) {
					y--;
					if ((y-1) == -1 || visit[y-1][x] == 1) {
						decreaseY = false;
						increaseX = true;
					}
				}
				visit[y][x] = 1;
			}
			System.out.println((y+1) + " " + (x+1));
		}
	
	}

}
