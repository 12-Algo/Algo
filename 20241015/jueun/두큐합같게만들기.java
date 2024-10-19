import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int len = queue1.length + queue2.length;
        int[] arr = new int[len];
        
        //두 큐의 각각의 합, 총합/2
        long q1=0, q2=0, goal=0;
        
        for(int i=0; i<queue1.length; i++) {
            q1 += queue1[i];
            arr[i] = queue1[i];
        }
        
        for(int i=0; i<queue2.length; i++) {
            q2 += queue2[i];
            arr[queue1.length + i] = queue2[i]; 
        }

        goal = (q1 + q2) / 2;
        
        int start = 0, end = queue1.length-1;
        int answer = 0;
        
        while(true) {      
            if(q1 == goal) {
                return answer; 
            }
            else if(q1 < goal) {
                end ++;
                
                if(end >= len)
                    return -1;
                
                q1 += arr[end];
                
            } else {
                q1 -= arr[start];
                
                start ++;
            }
            
            answer++;
        }
    }
}
