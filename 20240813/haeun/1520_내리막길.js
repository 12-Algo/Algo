const fs = require("fs");
const [[N, M], ...matrix] = fs
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

const countWays = Array.from({ length: N }, () => new Array(M).fill(-1));

function dfs(x, y) {
  if (x === N - 1 && y === M - 1) {
    return 1;
  }
  if (countWays[x][y] !== -1) {
    return countWays[x][y];
  }

  countWays[x][y] = 0;

  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i];
    const ny = y + dy[i];

    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

    if (matrix[x][y] > matrix[nx][ny]) {
      countWays[x][y] += dfs(nx, ny);
    }
  }
  return countWays[x][y];
}

console.log(dfs(0, 0));
