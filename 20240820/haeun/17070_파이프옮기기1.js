const fs = require("fs");
const inputLines = fs.readFileSync(0).toString().split("\n");
const N = +inputLines.shift();
const grid = inputLines.map((line) => line.split(" ").map(Number));

// dp 배열 초기화: dp[x][y][0]은 (x, y) 위치에 대해 가로 방향으로 놓인 경우,
// dp[x][y][1]은 세로 방향으로 놓인 경우, dp[x][y][2]는 대각선 방향으로 놓인 경우
const dp = Array.from({ length: N }, () =>
  Array.from({ length: N }, () => Array(3).fill(0))
);
// 시작점 (0, 1)에 가로 방향으로 놓인 경우를 1로 초기화
dp[0][1][0] = 1;

// 격자의 모든 셀을 순회
for (let x = 0; x < N; x++) {
  for (let y = 0; y < N; y++) {
    // 현재 셀이 장애물인 경우 (1인 경우) 건너뜀
    if (grid[x][y] === 1) continue;

    // 가로 방향으로 이동할 수 있는 경우
    if (y + 1 < N && grid[x][y + 1] === 0) {
      dp[x][y + 1][0] += dp[x][y][0] + dp[x][y][2];
    }

    // 세로 방향으로 이동할 수 있는 경우
    if (x + 1 < N && grid[x + 1][y] === 0) {
      dp[x + 1][y][1] += dp[x][y][1] + dp[x][y][2];
    }

    // 대각선 방향으로 이동할 수 있는 경우 (대각선 이동이 가능한 경우)
    if (
      x + 1 < N &&
      y + 1 < N &&
      grid[x + 1][y + 1] === 0 &&
      grid[x + 1][y] === 0 &&
      grid[x][y + 1] === 0
    ) {
      dp[x + 1][y + 1][2] += dp[x][y][0] + dp[x][y][1] + dp[x][y][2];
    }
  }
}

// 도착점 (N-1, N-1)에서 가능한 모든 경로의 개수를 계산
const routeCount =
  dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];

console.log(routeCount);
