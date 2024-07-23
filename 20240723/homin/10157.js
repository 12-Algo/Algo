const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [N, M] = input[0].split(" ").map(Number);
  const temp = Number(input[1]);
  if (N * M >= temp) {
    console.log(solution(N, M, temp));
  } else {
    console.log(0);
  }
});

const solution = (row, col, temp) => {
  const visited = Array.from({ length: col + 1 }, () =>
    Array.from({ length: row + 1 }).fill(0)
  );

  let nowRow = 1;
  let nowCol = 1;

  let flag = 0;

  let movePoint = 1;

  visited[nowCol][nowRow] = 1;

  while (movePoint < temp) {
    flag %= 4;
    if (flag === 0) {
      if (nowCol >= col || visited[nowCol + 1][nowRow] === 1) {
        flag += 1;
      } else {
        nowCol += 1;
        visited[nowCol][nowRow] = 1;
        movePoint += 1;
      }
    }

    if (flag === 1) {
      if (nowRow >= row || visited[nowCol][nowRow + 1] === 1) {
        flag += 1;
      } else {
        nowRow += 1;
        visited[nowCol][nowRow] = 1;
        movePoint += 1;
      }
    }

    if (flag === 2) {
      if (nowCol <= 1 || visited[nowCol - 1][nowRow] === 1) {
        flag += 1;
      } else {
        nowCol -= 1;
        visited[nowCol][nowRow] = 1;
        movePoint += 1;
      }
    }

    if (flag === 3) {
      if (nowRow <= 1 || visited[nowCol][nowRow - 1] === 1) {
        flag += 1;
      } else {
        nowRow -= 1;
        visited[nowCol][nowRow] = 1;
        movePoint += 1;
      }
    }
  }
  return `${nowRow} ${nowCol}`;
};
