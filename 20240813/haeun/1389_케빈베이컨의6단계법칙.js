const fs = require("fs");
const [[N, M], ...input] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const graph = Array.from({ length: N + 1 }, () => []);
const newVisited = Array.from({ length: N + 1 }, () => 0);

input.forEach(([from, to]) => {
  graph[from].push(to);
  graph[to].push(from);
});
let answer = [0, 100 * N];

function countStep(node, visited) {
  let sum = 0;
  let queue = [[node, 0]];
  visited[node] = 1;

  while (queue.length) {
    let [target, depth] = queue.shift();
    sum += depth;
    for (const next of graph[target]) {
      if (!visited[next]) {
        visited[next] = 1;
        queue.push([next, depth + 1]);
      }
    }
  }

  if (answer[1] > sum) {
    answer[0] = node;
    answer[1] = sum;
  }
}

for (let i = 1; i <= N; i++) {
  countStep(i, [...newVisited]);
}

console.log(answer[0]);
