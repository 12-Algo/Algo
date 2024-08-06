// 부등호

const fs = require("fs");
const [N, input] = fs.readFileSync("./input.txt").toString().trim().split("\n");
const signs = input.split(" ");

let max = "";
let min = "";

for (let i = 0; i <= 9; i++) {
  const arr = [];
  const exist = new Array(10).fill(0);
  arr.push(i);
  exist[i] = 1;
  backtracking(arr, 0, exist);
  exist[i] = 0;
}

console.log(`${max}\n${min}`);

function backtracking(arr, signIdx, exist) {
  if (arr.length === +N + 1) {
    let result = arr.join("");
    min === "" ? (min = result) : (max = result);
    return;
  }
  let cur = arr.at(-1);
  for (let n = 0; n <= 9; n++) {
    if (signs[signIdx] === "<" && cur < n && !exist[n]) {
      arr.push(n);
      exist[n] = 1;
      backtracking(arr, signIdx + 1, exist);
      arr.pop();
      exist[n] = 0;
    } else if (signs[signIdx] === ">" && cur > n && !exist[n]) {
      arr.push(n);
      exist[n] = 1;
      backtracking(arr, signIdx + 1, exist);
      arr.pop();
      exist[n] = 0;
    }
  }
}
