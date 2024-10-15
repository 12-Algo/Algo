import java.util.*;
import java.io.*;

class Solution {
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int N = 5;
    private static boolean[][] visited;
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        visited = new boolean[N][N];
        Arrays.fill(answer, 1);
        for (int k=0;k<5;k++) {
            out:
            for (int i=0;i<5;i++) {
                for (int j=0;j<5;j++) {
                    if (places[k][i].charAt(j) == 'P') {
                        visited[i][j] = true;
                        boolean flag = isStayAway(i, j, places[k], 0);
                        visited[i][j] = false;
                        if (!flag) {
                            answer[k] = 0;
                            break out;
                        }
                    }
                }
            }
        }
        return answer;
    }
    
    private static boolean isStayAway(int y, int x, String[] place, int depth) {
        if (depth >= 3) return true;
        
        for (int dir=0;dir<4;dir++) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (isMovable(ny, nx) && place[ny].charAt(nx) != 'X') {
                if (depth + 1 <= 2 && place[ny].charAt(nx) == 'P') return false;
                else {
                    visited[ny][nx] = true;
                    boolean flag = isStayAway(ny, nx, place, depth + 1);
                    visited[ny][nx] = false;
                    if (!flag) return false;
                }
            }
        }
        
        return true;
    }
    
    private static boolean isMovable(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N && !visited[y][x];
    }
}

//거리두기를 지키고 있는 지 아닌지를 각 대기실마다 확인하기

//대기실의 행과 열의 길이는 각각 5. -> 25 * 25 => 625
//완전탐색 문제.
