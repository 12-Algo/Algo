const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [nm, ...arr] = input;

  console.log(solution(nm, arr));
});

const 중력 = (n, arr) => {
  const newGraph = Array.from(Array(n), () => Array(n).fill(-2));

  for (let i = 0; i < n; i++) {
    let index = n - 1;
    for (let q = 0; q < n; q++) {
      if (arr[q][i] === -1) {
        index = q - 1;
        break;
      }
    }
    for (let j = n - 1; j >= 0; j--) {
      if (index < 0) break;

      if (arr[j][i] === -1) {
        newGraph[j][i] = -1;
      }

      if (arr[j][i] >= 0) {
        newGraph[index--][i] = arr[j][i];
      }
    }
  }
  return newGraph;
};

const solution = (nm, arr) => {
  const [n, m] = nm.split(" ").map(Number);

  let graph = arr.map((e) => e.split(" ").map(Number));
  const visited = Array.from(Array(n), () => Array(n).fill(false));

  const block = [];

  const bfs = (i, j, num) => {
    const dx = [0, 0, 1, -1];
    const dy = [-1, 1, 0, 0];

    const queue = [[i, j]];
    visited[i][j] = true;
    let index = 0;

    while (index < queue.length) {
      const [x, y] = queue[index++];
      for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];
        if (
          nx >= 0 &&
          nx < n &&
          ny >= 0 &&
          ny < n &&
          (graph[nx][ny] === num || graph[nx][ny] === 0) &&
          !visited[nx][ny]
        ) {
          visited[nx][ny] = true;
          queue.push([nx, ny]);
        }
      }
    }

    return queue;
  };

  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (graph[i][j] > 0 && !visited[i][j]) {
        block.push([bfs(i, j, graph[i][j]), i, j]);
      }
    }
  }

  block.sort((a, b) => {
    if (a[0].length === b[0].length) {
      if (a[1] === b[1]) {
        return a[2] - b[2];
      }
      return a[1] - b[1];
    }
    return b[0].length - a[0].length;
  });

  block[0][0].forEach(([x, y]) => {
    graph[x][y] = -2;
  });

  console.log("그래프", graph);
  console.log(중력(n, graph));
};

/**
 *
 * 1. 검은색 무지개 일반 블록있음
 * 2. 일반블록은 M가지 색상이 있고 각 색상은  M이하의 자연수
 * 3. 검은색 : -1
 * 4. 무지개 : 0
 * 상하좌우 +1의 거리만큼이 인접한 칸 이라고 한다.
 *
 * 블록그룹은 연결된 블록의 집합
 * 그룹에는 일반블록이 적어도 하나 있어야 한다. 또한 일반블록의 색은 모두 같아야 한다.
 * 또한 검은색 블록은 포함하면 안되고 무지개블록은 얼마든지 포함해도 됨
 *
 * 그룹에 속한 블록의 개수는 2보다 크거나 같아야 한다.
 *
 * 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 모든 다른칸으로 이동할 수 있어야 한다.
 *
 * 블록 그룹의 기준은 무지개 블록이 아닌, 블록중에서 행의번호 -> 열의 번호가 가작 작은 블록이 기준이다.
 *
 * 라운드별로 진행함
 * 1. 가장 큰 블록그룹을 제거
 * 2. 제거된 블록그룹의 개수의 제곱만큼 점수를 획득
 *  2-1) 블록그룹의 갯수가 동일하다면 가장 작은 그룹(기준) 행->열 기준으로 제거
 * 3. 검은블록을 제외하고 블록을 아래로 떨어뜨림
 * 4. 블록을 반시계 방향으로 90도 회전
 * 5. 한번 더 검은블록을 제외하고 블록을 아래로 떨어뜨림
 */
