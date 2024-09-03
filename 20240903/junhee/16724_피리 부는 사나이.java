import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724 {
    static int n, m;
    static int[][] map;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int answer = 0;

    // 방향 문자를 숫자로 변환
    static int find(char c) {
        char[] c_list = { 'D', 'R', 'U', 'L' };
        for (int i = 0; i < 4; i++) {
            if (c_list[i] == c)
                return i;
        }
        return -1;
    }

    static void dfs(int x, int y, int num) {
        // 방향 저장
        int dir = map[x][y];
        // 시작점 초기화
        map[x][y] = num;
        int nx = x + dx[dir]; 
        int ny = y + dy[dir];
        // 자기 자신을 찾았으면(Cycle이면)
        if (map[nx][ny] == num) {
            // 정답 갯수 증가
            answer++;
            return;
        }
        // 아직 방문하지 않았으면 다음 방향으로
        if(0 <= map[nx][ny] && map[nx][ny] < 4)
            dfs(nx, ny, num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String temp = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = find(temp.charAt(j));
            }
        }
        // 0 ~ 3은 방향을 가리키고 있으니 5부터 시작
        int num = 5;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (0 <= map[i][j] && map[i][j] < 4) {
                    // dfs 돌때마다 num 증가
                    dfs(i, j, num);
                    num++;
                }
            }
        }
        System.out.println(answer);
    }
}
