const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [N, ...graph] = input;
  console.log(solution(N, graph));
});

const solution = (N, input) => {
  const [Q, W] = N.split(" ").map(Number);
  const graph = input.map((e) => e.split(" ").map(Number));
  const safe = [];
  const virus = [];
  let max = 0;
  for (let i = 0; i < Q; i++) {
    for (let j = 0; j < W; j++) {
      if (graph[i][j] === 0) safe.push([i, j]);
      if (graph[i][j] === 2) virus.push([i, j]);
    }
  }
  const bfs = (change) => {
    const dx = [-1, 1, 0, 0];
    const dy = [0, 0, -1, 1];
    const temp_graph = graph.map((e) => [...e]);
    const temp_virus = virus.map((e) => [...e]);
    let count = 0;
    change.forEach((e) => {
      const [x, y] = e;
      temp_graph[x][y] = 1;
    });

    while (temp_virus.length > 0) {
      const [x_1, y_1] = temp_virus.shift();
      for (let i = 0; i <= 3; i++) {
        const nx = x_1 + dx[i];
        const ny = y_1 + dy[i];
        if (
          nx >= 0 &&
          ny >= 0 &&
          nx < Q &&
          ny < W &&
          temp_graph[nx][ny] === 0
        ) {
          temp_graph[nx][ny] = 2;
          count += 1;
          temp_virus.push([nx, ny]);
        }
      }
    }
    max = Math.max(max, safe.length - count - 3);
  };

  for (let first = 0; first < safe.length; first++) {
    for (let second = first + 1; second < safe.length; second++) {
      for (let third = second + 1; third < safe.length; third++) {
        bfs([safe[first], safe[second], safe[third]]);
      }
    }
  }

  return max;
};
