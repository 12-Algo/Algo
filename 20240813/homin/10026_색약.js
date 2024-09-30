const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [temp, ...inputs] = input;

  console.log(solution(temp, inputs));
});

const solution = (temp, inputs) => {
  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];
  let normalAnswer = 0;
  let unNormalAnswer = 0;

  const normalGraph = inputs.map((e) => e.split(""));
  const unNormalGraph = inputs.map((e) =>
    e.split("").map((j) => {
      if (j !== "B") return "R";
      return "B";
    })
  );

  const normalDfs = (x, y, now) => {
    normalGraph[x][y] = 1;
    for (let i = 0; i < 4; i++) {
      const nx = dx[i] + x;
      const ny = dy[i] + y;
      if (
        nx >= 0 &&
        ny >= 0 &&
        nx < temp &&
        ny < temp &&
        normalGraph[nx][ny] === now
      ) {
        normalDfs(nx, ny, now);
      }
    }
  };

  const unNormalDfs = (x, y, now) => {
    unNormalGraph[x][y] = 1;
    for (let i = 0; i < 4; i++) {
      const nx = dx[i] + x;
      const ny = dy[i] + y;
      if (
        nx >= 0 &&
        ny >= 0 &&
        nx < temp &&
        ny < temp &&
        unNormalGraph[nx][ny] === now
      ) {
        unNormalDfs(nx, ny, now);
      }
    }
  };

  for (let i = 0; i < temp; i++) {
    for (let j = 0; j < temp; j++) {
      if (normalGraph[i][j] !== 1) {
        normalDfs(i, j, normalGraph[i][j]);
        normalAnswer += 1;
      }
    }
  }

  for (let i = 0; i < temp; i++) {
    for (let j = 0; j < temp; j++) {
      if (unNormalGraph[i][j] !== 1) {
        unNormalDfs(i, j, unNormalGraph[i][j]);
        unNormalAnswer += 1;
      }
    }
  }

  return `${normalAnswer} ${unNormalAnswer}`;
};
