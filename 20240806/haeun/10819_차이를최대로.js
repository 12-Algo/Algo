// 차이를 최대로

const fs = require("fs");
const [[N], input] = fs
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

let max = 0;
const used = new Array(N).fill(0);

for (let i = 0; i < N; i++) {
  used[i] = 1;
  backtracking(input[i], 0, 1, used);
  used[i] = 0;
}

console.log(max);

function backtracking(last, sum, count, used) {
  if (count === N) {
    max = Math.max(max, sum);
    return;
  }
  for (let i = 0; i < N; i++) {
    if (used[i]) continue;

    used[i] = 1;
    backtracking(input[i], sum + Math.abs(last - input[i]), count + 1, used);
    used[i] = 0;
  }
}
