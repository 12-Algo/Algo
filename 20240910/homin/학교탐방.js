const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [N, case1, ...inputs] = input;
  console.log(solution(N, case1, inputs));
});

const solution = (nm, firstCase, inputs) => {
  const [n, m] = nm.split(" ").map(Number);
  const graph = inputs.map((e) => e.split(" ").map(Number));

  graph.sort((a, b) => a[2] - b[2]);

  let parents = Array.from({ length: n + 1 }, (_, i) => i);

  let counting = 0;

  let firstCost = 0;
  let secondCost = 0;

  const union = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);

    parents[nowY] = nowX;
  };

  const isUnion = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);

    return nowX === nowY;
  };

  const find = (x) => {
    if (x === parents[x]) return x;

    const nowX = find(parents[x]);
    parents[x] = nowX;
    return nowX;
  };

  const [entrance, one, cost] = firstCase.split(" ").map(Number);
  union(entrance, one);
  cost ? "" : (firstCost += 1);

  for (let i = 0; i < m; i++) {
    const [a, b, cost] = graph[i];
    if (!isUnion(a, b)) {
      union(a, b);
      cost ? "" : (firstCost += 1);
      counting += 1;
    }
    if (counting === n) break;
  }

  parents = Array.from({ length: n + 1 }, (_, i) => i);
  graph.sort((a, b) => b[2] - a[2]);
  union(entrance, one);
  cost ? "" : (secondCost += 1);

  counting = 0;

  for (let i = 0; i < m; i++) {
    const [a, b, cost] = graph[i];
    if (!isUnion(a, b)) {
      union(a, b);
      cost ? "" : (secondCost += 1);
      counting += 1;
    }
    if (counting === n) break;
  }

  const firstAnswer = firstCost ** 2;
  const secondAnswer = secondCost ** 2;

  return firstAnswer - secondAnswer;
};
