const fs = require("fs");
const [T, ...input] = fs.readFileSync(0).toString().trim().split("\n");
let idx = 0;
for (let i = 0; i < +T; i++) {
  const N = +input[idx];
  const next = idx + N + 1;
  const phones = input.slice(idx + 1, next);
  const answer = main(N, phones);
  console.log(answer ? "YES" : "NO");
  idx = next;
}
function main(N, phones) {
  const map = {};
  phones.sort((a, b) => a.length - b.length);
  const lenSet = new Set();
  for (let i = 0; i < N; i++) {
    lenSet.add(phones[i].length);
  }
  for (const phone of phones) {
    for (const len of lenSet) {
      if (phone.length <= len) break;
      const key = phone.substr(0, len);
      map[key] = (map[key] || 0) + 1;
    }
  }
  for (const phone of phones) {
    if (map[phone]) return 0;
  }
  return 1;
}
