import java.util.*;

class Solution {
    int N, M;
    int[][] land;
    
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        this.land = land;
        N = land.length;
        M = land[0].length;
        
        int num = 2;
        Map<Integer, Integer> oil = new HashMap<>();
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(land[i][j] == 1) {
                    oil.put(num, dfs(i, j, num, 1));
                    num++;
                }
            }
        }
        
        int max = 0;
        
        for(int j=0; j<M; j++) {
        	int sum = 0;
        	Set<Integer> set = new HashSet<>();
        	
        	for(int i=0; i<N; i++) {
        		if(land[i][j] != 0 && ! set.contains(land[i][j])) {
        			set.add(land[i][j]);
        			sum += oil.get(land[i][j]);
        		}
        	}
        	
        	max = Math.max(max, sum);
        }
        
        return max;
    }
    
    private int dfs(int x, int y, int num, int sum) {
        land[x][y] = num;
        
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx < 0 || nx >= N || ny < 0 || ny >= M)
                continue;
            else if(land[nx][ny] == 0 || land[nx][ny] == num)
                continue;
            
            sum = dfs(nx, ny, num, sum+1);
        }
        
        return sum;
    }
}
