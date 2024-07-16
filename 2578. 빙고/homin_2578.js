const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [line1, line2, line3, line4, line5, ...call] = input;
  const myBoard = [
    line1.split(" ").map(Number),
    line2.split(" ").map(Number),
    line3.split(" ").map(Number),
    line4.split(" ").map(Number),
    line5.split(" ").map(Number),
  ];

  console.log(
    solution(myBoard, call.map((e) => e.split(" ").map(Number)).flat())
  );
});

const solution = (myBoard, call) => {
  const isColCheckBingo = (arr) => arr.every((e) => e === 0);
  const isLowCheckBingo = (arr) => arr.every((e) => e === 0);
  const isCrossCheckBingo = (arr) => arr.every((e) => e === 0);

  let changeBoard = myBoard;
  let flag = 0;
  call.forEach((e, index) => {
    changeBoard = changeBoard.map((i) => i.map((j) => (j === e ? 0 : j)));
    let count = 0;

    // 가로열 빙고 확인
    changeBoard.forEach((e) => {
      isLowCheckBingo(e) ? (count += 1) : count;
    });

    // 세로열 빙고 확인
    for (let i = 0; i <= 4; i++) {
      const temp = [
        changeBoard[0][i],
        changeBoard[1][i],
        changeBoard[2][i],
        changeBoard[3][i],
        changeBoard[4][i],
      ];
      isColCheckBingo(temp) ? (count += 1) : count;
    }

    // 대각선 빙고 확인
    isCrossCheckBingo([
      changeBoard[0][0],
      changeBoard[1][1],
      changeBoard[2][2],
      changeBoard[3][3],
      changeBoard[4][4],
    ])
      ? (count += 1)
      : count;

    isCrossCheckBingo([
      changeBoard[0][4],
      changeBoard[1][3],
      changeBoard[2][2],
      changeBoard[3][1],
      changeBoard[4][0],
    ])
      ? (count += 1)
      : count;

    // 종료플래그가 세워지지 않았고, 빙고가 세줄이 되는 숫자를 부르는 순간
    if (flag === 0 && count >= 3) {
      flag = index + 1;
    }
  });

  return flag;
};
