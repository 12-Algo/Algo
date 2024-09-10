class MaxHeap {
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
      if (this.heap[i][2] <= this.heap[parent][2]) break;
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
      let bigger = i;
      if (this.heap[left] && this.heap[left][2] > this.heap[bigger][2])
        bigger = left;
      if (this.heap[right] && this.heap[right][2] > this.heap[bigger][2])
        bigger = right;
      if (bigger === i) break;

      this.swap(bigger, i);
      i = bigger;
      left = i * 2 + 1;
      right = i * 2 + 2;
    }
  }
}

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
const [[N, M], ...input] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));

const downhill = new MaxHeap();
const uphill = new MinHeap();

for (const [a, b, c] of input) {
  downhill.add([a, b, c]);
  uphill.add([a, b, c]);
}

console.log(kruskal(uphill) - kruskal(downhill));

function kruskal(pq) {
  const parent = Array.from({ length: N + 1 }, (_, i) => i);
  let count = 0;

  while (pq.size() !== 0) {
    const [a, b, c] = pq.poll();
    if (union(a, b, parent) && c == 0) count++;
  }

  return count * count;
}

function find(a, parent) {
  if (a !== parent[a]) parent[a] = find(parent[a], parent);
  return parent[a];
}
function union(a, b, parent) {
  const fa = find(a, parent);
  const fb = find(b, parent);

  if (fa !== fb) {
    a < b ? (parent[fb] = fa) : (parent[fa] = fb);
    return true;
  }
  return false;
}
