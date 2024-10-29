import java.util.*;

class Solution {
    private static int[][] board;
    private static int N, M;
    private static boolean[][] check;
    private static int[] oil; // x좌표의 석유 합
    private static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    private static void bfs(int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] isExist = new boolean[M];  // 석유의 x좌표 저장
        q.offer(new int[] {i, j});
        check[i][j] = true;
        isExist[j] = true;
        int cnt = 1;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            
            for (int[] d : delta) {
                int ny = now[0] + d[0];
                int nx = now[1] + d[1];
                
                if (ny >= 0 && ny < N && nx >= 0 && nx < M && board[ny][nx] == 1 && !check[ny][nx]) {
                    check[ny][nx] = true;
                    q.offer(new int[] {ny, nx});
                    cnt++;
                    isExist[nx] = true;
                }
            }
        }
        
        for (int k = 0; k < M; k++) {
            if (isExist[k]) {
                oil[k] += cnt;
            }
        }
    }
    
    public int solution(int[][] land) {
        board = land;
        N = land.length;
        M = land[0].length;
        check = new boolean[N][M];
        oil = new int[M];
        
        for (int i = 0; i< N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1 && !check[i][j]) {
                    bfs(i, j);
                }
            }
        }
        
        int ans = 0;
        
        for (int i = 0; i < M; i++) {
            ans = Math.max(ans, oil[i]);
        }
        
        return ans;
    }
}