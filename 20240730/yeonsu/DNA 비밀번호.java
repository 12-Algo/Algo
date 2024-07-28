
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer token = new StringTokenizer(input);
		
		int s = Integer.parseInt(token.nextToken());
		int p = Integer.parseInt(token.nextToken());
		String str = br.readLine();
		input = br.readLine();
		token = new StringTokenizer(input);
		int a = Integer.parseInt(token.nextToken());
		int c = Integer.parseInt(token.nextToken());
		int g = Integer.parseInt(token.nextToken());
		int t = Integer.parseInt(token.nextToken());

		int left = 0;
		int right = p-1;
		for (int i=0;i<p;i++) {
			if (str.charAt(i) == 'A') a--;
			else if (str.charAt(i) == 'C') c--;
			else if (str.charAt(i) == 'G') g--;
			else if (str.charAt(i) == 'T') t--;
		}
		int answer = 0;
		while(right < s) {
			if (a < 1 && c < 1 && g < 1 && t < 1) {
				answer++;
			}
			if (str.charAt(left) == 'A') a++;
			else if (str.charAt(left) == 'C') c++;
			else if (str.charAt(left) == 'G') g++;
			else if (str.charAt(left) == 'T') t++;
			if (right+1 >= s) break;
			if (str.charAt(right+1) == 'A') a--;
			else if (str.charAt(right+1) == 'C') c--;
			else if (str.charAt(right+1) == 'G') g--;
			else if (str.charAt(right+1) == 'T') t--;
			left++;
			right++;
		}
		System.out.println(answer);
	}
}
