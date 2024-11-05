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

const 회전 = (n, arr) => {
  const graph = Array.from(Array(n), () => Array(n).fill(0));
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      graph[n - 1 - j][i] = arr[i][j];
    }
  }
  return 중력(n, graph);
};

const 중력 = (n, arr) => {
  for (let i = 0; i < n; i++) {
    let bottom = n - 1;
    for (let j = n - 1; j >= 0; j--) {
      if (arr[j][i] >= 0) {
        [arr[bottom][i], arr[j][i]] = [arr[j][i], arr[bottom][i]];
        bottom--;
      } else if (arr[j][i] === -1) {
        bottom = j - 1;
      }
    }
  }
  return arr;
};

const solution = (nm, arr) => {
  const [n, m] = nm.split(" ").map(Number);
  let answer = 0;
  let graph = arr.map((e) => e.split(" ").map(Number));

  const bfs = (i, j, num) => {
    const dx = [0, 0, 1, -1];
    const dy = [-1, 1, 0, 0];
    const queue = [[i, j]];
    const visited = Array.from(Array(n), () => Array(n).fill(false));
    visited[i][j] = true;
    let blocks = [[i, j]];
    let rainbowCount = 0;

    while (queue.length > 0) {
      const [x, y] = queue.shift();
      for (let k = 0; k < 4; k++) {
        const nx = x + dx[k];
        const ny = y + dy[k];
        if (
          nx >= 0 &&
          nx < n &&
          ny >= 0 &&
          ny < n &&
          !visited[nx][ny] &&
          (graph[nx][ny] === num || graph[nx][ny] === 0)
        ) {
          visited[nx][ny] = true;
          queue.push([nx, ny]);
          blocks.push([nx, ny]);
          if (graph[nx][ny] === 0) rainbowCount++;
        }
      }
    }

    const standardBlock = blocks
      .filter(([x, y]) => graph[x][y] === num)
      .sort((a, b) => a[0] - b[0] || a[1] - b[1])[0];

    return [blocks, rainbowCount, ...standardBlock];
  };

  while (true) {
    let maxGroup = null;
    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        if (graph[i][j] > 0) {
          const group = bfs(i, j, graph[i][j]);
          if (
            group[0].length >= 2 &&
            (!maxGroup ||
              group[0].length > maxGroup[0].length ||
              (group[0].length === maxGroup[0].length &&
                group[1] > maxGroup[1]) ||
              (group[0].length === maxGroup[0].length &&
                group[1] === maxGroup[1] &&
                (group[2] > maxGroup[2] ||
                  (group[2] === maxGroup[2] && group[3] > maxGroup[3]))))
          ) {
            maxGroup = group;
          }
        }
      }
    }

    if (!maxGroup || maxGroup[0].length < 2) break;

    answer += maxGroup[0].length ** 2;
    maxGroup[0].forEach(([x, y]) => {
      graph[x][y] = -2;
    });

    graph = 중력(n, graph);
    graph = 회전(n, graph);
  }

  return answer;
};
