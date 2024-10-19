import java.util.*;
import java.io.*;

class Solution {
    private static Queue<Integer> q1, q2;
    private static long total, sum1, sum2;
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;
        total = 0; sum1 = 0; sum2 = 0;
        //initialize q1 and q2 and get total and each sum of queue 
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        for (int q : queue1) {
            q1.offer(q);
            sum1 += q;
        }
        for (int q : queue2) {
            q2.offer(q);
            sum2 += q;
        }
        total = sum1  + sum2;
        
        //get from answer
        answer = getAnswer();
        
        return answer;
    }
    
    private static int getAnswer() {
        int answer = 0;
        if (sum1 == sum2) return answer;
        // System.out.println(sum1 + " " + sum2);
        while (answer < 90000000) {
            if (sum1 < sum2 && !q2.isEmpty()) {
                int popped = q2.poll();
                // System.out.println(popped);
                sum2 -= popped;
                sum1 += popped;
                q1.offer(popped);
            } else if (sum1 > sum2 && !q1.isEmpty()){
                int popped = q1.poll();
                sum1 -= popped;
                sum2 += popped;
                q2.offer(popped);
            }
            // System.out.println(sum1 + " " + sum2);
            answer++;
            if (sum1 == sum2) return answer;
            
        }
        
//         //q1만 남은 움직이지 않은 원소가 남아있음. 그리고 sum2가 더 작음
//         while(!q1.isEmpty() && sum1 > sum2) {
//             int popped = q1.poll();
//             sum1 -= popped;
//             sum2 += popped;
//             answer++;
//             if (sum1 == sum2) return answer;
//         }
        
//         //q2만 남은 움직이지 않은 원소가 남아있음. 그리고 sum1이 더 작음
//         while(!q2.isEmpty() && sum1 < sum2) {
//             int popped = q2.poll();
//             sum1 -= popped;
//             sum2 += popped;
//             answer++;
//             if (sum1 == sum2) return answer;
//         }
        return -1;
    }
}


//합이 큰 큐에서 하나빼서 작은 큐로 옮기는 방법
//14->18->15
//16->12->15