const fs = require("fs");
const [[N, _], known, ...parties] = fs
  .readFileSync(0)
  .toString()
  .trim()
  .split("\n")
  .map((line) => line.split(" ").map(Number));
const parents = Array.from({ length: N + 1 }, (_, i) => i);
let count = parties.length;
function find(a) {
  if (parents[a] !== a) parents[a] = find(parents[a]);
  return parents[a];
}
function union(a, b) {
  const fa = find(a);
  const fb = find(b);
  if (fa !== fb) parents[fa] = fb;
}
for (const party of parties) {
  if (party.length > 2) {
    for (let i = 1; i < party.length - 1; i++) {
      const fa = find(party[i]);
      const fb = find(party[i + 1]);
      if (fa !== fb) union(party[i], party[i + 1]);
    }
  }
}
for (const party of parties) {
  let flag = false;
  for (let i = 1; i <= party[0]; i++) {
    const fa = find(party[i]);
    for (let j = 1; j <= known[0]; j++) {
      const fb = find(known[j]);
      if (fa === fb) {
        flag = true;
        break;
      }
    }
  }
  if (flag) count--;
}
console.log(count);
