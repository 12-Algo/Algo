function solution(queue1, queue2) {
  let leftIndex = 0;
  let rightIndex = 0;

  let breakPoint = 4 * queue1.length;

  let leftSum = queue1.reduce((acc, cur) => acc + cur, 0);
  let rightSum = queue2.reduce((acc, cur) => acc + cur, 0);

  let count = 0;

  while (leftSum + rightSum !== leftSum * 2) {
    count += 1;
    if (leftSum > rightSum) {
      const now = queue1[leftIndex];
      leftSum -= now;
      rightSum += now;
      queue2.push(now);
      leftIndex += 1;
    } else {
      const now = queue2[rightIndex];
      leftSum += now;
      rightSum -= now;
      queue1.push(now);
      rightIndex += 1;
    }

    if (count === breakPoint) {
      return -1;
    }
  }

  return count;
}

// 2n으로 계산을 했는데, 질문글 반례보고 알았음

// [1,1,1,1] , [1,1,7,1] 일 때 총 9번 움직여야 함
// 즉 좌우가 바뀌고 원복이 되는 과정에서도 값이 나올 수 있기에 4n으로 해줘야함
