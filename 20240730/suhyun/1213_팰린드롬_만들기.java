import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] count = new int[26];

    // 홀수 개인 알파벳이 1개이거나 없어야 펠린드롬 가능
    // 아스키코드를 이용해 알파벳 카운트를 배열에 저장		
		for (int i = 0; i < input.length(); i++) {
			count[input.charAt(i)-'A']++;
		}
		
		int oddCnt = 0;
		int oddIdx = -1;
		
    // 홀수 개인 알파벳 확인
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0 && count[i]%2 != 0) {
				oddCnt++;
				oddIdx = i;
			}
		}
		
		if (oddCnt > 1) {
			System.out.println("I'm Sorry Hansoo");
		} else {
			StringBuilder res = new StringBuilder();
			
      // 팰린드롬을 절반으로 나눴을 때의 앞 부분 문자열 생성
			for (int i = 0; i < 26; i++) {
				for (int j = 0; j < count[i]/2; j++) {
					res.append((char) (i+'A'));
				}
			}
			
			String temp = res.toString();
			
      // 가운데 문자열 있으면 붙이기
			if (oddIdx != -1) {
				res.append((char) (oddIdx+'A'));
			}
			
      // 뒷 부분 마저 생성
			for (int i = temp.length()-1; i >= 0; i--) {
				res.append(temp.charAt(i));
			}
			
			System.out.println(res.toString());
		}
		
	}
}
