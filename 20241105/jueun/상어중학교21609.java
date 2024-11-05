import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static final int RAINBOW = 0;
    static final int BLACK = -1;
    static final int NONE = 10;

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    private static final class Group implements Comparable<Group> {
        int r, c; // 기준 블록
        int cnt, rainbowCnt;

        private Group() {
            this.r = 20;
            this.c = 20;
            this.cnt = 0;
            this.rainbowCnt = 0;
        }

        private void updateStandardBlock(int r, int c) {
            if (this.r > r) {
                this.r = r;
                this.c = c;
            }

            if (this.r == r && this.c > c) {
                this.c = c;
            }
        }

        private void update(int block, int r, int c) {
            cnt++;
            if (block == RAINBOW) {
                rainbowCnt++;
                return;
            }

            updateStandardBlock(r, c);
        }

        @Override
        public int compareTo(Group o) {
            if (this.cnt != o.cnt)
                return o.cnt - this.cnt;
            else if (this.rainbowCnt != o.rainbowCnt)
                return o.rainbowCnt - this.rainbowCnt;
            else if (this.r != o.r)
                return o.r - this.r;
            else
             return o.c - this.c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        PriorityQueue<Group> pq = new PriorityQueue<>();

        while (true) {
            visited = new boolean[N][N];

            // 1. 블록 그룹 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (isNormal(map[i][j]) && !visited[i][j]) {
                      
                        Group g = new Group();
                        findGroup(i, j, g, map[i][j], new boolean[N][N]);
                        
                        if(g.cnt >= 2)
                            pq.add(g);
                    }
                }
            }

            // 2. 블록 그룹 선택 및 제거
            if (pq.isEmpty())
                break;

            Group g = pq.poll();
            pq.clear();

            int cnt = deleteGroup(g.r, g.c, 1, map[g.r][g.c], new boolean[N][N]);
            answer += Math.pow(cnt, 2);

            onGravity();
         
            //3. 회전
            rotate();
            
            onGravity();

        }
        
        System.out.println(answer);
    }
    
    private static void rotate() {
        int[][] newMap = new int[N][N];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                newMap[i][j] = map[j][N-1-i];
            }
        }
        
        map = newMap;
    }
    
    private static void onGravity() {
        ArrayDeque<Integer> queue = new ArrayDeque<>(N);
        
        for(int j=0; j<N; j++) {
            queue.clear();
            
            int k = N -1;
            for(int i=N-1; i>=0; i--) {
                if(isNormal(map[i][j]) || map[i][j] == RAINBOW) {
                    queue.add(map[i][j]);
                    map[i][j] = NONE;
                }
                else if(map[i][j] == BLACK){
                    while(!queue.isEmpty()) {
                        map[k][j] = queue.poll();
                        k--;
                    }
                    
                    k = i-1;
                }
            }
            
            while(!queue.isEmpty()) {
                map[k][j] = queue.poll();
                k--;
            }
        }
    }
    

    private static int deleteGroup(int x, int y, int cnt, int color, boolean[][] visited) {
        visited[x][y] = true;
        map[x][y] = NONE;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny])
                continue;

            if (map[nx][ny] == RAINBOW || color == map[nx][ny])
                cnt = deleteGroup(nx, ny, cnt + 1, color, visited);
        }

        return cnt;
    }

    private static void findGroup(int x, int y, Group g, int color, boolean[][] rVisited) {
        visited[x][y] = true;
        rVisited[x][y] = true;

        g.update(map[x][y], x, y);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                continue;

            if (map[nx][ny] == RAINBOW && !rVisited[nx][ny]) {
                findGroup(nx, ny, g, color, rVisited);
            } else if (!visited[nx][ny] && color == map[nx][ny]) {
                findGroup(nx, ny, g, color, rVisited);
            }
        }
    }

    private static boolean isNormal(int block) {

        if (block >= 1 && block <= M)
            return true;
        else
            return false;
    }
}
