import java.util.Queue;
import java.util.LinkedList;

class Solution {
    int m;
    int n;
    int[] dx = new int[] {0,1,0,-1};
    int[] dy = new int[] {1,0,-1,0};
    int[][] map = new int[n][m];
    
    public int[] bfs(int s_x, int s_y) {
        int min_y = s_y, max_y = s_y;
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        map[s_x][s_y] = -1;
        q.add(new int[] {s_x, s_y});
        
        while(!q.isEmpty()) {
            int[] coord = q.peek();
            int x = coord[0];
            int y = coord[1];
            cnt++;
            q.remove();
            
            if(min_y > y) min_y = y;
            else if(max_y < y) max_y = y;
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if(0 <= nx && nx < n && 0 <= ny && ny < m){
                    if(map[nx][ny] == 1) {
                        map[nx][ny] = -1;
                        q.add(new int[] {nx, ny});
                    }
                }
            }
        }
        return new int[] {min_y, max_y, cnt};
    }
    
    public int solution(int[][] land) {
        int answer = 0;
        n = land.length;
        m = land[0].length;
        map = land;
        int[] oils = new int[m];
        for(int i = 0; i < land.length; i++) {
            for(int j = 0; j < land[0].length; j++) {
                if(map[i][j] == 1) {
                    int[] temp = bfs(i, j);
                    for(int k = temp[0]; k <= temp[1]; k++) {
                        oils[k] += temp[2];
                    }
                }
            }
        }
        for(int i = 0; i < oils.length; i++) {
            if(answer < oils[i]) answer = oils[i];
        }
        return answer;
    }
}