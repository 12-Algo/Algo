const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [abc, ...inputs] = input;
  console.log(solution(abc, inputs));
});

const solution = (abc, inputs) => {
  const [a, b, c] = abc.split(" ").map(Number);
  const costGraph = inputs.map((e) => e.split(" ").map(Number));
  costGraph.sort((a, b) => a[2] - b[2]);
  let answer = 0;
  let counting = 0;
  const parents = Array.from({ length: a + 1 }, (_, i) => i);

  const union = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);
    parents[nowY] = nowX;
  };

  const find = (x) => {
    if (x === parents[x]) return parents[x];

    const nowX = find(parents[x]);
    parents[x] = nowX;
    return nowX;
  };

  const canUnion = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);

    return nowX !== nowY;
  };

  for (let i = 0; i < b; i++) {
    const [from, to, cost] = costGraph[i];
    if (canUnion(from, to)) {
      answer += cost;
      union(from, to);
      answer += counting * c;
      counting += 1;
    }

    if (counting === a - 1) break;
  }

  return answer;
};
