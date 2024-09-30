const fs = require("fs");
const [[K], [W, H], ...board] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

let min = Infinity;

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

const horsedx = [1, 1, 2, 2, -1, -1, -2, -2];
const horsedy = [2, -2, 1, -1, 2, -2, 1, -1];

const visited = Array.from({ length: H }, () =>
  Array.from({ length: W }, () => new Array(K + 1).fill(false))
);

BFS(0, 0);
console.log(min === Infinity ? -1 : min);

function BFS(r, c) {
  const queue = [[r, c, 0, K]];
  visited[r][c][K] = true;

  let head = 0;

  while (queue.length > head) {
    const [x, y, depth, remainingK] = queue[head++];

    if (x === H - 1 && y === W - 1) {
      min = Math.min(min, depth);
      continue;
    }

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny]) continue;
      if (!visited[nx][ny][remainingK]) {
        visited[nx][ny][remainingK] = true;
        queue.push([nx, ny, depth + 1, remainingK]);
      }
    }

    if (remainingK > 0) {
      for (let i = 0; i < 8; i++) {
        const nx = x + horsedx[i];
        const ny = y + horsedy[i];

        if (nx < 0 || nx >= H || ny < 0 || ny >= W || board[nx][ny]) continue;
        if (!visited[nx][ny][remainingK - 1]) {
          visited[nx][ny][remainingK - 1] = true;
          queue.push([nx, ny, depth + 1, remainingK - 1]);
        }
      }
    }
  }
}
