import java.util.*;

public class Solution {
    static int[][] deltas = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int i = 0; i < places.length; i++) {
            if(checkDistance(places[i]))
                answer[i] = 1;
            else
                answer[i] = 0; 
        }
        
        return answer;
    }
    // 거리두기 확인
    private boolean checkDistance(String[] place) {
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        for (int r = 0; r < place.length; r++) {
            for (int c = 0; c < place[r].length(); c++) {
                if (place[r].charAt(c) == 'P') {
                    queue.offer(new int[]{r, c}); 
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int[] dir : deltas) {
                // 거리 1 체크
                int nx1 = x + dir[0];
                int ny1 = y + dir[1];

                if (nx1 >= 0 && nx1 < 5 && ny1 >= 0 && ny1 < 5) {
                    if (place[nx1].charAt(ny1) == 'P') {
                        return false; 
                    }
                }

                // 거리 2 체크
                int nx2 = x + dir[0] * 2;
                int ny2 = y + dir[1] * 2;
                int midX = x + dir[0];
                int midY = y + dir[1];

                if (nx2 >= 0 && nx2 < 5 && ny2 >= 0 && ny2 < 5) {
                    if (place[nx2].charAt(ny2) == 'P') {
                        // 거리 2에 'P'가 있는 경우 중간에 칸막이가 없으면 false
                        if (place[midX].charAt(midY) != 'X') {
                            return false; 
                        }
                    }
                }
            }
	        // 대각선 체크 (거리 2)
	        for (int i = -1; i <= 1; i++) {
	            for (int j = -1; j <= 1; j++) {
	                if (Math.abs(i) + Math.abs(j) == 2) { // 대각선 체크
	                    int nx = x + i;
	                    int ny = y + j;
	
	                    if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
	                        if (place[nx].charAt(ny) == 'P') {
	                            // 대각선 거리 2에 'P'가 있을 때 대각선 주변에 'X'가 없으면 위반
	                            if (place[nx].charAt(y) != 'X' || place[x].charAt(ny) != 'X') {
	                                return false; 
	                            }
	                        }
	                    }
	                }
	            }
	        }
	    }
	
	    return true; 
	}

}
