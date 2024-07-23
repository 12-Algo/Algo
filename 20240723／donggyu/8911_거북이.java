package javaAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Turtle {
		//dx dy?
		//maxX, maxY, minX, minY 저장
		//정사각형 넓이 = (maxX-minX)*(maxY-minY)		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(bf.readLine());
			
			//상우하좌
			int[] dxs = {-1, 0, 1, 0};
			int[] dys = {0, 1, 0, -1};
					
			for(int i=0;i<T;i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());	
				String[] orders = st.nextToken().split("");
				int maxX = 0;
				int minX = 0;
				int maxY = 0;
				int minY = 0;
				//시작은 북쪽방향
				int dirNum = 0;
				int nowX = 0;
				int nowY = 0;
				
				for(int j=0;j<orders.length;j++) {
					if (orders[j].equals("F")) {
						nowX += dxs[dirNum];
						nowY += dys[dirNum];
						maxX = Math.max(maxX, nowX);
						maxY = Math.max(maxY, nowY);
						minX = Math.min(minX, nowX);
						minY = Math.min(minY, nowY);
					} else if(orders[j].equals("L")) {
						dirNum = (dirNum+3)%4;
					} else if(orders[j].equals("R")) {
						dirNum = (dirNum+1)%4;
					} else if(orders[j].equals("B")) {
						nowX -= dxs[dirNum];
						nowY -= dys[dirNum];
						maxX = Math.max(maxX, nowX);
						maxY = Math.max(maxY, nowY);
						minX = Math.min(minX, nowX);
						minY = Math.min(minY, nowY);
					}
				}
				System.out.println(""+((maxX-minX)*(maxY-minY)));
			}
				
		}

	}

}
