package algo;

import java.io.*;
import java.util.*;

public class Inequality {

	static int n;
	static boolean[] isVisited;
	static String[] arr;
	static List<String> result = new ArrayList<>();
	
	static void dfs(String num, int idx) {
		// 주어진 부등호의 모든 조건을 완성하면 리턴
		if(idx == n+1) {
			// 부등호가 성립되는 모든 경우의 수가 등록됨
			result.add(num);
			return;
		}

		for(int j = 0 ; j < 10; j++) {
			if(isVisited[j]) {
				continue;						
			}
			// Character.getNumericValue(num.charAt(idx - 1))(현재까지 경로중 마지막숫자)
			// j , arr[idx-1] 다음 숫자와 부등호
			if(idx == 0 || ckeck(Character.getNumericValue(num.charAt(idx - 1)), j , arr[idx-1])) {
				isVisited[j] = true;
				//num에 문자열 붙임 [depth 1증가]
				dfs(num+j, idx+1);
				isVisited[j] = false;				
			}
		}
		
	}
	
	static boolean ckeck(int a, int b, String c) {
		// 현재 사용하는 숫자와 j번째 숫자와 비교하여, 부등호가 성립되면 true
		if (c.equals(">")) {
			return a>b;
		} else if (c.equals("<")){
			return a<b;
		}
//		입력값이 정상이라면 도달하지 않음
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = br.readLine().split(" ");

		isVisited = new boolean[10];
		
		//결과문자열, 시작 depth
		dfs("",0);

		//최대값
		System.out.print(result.get(result.size() - 1) + "\n");
		//최소값
		System.out.print(result.get(0));	
	}
}
