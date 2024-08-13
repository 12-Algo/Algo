const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const inputs = input[0].split(" ").map(Number);
  const obstacle = input[1].split(" ").map(Number);

  console.log(solution(inputs, obstacle));
});

const solution = (inputs, obstacle) => {
  const [n, k] = inputs;

  for (let i = 1; i < n; i++) {
    let start = 0;
    const visited = Array.from({ length: n }).fill(0);
    visited[start] = 1;
    obstacle.forEach((e) => {
      visited[e - 1] = 1;
    });
    while (true) {
      start = (start + i) % n;
      if (visited[start] === 1) {
        break;
      }
      visited[start] = 1;

      if (start === k - 1) {
        return i;
      }
    }
  }
};
