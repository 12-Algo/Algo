class MinHeap {
  constructor() {
    this.heap = [];
  }

  size() {
    return this.heap.length;
  }

  swap(a, b) {
    [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
  }
  add(a) {
    this.heap.push(a);
    this.bubbleUp();
  }

  poll() {
    if (this.heap.length === 1) return this.heap.pop();
    let value = this.heap[0];
    this.heap[0] = this.heap.pop();
    this.bubbleDown();
    return value;
  }

  bubbleUp() {
    let i = this.heap.length - 1;
    let parent = parseInt((i - 1) / 2);

    while (i > 0) {
      if (this.heap[i][2] >= this.heap[parent][2]) break;
      this.swap(i, parent);
      i = parent;
      parent = parseInt((i - 1) / 2);
    }
  }
  bubbleDown() {
    let i = 0;
    let left = i * 2 + 1;
    let right = i * 2 + 2;

    while (1) {
      let smaller = i;
      if (this.heap[left] && this.heap[left][2] < this.heap[smaller][2])
        smaller = left;
      if (this.heap[right] && this.heap[right][2] < this.heap[smaller][2])
        smaller = right;
      if (smaller === i) break;

      this.swap(smaller, i);
      i = smaller;
      left = i * 2 + 1;
      right = i * 2 + 2;
    }
  }
}

const fs = require("fs");
const [nums, ...boardContent] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n");

const [M, N] = nums.split(" ").map(Number);
const board = boardContent.map((line) => line.split("").map(Number));
const dx = [0, 1, 0, -1];
const dy = [1, 0, -1, 0];
const visited = Array.from({ length: N }, () => new Array(M).fill(0));
const dist = Array.from({ length: N }, () => new Array(M).fill(Infinity));

const pq = new MinHeap();
pq.add([0, 0, board[0][0]]);
visited[0][0] = 1;
dist[0][0] = 0;

while (pq.size() !== 0) {
  const [x, y, cost] = pq.poll();

  if (dist[x][y] < cost) continue;

  for (let i = 0; i < 4; i++) {
    const [nx, ny] = [x + dx[i], y + dy[i]];
    if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

    if (dist[nx][ny] > cost + board[nx][ny]) {
      dist[nx][ny] = cost + board[nx][ny];
      visited[nx][ny] = 1;
      pq.add([nx, ny, dist[nx][ny]]);
    }
  }
}

console.log(dist[N - 1][M - 1]);
