const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [n, ...inputs] = input;
  console.log(solution(n, inputs));
});

const solution = (n, inputs) => {
  const [c, _] = n.split(" ").map(Number);
  const dp = new Array(c + 1).fill(Infinity);
  // dp에 Index는 비용, value는 고객

  const tc = inputs.map((e) => e.split(" ").map(Number));
  tc.sort((a, b) => a[0] - b[0]);

  dp[0] = 0;

  tc.forEach((e) => {
    const [consumer, payload] = e;
    for (let i = 0; i < c + 1; i++) {
      if (i <= payload) {
        // ex) tc에서 정해진 손님 이하의 고객에는 동일한 payload이므로 dp에 저장
        dp[i] = Math.min(dp[i], consumer);
      } else {
        // 3,5일 때 3번째 전 고객에 3번째 고객을 유치하는데 드는 비용을 더하면 동일값이 나옴
        // 작은순으로 정렬해야 하는 이유임
        dp[i] = Math.min(dp[i], dp[i - payload] + dp[payload]);
      }
    }
  });

  //   console.log(dp);
  return dp[c];
};
