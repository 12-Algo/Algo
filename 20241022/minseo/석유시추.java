import java.util.*;

class Solution {
    static int N, M, land[][], oilSum[];
    static boolean[][] visited;
    
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        this.land = land;
        
        oilSum = new int[M]; // 가로 길이만큼
        visited = new boolean[N][M];
        
        seek();
        
        System.out.println(Arrays.toString(oilSum));
        int max = 0;
        for(int i = 0;  i < M; i++){
            max = Math.max(max, oilSum[i]);
        }
        int answer = 0;
        return max;
    }
    
    void seek(){
        Queue<int[]> queue = new ArrayDeque<>();
        boolean columnVisited[];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(land[i][j] == 1 && !visited[i][j]){
                    columnVisited = new boolean[M];
                    // 최초 발견
                    
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                    columnVisited[j] = true; // 해당 열에 있다
                    int cnt = 1;
                    
                    // 일단 탐색하기
                    while(!queue.isEmpty()){
                        int[] node = queue.poll();
                        
                        for(int k = 0; k < 4; k++){
                            int nx = node[0] + dx[k];
                            int ny = node[1] + dy[k];
                            
                            if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
                                continue;
                            }
                            
                            if(land[nx][ny] == 1 && !visited[nx][ny]){
                                queue.add(new int[]{nx, ny});
                                visited[nx][ny] = true;
                                columnVisited[ny] = true; // 해당 열에 있다.
                                cnt++;
                            }
                        }
                    }
                    
                    for(int k = 0; k < M; k++){
                        if(columnVisited[k]){
                            // 해당 열에 석유가 있었다면
                            oilSum[k] += cnt; // 석유량 만큼 더해주기!
                        }
                    }
                }
            }
        }
    }
}