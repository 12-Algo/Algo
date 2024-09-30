const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [nm, ...inputs] = input;
  console.log(solution(nm, inputs));
});

const solution = (nm, inputs) => {
  const [n, m] = nm.split(" ").map(Number);
  const nodeGraph = inputs.map((e) => e.split(" ").map(Number));
  let answer = 0;
  let counting = 0;

  nodeGraph.sort((a, b) => a[2] - b[2]);

  const parents = Array.from({ length: n + 1 }, (_, i) => i);

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

  //   const isAnswer = () => {
  //     for (let i = 1; i < n + 1; i++) {
  //       find(i);
  //     }
  //     const set = new Set(parents);

  //     // 0번 인덱스도 초기화해둬서..
  //     return set.size === 3;
  //   };

  const canUnion = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);

    return nowX !== nowY;
  };

  for (let i = 0; i < m; i++) {
    const [x, y, cost] = nodeGraph[i];
    if (canUnion(x, y)) {
      union(x, y);
      answer += cost;
      counting += 1;
    }

    if (counting === n - 2) {
      break;
    }
  }
  return n === 2 ? 0 : answer;
};
