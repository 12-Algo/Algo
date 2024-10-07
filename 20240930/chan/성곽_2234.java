package Algo20240930;

import java.io.*;
import java.util.*;

public class 성곽_2234 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int Size;
    static int SizeMax = 0; // 방의 최대 사이즈
    static int SizeMaxMax = 0; // 벽을 허물었을 때 진짜 최대 사이즈
    static int[][] map;
    static boolean[][] visited;
    static int[][] D = {
            {0, -1},    // 좌    서
            {-1, 0},    // 상    북
            {0, 1},     // 우    동
            {1, 0},     // 하    남
    };

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Size = 0;

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력 완료

        int result = startXY(); // 방의 갯수
        System.out.println(result); // 방의 갯수 출력
        System.out.println(SizeMax); // 방의 최대 넓이 출력

        removeWall(); // 벽을 제거했을 때 최대 방의 크기
        System.out.println(SizeMaxMax); // 벽을 허물었을 때의 최대 방 크기 출력
    }

    static int startXY() {
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    ++cnt;
                    Size = bfs(new int[] { i, j }); // 방문하지 않았다면 bfs발동!
                    SizeMax = Math.max(Size, SizeMax);
                }
            }
        }
        return cnt;
    }

    static int bfs(int[] start) {
        int size = 0;
        Queue<int[]> q = new ArrayDeque<>();
        visited[start[0]][start[1]] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            ++size; // q에서 꺼낸 만큼 넓이
            int x = cur[0];
            int y = cur[1];
            int wall = map[x][y];

            for (int i = 0; i < 4; i++) { // 서 -> 북 -> 동 -> 남 순서로 탐색
                if ((wall & (1 << i)) == 0) { // 벽이 없으면 탐색 가능
                    int nx = x + D[i][0];
                    int ny = y + D[i][1];

                    if (nx >= 0 && nx < M && ny >= 0 && ny < N && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        q.add(new int[] { nx, ny });
                    }
                }
            }
        }
        return size;
    }

    static void removeWall() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int wall = map[i][j];
                
                for (int d = 0; d < 4; d++) {// 현재 칸에서 각 방향에 벽이 있는지 확인
                    if ((wall & (1 << d)) != 0) { // 벽이 있다면
                        int nx = i + D[d][0];
                        int ny = j + D[d][1];

                        if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                            for (int k = 0; k < M; k++) Arrays.fill(visited[k], false);// 방문처리 배열 초기화
                            int curSize = bfs(new int[] { i, j }); // 현재 방 크기
                            // 만약 ㄷ자 처럼 같은방의 벽을 허물어도 두번째 bfs에서 방문처리로 인해 걸러짐
                            int nextSize = bfs(new int[] { nx, ny });  // 벽 너머 방 크기
                            SizeMaxMax = Math.max(SizeMaxMax, curSize + nextSize);
                        }
                    }
                }
            }
        }
    }
}