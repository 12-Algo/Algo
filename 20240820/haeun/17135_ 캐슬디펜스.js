const fs = require("fs");
// N, M, D (행, 열, 공격 거리)
const [[N, M, D], ...matrix] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

// 각 열에서 공격 여부
const attackers = new Array(M).fill(0);
// 가능한 공격 조합의 개수
const caseLen = (M * (M - 1) * (M - 2)) / 6;
// 모든 공격 조합을 저장할 배열
const attackCases = new Array(caseLen).fill(0);

const newMatrix = new Array(M).fill(0);
let max = 0; // 최대 적 처치 수
let caseIdx = 0; // 공격 조합의 인덱스

// 공격 조합을 찾기
backtracking(0, 0);

// 모든 공격 조합을 시뮬레이션
for (const attackCase of attackCases) {
  const count = simulate(JSON.parse(JSON.stringify(matrix)), attackCase);
  max = Math.max(count, max);
}

console.log(max);

function backtracking(start, count) {
  if (count === 3) {
    // 3개의 공격이 결정되면 공격 조합을 저장
    attackCases[caseIdx++] = [...attackers];
    return;
  }
  for (let i = start; i < M; i++) {
    attackers[i] = 1;
    backtracking(i + 1, count + 1);
    attackers[i] = 0;
  }
}

// 주어진 공격 조합으로 적을 처치
function simulate(mtrx, attack) {
  let count = 0; // 처치된 적의 수
  for (let i = 0; i < N; i++) {
    const died = new Set(); // 처치된 적의 위치를 저장

    // 각 열에서 공격 여부에 따라 적을 찾아서 처리
    for (let y = 0; y < M; y++) {
      if (attack[y]) {
        const queue = [];
        if (mtrx[N - 1][y]) {
          died.add(`[${N - 1},${y}]`);
          continue;
        }
        queue.push([N - 1, y, 1]); // 현재 열에서 공격 시작
        findEnemy(mtrx, queue, died); // 적 찾기 함수 호출
      }
    }

    // 처치된 적의 위치를 업데이트
    Array.from(died)
      .map(JSON.parse)
      .forEach(([r, c]) => {
        count++;
        mtrx[r][c] = 0;
      });

    // 적이 공격받은 후, 맵을 한 줄 위로 이동시키고 새로운 행 추가
    mtrx.pop();
    mtrx.unshift([...newMatrix]);
  }
  return count; // 총 처치된 적의 수를 반환
}

// BFS를 이용하여 적을 찾아서 처리
function findEnemy(mtrx, queue, died) {
  while (queue.length) {
    const [x, y, depth] = queue.shift();

    // 공격 거리 제한에 도달하면 종료
    if (depth >= D) return;

    // 상하좌우로 이동하며 적을 찾기
    for (let i = 0; i < 3; i++) {
      const [nx, ny] = [x + dx[i], y + dy[i]];
      if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 경계를 벗어나면 무시
      if (mtrx[nx][ny]) {
        died.add(`[${nx},${ny}]`); // 적을 찾으면 위치를 저장하고 종료
        return;
      } else queue.push([nx, ny, depth + 1]); // 이동할 위치를 큐에 추가
    }
  }
}
