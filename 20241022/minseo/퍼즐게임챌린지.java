class Solution {
    public long solution(int[] diffs, int[] times, long limit) {
        long start = 1;
        long end = diffs[0]; // 초기 값을 0번째 인덱스로 해놓으면 후에 N-1번만 비교하면 됨!

        // diff의 최대값을 찾아서 right에 저장
        // 파이썬이었으면 max...
        for (int i = 1; i < diffs.length; i++) {
            if (end < diffs[i]) {
                end = diffs[i];
            }
        }

        // 이분 탐색
        // diff가 너무 커서!
        while (start <= end) {
            long mid = (start + end) / 2;
            if (canSolve(mid, diffs, times, limit)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private boolean canSolve(long level, int[] diffs, int[] times, long limit) {
        long time_cur = times[0]; // 현재 퍼즐의 소요 시간
        long time_prev = 0; // 이전 퍼즐의 소요 시간 (처음엔 0)
        long result = 0;

        for (int i = 0; i < diffs.length; i++) {
            if (i != 0) {
                // 그 다음 퍼즐부터는 계속 값 초기화해줌
                time_cur = times[i];
                time_prev = times[i - 1];
            }
            
            long temp = diffs[i] - level;

            if (temp > 0) {
                // 틀린 횟수만큼 걸린 시간
                result += (time_cur + time_prev) * temp + time_cur;
                
            } else {
                result += time_cur;
            }
        }
        if(result <= limit){
            // limit보다 크면 퍼즐 풀수 있음
            return true;
        }
        else{
            return false;
        }
    }
}
