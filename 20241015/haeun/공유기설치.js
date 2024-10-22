const fs = require("fs");
const inputs = fs.readFileSync(0).toString().trim().split("\n");
const [N, C] = inputs.shift().split(" ").map(Number);

const houses = inputs.map(Number);
houses.sort((a, b) => a - b);

let left = 0;
let right = houses[N - 1];

while (left <= right) {
  let mid = parseInt((left + right) / 2);
  let prevCoor = houses[0];
  let count = 1;

  for (let i = 1; i < N; i++) {
    if (houses[i] - prevCoor >= mid) {
      count++;
      prevCoor = houses[i];
    }

    if (count > C) break;
  }

  if (count >= C) left = mid + 1;
  else right = mid - 1;
}
console.log(right);
