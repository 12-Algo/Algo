import java.util.*;

class Solution {
    static long sum1, sum2, s1, s2, e1, e2;
    public long solution(int[] queue1, int[] queue2) {
        sum1 = 0; 
        sum2 = 0; 

        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        int N = queue1.length;
        
        for(int i = 0; i < queue1.length; i++){
            sum1 += queue1[i];
            q1.add(queue1[i]);
        }
        
        for(int i = 0; i < queue2.length; i++){
            sum2 += queue2[i];
            q2.add(queue2[i]);
        }
        
        if((sum1 + sum2)%2 == 1){
            return -1;
        }
        long cnt = 0;
        while(sum1 != sum2){
            
            if(sum1 < sum2){
                // 2에서 빼서 1로 넣기
                int k = q2.poll();
                q1.add(k);
                sum2 -= k;
                sum1 += k;
            }
            else{
                int k = q1.poll();
                q2.add(k);
                sum2 += k;
                sum1 -= k;
            }
            cnt++;
            
            if(cnt >= 4*N){
                cnt = -1;
                break;
            }
        }
        
        return cnt;
    }
}