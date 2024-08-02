import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	private static int N;    //입력값 크기
	private static boolean[] visit = new boolean[10];    //숫자 방문 체크
	private static String sign;    //부등호 저장 문자열
	private static String maxAnswer = "0000000000";    //최댓값 초기화
	private static String minAnswer = "9999999999";    //최솟값 초기화
    
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		N = Integer.parseInt(input);
        
        //부등호 문자열로 저장
		sign = in.readLine().replaceAll(" ", "");
        
        //함수 호출
		for (int i=0;i<10;i++) {
			visit[i] = true;
			dfs(0, i, Integer.toString(i));
			visit[i] = false;
		}
        
        //출력
		System.out.println(maxAnswer);
		System.out.println(minAnswer);
	}

	private static void dfs(int index, int prev, String result) {
        //n만큼 돌고 종료 result와 최솟값 최댓값 비교 후 저장
		if (index == N) {
			if (result.compareTo(maxAnswer) > 0) {
				maxAnswer = result;
			}
			if (result.compareTo(minAnswer) < 0) {
				minAnswer = result;
			}
			return;
		}
    
        //0부터 9까지 검색
		for (int i=0;i<10;i++) {
			//방문하지 않았고, 전 숫자와 부등호를 조합해 적합한 숫자를 찾는다
			if (!visit[i] && ((sign.charAt(index) == '<' && i > prev) || (sign.charAt(index) == '>' && i < prev))) {
				visit[i] = true;
				dfs(index + 1, i, result.concat(Integer.toString(i)));
				visit[i] = false;
			}
		}
	}
	
}
