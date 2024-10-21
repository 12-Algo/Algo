import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        // Arrays.sort의 평균 시간복잡도: O(nlogn), 최악: O(n^2)
        int minDiff = 987654321;
        int maxDiff = 0;
        for (int i=0;i<diffs.length;i++) {
            minDiff = Math.min(minDiff, diffs[i]);
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        
        int midDiff;
        int answer = 987654321;
        while (minDiff <= maxDiff) {
            midDiff = (minDiff + maxDiff) / 2;
            long nowTime = 0;
            for (int i=0;i<diffs.length;i++) {
                if (midDiff >= diffs[i]) {
                    nowTime += times[i];
                } else if (midDiff < diffs[i]) {
                    nowTime += (times[i-1] + times[i]) * (diffs[i] - midDiff) + times[i];
                }
            }
            if (nowTime <= limit) {
                answer = Math.min(answer, midDiff);
                maxDiff = midDiff - 1;
            } else {
                minDiff = midDiff + 1;
            }
        }
        
        return answer;
    }
}