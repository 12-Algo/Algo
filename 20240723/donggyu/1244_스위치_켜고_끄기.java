package javaAlgorithm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
//		스위치 개수(100이하 양의 정수)
//		각 스위치의 상태(켜져 있으면 1 | 꺼져 있으면 0)
//		학생 수(100 이하 양의 정수)
//		학생성별 / 학생이 받은 수 -> 학생 수만큼 반복
//		남학생(1) -> 스위치 번호가 받은 수의 배수이면 상태 반전
//		여학생(2) -> 받은 수 기준 좌우대칭인 경우 상태 반전
		
		int n = Integer.parseInt(bf.readLine());
		int[] switches = new int [n];
		
		StringTokenizer stk = new StringTokenizer(bf.readLine());
		for(int i=0;i<n;i++) {
			switches[i] = Integer.parseInt(stk.nextToken());
		}
		
		
		int studentNum = Integer.parseInt(bf.readLine());
		for(int i=0;i<studentNum;i++) {
			stk = new StringTokenizer(bf.readLine(), " ");
			int gender = Integer.parseInt(stk.nextToken());
			int switchNum = Integer.parseInt(stk.nextToken());
			
			if (gender == 1) {
				changeSwitchBoy(switchNum, switches);
			} else {
				changeSwitchGirl(switchNum, switches);
			}
		}

		for(int i=0;i<switches.length;i++) {
			System.out.print(switches[i]+" ");
			if((i+1)%20 == 0) {
				System.out.println();
			}
		}
	}
	
	//남학생
	static void changeSwitchBoy(int switchNum, int[] switches) {
		for(int i=0;i<switches.length;i++) {
			if ((i+1)%switchNum == 0) {
				switches[i] = (switches[i]+1)%2;
			}
		}
	}
	
	//여학생
	static void changeSwitchGirl(int switchNum, int[] switches) {
		switches[switchNum-1] = (switches[switchNum-1]+1)%2;
		for(int i=1;i<switches.length/2;i++) {
			//범위 벗어나면 종료
			if(switchNum-1+i >= switches.length || switchNum-1-i<0) {
				break;
			}
			if(switches[switchNum-1-i] == switches[switchNum-1+i]) {
				switches[switchNum-1-i] = (switches[switchNum-1-i]+1)%2;
				switches[switchNum-1+i] = (switches[switchNum-1+i]+1)%2;
			}
			else {
				//대칭이 아닐 경우 종료
				break;
			}
		}
	}
	
	
}
