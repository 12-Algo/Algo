import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class chan1141 {
	static int N;
	static String str[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.valueOf(br.readLine());
		str = new String[N];
		for(int i=0; i<N; i++) 
			str[i] = br.readLine();
		
		HashSet<String> set = new HashSet<>();
		
		for(int i=0; i<N; i++) {
			String origin = str[i];
			int cnt = 0;//접두사가 아니면 ++
			for(int j=0; j<N; j++) {
				if(i == j) continue;//같은 배열을 가르키면 패스 continue
				
				String compare = str[j];//비교할 문자열 생성
				if(origin.length() <= compare.length()) {//origin이 compare보다 길이가 작거나 같으면
					if(origin.equals(compare)) //origin이 compare과 같다면 cnt증가
						cnt++;
					else 
						for(int k=0; k<origin.length(); k++)//origin과 compare문자열을 비교함
							if(origin.charAt(k) != compare.charAt(k)){//첫글자 부터 비교하다가 다르면 break 다르다면 #접두사가 아니기 때문
								cnt++;
								break;
							}
				}else cnt++;//origin의 길이가 compare보다 크다면 접두사가 아니기 때문에 cnt증가
			}
			if(cnt == N-1) set.add(origin);//origin이 자기자신을 제외하고 N-1의 갯수의 문자열과 비교하여 cnt가 자기자신을 제외한 값(N-1)이라면 중복을 제외하기 위해 set에 추가
		}
		System.out.println(set.size());
	}
}
