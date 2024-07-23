import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		char[] directions = {'U', 'R', 'D', 'L'};
		
		for (int tc=0;tc<T;tc++) {
				String move = br.readLine();
				int x = 0;
				int y = 0;
				int maxX = 0;
				int maxY = 0;
				int minX = 0;
				int minY = 0;
				int dir = 0;
				
				for (int i=0;i<move.length();i++) {
					char direction = directions[dir];
					if (move.charAt(i) == 'R') {
						dir++;
						if (dir > 3) {
							dir %= 4;
						}
					} else if (move.charAt(i) == 'L') {
						dir--;
						if (dir < 0) {
							dir+=4;
						}
					} else if (move.charAt(i) == 'F') {
						if (direction == 'U') {
							y++;
							maxY = Math.max(maxY, y);
						} else if (direction == 'R') {
							x++;
							maxX = Math.max(maxX, x);
						} else if (direction == 'D') {
							y--;
							minY = Math.min(minY, y);
						} else if (direction == 'L') {
							x--;
							minX = Math.min(minX,  x);
						}
					} else if (move.charAt(i) == 'B') {
						if (direction == 'U') {
							y--;
							minY = Math.min(minY, y);
						} else if (direction == 'R') {
							x--;
							minX = Math.min(minX, x);
						} else if (direction == 'D') {
							y++;
							maxY = Math.max(maxY, y);
						} else if (direction == 'L') {
							x++;
							maxX = Math.max(maxX,  x);
						}
					}
				}
				
				int answer = (maxX - minX) * (maxY - minY);
				System.out.println(answer);
		}
	}

}
