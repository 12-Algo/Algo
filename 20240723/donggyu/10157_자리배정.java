package javaAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AssignSeat {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		//상우하좌
		int[] dxs = {-1, 0, 1, 0};
		int[] dys = {0, 1, 0, -1};
		
				
		int C = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(bf.readLine());
		
		//관객 대기번호가 전체 자리수보다 크면 배정 불가
		if(target > (C*R)) {
			System.out.println(0);
			return;
		}
		
		int nowX = R-1;
		int nowY = 0;
		int[][] board = new int [R][C];
		int cnt = 1;
		board[nowX][nowY] = cnt;
		cnt++;
		int nextX = 0;
		int nextY = 0;
		//시작은 위족을 바라보고 출발
		int dirNum = 0;

		while(true) {
			//대기번호 K인 관객에게 좌석이 배정되면 반복문 종료
			if(target < cnt) {
				//출력형식에 맞게 세팅
				System.out.println(String.format("%d %d", nowY+1, R-nowX));
				break;
			}

			nextX = nowX+dxs[dirNum];
			nextY = nowY+dys[dirNum];
			
			//공연장을 벗어나지 않고 해당 칸이 이미 차 있지 않으면 값 추가
			if (inRange(nextX, nextY, R, C)==true && board[nextX][nextY]==0) {
				board[nextX][nextY] = cnt;
				nowX = nextX;
				nowY = nextY;
				cnt++;
			} else {
				dirNum = (dirNum+1)%4;
			}
			
		}

	}
	
	//격자 범위 확인 메서드
	static boolean inRange(int x, int y, int r, int c) {
		return (0<=x && x<r && 0<=y && y<c); 
	}
	
}
