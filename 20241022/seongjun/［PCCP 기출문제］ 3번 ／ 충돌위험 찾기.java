import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        
        // 각 로봇별 방문할 point 좌표 저장
        List<int[]>[] pointsPos = new List[routes.length];
        for (int i=0;i<routes.length;i++) {
            pointsPos[i] = new ArrayList<>();
            for (int j=0;j<routes[i].length;j++) {
                pointsPos[i].add(points[routes[i][j]-1]);
            }
        }
        
        // 각 로봇별 이동 경로 저장
        List<int[]>[] path = new List[routes.length];
        for (int i=0;i<pointsPos.length;i++) {
            path[i] = new ArrayList<>();
            for (int j=0;j<pointsPos[i].size()-1;j++) {
                int row = pointsPos[i].get(j)[0];
                int col = pointsPos[i].get(j)[1];
                int nRow = pointsPos[i].get(j+1)[0];
                int nCol = pointsPos[i].get(j+1)[1];
                // 행 우선 이동
                // 절댓값으로 반복문을 돌리려 했으나, 이는 값을 증가시키기만 하므로 안됨.
                if (row <= nRow) {
                    for (int k=row;k<=nRow;k++) {
                        path[i].add(new int[]{k, col});
                    }
                } else {
                    for (int k=row;k>=nRow;k--) {
                        path[i].add(new int[]{k, col});
                    }
                }
                // 열 이동
                int nowRow = path[i].get(path[i].size()-1)[0];
                if (col <= nCol) {
                    for (int k=col+1;k<=nCol;k++) {
                        path[i].add(new int[]{nowRow, k});
                    }
                } else {
                    for (int k=col-1;k>=nCol;k--) {
                        path[i].add(new int[]{nowRow, k});
                    }
                }
                // 마지막 좌표를 지우지 않으면 다음 포인트로 갈 때 좌표가 중복 저장됨.
                path[i].remove(path[i].size()-1);
            }
            path[i].add(pointsPos[i].get(pointsPos[i].size()-1));
        }
        
        // 각 로봇별 경로들을 탐색하면서 충돌위험 확인
        int index = 0;
        int answer = 0;
        boolean breakable = false;
        while (!breakable) {
            boolean[] visit = new boolean[routes.length];
            breakable = true;
            for (int i=0;i<path.length-1;i++) {
                // i번 로봇의 경로가 끝남.
                if (index >= path[i].size()) {
                    continue;
                }
                breakable = false;
                // i번 로봇이 이전의 로봇과 이미 겹쳐있음.
                if (visit[i]) {
                    continue;
                }
                visit[i] = true;
                int count = 0;
                for (int j=i+1;j<path.length;j++) {
                    // j번 로봇의 경로가 끝남.
                    if (index >= path[j].size()) {
                        continue;
                    }
                    breakable = false;
                    if (path[i].get(index)[0] == path[j].get(index)[0] && path[i].get(index)[1] == path[j].get(index)[1]) {
                        count = 1;
                        visit[j] = true;
                    }                    
                }
                
                answer += count;  
            }
            index++;
        }
        
        
        

        return answer;
    }
}