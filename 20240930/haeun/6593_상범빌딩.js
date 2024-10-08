const fs = require("fs");
const inputLines = fs.readFileSync(0).toString().trim().split("\n");
caseStart = 0;

const dx = [0, 1, 0, -1, 0, 0];
const dy = [1, 0, -1, 0, 0, 0];
const dz = [0, 0, 0, 0, 1, -1];

while (inputLines[caseStart] !== "0 0 0") {
  const [L, R, C] = inputLines[caseStart].split(" ").map(Number);

  const board = [];
  const start = [-1, -1, -1];
  const end = [-1, -1, -1];

  for (let i = 0; i < L; i++) {
    board.push(inputLines.slice(caseStart + 1, caseStart + R + 1));

    if (start[0] === -1 || end[0] === -1) {
      for (let j = 0; j < R; j++) {
        for (let k = 0; k < C; k++) {
          if (board[i][j][k] === "S") {
            start[0] = i;
            start[1] = j;
            start[2] = k;
          }
          if (board[i][j][k] === "E") {
            end[0] = i;
            end[1] = j;
            end[2] = k;
          }
        }
      }
    }

    caseStart = caseStart + R + 1;
  }

  const queue = [[...start, 0]];
  let head = 0;
  const visited = Array.from({ length: L }, () =>
    Array.from({ length: R }, () => new Array(C).fill(0))
  );
  visited[start[0]][start[1]][start[2]] = 1;

  let pathLength = -1;

  while (queue.length > head) {
    const [l, r, c, time] = queue[head++];
    if (board[l][r][c] === "E") {
      pathLength = time;
      break;
    }
    for (let i = 0; i < 6; i++) {
      const nl = l + dz[i];
      const nr = r + dx[i];
      const nc = c + dy[i];

      if (
        nl >= L ||
        nl < 0 ||
        nr >= R ||
        nr < 0 ||
        nc >= C ||
        nc < 0 ||
        visited[nl][nr][nc]
      )
        continue;
      visited[nl][nr][nc] = 1;
      if (board[nl][nr][nc] !== "#") {
        queue.push([nl, nr, nc, time + 1]);
      }
    }
  }

  pathLength > 0
    ? console.log(`Escaped in ${pathLength} minute(s).`)
    : console.log("Trapped!");

  //테스트케이스 종료
  caseStart++;
}
