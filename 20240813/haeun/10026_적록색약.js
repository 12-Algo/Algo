const fs = require("fs");
const [N, ...input] = fs.readFileSync(0).toString().split("\n");
const graph = input.map((str) => str.split(""));
let count = 0;
let daltonismCount = 0;
const visited = Array.from({ length: +N }, () =>
  Array.from({ length: +N }, () => 0)
);

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

function DFS(x, y, type) {
  for (let i = 0; i < 4; i++) {
    const nx = x + dx[i];
    const ny = y + dy[i];
    if (nx < 0 || nx >= +N || ny < 0 || ny >= +N) continue;

    if (
      (graph[nx][ny] === type[0] || graph[nx][ny] === type[1]) &&
      visited[nx][ny] === 0
    ) {
      visited[nx][ny] = 1;
      DFS(nx, ny, type);
    }
  }
}

for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    if (!visited[r][c]) {
      DFS(r, c, [graph[r][c], graph[r][c]]);
      count++;
    }
  }
}

visited.forEach((row) => row.fill(0));
for (let r = 0; r < N; r++) {
  for (let c = 0; c < N; c++) {
    if (!visited[r][c]) {
      if (graph[r][c] === "B") DFS(r, c, ["B", "B"]);
      else DFS(r, c, ["R", "G"]);
      daltonismCount++;
    }
  }
}

console.log(`${count} ${daltonismCount}`);
