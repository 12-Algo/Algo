const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [nm, ...inputs] = input;
  console.log(solution(nm, inputs));
});

const solution = (nm, inputs) => {
  const [m, n] = nm.split(" ").map(Number);
  let visited = Array.from({ length: n }, () => new Array(m).fill(false));
  const graph = inputs.map((e) => {
    return e.split(" ").map((j) => {
      const temp = [];
      for (let i = 0; i < 4; i++) {
        if ((Number(j) >> i) & 1) {
          temp.push(1);
        } else {
          temp.push(0);
        }
      }
      return temp;
    });
  });

  const dx = [0, -1, 0, 1];
  const dy = [-1, 0, 1, 0];

  const answer = [];

  const bfs = (x, y) => {
    visited[x][y] = true;

    const queue = [[x, y]];
    let count = 0;

    while (queue.length > 0) {
      const [x, y] = queue.shift();
      count += 1;
      for (let i = 0; i < 4; i++) {
        const nx = dx[i] + x;
        const ny = dy[i] + y;

        if (
          graph[x][y][i] === 0 &&
          nx >= 0 &&
          ny >= 0 &&
          nx < n &&
          ny < m &&
          visited[nx][ny] === false
        ) {
          visited[nx][ny] = true;
          queue.push([nx, ny]);
        }
      }
    }
    return count;
  };

  let max = 0;

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      if (visited[i][j] === false) {
        answer.push(bfs(i, j));
      }
    }
  }

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < m; j++) {
      for (let k = 0; k < 4; k++) {
        visited = Array.from({ length: n }, () => new Array(m).fill(false));
        if (graph[i][j][k] === 1) {
          graph[i][j][k] = 0;
          for (let i = 0; i < n; i++) {
            for (let j = 0; j < m; j++) {
              if (visited[i][j] === false) {
                max = Math.max(max, bfs(i, j));
              }
            }
          }
          graph[i][j][k] = 1;
        }
      }
    }
  }

  return [answer.length, Math.max(...answer), max].join("\n");
};
