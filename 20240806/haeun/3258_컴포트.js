const fs = require("fs");
const [[N, Z, _], obstacles] = fs
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const fields = new Array(N).fill(0);
let answer = 0;

for (const obstacle of obstacles) {
  fields[obstacle - 1] = 1;
}

for (let j = 1; j < N; j++) {
  const isPossibleToGo = checkfield(N, Z, j);
  if (isPossibleToGo) {
    answer = j;
    break;
  }
}

console.log(answer);

function checkfield(fieldLength, goal, jump) {
  let jumpCount = 0;
  let current = 0;
  while (jumpCount++ < fieldLength) {
    const next = (current + jump) % fieldLength;
    if (next === goal - 1) return true;
    if (next === 0 || fields[next]) return false;

    current = next;
  }
  return false;
}
