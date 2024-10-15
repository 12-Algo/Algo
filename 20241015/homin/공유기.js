const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [nm, ...inputs] = input;
  console.log(solution(nm, inputs));
});

const solution = (nm, inputs) => {
  const position = inputs.map(Number);
  const [n, m] = nm.split(" ").map(Number);

  position.sort((a, b) => a - b);

  let left = 1;
  let right = position.at(-1);

  while (left <= right) {
    const mid = Math.round((left + right) / 2);
    let prevPlace = position[0];
    // 공유기 하나는 존재함
    let count = 1;

    for (const nowPlace of position) {
      if (nowPlace - prevPlace >= mid) {
        // 현재 측정하고자 하는 간격보다 이전거리랑 비교했을 때 클 때만 타워를 세움
        prevPlace = nowPlace;
        count += 1;
      }
    }
    // 세워진 타워가 주어진 타워제한수보다 작으면 거리를 줄인다.
    // 반대면 거리를 올린다.

    // 세워질 타워의 수가 주어진 조건보다 많다면 거리를 늘리고 적다면, 거리를 좁힌다.21
    if (m > count) {
      right = mid - 1;
    } else {
      left = mid + 1;
    }
  }

  return right;
};
