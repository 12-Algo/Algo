// 두 큐 합 같게 만들기
function solution(queue1, queue2) {
  let count = 0;
  const len = queue1.length;
  let sum1 = queue1.reduce((sum, cur) => sum + cur, 0);
  let sum2 = queue2.reduce((sum, cur) => sum + cur, 0);
  let head1 = 0;
  let head2 = 0;
  for (let i = 0; i < 4 * len; i++) {
    if (sum1 === sum2) return count;
    count++;
    if (sum1 > sum2) {
      const value = queue1[head1++];
      queue2.push(value);
      sum1 -= value;
      sum2 += value;
    } else {
      const value = queue2[head2++];
      queue1.push(value);
      sum2 -= value;
      sum1 += value;
    }
  }
  return -1;
}
