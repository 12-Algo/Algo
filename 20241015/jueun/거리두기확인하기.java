import java.util.*;

class Solution {
    
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int i=0; i<5; i++) {
            answer[i] = check(places[i]);
        }
        
        return answer;
    }
    
    private int check(String[] place) {
        
        
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                if(place[i].charAt(j) == 'P' && !bfs(i, j, place)) {
                    return 0;
                }
            }
        }
        
        return 1;
    }
    
    private boolean bfs(int x, int y, String[] place) {
        int[][] dist = new int[5][5];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {x, y});
        dist[x][y] = 1;
        
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            x = now[0];
            y = now[1];
            
            if(dist[x][y] == 3)
                break;
            
            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
                    continue;
                if(place[nx].charAt(ny) == 'X' || dist[nx][ny] != 0)
                    continue;
                if(place[nx].charAt(ny) == 'P')
                    return false;
                
                dist[nx][ny] = dist[x][y] + 1;
                queue.add(new int[] {nx, ny});
            }
        }
        
        return true;
    }
}
