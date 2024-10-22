function solution(land) {
  const [n, m] = [land[0].length, land.length];
  const answerArray = new Array(n).fill(0);
  const visited = Array.from({ length: n }, () => new Array(m).fill(false));

  const landDict = {};
  let landNumber = 2;

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];

  const bfs = (x, y) => {
    const queue = [[x, y]];
    let index = 0;
    while (queue.length !== index) {
      const [nowX, nowY] = queue[index];
      index += 1;
      for (let i = 0; i < 4; i++) {
        const nx = nowX + dx[i];
        const ny = nowY + dy[i];
        if (
          nx >= 0 &&
          ny >= 0 &&
          nx < n &&
          ny < m &&
          !visited[nx][ny] &&
          land[ny][nx] === 1
        ) {
          visited[nx][ny] = true;
          land[ny][nx] = landNumber;
          queue.push([nx, ny]);
        }
      }
    }
    landDict[landNumber] = queue.length;
  };

  for (let x = 0; x < n; x++) {
    for (let y = 0; y < m; y++) {
      if (!visited[x][y] && land[y][x] === 1) {
        visited[x][y] = true;
        land[y][x] = landNumber;
        bfs(x, y);
        landNumber += 1;
      }
    }
  }

  let answer = 0;

  for (let x = 0; x < n; x++) {
    const set = new Set();
    for (let y = 0; y < m; y++) {
      if (land[y][x]) {
        set.add(land[y][x]);
      }
    }
    const temp = [...set].reduce((acc, cur) => acc + landDict[cur], 0);
    answer = Math.max(answer, temp);
  }

  return answer;
}
