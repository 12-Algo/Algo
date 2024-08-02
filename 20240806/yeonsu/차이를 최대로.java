import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	private static boolean[] visit;    //방문 체크
	private static int n;            //숫자 개수
	private static int[] nList;    //숫자를 담는 배열
	
	public static void main(String[] args) throws IOException {
        //입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		n = Integer.parseInt(input);
		visit = new boolean[n];
		
		input = br.readLine();
		StringTokenizer token = new StringTokenizer(input);
		nList = new int[n];
		for (int i=0;i<n;i++) {
			nList[i] = Integer.parseInt(token.nextToken());
		}
        
        //함수 호출
		int answer = 0;
		for (int i=0;i<n;i++) {
			visit[i] = true;
			answer = Math.max(answer, dfs(i));
			visit[i] = false;
		}
        
        //출력
		System.out.println(answer);
	}
    
	//dfs로 숫자 배열 탐색.
	private static int dfs(int index) {
		int sum = 0;
		for (int i=0;i<n;i++) {
			if (!visit[i]) {
				visit[i] = true;
				sum = Math.max(sum, Math.abs(nList[index] - nList[i]) + dfs(i));
				visit[i] = false;
			}
		}
		return sum;
	}
}
