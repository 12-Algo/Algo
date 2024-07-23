import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/***
 * F: 한 눈금 앞으로
 * B: 한 눈금 뒤로
 * L: 왼쪽으로 90도 회전
 * R: 오른쪽으로 90도 회전3
 * FFLF
 * FFRRFF
 * FFFBBBRFFFBBB
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int n = Integer.parseInt(input);

        for (int test_case=0;test_case<n;test_case++) {
            int north = 0;
            int south = 0;
            int east = 0;
            int west = 0;
            int positionX = 0;
            int positionY = 0;
            int dir = 0;
            String cmd = br.readLine();
            for (int i=0;i<cmd.length();i++) {
                char command = cmd.charAt(i);
                switch (command) {
                    case 'F':
                        positionX += dx[dir];
                        positionY += dy[dir];
                        break;
                    case 'B':
                        positionX -= dx[dir];
                        positionY -= dy[dir];
                        break;
                    case 'L':
                        dir = (dir + 3) % 4; // 왼쪽으로 90도 회전 (시계 반대 방향)
                        break;
                    case 'R':
                        dir = (dir + 1) % 4; // 오른쪽으로 90도 회전 (시계 방향)
                        break;
                }


                north = Math.max(north, positionY);
                south = Math.min(south, positionY);
                east = Math.max(east, positionX);
                west = Math.min(west, positionX);
            }
            System.out.println((north - south) * (east - west));
        }
    }
}