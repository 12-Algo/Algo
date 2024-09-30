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
    this.heap[1] = this.heap.pop();

    let currentNode = 1;
    let leftChild = currentNode * 2;
    let rightChild = currentNode * 2 + 1;

    while (leftChild < this.heap.length) {
      let targetNode = leftChild;

      if (
        rightChild < this.heap.length &&
        this.compare(this.heap[rightChild], this.heap[leftChild])
      ) {
        targetNode = rightChild;
      }

      if (this.compare(this.heap[targetNode], this.heap[currentNode])) {
        this._swap(currentNode, targetNode);
        currentNode = targetNode;
        leftChild = currentNode * 2;
        rightChild = currentNode * 2 + 1;
      } else {
        break;
      }
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
  const [n, m] = nm.split(" ").map(Number);

  const cost = Array.from({ length: n + 1 }, () => []);
  // 비용정보

  const answer = Array.from({ length: n + 1 }).fill(0);

  const graph = Array.from({ length: n + 1 }).fill(Infinity);
  // 누적비용

  inputs.forEach((e) => {
    const [from, to, payment] = e.split(" ").map(Number);
    cost[from].push([to, payment]);
    cost[to].push([from, payment]);
  });

  graph[1] = 0;

  const minHeap = new PriorityQueue((a, b) => a[1] < b[1]);

  minHeap.push([1, 0]);

  while (minHeap.size() > 0) {
    const [node, sumPay] = minHeap.pop();

    cost[node].forEach((e) => {
      const [next, distance] = e;

      if (graph[next] > distance + sumPay) {
        graph[next] = distance + sumPay;
        minHeap.push([next, distance + sumPay]);
        answer[next] = node;
      }
    });
  }

  const returnAnswer = answer
    .map((e, i) => {
      if (i < 2) return undefined;
      return `${i} ${e}`;
    })
    .filter((e) => e);

  return [returnAnswer.length, ...returnAnswer].join("\n");
};
