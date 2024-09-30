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
  const [col, row] = nm.split(" ").map(Number);
  const parents = Array.from({ length: col * row }, (_, i) => i);
  const stringGraph = inputs.map((e) => e.split(""));

  const union = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);

    if (nowX > nowY) parents[nowX] = nowY;
    else parents[nowY] = nowX;
  };

  const find = (x) => {
    if (x === parents[x]) return x;

    const nextX = find(parents[x]);
    parents[x] = nextX;
    return nextX;
  };

  const checkDirection = {
    D: row,
    U: -row,
    L: -1,
    R: +1,
  };

  for (let i = 0; i < col; i++) {
    for (let j = 0; j < row; j++) {
      const from = i * row + j;
      const to = i * row + j + checkDirection[stringGraph[i][j]];
      union(from, to);
    }
  }

  for (let i = 0; i < col; i++) {
    for (let j = 0; j < row; j++) {
      const from = i * row + j;
      find(from);
    }
  }

  const set = new Set(parents);
  return set.size;
  const isUnion = (x, y) => {};
};
