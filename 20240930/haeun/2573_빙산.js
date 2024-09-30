const fs = require("fs");
const [[N, M], ...matrix] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const visited = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => 0)
);
const decrease = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => 0)
);
const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

function dfs(x, y, targetVisited) {
  visited[x][y]++;

  let seaCount = 0;

  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i];
    const ny = y + dy[i];

    if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

    if (matrix[nx][ny] === 0) seaCount++;

    if (visited[nx][ny] < targetVisited && matrix[nx][ny] !== 0) {
      dfs(nx, ny, targetVisited);
    }
  }

  decrease[x][y] = seaCount;
}

function simulate() {
  let totalVisited = 0;

  while (true) {
    let icebergCount = 0;

    for (let i = 0; i < N; i++) {
      for (let j = 0; j < M; j++) {
        if (matrix[i][j] !== 0 && visited[i][j] <= totalVisited) {
          dfs(i, j, totalVisited + 1);
          icebergCount++;
        }
      }
    }

    for (let i = 0; i < N; i++) {
      for (let j = 0; j < M; j++) {
        matrix[i][j] = Math.max(matrix[i][j] - decrease[i][j], 0);
        decrease[i][j] = 0;
      }
    }

    if (icebergCount > 1) return totalVisited;
    if (icebergCount === 0) return 0;
    else totalVisited++;
  }
}

console.log(simulate());
