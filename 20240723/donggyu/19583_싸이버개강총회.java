package javaAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class SemesterMeeting {
	//입장 확인 학회원 -> 시작 시간 이전에 대화 기록 있음
	//퇴장 확인 학회원 -> 종료 시간 이후 스트리밍 종료 이전 대화 기록 있음
	//공백 기준 시각과 이름 분할
	//시간에서 : 기준 시간과 분 분할
	//분으로 모두 변환하여 계산
	//입장 학회원 배열 / 퇴장 학회원 배열 따로 관리 -> 무조건 런타임 에러 나는 듯
	//HashMap 사용
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		String start[] = st.nextToken().split(":");
		String end[] = st.nextToken().split(":");
		String quit[] = st.nextToken().split(":");
		int Start = Integer.parseInt(start[0])*60+Integer.parseInt(start[1]);
		int End = Integer.parseInt(end[0])*60+Integer.parseInt(end[1]);
		int Quit = Integer.parseInt(quit[0])*60+Integer.parseInt(quit[1]);
		
		HashSet<String> attend = new HashSet<>();
		
		int ans = 0;
		String input;
		
		while((input = bf.readLine()) != null) {
			
			String temp[] = input.split(" ");
			String tempTime[] = temp[0].split(":");
			int m = Integer.parseInt(tempTime[0])*60+Integer.parseInt(tempTime[1]);
			String name = temp[1];
//			System.out.println("시간 : "+m+", 이름: "+name);
//			스트리밍 종료 이후는 확인할 필요 없음
			if (m > Quit) {
				break;
			}
//			입장 학회원 세팅
			if(m <= Start) {
				attend.add(name);
				continue;
			}
			
//			퇴장 학회원 확인
			if(End <=m && m <= Quit) {
				if(attend.contains(name)) {
					//퇴장처리 -> 다시 확인 반복하지 않도록
					attend.remove(name);
					ans += 1;
//					System.out.println(name);
				}
			}
		}
		System.out.println(ans);
		
	}
	
	//HH:MM형식으로 들어온 인자 파싱 후 분으로 통일 메서드
//	static int toMinute(String hm) {
//		String[] input = hm.split(":");
//		int h = Integer.parseInt(input[0]);
//		int m = Integer.parseInt(input[1]);
//		return h*60+m;
//	}
}
