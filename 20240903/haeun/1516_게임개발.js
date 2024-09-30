// 게임 개발
// 위상 정렬 풀이

const fs = require("fs");
const [[N], ...input] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

// 인접리스트, 집입차수, 가중치, 누적비용
const edges = Array.from({ length: N }, () => []);
const indegrees = new Array(N).fill(0);
const weights = new Array(N).fill(0);
const result = new Array(N).fill(0);

for (let i = 0; i < N; i++) {
  // 가중치(걸리는 시간) 초기화
  const time = input[i][0];
  weights[i] = time;

  for (let j = 1; j < input[i].length - 1; j++) {
    // 유향 그래프를 고려한 리스트 생성, 진입차수
    const node = input[i][j] - 1;
    edges[node].push(i);
    indegrees[i]++;
  }
}

// 큐에 진입차수가 0인 노드의 인덱스를 넣고 누적비용 갱신
const queue = [];
let head = 0;

indegrees.forEach((indegree, idx) => {
  if (indegree === 0) {
    queue.push(idx);
    result[idx] = weights[idx];
  }
});

while (queue.length > head) {
  const n = queue[head++];

  for (const nn of edges[n]) {
    indegrees[nn]--;
    result[nn] = Math.max(result[nn], result[n] + weights[nn]); // 누적비용 갱신
    if (indegrees[nn] === 0) {
      // 다음 노드가 진입차수가 0이 됐는지 확인
      queue.push(nn);
    }
  }
}

console.log(result.join("\n"));
