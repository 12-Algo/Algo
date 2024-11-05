const fs = require("fs");
const [[N], ...board] = fs.readFileSync(0).toString().trim().split("\n").map(line => line.split(" ").map(Number));
const dp = Array.from({ length: N }, () => new Array(N).fill(0));

const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];

let answer = 1
for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
        answer = Math.max(answer, dfs(i, j));
    }
}

function dfs(x, y) {
    if (dp[x][y] !== 0) return dp[x][y];

    dp[x][y] = 1;

    for (let i = 0; i < 4; i++) {
        const [nx, ny] = [x + dx[i], y + dy[i]];
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
        if (board[nx][ny] > board[x][y]) {
            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
        }
    }

    return dp[x][y];
}
console.log(dp)