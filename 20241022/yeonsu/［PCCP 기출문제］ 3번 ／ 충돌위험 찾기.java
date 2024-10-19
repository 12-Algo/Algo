import java.util.*;

public class Solution {
    static Map<Integer, List<int[]>> pointsPerTime;
    static int[][] field;
    public int solution(int[][] points, int[][] routes) {
        int n = points.length; // Number of points
        int x = routes.length; // Number of robots
        int m = routes[0].length; // Length of each robot's route
        int answer = 0;
        pointsPerTime = new HashMap<>();
        
        for (int i=0;i<routes.length;i++) {
            int sy = points[routes[i][0] - 1][0];
            int sx = points[routes[i][0] - 1][1];
            int time = 0;
            for (int j=1;j<routes[i].length;j++) {
                int ny = points[routes[i][j] - 1][0];
                int nx = points[routes[i][j] - 1][1];
                while(sy > ny) {
                    if (pointsPerTime.get(time) == null) 
                        pointsPerTime.put(time, new ArrayList<>());
                    pointsPerTime.get(time++).add(new int[] {sy, sx});
                    sy--;
                }
                while(sy < ny) {
                    if (pointsPerTime.get(time) == null) 
                        pointsPerTime.put(time, new ArrayList<>());
                    pointsPerTime.get(time++).add(new int[] {sy, sx});
                    sy++;
                }
                while(sx > nx) {
                    if (pointsPerTime.get(time) == null) 
                        pointsPerTime.put(time, new ArrayList<>());
                    pointsPerTime.get(time++).add(new int[] {sy, sx});
                    sx--;
                }
                while(sx < nx) {
                    if (pointsPerTime.get(time) == null) 
                        pointsPerTime.put(time, new ArrayList<>());
                    pointsPerTime.get(time++).add(new int[] {sy, sx});
                    sx++;
                }
            }
            //도착했을 때도 넣어줌
            if (pointsPerTime.get(time) == null) 
                pointsPerTime.put(time, new ArrayList<>());
            pointsPerTime.get(time++).add(new int[] {sy, sx});
        }
        
        //map에 넣었던 path들 중 중복이 있는 지 확인해보자.
        int idx = -1;
        while(pointsPerTime.get(++idx) != null) {
            field = new int[101][101];
            for (int[] p : pointsPerTime.get(idx)) {
                field[p[0]][p[1]]++;
                // System.out.print(p[0] + " " + p[1] + "   ");
            }
            // System.out.println();
            
            for (int i=0;i<101;i++) {
                for (int j=0;j<101;j++) {
                    if (field[i][j] > 1) {
                        answer++;
                    }
                }
            }
        }
        return answer;
    }
}