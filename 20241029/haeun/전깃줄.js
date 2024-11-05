const fs = require("fs");
const [[N], ...input] = fs.readFileSync(0).toString().trim().split("\n").map(line => line.split(" ").map(Number));
const dp = new Array(501).fill(1);

input.sort((a, b) => a[0] - b[0]);

let totalMax = 0;
for (let i = N - 1; i >= 0; i--) {
    const target = input[i][1];
    let max = 0;

    for (let j = i + 1; j < N; j++) {
        if (input[j][1] > target) {
            max = Math.max(max, dp[input[j][1]]);
        }
    }
    dp[target] += max;
    totalMax = Math.max(totalMax, dp[target]);
}
console.log(N - totalMax);