const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [moveCount, nm, ...inputs] = input;
  console.log(solution(Number(moveCount), nm, inputs));
});

const solution = (moveCount, nm, inputs) => {
  const [row, col] = nm.split(" ").map(Number);
  const graph = inputs.map((e) => e.split(" ").map(Number));

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];

  const horse_dx = [-2, -2, -1, -1, 1, 1, 2, 2];
  const horse_dy = [-1, 1, -2, 2, -2, 2, 1, -1];

  const visited = Array.from({ length: col }, () =>
    Array.from({ length: row }, () => Array(moveCount + 1).fill(false))
  );

  const queue = [[0, 0, 0, 0]];
  visited[0][0][0] = true;

  while (queue.length > 0) {
    const [x, y, horseMoves, distance] = queue.shift();

    if (x === col - 1 && y === row - 1) {
      return distance;
    }

    for (let i = 0; i < 4; i++) {
      const nx = x + dx[i];
      const ny = y + dy[i];
      if (
        nx >= 0 &&
        nx < col &&
        ny >= 0 &&
        ny < row &&
        !visited[nx][ny][horseMoves] &&
        graph[nx][ny] === 0
      ) {
        visited[nx][ny][horseMoves] = true;
        queue.push([nx, ny, horseMoves, distance + 1]);
      }
    }

    if (horseMoves < moveCount) {
      for (let i = 0; i < 8; i++) {
        const nx = x + horse_dx[i];
        const ny = y + horse_dy[i];
        if (
          nx >= 0 &&
          nx < col &&
          ny >= 0 &&
          ny < row &&
          !visited[nx][ny][horseMoves + 1] &&
          graph[nx][ny] === 0
        ) {
          visited[nx][ny][horseMoves + 1] = true;
          queue.push([nx, ny, horseMoves + 1, distance + 1]);
        }
      }
    }
  }

  return -1;
};
