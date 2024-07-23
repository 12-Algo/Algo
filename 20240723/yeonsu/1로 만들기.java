import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
/**
 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
 */
public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int n = Integer.parseInt(input);
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(n);
		int count = 0;
		while (!queue.isEmpty()) {
			int length = queue.size();
			if (length == 0) break;
			boolean flag = false;
			for (int i=0;i<length;i++) {
				int k = queue.remove();
				if (k == 1) {
					flag = true;
					break;
				}
				if (k >= 3 && k % 3 == 0) queue.add(k / 3);
				if (k >= 2 && k % 2 == 0) queue.add(k / 2);
				if (k > 1) queue.add(k-1);
			}
			if (flag == true) break;
			count++;
		}
		System.out.println(count);
	}

}