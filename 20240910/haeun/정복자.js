const fs = require("fs");
const [[N, M, T], ...input] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));
const parent = Array.from({ length: N }, (_, i) => i);
let sum = 0;
input.sort((a, b) => a[2] - b[2]);

for (let i = 0; i < M; i++) {
  const [from, to, cost] = input[i];
  const fa = find(from);
  const fb = find(to);

  if (fa != fb) {
    union(from, to);
    sum += cost;
  }
}

console.log(sum + (((N - 2) * (N - 2 + 1)) / 2) * T);

function find(a) {
  if (parent[a] !== a) parent[a] = find(parent[a]);
  return parent[a];
}

function union(a, b) {
  const fa = find(a);
  const fb = find(b);

  if (fa !== fb) parent[fa] = fb;
}
