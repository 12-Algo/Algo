import java.util.*;

class Solution {
    private static int L;
    
    private long calcTime(int[] diffs, int[] times, int level) {
        long res = times[0];
        
        for (int i = 1; i < L; i++) {
            res += times[i];
            
            if (diffs[i] > level) {
                res += (times[i-1] + times[i]) * (diffs[i] - level);
            }
        }
        
        return res;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        L = diffs.length;
        int s = 1;
        int e = 100000;
        int res = Integer.MAX_VALUE;
        
        while (s <= e) {
            int mid = (s + e) / 2;
            long time = calcTime(diffs, times, mid);
            
            if (time > limit) {
                s = mid + 1;
            } else {
                res = Math.min(res, mid);
                e = mid - 1;
            }
        }
        
        return res;
    }
}