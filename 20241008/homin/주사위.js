function solution(dice) {
  const n = dice.length;

  const combination = [];

  const makeCombination = (arr, index) => {
    if (arr.length === n / 2) {
      combination.push(arr);
      return;
    }

    for (let i = index; i < n; i++) {
      makeCombination([...arr, i + 1], i + 1);
    }
  };

  makeCombination([], 0);

  let max = 0;
  let answer = [0, 0];

  const sumAccumulate = (arrays) => {
    const result = [];

    const combination = (index, sumNum) => {
      if (arrays.length === index) {
        result.push(sumNum);
        return;
      }

      for (const now of arrays[index]) {
        combination(index + 1, sumNum + now);
      }
    };

    combination(0, 0);
    return result;
  };

  combination.forEach((e) => {
    const ADice = dice.filter((_, i) => e.includes(i + 1));
    const BDice = dice.filter((_, i) => !e.includes(i + 1));

    const ASum = sumAccumulate(ADice);
    const BSum = sumAccumulate(BDice);

    let aWin = 0;

    ASum.sort((a, b) => a - b);
    BSum.sort((a, b) => a - b);

    const map = new Map();

    for (const a of ASum) {
      if (!map.has(a)) {
        let count = 0;
        let left = 0;
        let right = BSum.length - 1;

        while (left <= right) {
          const mid = Math.floor((left + right) / 2);
          if (BSum[mid] < a) {
            left = mid + 1;
          } else {
            right = mid - 1;
          }
        }
        aWin += left;
        map.set(a, left);
      } else {
        aWin += map.get(a);
      }
    }

    //         for (let i = 0; i < ASum.length; i++) {

    //             let left = 0;
    //             let right = BSum.length - 1;

    //             while (left <= right) {
    //                 const mid = Math.floor((left + right) / 2);

    //                 if (BSum[mid] < ASum[i]) {
    //                     left = mid + 1;
    //                 } else {
    //                     right = mid - 1;
    //                 }
    //             }
    //                 aWin += left;
    //         }

    if (max < aWin) {
      max = aWin;
      answer = e;
    }
  });

  return answer;
}
