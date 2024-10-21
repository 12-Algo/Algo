import java.util.*;

class Solution {
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    static void marking(int[][] land, int x, int y, boolean[][] visit, List<int[]> posForMark) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visit[y][x] = true;
        posForMark.add(new int[]{x, y});
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i=0;i<4;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || nx >= land[0].length || ny < 0 || ny >= land.length || visit[ny][nx] || land[ny][nx] == 0) {
                    continue;
                }
                visit[ny][nx] = true;
                q.add(new int[]{nx, ny});
                posForMark.add(new int[]{nx, ny});
            }
        }
    }
    
    
    public int solution(int[][] land) {
        boolean[][] visit = new boolean[land.length][land[0].length];
        int[][] markedLand = new int[land.length][land[0].length];
        Map<Integer, Integer> map = new HashMap<>();
        int mark = 1;
        for (int i=0;i<land.length;i++) {
            for (int j=0;j<land[i].length;j++) {
                if (land[i][j] == 1 && !visit[i][j]) {
                    List<int[]> posForMark = new ArrayList<>();
                    marking(land, j, i, visit, posForMark);
                    map.put(mark, posForMark.size());
                    for (int k=0;k<posForMark.size();k++) {
                        int x = posForMark.get(k)[0];
                        int y = posForMark.get(k)[1];
                        markedLand[y][x] = mark;
                    }
                    mark++;
                }
            }
        }
        
        int answer = 0;
        for (int i=0;i<land[0].length;i++) {
            List<Integer> markList = new ArrayList<>();
            boolean[] markVisit = new boolean[mark];
            for (int j=0;j<land.length;j++) {
                if (land[j][i] == 1) {
                    if (markVisit[markedLand[j][i]]) {
                        continue;
                    }
                    markVisit[markedLand[j][i]] = true;
                    markList.add(markedLand[j][i]);
                }
            }
            int sum = 0;
            for (int j=0;j<markList.size();j++) {
                sum += map.get(markList.get(j));
            }
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
}