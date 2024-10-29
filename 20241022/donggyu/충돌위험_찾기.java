import java.io.*;
import java.util.*;
class Solution {
    static int n, maxTime;
	static Map<Integer, List<int[]>> shortestRoutes;
	static Map<List<int[]>, Integer> results;
    
    public int solution(int[][] points, int[][] routes) {
        n = routes.length;
		maxTime = 0;
		shortestRoutes = new HashMap<>();
		for(int i=0;i<n;i++) {
			shortestRoutes.put(i+1, new ArrayList<>());
		}
		for(int i=0;i<n;i++) {
			shortestRoutes.put(i+1, new ArrayList<>());
		}
		findRoute(points, routes);
		findMaxLength();
        return countCollision();
    }
    
    static int countCollision() {
	    int collisionCount = 0; 
	    Map<String, Integer> positionCount = new HashMap<>();
	        
	    for (int t = 0; t < maxTime; t++) {
	        positionCount.clear(); // 매 시간마다 초기화
	        for (int k : shortestRoutes.keySet()) {
	            if (t < shortestRoutes.get(k).size()) {
	                int[] pos = shortestRoutes.get(k).get(t);
	                String key = pos[0] + "," + pos[1]; // 위치를 문자열로 생성
	                positionCount.put(key, positionCount.getOrDefault(key, 0) + 1);
                }
            }
	        // 충돌 확인
	        for (int count : positionCount.values()) {
	            if (count > 1) {
	                collisionCount += count - 1;
	                break; 
	            }
            }
	    }
	        
	    return collisionCount; 
	}

	static void findMaxLength() {
		int tempLength = 0;
		for(int k : shortestRoutes.keySet()) {
			tempLength = shortestRoutes.get(k).size();
		}
		maxTime = Math.max(maxTime, tempLength);
	}
	
	static void findRoute(int[][] points, int[][] routes) {
		for(int i=0;i<n;i++) {
			int[] route = routes[i];
			int x = points[route[0]-1][0];
			int y = points[route[0]-1][1];
			shortestRoutes.get(i+1).add(new int[] {x, y});
			
			for(int j=1;j<route.length;j++) {
				int nx = points[route[j]-1][0];
				int ny = points[route[j]-1][1];
		
				while(x != nx) {
					if(x < nx)
						x++;
					else
						x--;
					shortestRoutes.get(i+1).add(new int[] {x, y});
				}
				
				while(y != ny) {
					if(y < ny)
						y++;
					else
						y--;
					shortestRoutes.get(i+1).add(new int[] {x, y});
				}
			}
		}
	}
}