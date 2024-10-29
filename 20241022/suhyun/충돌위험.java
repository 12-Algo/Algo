import java.util.*;

class Solution {
    private static int N, res;
    private static int[][] robots;
    private static int[] targetIdx;
    
    // 좌표에 몇 개의 로봇 있는지 확인
    private static void checkDanger() {
        int[][] check = new int[101][101];
        
        for (int[] robot : robots) {
            if (robot[0] != -1 && robot[1] != -1) {
                check[robot[0]][robot[1]] += 1;
            }
        }
        
        int cnt = 0;
        
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (check[i][j] > 1) {
                    cnt++;
                }
            }
        }
        
        res += cnt;
    }
    
    // 모든 로봇이 배달 끝났는지(-1) 확인
    private static boolean isEnd() {
        for (int idx : targetIdx) {
            if (idx != -1) {
                return false;
            }
        }
        
        return true;
    }
    
    private static void delivery(int[][] points, int[][] routes) {
        while(!isEnd()) {
            for (int i = 0; i < N; i++) {
                int[] robot = robots[i];

                if (targetIdx[i] == -1) {
                    continue;
                }

                int tIdx = routes[i][targetIdx[i]] - 1;
                int[] target = points[tIdx];

                if (robot[0] == target[0] && robot[1] == target[1]) {
                  // 운송이 끝나면 로봇 좌표랑 타겟 index를 모두 -1로 변경
                    if (targetIdx[i] == routes[i].length-1) {
                        targetIdx[i] = -1;
                        robot[0] = -1;
                        robot[1] = -1;
                    } else {
                        targetIdx[i] += 1;
                        tIdx = routes[i][targetIdx[i]] - 1;
                        target = points[tIdx];
                    }
                }

                if (robot[0] != target[0]) {
                    if (robot[0] > target[0]) {
                        robot[0] -= 1;
                    } else {
                        robot[0] += 1;
                    }
                } else {
                    if (robot[1] > target[1]) {
                        robot[1] -= 1;
                    } else {
                        robot[1] += 1;
                    }
                }
            }
            
            checkDanger();
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        N = routes.length;
        res = 0;
        robots = new int[N][2];
        targetIdx = new int[N];
        
        // 로봇 위치, 다음에 갈 포인트 인덱스 세팅
        for (int i = 0; i < N; i++) {
            int[] p = points[routes[i][0]-1];
            robots[i][0] = p[0];
            robots[i][1] = p[1];
            
            targetIdx[i] = 1;
        }
        
        checkDanger();
        delivery(points, routes);        
        
        return res;
    }
}