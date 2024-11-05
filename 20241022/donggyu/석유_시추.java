import java.io.*;
import java.util.*;
class Solution {
    static int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[] oilBlock;
    
    public int solution(int[][] land) {
        int answer = 0;
        boolean [][] visited = new boolean[land.length][land[0].length];
        oilBlock = new int[land[0].length];
		for(int i=0;i<land.length;i++) {
			for(int j=0;j<land[0].length;j++) {
                if(land[i][j] != 0 && !visited[i][j]){
                    bfs(i, j, land, visited);				
                }
			}
		}
		
        for(int i=0;i<land[0].length;i++) {
        	answer = Math.max(answer, oilBlock[i]);
        }
        
        System.out.println(answer);
        return answer;
    }
    
    static void bfs(int x, int y, int[][] land, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{x, y});
        visited[x][y] = true;
		int size = 1;
		
		Set<Integer> set = new HashSet<>();
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int nowX = now[0], nowY = now[1];
			set.add(nowY);
			
			for(int[] d : deltas) {
				int nx = nowX+d[0];
				int ny = nowY+d[1];
				if(nx<0 || ny<0 || nx>=land.length||ny>=land[0].length) continue;
				if(visited[nx][ny]) continue;
                if(land[nx][ny]==0) continue;
				q.add(new int[] {nx, ny});
                visited[nx][ny] = true;
                size++;
			}
		}
		
		for (int oilY : set) {
			oilBlock[oilY] += size;
		}
	}
}