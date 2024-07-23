const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  console.log(solution(input[0]));
});

const solution = (n, memo = [0, 0]) => {
  let i = 2;
  while (i <= n) {
    memo[i] = memo[i - 1] + 1;
    if (i % 3 === 0) {
      memo[i] = Math.min(memo[i], memo[i / 3] + 1);
    }
    if (i % 2 === 0) {
      memo[i] = Math.min(memo[i], memo[i / 2] + 1);
    }
    i += 1;
  }
  return memo[n];
};
