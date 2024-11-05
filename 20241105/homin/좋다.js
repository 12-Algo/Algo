const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [n, m] = input;
  console.log(solution(n, m.split(" ").map(Number)));
});

const solution = (n, arr) => {
  const newArr = arr.sort((a, b) => a - b);
  const answer = newArr.filter((e, i) => {
    let left = 0;
    let right = n - 1;
    while (left < right) {
      const sum = newArr[left] + newArr[right];
      // 나와 합이 같을 때의 예외처리를 해줘야 함
      if (sum === e) {
        if (i === left) {
          left++;
        } else if (i === right) {
          right--;
        } else {
          // 합이 왼쪽과 오른쪽 어느 누구도 아닐 때 true를 리턴
          return true;
        }
      } else if (sum < e) {
        left++;
      } else {
        right--;
      }
    }
    return false;
  });
  return answer.length;
};
