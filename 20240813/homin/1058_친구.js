const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [num, ...inputs] = input;
  console.log(
    solution(
      Number(num),
      inputs.map((e) => e.split(""))
    )
  );
});

const solution = (n, friends) => {
  let max = 0;
  const temp = friends.map((e) =>
    e
      .map((j, i) => {
        if (j === "Y") {
          return i;
        }
        return "";
      })
      .filter((e) => e !== "")
  );

  temp.forEach((a, i, arr) => {
    const visited = Array.from({ length: n }).fill(0);

    a.forEach((now) => {
      if (i !== now) {
        visited[now] = 1;
        arr[now].forEach((friend) => {
          if (friend !== i) {
            visited[friend] = 1;
          }
        });
      }
    });
    max = Math.max(max, visited.filter((e) => e).length);
  });

  return max;
};
