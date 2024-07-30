const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  console.log(solution(input[0]));
});

const solution = (str) => {
  let answer = [];

  for (let i = 1; i < str.length - 1; i++) {
    const first = str.slice(0, i).split("").reverse().join("");
    for (let j = i + 1; j < str.length; j++) {
      const second = str.slice(i, j).split("").reverse().join("");
      const third = str.slice(j).split("").reverse().join("");
      const temp = first + second + third;
      answer.push(temp);
    }
  }
  answer.sort();
  return answer[0];
};
