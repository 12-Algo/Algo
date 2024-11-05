const fs = require("fs");
const [[c, n], ...input] = fs.readFileSync(0).toString().trim().split("\n").map(line => line.split(" ").map(Number));

const dp = new Array(100001).fill(Infinity); // 명 당 최소 비용
dp[0] = 0;

for (let i = 1; i <= 100000; i++) {
    for (const [cost, client] of input) {
        if (i >= client) dp[i] = Math.min(dp[i], dp[i - client] + cost);
    }
}

let answer = Infinity;
for (let i = c; i <= 100000; i++) {
    answer = Math.min(answer, dp[i]);
}

console.log(answer);