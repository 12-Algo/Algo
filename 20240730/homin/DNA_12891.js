const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [st, dna, splitDNA] = input;
  console.log(
    solution(st.split(" ").map(Number)[1], dna, splitDNA.split(" ").map(Number))
  );
});

const solution = (num, dna, splitDNA) => {
  const [aCount, cCount, gCount, tCount] = splitDNA;

  let front = 0;
  let rear = num - 1;

  const FIRST_WINDOW = {
    A: aCount,
    C: cCount,
    G: gCount,
    T: tCount,
  };

  const dnaWindow = {
    A: 0,
    C: 0,
    G: 0,
    T: 0,
  };

  for (let i = front; i <= rear; i++) {
    dnaWindow[dna[i]] += 1;
  }

  let answer = 0;

  while (rear < dna.length) {
    if (
      dnaWindow["A"] >= FIRST_WINDOW["A"] &&
      dnaWindow["C"] >= FIRST_WINDOW["C"] &&
      dnaWindow["G"] >= FIRST_WINDOW["G"] &&
      dnaWindow["T"] >= FIRST_WINDOW["T"]
    ) {
      answer += 1;
    }

    dnaWindow[dna[front]] -= 1;
    front += 1;
    rear += 1;
    dnaWindow[dna[rear]] += 1;
  }

  return answer;
};
