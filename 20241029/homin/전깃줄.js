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
  const line = inputs.map((e) => e.split(" ").map(Number));

  line.sort((a, b) => a[0] - b[0]);

  const newLine = line.map((e) => e[1]);

  const dp = Array(Math.max(n)).fill(1);

  for (let i = 0; i < n; i++) {
    // i는 나자신
    for (let j = 0; j < i; j++) {
      // j는 나보다 작은 애들
      if (newLine[j] < newLine[i]) {
        dp[i] = Math.max(dp[i], dp[j] + 1);
      }
    }
  }
  return n - Math.max(...dp);

  //   정렬 이후에 스택처럼 증가될때만 제거하려고 했는데, 논리가 잘못됨
  //   let current = newLine.pop();
  //   let count = 0;
  //   while (newLine.length) {
  //     const next = newLine.pop();
  //     if (current < next) {
  //       count++;
  //     }
  //     current = next;
  //   }

  //   console.log(count);
};

// 6 8 2 9 5 10 4 1 7 3
