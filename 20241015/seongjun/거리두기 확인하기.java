import java.util.*;
import java.io.*;

class Solution {
    
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static boolean isDistanceOK;
    
    static boolean checkDistance(int x, int y, String[] place) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y, 0});
        boolean[][] visit = new boolean[place.length][place[0].length()];
        visit[y][x] = true;
        
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i=0;i<4;i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if (nx < 0 || nx >= place[0].length() || ny < 0 || ny >= place.length || visit[ny][nx] || place[ny].charAt(nx) == 'X') {
                    continue;
                }
                if (place[ny].charAt(nx) == 'P') {
                    // 맨해튼 거리 2이하이면 종료
                    if (now[2]+1 <= 2) {
                        return false;
                    }
                }
                visit[ny][nx] = true;
                q.add(new int[]{nx, ny, now[2]+1});
            }
        }
        return true;
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        // places를 순회하며 응시자의 위치에서 다른 응시자 위치 탐색 (BFS)
        // 만약 맨해튼 거리가 2 이하라면 해당 대기실은 탐색 종료(0).
        // 모든 응시자의 맨해튼 거리가 2 이하인 경우가 없다면 1
        for (int i=0;i<places.length;i++) {
            for (int j=0;j<places[i].length;j++) {
                isDistanceOK = true;
                for (int k=0;k<places[i][j].length();k++) {
                    if (places[i][j].charAt(k) == 'P') {
                        // 한 명이라도 거리두기 안지키면 종료
                        if (!checkDistance(k, j, places[i])) {
                            isDistanceOK = false;
                            break;
                        }
                    }
                }
                // 거리두기 안지키면 종료
                if (!isDistanceOK) {
                    break;
                }
            }
            if (isDistanceOK) {
                answer[i] = 1;
            }
        }

        return answer;
    }
}