const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [max, min] = solution(Number(input[0]), input[1]);
  console.log(max);
  console.log(min);
});

const check = (arr, string) => {
  const checkString = string.split(" ");

  for (let i = 0; i < arr.length - 1; i++) {
    if (checkString[i] === ">") {
      if (arr[i] < arr[i + 1]) return false;
    } else {
      if (arr[i] > arr[i + 1]) return false;
    }
  }
  return true;
};

const solution = (k, string) => {
  let result = [];
  let max = 0;
  let min = 9999999999;

  const permutation = (item, result) => {
    if (item.length === k + 1) {
      result.push(item.join(""));
      max = Math.max(max, Number(item.join("")));
      min = Math.min(min, Number(item.join("")));
      return;
    }

    for (let i = 0; i < 10; i++) {
      if (!item.includes(i) && check([...item, i], string)) {
        permutation([...item, i], result);
      }
    }
  };

  permutation([], result);
  return [String(max), "0".repeat(k + 1 - String(min).length) + min];
};
