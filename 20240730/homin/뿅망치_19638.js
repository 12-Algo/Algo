class PriorityQueue {
  constructor(compare) {
    this.heap = [null];
    this.compare = compare;
  }

  size() {
    return this.heap.length;
  }

  push(item) {
    this.heap.push(item);
    let currentNode = this.heap.length - 1;
    let parentNode = Math.floor(currentNode / 2);
    while (
      parentNode !== 0 &&
      this.compare(this.heap[currentNode], this.heap[parentNode])
    ) {
      this._swap(parentNode, currentNode);
      currentNode = parentNode;
      parentNode = Math.floor(currentNode / 2);
    }
  }
  pop() {
    if (this.heap.length === 1) return;
    if (this.heap.length === 2) return this.heap.pop();

    const top = this.heap[1];

    this.heap[1] = this.heap.pop();
    let [currentNode, leftChild, rightChild] = [1, 2, 3];

    while (
      this.compare(this.heap[leftChild], this.heap[currentNode]) ||
      this.compare(this.heap[rightChild], this.heap[currentNode])
    ) {
      const targetNode = this.compare(
        this.heap[rightChild],
        this.heap[leftChild]
      )
        ? rightChild
        : leftChild;

      this._swap(currentNode, targetNode);
      currentNode = targetNode;
      leftChild = currentNode * 2;
      rightChild = currentNode * 2 + 1;
    }

    return top;
  }
  size() {
    return this.heap.length;
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
  const [weapon, ...longTargets] = input;
  console.log(solution(weapon.split(" ").map(Number), longTargets.map(Number)));
});

const solution = (inputs, targets) => {
  const [_, my, maxCount] = inputs;
  let flag = "NO";
  let count = 0;

  const maxHeap = new PriorityQueue((a, b) => b < a);
  targets.forEach((e) => {
    maxHeap.push(e);
  });

  while (count !== maxCount) {
    const now = maxHeap.pop();

    if (now < my) {
      flag = "YES";
      break;
    } else {
      count += 1;
      maxHeap.push(now > 1 ? now / 2 : 1);
    }
  }

  const lastPop = maxHeap.pop();

  if (lastPop < my) {
    flag = "YES";
  }

  if (flag === "YES") return `${flag}\n${count}`;
  return `${flag}\n${Math.floor(lastPop)}`;
};
