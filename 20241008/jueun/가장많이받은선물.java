import java.util.*;

class Solution {
    Map<String, Integer> map = new HashMap<>();
    
    int[][] log;
    int[] score;
    
    int N;
     
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        N = friends.length;
        
        log = new int[N][N];
        score = new int[N];
        
        for(int i=0; i<friends.length; i++) {
            map.put(friends[i], map.size());
        }
        
        for(int i=0; i<gifts.length; i++) {
            StringTokenizer st = new StringTokenizer(gifts[i]);
            
            int from = map.get(st.nextToken());
            int to = map.get(st.nextToken());
            
            log[from][to] ++;
            
            score[from] ++;
            score[to] --;
        }
        
        for(int i=0; i<N; i++) {

            int sum = 0;
            for(int j=0; j<N; j++) {

                if(log[i][j] > log[j][i]){
                    sum++;

                }
                    
                else if(log[i][j] == log[j][i] && score[i] > score[j]) {
                    sum++;
 
                }
            }
            
            answer = Math.max(answer, sum);
        }
        
        return answer;
    }
}
