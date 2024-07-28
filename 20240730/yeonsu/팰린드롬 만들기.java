import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String failed_massage = "I'm Sorry Hansoo";
		int[] name = new int[26];
		
		String input = br.readLine();
		for (int i=0;i < input.length();i++) {
			name[input.charAt(i) - 'A']++;
		}
		StringBuilder sb = new StringBuilder();
		//문자열의 길이가 홀수일 때
		int cnt = 0;
		if (input.length() % 2 == 1) {
			//홀수개인 문자가 하나가 아니라면 실패
			int holsu = 0;
			for (int i=0;i < 26;i++) {
				if (name[i] % 2 == 1) {
					cnt++;
					holsu = i;
				}
			}
			
			if (cnt != 1) {
				sb.append(failed_massage);
			} else {
				for (int i=0;i<26;i++) {
					while (name[i] > 1) {
						sb.append((char)(i + 'A'));
						name[i] -= 2;
					}
				}
				StringBuilder reversed = new StringBuilder(sb).reverse();
				sb.append((char)(holsu + 'A'));
				sb.append(reversed);
			}
			
		}
		//문자열의 길이가 짝수일 때
		else {
			boolean flag = true;
			for (int i=0;i < 26;i++) {
				//홀수개인 문자가 하나라도 있으면 실패
				if (name[i] % 2 == 1) {
					flag = false;
					break;
				}
			}
			
			if (!flag) {
				sb.append(failed_massage);
			} else {
				for (int i=0;i<26;i++) {
					while (name[i] > 1) {
						sb.append((char)(i + 'A'));
						name[i] -= 2;
					}
				}
				StringBuilder reversed = new StringBuilder(sb).reverse();
				sb.append(reversed);
			}
		}
		System.out.println(sb);	
	}	
}