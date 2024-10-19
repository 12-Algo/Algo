class Solution {
    static int N, diffs[], times[];
    static long limit;
    public int solution(int[] inputdiffs, int[] inputtimes, long inputlimit) {
        int answer = 0;
        diffs = inputdiffs;
        times = inputtimes;
        limit = inputlimit;
        N = inputdiffs.length;
        answer = (int)binarySearch(1L, limit);
        return answer;
    }
    
    static long binarySearch(long left, long right) {
        if (left > right) return left;
        
        long mid = (left + right)  / 2;
        
        long take = test(mid);
        
        //숙련도가 크면 빨리 끝낼 수 있음. 숙련도가 너무 큼 -> 조금 낮춰보자
        if (take <= limit) {
            return binarySearch(left, mid - 1);
        } else if (take > limit) {        //숙련도가 낮음. -> 조금 늘려보자.
            return binarySearch(mid + 1, right);
        }
        return -1;
    }
    
    static long test(long level) {
        long ret = 0;
        for (int i=0;i<N;i++) {
            if (level >= diffs[i]) {
                ret += times[i];
            } else {
                if (i >= 1) {
                    ret += (diffs[i] - level) * (times[i] + times[i - 1]);
                } else {
                    ret += (diffs[i] - level) * (times[i]);
                }
                ret += times[i];
            }
        }
        return ret;
    }
}