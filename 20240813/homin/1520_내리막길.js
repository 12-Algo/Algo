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
  console.log(solution(temp, inputs));
});

const solution = (temp, inputs) => {
  const [n, m] = temp.split(" ").map(Number);
  const graph = inputs.map((e) => e.split(" ").map(Number));
  const dp = Array.from({ length: n }, () => new Array(m).fill(-1)); // Initialize with -1

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];

  dp[n - 1][m - 1] = 1; // Start from the destination

  const dfs = (x, y) => {
    if (dp[x][y] !== -1) {
      return dp[x][y];
    }

    let dpPoint = 0;

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];

      if (
        nx >= 0 &&
        ny >= 0 &&
        nx < n &&
        ny < m &&
        graph[nx][ny] < graph[x][y]
      ) {
        dpPoint += dfs(nx, ny);
      }
    }
    dp[x][y] = dpPoint;
    return dpPoint;
  };

  return dfs(0, 0);
};
