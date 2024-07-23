const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [inputNumber, ...processes] = input;
  for (let i = 0; i < Number(inputNumber); i++) {
    console.log(solution(processes[i]));
  }
});

const solution = (process) => {
  // 위 , 아래방향일 때 Flag는 1,3 , 좌, 우 방향일 때 플래그는 2,4
  let flag = 1;
  let xPosition = 0;
  let yPosition = 0;

  let minXPosition = 0;
  let maxXPosition = 0;
  let minYPosition = 0;
  let maxYPosition = 0;

  [...process].forEach((e) => {
    if (e === "F") {
      if (flag === 1) {
        xPosition += 1;
        maxXPosition = Math.max(maxXPosition, xPosition);
      }
      if (flag === 2) {
        yPosition += 1;
        maxYPosition = Math.max(maxYPosition, yPosition);
      }
      if (flag === 3) {
        xPosition -= 1;
        minXPosition = Math.min(minXPosition, xPosition);
      }
      if (flag === 4) {
        yPosition -= 1;
        minYPosition = Math.min(minYPosition, yPosition);
      }
    }

    if (e === "B") {
      if (flag === 1) {
        xPosition -= 1;
        minXPosition = Math.min(minXPosition, xPosition);
      }
      if (flag === 2) {
        yPosition -= 1;
        minYPosition = Math.min(minYPosition, yPosition);
      }
      if (flag === 3) {
        xPosition += 1;
        maxXPosition = Math.max(maxXPosition, xPosition);
      }
      if (flag === 4) {
        yPosition += 1;
        maxYPosition = Math.max(maxYPosition, yPosition);
      }
    }
    if (e === "R") {
      flag += 1;
      flag = flag === 5 ? 1 : flag;
    }
    if (e === "L") {
      flag -= 1;
      flag = flag === 0 ? 4 : flag;
    }
  });
  return (maxXPosition - minXPosition) * (maxYPosition - minYPosition);
};
