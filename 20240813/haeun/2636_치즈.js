const fs = require("fs");
const [[N, M], ...matrix] = fs
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

let count = 0;
let prev = 0;
const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

while (1) {
  const contected = bfs();
  if (contected.length === 0) {
    console.log(`${count}\n${prev}`);
    break;
  }

  count++;
  prev = contected.length;
  for (const [r, c] of contected) {
    matrix[r][c] = 0;
  }
}

function bfs() {
  // c 찾기
  const c = [];
  const visited = Array.from({ length: N }, () => new Array(M).fill(0));

  const queue = [[0, 0]];
  visited[0][0] = 1;
  let head = 0;

  while (queue.length > head) {
    const [x, y] = queue[head++];

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
      if (visited[nx][ny] === 0) {
        if (matrix[nx][ny] === 1) {
          visited[nx][ny] = 1;
          c.push([nx, ny]);
        } else {
          visited[nx][ny] = 1;
          queue.push([nx, ny]);
        }
      }
    }
  }
  return c;
}
