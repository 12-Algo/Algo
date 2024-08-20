const fs = require("fs");
const input = fs.readFileSync(0).toString().trim().split("\n");
const n = parseInt(input[0]);
const friends = input.slice(1).map((line) => line.split(""));

const connected = Array.from({ length: n }, () => Array(n).fill(0));

for (let k = 0; k < n; k++) {
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) {
      if (j === i) continue;
      if (
        friends[i][j] === "Y" ||
        (friends[i][k] === "Y" && friends[k][j] === "Y")
      ) {
        connected[i][j] = 1;
      }
    }
  }
}

let answer = 0;
for (const row of connected) {
  const sum = row.reduce((acc, cur) => acc + cur, 0);
  if (answer < sum) answer = sum;
}
console.log(answer);
