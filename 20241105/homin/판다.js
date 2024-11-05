const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [n, ...arr] = input;
  console.log(solution(Number(n), arr));
});

const solution = (n, arr) => {
  const graph = arr.map((e) => e.split(" ").map(Number));
  const dp = Array.from(Array(n), () => Array(n).fill(-1));

  const dfs = (x, y) => {
    if (dp[x][y] !== -1) return dp[x][y];

    const dx = [0, 0, 1, -1];
    const dy = [-1, 1, 0, 0];

    let result = 0;

    for (let i = 0; i < 4; i++) {
      const nx = dx[i] + x;
      const ny = dy[i] + y;

      if (
        nx >= 0 &&
        nx < n &&
        ny >= 0 &&
        ny < n &&
        graph[nx][ny] > graph[x][y]
      ) {
        result = Math.max(result, dfs(nx, ny) + 1);
      }
    }
    dp[x][y] = result;
    return result;
  };

  let answer = 0;

  for (let y = 0; y < n; y++) {
    for (let x = 0; x < n; x++) {
      answer = Math.max(answer, 1 + dfs(x, y));
    }
  }

  return answer;
};

// 각 좌표별로 DP테이블을 만들어서 나보다 작은 곳으로 이동할 수 있는 최대값을 저장한다.
