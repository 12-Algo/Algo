const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [_, ...inputs] = input;
  console.log(solution(inputs));
});

const solution = (inputs) => {
  let count = 0;
  const input = [];

  [...new Set(inputs)]
    .sort((a, b) => b.length - a.length)
    .forEach((e) => {
      if (!input.some((v) => v.startsWith(e))) {
        input.push(e);
        count += 1;
      }
    });

  return count;
};
