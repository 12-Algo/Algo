const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  console.log(solution(input[0], input.slice(1, -1), input.at(-1)));
});

const solution = (NM, graphCase, fromTo) => {
  const [n, m] = NM.split(" ").map(Number);
  const [start, end] = fromTo.split(" ").map(Number);
  let answer = 0;

  const parents = Array.from({ length: n + 1 }, (_, i) => i);

  const union = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);

    if (nowX > nowY) parents[nowX] = nowY;
    else parents[nowY] = nowX;
  };

  const find = (x) => {
    if (x === parents[x]) return x;

    const nowX = find(parents[x]);
    parents[x] = nowX;
    return nowX;
  };

  const isUnion = (x, y) => {
    return find(x) === find(y);
  };

  const graph = graphCase.map((e) => e.split(" ").map(Number));
  graph.sort((a, b) => b[2] - a[2]);

  for (let i = 0; i < graph.length; i++) {
    const [from, to, cost] = graph[i];
    if (!isUnion(from, to)) {
      union(from, to);
    }

    if (isUnion(start, end)) {
      answer = cost;
      break;
    }
  }

  return answer;
};
