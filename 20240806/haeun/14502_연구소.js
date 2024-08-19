const fs = require("fs");
const [[N, M], ...map] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((str) => str.split(" ").map(Number));

const answer = [];

function backtracking(count, prevR, prevC) {
  if (count === 3) {
    let arr = map.map((v) => [...v]);
    bfs(arr);
    return;
  }
  for (let r = 0; r < N; r++) {
    for (let c = 0; c < M; c++) {
      if (r < prevR) continue;
      if (r === prevR && c < prevC) continue;
      if (map[r][c] === 0) {
        map[r][c] = 1;
        backtracking(count + 1, r, c);
        map[r][c] = 0;
      }
    }
  }
}

function bfs(graph) {
  const dx = [0, 1, 0, -1];
  const dy = [1, 0, -1, 0];
  let virus = 0;
  const queue = [];

  for (let r = 0; r < N; r++) {
    for (let c = 0; c < M; c++) {
      if (graph[r][c] === 2) queue.push([r, c]);
    }
  }
  while (queue.length) {
    const [targetR, targetC] = queue.shift();
    for (let i = 0; i < 4; i++) {
      const nr = targetR + dx[i];
      const nc = targetC + dy[i];

      if (nr >= 0 && nr < N && nc >= 0 && nc < M && graph[nr][nc] === 0) {
        graph[nr][nc] = 2;
        queue.push([nr, nc]);
      }
    }
  }

  for (let r = 0; r < N; r++) {
    for (let c = 0; c < M; c++) {
      if (graph[r][c] === 0) virus++;
    }
  }

  answer.push(virus);
}

backtracking(0, 0, 0);
console.log(Math.max(...answer) | 0);
