class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int first = 1;
        int last = findMax(diffs);
        answer = binarySearch(diffs, times, limit, first, last);
        return answer;
    }
    
    static int binarySearch(int[] diffs, int[] times, long limit, int first, int last){
        long nowUsingTime = 0;
        int level = 0;
        int answer = 0;
        
        while(first <= last){
            level = (first+last)/2;
            nowUsingTime = calcTime(diffs, times, level);
            if(nowUsingTime <= limit){
            	answer = level;
                last = level-1;
            } else if(nowUsingTime > limit){
                first = level+1;
            }
        }
        return answer; 
    }
    
    static long calcTime(int[] diffs, int[] times, int level){
        long usingTime = times[0];
        for(int i=1;i<diffs.length;i++){
            int wrongCount = diffs[i] - level;
            if(wrongCount<=0){
                usingTime += times[i];
            } else if(wrongCount>0){
                usingTime += (times[i-1]+times[i])*wrongCount+times[i];
            }
        }
        return usingTime;
    }
    
    static int findMax(int[] diffs){
        int diffMax = diffs[0];
        for(int i=1;i<diffs.length-1;i++){
            if(diffs[i] > diffMax)
                diffMax = diffs[i];
        }
        return diffMax;
    }
}