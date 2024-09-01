package algorithm;

import java.io.*;
import java.util.*;

public class Slope {

    static int n;
    static int m;
    static int[][] boards;
    static int[][] isVisited;
//    상우하좌
    static int[] dxs = {-1, 0, 1, 0};
    static int[] dys = {0, 1, 0, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        boards = new int[m][n];
        isVisited = new int[m][n];
        
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<n;j++) {
                boards[i][j] = Integer.parseInt(st.nextToken());
                isVisited[i][j] = -1;
            }
        }
        System.out.println(dfs(0, 0));
        
    }
    
    static int dfs(int x, int y){
        
        if(x==m-1 && y==n-1) {
            return 1;
        }
//        이미 방문한 적 있는 경우 
        if(isVisited[x][y] != -1) {
            return isVisited[x][y];
        }
//        방문
        isVisited[x][y] = 0;
        for(int i=0;i<4;i++) {
            int nx = x+dxs[i];
            int ny = y+dys[i];
            if(inRange(nx, ny, m, n) && boards[x][y] > boards[nx][ny]) {
                isVisited[x][y] += dfs(nx, ny);
            }
        }
        return isVisited[x][y];
    }
    
    static boolean inRange(int x, int y, int m, int n) {
        return x>=0 && x<m && y>=0 && y<n;
    }
}