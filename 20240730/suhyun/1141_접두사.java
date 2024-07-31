import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] words = new String[N];
		
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
    // 길이 순으로 정렬
		Arrays.sort(words, (o1, o2) -> {
			return o1.length() - o2.length();
		});
		
		int cnt = 0;
		
    // 지금 문자열이 뒤의 문자열의 접두사인지 확인하여 카운트
		for (int i = 0; i < N; i++) {
			boolean isContained = false;
			int l = words[i].length();
			
			for (int j = i+1; j < N; j++) {
				if (words[j].substring(0, l).equals(words[i])) {
					isContained = true;
				}
			}
			
			if (!isContained) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
