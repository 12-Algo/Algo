import java.util.*;

class Solution {
    static int[][] land;
    static int N, M, NM;
    static int[] amountPerId;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[] visited;
    static List<Integer>[] columnIds;

    public int solution(int[][] inputLand) {
        land = inputLand;
        N = land.length;
        M = land[0].length;
        NM = N * M;
        amountPerId = new int[NM];
        visited = new boolean[NM];
        columnIds = new List[M];
        for (int i = 0; i < M; i++) {
            columnIds[i] = new ArrayList<>();
        }

        int id = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (land[i][j] == 1) {
                    amountPerId[id] = findGroupOfOil(i, j, id);
                    id++;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int groupId : columnIds[i]) {
                sum += amountPerId[groupId];
            }
            answer = Math.max(answer, sum);
        }
        return answer;
    }

    static int findGroupOfOil(int startY, int startX, int id) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startY, startX});
        land[startY][startX] = id;
        int amount = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0], x = current[1];
            amount++;

            if (!columnIds[x].contains(id)) {
                columnIds[x].add(id);
            }

            for (int dir = 0; dir < 4; dir++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                if (isNext(ny, nx) && land[ny][nx] == 1) {
                    land[ny][nx] = id;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }
        return amount;
    }

    static boolean isNext(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}