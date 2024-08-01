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

const solution = (name) => {
  const map = new Map();
  name
    .split("")
    .sort()
    .forEach((e) => (map.has(e) ? map.set(e, map.get(e) + 1) : map.set(e, 1)));

  const sortingMap = [...map];

  if (sortingMap.filter((e) => e[1] % 2).length > 1) return `I'm Sorry Hansoo`;

  let answer = "";
  let rest = "";

  sortingMap.forEach((e) => {
    let [alpha, count] = e;

    if (count % 2) {
      rest = alpha;
      count -= 1;
    }

    answer += alpha.repeat(count / 2);
  });

  return answer + rest + answer.split("").reverse().join("");
};
