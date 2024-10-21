import java.util.*;

class Solution {
    int N, M, K;
    int[][] map = new int[101][101];
    int[][] points;
    int[][] routes;
    List<Robot> list;
    
    private final class Robot {
        int num;
        int x;
        int y;
        int to;
        
        public Robot(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.to = 0;
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        N = points.length; //포인트의 개수
        M = routes.length; //로봇의 개수
        K = routes[0].length; //경로의 개수
        
        list = new ArrayList<>();
        this.points = points;
        this.routes = routes;
        
        for(int i=0; i<M; i++) {
            int p = routes[i][0] - 1; //시작 포인트 번호 (0번인덱스 고려)
            list.add(new Robot(i, points[p][0], points[p][1]));
        }
        
        List<int[]> pointList = new ArrayList<>();//충돌 좌표를 저장하는 리스트
        
        boolean end = true; //모든 로봇이 완주했는지 체크
    
        for(int t=1; end; t++) {
            end = false;
            
            pointList.clear();
            
            for(int i=0; i<M; i++) {
                Robot robot = list.get(i);
                
                if(robot != null) {
                    end = true;

                    //충돌이 발생하면 true
                    if(go(robot, t)) {
                        
                        //이미 충돌 체크한 좌표의 충돌인지 확인
                        boolean check = true;
                        for(int[] point: pointList) {
                            if(robot.x == point[0] && robot.y == point[1]) {
                                check = false;
                                break;
                            }
                        }
                        
                        if(check) {
                            answer++;
                            pointList.add(new int[] {robot.x, robot.y});
                        }
                    }

                    //최종포인트에 도착했다면
                    if(robot.to == routes[0].length) {
                        list.set(i, null);
                    }
                }
            }
        }
        
        return answer;
    }
    
    private boolean go(Robot robot, int time) {
        boolean collision = false;
        
        //이동해야할 포인트 번호
        int goalNum = routes[robot.num][robot.to] - 1;
        
        int goalX = points[goalNum][0];
        int goalY = points[goalNum][1];
        
        if(robot.x < goalX) {
            robot.x ++;
        } else if(robot.x > goalX) {
            robot.x --;
        } else if(robot.y < goalY) {
            robot.y ++;
        } else if(robot.y > goalY) {
            robot.y --;
        }
        
        if(robot.x == goalX && robot.y == goalY)
            robot.to++;
        
        if(map[robot.x][robot.y] == time) {
            return true;
        }
        else {
            map[robot.x][robot.y] = time;
            return false;   
        }
    }
}
