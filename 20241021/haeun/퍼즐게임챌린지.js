function solution(diffs, times, limit) {
    const diffLen = diffs.length;

    const answer = binarySearch();

    function binarySearch() {
        let left = 1;
        let right = 100000;

        while (left <= right) {
            const mid = parseInt((left + right) / 2);
            const time = checkTime(mid);

            if (time <= limit) right = mid - 1;
            else left = mid + 1;
        }

        return left;
    }

    function checkTime(level) {
        let totalTime = times[0];
        for (let i = 1; i < diffLen; i++) {
            let k = 0;
            if (diffs[i] > level) {
                k = diffs[i] - level;
            }
            totalTime += (times[i] + times[i - 1]) * k + times[i];
            if (totalTime > limit) return totalTime;
        }

        return totalTime;
    }

    return answer;
}
