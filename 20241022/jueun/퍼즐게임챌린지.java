class Solution {
    int MAX = 100_001;
    
    int[] diffs;
    int[] times;
    long limit;
    
    public int solution(int[] diffs, int[] times, long limit) {
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        return binarySearch(0, MAX);
    }
    
    private int binarySearch(int low, int high) {
        while(low+1 < high) {
            int mid = (low+high)/2;
            
            if(check(mid)) {
                low = mid;
            } else {
                high = mid;
            }
        }
        
        return high;
    }
    
    private boolean check(int level) {
        long time = 0;
        
        for(int i=0; i<diffs.length; i++) {
            long tryTimes = diffs[i] - level;
            
            if(tryTimes > 0) {
                time += (times[i-1]+times[i]) * tryTimes;
            }
            time += times[i];
        }
        
        //level 0에서 true -> time이 큼.
        //level MAX에서 false -> time이 작음..
        //true가 리턴되는 범위가 작게 (TTT FFF)
        return time > limit;
    }
}
