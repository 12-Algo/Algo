import java.util.*;
import java.io.*;

class Solution {
    
    static int answer = Integer.MAX_VALUE;
    
        static void makeSumSame(int[] queue1, int[] queue2, Deque<Integer> deque1, Deque<Integer> deque2, long q1Sum, long q2Sum, int count) {
    	if (q1Sum == q2Sum) {
    		answer = Math.min(answer, count);
    		return;
    	}
    	
        if (count > queue1.length * 4) {
            answer = -1;
            return;
        }
            
    	// q1Sum이 더 크면 q1 추출, q2 삽입
    	// q2Sum이 더 크면 q2 추출, q1 삽입
    	if (q1Sum > q2Sum) {
    		deque2.add(deque1.poll());
    		makeSumSame(queue1, queue2, deque1, deque2, q1Sum-deque2.peekLast(), q2Sum+deque2.peekLast(), count+1);
            return;
    	} else if (q1Sum < q2Sum) {
    		deque1.add(deque2.poll());
    		makeSumSame(queue1, queue2, deque1, deque2, q1Sum+deque1.peekLast(), q2Sum-deque1.peekLast(), count+1);
            return;
    	}
    }
    
     public static int solution(int[] queue1, int[] queue2) {
		Deque<Integer> deque1 = new ArrayDeque<>();
		Deque<Integer> deque2 = new ArrayDeque<>();
		long q1Sum = 0;
		long q2Sum = 0;
		for (int i=0;i<queue1.length;i++) {
			deque1.add(queue1[i]);
			deque2.add(queue2[i]);
			q1Sum += queue1[i];
			q2Sum += queue2[i];
		}
        answer = -1;
        for (int i=0;i<queue1.length * 4;i++) {
            if (q1Sum == q2Sum) {
                answer = i;
                break;
            }
            
            if (q1Sum > q2Sum) {
                q1Sum -= deque1.peek();
                q2Sum += deque1.peek();
                deque2.add(deque1.poll());
            } else if (q1Sum < q2Sum) {
                q1Sum += deque2.peek();
                q2Sum -= deque2.peek();
                deque1.add(deque2.poll());
            }
        }
        // System.out.println(q1Sum);
        // makeSumSame(queue1, queue2, deque1, deque2, q1Sum, q2Sum, 0);

        return answer;
    }
}