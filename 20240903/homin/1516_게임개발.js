const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [N, ...inputs] = input;
  console.log(solution(Number(N), inputs));
});

const solution = (n, inputs) => {
  const graph = Array.from({ length: n + 1 }, () => []);
  const degree = new Array(n + 1).fill(0);
  const time = new Array(n + 1).fill(0);
  const result = new Array(n + 1).fill(0);

  inputs.forEach((e, from) => {
    const [count, ...to] = e.split("-1")[0].trim().split(" ").map(Number);
    time[from + 1] = count;
    degree[from + 1] = to.length;
    to.forEach((i) => {
      graph[i].push(from + 1);
    });
  });

  const queue = [];

  for (let i = 1; i <= n; i++) {
    if (degree[i] === 0) {
      queue.push(i);
      result[i] = time[i];
    }
  }

  while (queue.length) {
    const now = queue.shift();

    for (const next of graph[now]) {
      degree[next] -= 1;
      if (degree[next] === 0) {
        queue.push(next);
      }

      result[next] = Math.max(time[next] + result[now], result[next]);
    }
  }

  return result.slice(1).join("\n");
};
