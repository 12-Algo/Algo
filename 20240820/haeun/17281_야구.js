// ⚾

const fs = require("fs");
const [[N], ...innings] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const sequence = new Array(9).fill(-1);
const assigned = new Array(9).fill(0);
const playSeq = Array.from({ length: 40320 }, () => []);
sequence[3] = 0;
assigned[0] = 1;
let max = 0;
let i = 0;

backtracking(0);

for (const seq of playSeq) {
  const score = play(seq);
  max = Math.max(score, max);
}

console.log(max);

function backtracking(sequenceIdx) {
  if (sequenceIdx === 9) {
    playSeq[i++] = [...sequence];
    return;
  }

  if (sequence[sequenceIdx] !== -1) {
    backtracking(sequenceIdx + 1);
    return;
  }

  for (let player = 1; player < 9; player++) {
    if (!assigned[player]) {
      sequence[sequenceIdx] = player;
      assigned[player] = 1;
      backtracking(sequenceIdx + 1);
      sequence[sequenceIdx] = -1;
      assigned[player] = 0;
    }
  }
}

function play(seq) {
  const current = [0, 0, 0];
  let outCount = 0;
  let score = 0;
  let playerIdx = 0;
  for (const result of innings) {
    for (let i = 0; i < 3; i++) {
      current[i] = 0;
    }
    while (outCount !== 3) {
      const target = result[seq[playerIdx]];

      if (target === 0) {
        outCount++;
      } else if (target === 1) {
        if (current[2] === 1) score++;
        current[2] = current[1];
        current[1] = current[0];
        current[0] = 1;
      } else if (target === 2) {
        if (current[2] === 1) score++;
        if (current[1] === 1) score++;
        current[2] = current[0];
        current[1] = 1;
        current[0] = 0;
      } else if (target === 3) {
        for (let i = 0; i < 3; i++) {
          if (current[i] === 1) {
            score++;
            current[i] = 0;
          }
        }
        current[2] = 1;
      } else {
        for (let i = 0; i < 3; i++) {
          if (current[i] === 1) {
            score++;
            current[i] = 0;
          }
        }
        score++;
      }
      playerIdx = (playerIdx + 1) % 9;
    }
    outCount = 0;
  }

  //if (score === 47) console.log(seq);
  return score;
}
