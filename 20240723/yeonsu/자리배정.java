import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
/**
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		StringTokenizer token = new StringTokenizer(input);
		int c1 = Integer.parseInt(token.nextToken());
		int r1 = Integer.parseInt(token.nextToken());
		int c0 = 1, r0 = 1;
		int wait = Integer.parseInt(br.readLine());
		int dir = 0;
		int x = 1, y = 1;
		int n = c1, m = r1;
		int answer = 0;
		boolean flag = false;
		for (int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				wait--;
				if (wait == 0) {
					flag = true;
					break;
				}
				x += dx[dir];
				y += dy[dir];
				if (dir == 0 && y == r1) {
					c0++;
					dir = (dir + 1) % 4;
				}
				else if (dir == 1 && x == c1) {
					r1--;
					dir = (dir + 1) % 4;
				}
				else if (dir == 2 && y == r0) {
					c1--;
					dir = (dir + 1) % 4;
				}
				else if (dir == 3 && x == c0) {
					r0++;
					dir = (dir + 1) % 4;
				}
			}
			if (flag == true) break;
		}
		if (flag == false) System.out.println(0);
		else System.out.println(x + " " + y);
	}

}