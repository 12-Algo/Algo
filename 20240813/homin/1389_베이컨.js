const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [temp, ...inputs] = input;
  const [friends, cases] = temp.split(" ").map(Number);

  console.log(solution(friends, inputs));
});

const solution = (friends, inputs) => {
  const graphs = Array.from({ length: friends + 1 }, () => []);
  let min = Infinity;
  let answer = 0;
  inputs.forEach((e) => {
    const [start, end] = e.split(" ").map(Number);
    graphs[start].push(end);
    graphs[end].push(start);
  });

  for (let i = 1; i <= friends; i++) {
    const visited = new Array(friends + 1).fill(0);
    graphs[i].forEach((e) => (visited[e] = 1));
    const queue = [...graphs[i]];

    while (queue.length !== 0) {
      const now = queue.shift();
      graphs[now].forEach((e) => {
        if (i !== e && visited[e] === 0) {
          queue.push(e);
          visited[e] = visited[now] + 1;
        }
      });
    }
    const visitedMin = visited.reduce((acc, cur) => acc + cur, 0);
    if (min > visitedMin) {
      answer = i;
      min = visitedMin;
    }
  }
  return answer;
};
