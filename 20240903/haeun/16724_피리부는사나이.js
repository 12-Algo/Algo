// 피리부는 사나이

const fs = require("fs");
const [nums, ...inputs] = fs
  .readFileSync("./input.txt")
  .toString()
  .trim()
  .split("\n");

let count = 0;
const [N, M] = nums.split(" ").map(Number);
const board = inputs.map((line) => line.trim().split(""));
const parent = Array.from({ length: N * M }, (_, i) => i);
const direction = { D: [1, 0], L: [0, -1], R: [0, 1], U: [-1, 0] };
const safeZone = new Array(N * M).fill(0);

// 안전한 존이 아니면 DFS
for (let i = 0; i < N; i++) {
  for (let j = 0; j < M; j++) {
    if (!safeZone[find(i * M + j)]) findSafeZone(i, j);
  }
}

console.log(count);

function findSafeZone(r, c) {
  const stack = [];
  stack.push([r, c]);

  while (stack.length) {
    const [x, y] = stack.pop();
    const d = board[x][y];
    const [nx, ny] = [x + direction[d][0], y + direction[d][1]];
    const node = x * M + y;
    const nextNode = nx * M + ny;

    const fa = find(node);
    const fb = find(nextNode);

    if (fa === fb) {
      count++;
      safeZone[fb] = 1;
      return;
    }

    if (!safeZone[fb]) {
      stack.push([nx, ny]);
    }

    union(node, nextNode);
  }
}

function union(a, b) {
  const fa = find(a);
  const fb = find(b);

  if (fa !== fb) parent[fa] = fb;
}
function find(a) {
  if (a !== parent[a]) parent[a] = find(parent[a]);
  return parent[a];
}
