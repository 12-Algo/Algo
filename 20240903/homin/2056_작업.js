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
  const degree = new Array(n).fill(0);
  const time = new Array(n).fill(0);
  const result = new Array(n).fill(0);
  const graph = Array.from({ length: n }, () => []);

  inputs.forEach((e, i) => {
    const [taskTime, degreeCount, ...pre] = e.split(" ").map(Number);
    time[i] = taskTime;
    degree[i] = degreeCount;
    pre.forEach((ele) => {
      graph[ele - 1].push(i);
    });
  });

  const queue = [];

  // 변수명을 헷갈리게 해놔서 실수했음
  // 차수가 0일 때의 index를 넣어야하는데 멍청하게 풀었다..

  //   for (const now of degree) {
  //     if (now === 0) {
  //       queue.push(now);
  //       result[now] = time[now];
  //     }
  //   }

  for (let i = 0; i < n; i++) {
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
      result[next] = Math.max(result[next], result[now] + time[next]);
    }
  }
  return Math.max(...result);
};
