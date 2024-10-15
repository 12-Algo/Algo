package Algo20241015;

import java.util.ArrayDeque;
import java.util.Queue;

class 거리두기_확인하기 {
    
	static int[][] D = {
			{-1,0},
			{1,0},
			{0,-1},
			{0,1},
	};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for(int i=0; i<places.length; i++){
            answer[i] = Cal(places[i]);
        }
        return answer;
    }
    
    // P일 때 P인 위치를 기준으로 bfs를 돎
    public int Cal(String[] arr) {
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length(); j++){
                if(arr[i].charAt(j) == 'P' && !bfs(arr, i, j)) { 
                    return 0; // 거리두기 실패라면 0
                }
            }
        }
        return 1;// 거리두기 성공이라면 1
    }
    
    // bfs탐색으로 거리두기 계산
    public boolean bfs(String[] map, int x, int y) {
        Queue<int[]> q = new ArrayDeque();
        boolean[][] visited = new boolean[map.length][map.length];
        q.add(new int[] {x,y});
        visited[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int cur_x = cur[0];
            int cur_y = cur[1];
            
            for(int i = 0; i < 4; i++) {
                int nx = cur_x + D[i][0];
                int ny = cur_y + D[i][1];
                int dep = Math.abs(x - nx) + Math.abs(y - ny);// 기준이 되는 x,y기준으로 nx,ny와 거리 계산
                
                if(nx < 0 || ny < 0 || nx >= map.length || ny >= map.length) continue;// 범위 벗어나면 넘김
                if(visited[nx][ny] || dep > 2) continue;// 방문했거나 거리가 2초과면 넘김
                
                visited[nx][ny] = true;
                if(map[nx].charAt(ny) == 'X') continue;
                else if(map[nx].charAt(ny) == 'P') return false;// 거리두기 실패
                else q.add(new int[] {nx,ny});
            }
        }
        return true;// 거리두기 성공
    }
}
