const fs = require("fs");
const input = fs.readFileSync(0).toString().trim().split(" ").map(Number);
const N = input.length;
const dp = Array.from({ length: 5 }, () => Array.from({ length: 5 }, () => new Array(N).fill(0)));

console.log(topDown(0, 0, 0));

function calculatePower(prev, next) {
    if (prev === 0 || next === 0) return 2;
    if (prev - next === 2 || next - prev === 2) return 4;
    if (prev - next === 0) return 1;
    return 3;
}

function topDown(left, right, index) {
    if (dp[left][right][index] !== 0) return dp[left][right][index];
    if (index === N - 1) return 0;

    const rightside = topDown(left, input[index], index + 1) + calculatePower(right, input[index]);
    const leftside = topDown(input[index], right, index + 1) + calculatePower(left, input[index]);

    return dp[left][right][index] = Math.min(rightside, leftside);
}