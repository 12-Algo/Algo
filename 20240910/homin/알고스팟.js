class PriorityQueue {
  constructor(compare) {
    // 계산 편의성을 위해 0번 index는 사용하지 않는다.
    this.heap = [null];
    // 인스턴스 생성 시 우선순위를 비교하는 함수를 받는다.
    this.compare = compare;
  }

  push(item) {
    // 트리의 마지막 노드에 요소를 추가한다.
    this.heap.push(item);
    let currentNode = this.heap.length - 1;
    let parentNode = Math.floor(currentNode / 2);

    // 현재 노드와 부모 노드의 우선순위를 비교한다
    while (
      parentNode !== 0 &&
      this.compare(this.heap[currentNode], this.heap[parentNode])
    ) {
      // 우선순위가 높다면(낮다면) 부모 노드와 순서를 바꾼다.
      this._swap(parentNode, currentNode);
      // 현재 노드와 부모 노드의 인덱스를 업데이트한다.
      currentNode = parentNode;
      parentNode = Math.floor(currentNode / 2);
    }
  }

  pop() {
    if (this.heap.length === 1) return;
    if (this.heap.length === 2) return this.heap.pop();

    const top = this.heap[1];
    // 루트 노드를 삭제하고 마지막 노드를 루트 노드에 위치시킨다.
    this.heap[1] = this.heap.pop();

    let currentNode = 1;
    let leftChild = currentNode * 2;
    let rightChild = currentNode * 2 + 1;

    // 현재 노드와 자식 노드들의 우선 순위를 비교한다.
    while (
      (leftChild < this.heap.length &&
        this.compare(this.heap[leftChild], this.heap[currentNode])) ||
      (rightChild < this.heap.length &&
        this.compare(this.heap[rightChild], this.heap[currentNode]))
    ) {
      // 자식 노드들 중에서 더 우선순위가 높은(낮은) 노드와 순서를 바꾼다.
      const targetNode =
        rightChild < this.heap.length &&
        this.compare(this.heap[rightChild], this.heap[leftChild])
          ? rightChild
          : leftChild;
      this._swap(currentNode, targetNode);
      // 현재 노드와 자식 노드들의 인덱스를 업데이트한다.
      currentNode = targetNode;
      leftChild = currentNode * 2;
      rightChild = currentNode * 2 + 1;
    }

    return top;
  }

  size() {
    return this.heap.length - 1;
  }

  top() {
    return this.heap[1];
  }

  _swap(a, b) {
    [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
  }
}

const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [N, ...inputs] = input;
  console.log(solution(N, inputs));
});

const solution = (nm, inputs) => {
  const [m, n] = nm.split(" ").map(Number);
  const minHeap = new PriorityQueue((a, b) => a[2] < b[2]);
  const graph = Array.from({ length: n }, () => new Array(m).fill(Infinity));
  const visited = Array.from({ length: n }, () => new Array(m).fill(false));
  const costGraph = inputs.map((e) => e.split("").map(Number));

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];

  graph[0][0] = 0;
  minHeap.push([0, 0, 0]);

  while (minHeap.size() !== 0) {
    const [x, y, cost] = minHeap.pop();
    visited[x][y] = true;

    for (let i = 0; i < 4; i++) {
      const nx = dx[i] + x;
      const ny = dy[i] + y;

      if (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny]) {
        const newCost = graph[x][y] + costGraph[nx][ny];
        if (newCost < graph[nx][ny]) {
          graph[nx][ny] = newCost;
          minHeap.push([nx, ny, newCost]);
        }
      }
    }
  }
  return graph[n - 1][m - 1];

  //   return answer;
};
