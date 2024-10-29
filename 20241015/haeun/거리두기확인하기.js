// 거리두기 확인하기
function solution(places) {
  const answer = [];
  const dx = [0, 1, 0, -1, 1, 1, -1, -1];
  const dy = [1, 0, -1, 0, 1, -1, 1, -1];
  for (const place of places) {
    answer.push(simulate(place));
  }
  return answer;
  function simulate(place) {
    for (let i = 0; i < 5; i++) {
      for (let j = 0; j < 5; j++) {
        if (place[i][j] === "P") {
          if (bfs(i, j, place) === 0) return 0;
        }
      }
    }
    return 1;
  }
  function bfs(r, c, board) {
    const visited = Array.from({ length: 5 }, () => new Array(5).fill(0));
    const queue = [[r, c, 0]];
    visited[r][c] = 1;
    let head = 0;
    while (queue.length > head) {
      const [x, y, depth] = queue[head++];
      if (depth > 2) continue;
      for (let i = 0; i < 4; i++) {
        const [nx, ny] = [x + dx[i], y + dy[i]];
        if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || visited[nx][ny] === 1)
          continue;
        if (depth < 2 && board[nx][ny] === "P") {
          return 0;
        }
        if (board[nx][ny] !== "X") {
          visited[nx][ny] = 1;
          queue.push([nx, ny, depth + 1]);
        }
      }
    }
    return 1;
  }
}
