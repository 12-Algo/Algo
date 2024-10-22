function solution(diffs, times, limit) {
  let minLevel = Infinity;
  let maxLevel = -Infinity;

  diffs.forEach((e) => {
    minLevel = Math.min(minLevel, e);
    maxLevel = Math.max(maxLevel, e);
  });

  const argumentSearch = (level) => {
    let sum = 0;
    for (let i = 0; i < diffs.length; i++) {
      if (level >= diffs[i]) {
        sum += times[i];
      } else {
        sum += (diffs[i] - level) * (times[i - 1] + times[i]) + times[i];
      }

      if (sum > limit) {
        return false;
      }
    }

    return limit >= sum ? true : false;
  };

  while (minLevel <= maxLevel) {
    const middle = Math.floor((minLevel + maxLevel) / 2);
    if (argumentSearch(middle)) {
      maxLevel = middle - 1;
    } else {
      minLevel = middle + 1;
    }
  }

  return minLevel;
}

// 1. diff퍼즐의 난이도
// 2. 현재 퍼즐 소요 시간 cur
// 3. 이전 퍼즐 소요 시간 prev
// 4. 숙련도 level
// ----

// 1. 숙련도 >= 난이도  => 0회 틀림 // cur시간 소요
// 2. 반대 => (diff - level)회 틀림 // 틀리면 cur + prev 시간을 추가로 사용해야 함 n번 틀리면 cur + n * (cur + prev

// 또 .. 매개변수.. 탐색.. ?
// 최소는 1로 최대는 diffs의 max값으로 설정
