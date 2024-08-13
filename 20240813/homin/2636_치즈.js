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

  console.log(solution(temp, inputs).join("\n"));
});

const solution = (temp, inputs) => {
  const [m, n] = temp.split(" ").map(Number);
  const graph = inputs.map((e) => e.split(" ").map(Number));

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];

  let count = 0;
  let lastCheeze = 0;

  const bfs = () => {
    const visited = Array.from({ length: m }, () => new Array(n).fill(0));
    const queue = [[0, 0]];
    const visitedNode = [];
    visited[0][0] = 1;

    while (queue.length !== 0) {
      const now = queue.shift();
      const [x, y] = now;
      for (let i = 0; i < 4; i++) {
        const nx = x + dx[i];
        const ny = y + dy[i];

        if (nx >= 0 && ny >= 0 && nx < m && ny < n && visited[nx][ny] === 0) {
          if (graph[nx][ny] === 1) {
            visitedNode.push([nx, ny]);
          } else {
            queue.push([nx, ny]);
          }
          visited[nx][ny] = 1;
        }
      }
    }

    if (visitedNode.length === 0) {
      return false;
    }

    lastCheeze = visitedNode.length;

    for (const [a, b] of visitedNode) {
      graph[a][b] = 0;
    }

    return true;
  };

  while (bfs()) {
    count += 1;
  }

  return [count, lastCheeze];
};

/**
 *
 * 초기 접근 : DFS돌면서 내가 다음에 방문 할 예정인 node의 주변에 0이 있으면, 해당 노드를 최대한 방문한다
 * -> 문제점 발견 : 외부의 공기만을 계산해야한다. : 예제에 있는 가운데 비어있는 공간은 침투하지 못한다
 * -> ㄴ자로 된 공간에 방문하지 않는다.
 */

/**
 * 찾아본 정답
 * 전체를 bfs도는데 -> 반드시 0,0부터 돈다
 * 이유 : 0,0에는 치즈가 있을 수 없고 queue에 저장할 내용은 0인 좌표값이고 node라는 0과 닿아있는 치즈공간을 저장하는 새로운 배열을 만든다.
 * bfs를 돌때마다 카운팅을 진행 -> 0과 맞닿아 있는 치즈를 모두 지울 수 있음
 * bfs를 돌고 1이 저장되어있던 배열의 길이를 저장함 -> 가장 최근의 상태를 업데이트
 */
