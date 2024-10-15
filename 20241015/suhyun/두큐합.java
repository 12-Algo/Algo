import java.util.*;

public class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sumQ1 = 0;
        long sumQ2 = 0;
        int ans = 0;

        for (int num : queue1) {
            q1.offer(num);
            sumQ1 += num;
        }
        for (int num : queue2) {
            q2.offer(num);
            sumQ2 += num;
        }

        long totalSum = sumQ1 + sumQ2;
        if (totalSum % 2 != 0) {
            return -1;
        }

        long target = totalSum / 2;
        int maxOperations = queue1.length * 4;

        while (ans <= maxOperations) {
            if (sumQ1 < target) {
                int x = q2.poll();
                q1.offer(x);
                sumQ1 += x;
                sumQ2 -= x;
            } else if (sumQ1 > target) {
                int x = q1.poll();
                q2.offer(x);
                sumQ1 -= x;
                sumQ2 += x;
            } else {
                return ans;
            }
            ans++;
        }

        return -1;
    }
}
