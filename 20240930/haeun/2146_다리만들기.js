const fs = require("fs");
const [[N], ...board] = fs
  .readFileSync(0)
  .toString()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

let island = 2;
const starts = [];

let min = Infinity;

for (let i = 0; i < N; i++) {
  for (let j = 0; j < N; j++) {
    if (board[i][j] === 1) {
      board[i][j] = island;
      // 섬 숫자 다 다르게 만들기
      starts.push([island, i, j]);
      bfs(island, i, j);
      island++;
    }
  }
}

for (const [num, sr, sc] of starts) {
  const len = findBridge(num, sr, sc);
  min = Math.min(len, min);
}

console.log(min);

function bfs(islandNum, r, c) {
  const queue = [[r, c]];
  let head = 0;

  while (queue.length > head) {
    const [x, y] = queue[head++];
    for (let i = 0; i < 4; i++) {
      const [nx, ny] = [x + dx[i], y + dy[i]];

      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
      if (board[nx][ny] === 1) {
        board[nx][ny] = islandNum;
        queue.push([nx, ny]);
      }
    }
  }
}

function findBridge(num, r, c) {
  const visited = Array.from({ length: N }, () => new Array(N).fill(0));

  let min = Infinity;
  const queue = [[r, c, 0]];
  visited[r][c] = 1;
  let head = 0;

  while (queue.length > head) {
    const [x, y, bridge] = queue[head++];

    for (let i = 0; i < 4; i++) {
      const [nx, ny] = [x + dx[i], y + dy[i]];

      if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

      if (board[nx][ny] !== 0 && board[nx][ny] !== num) {
        min = Math.min(min, bridge);
        continue;
      }

      visited[nx][ny] = 1;

      if (board[nx][ny] === num) {
        queue.push([nx, ny, 0]);
        continue;
      }
      if (board[nx][ny] === 0) {
        queue.push([nx, ny, bridge + 1]);
        continue;
      }
    }
  }
  return min;
}
