const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  console.log(solution(input[1].split(" ").map(Number)));
});

const solution = (inputs) => {
  let max = 0;

  const calculateMax = (arr) => {
    let temp = 0;
    for (let i = 0; i < arr.length - 1; i++) {
      temp += Math.abs(inputs[arr[i]] - inputs[arr[i + 1]]);
    }
    return temp;
  };

  const permutation = (item) => {
    if (item.length === inputs.length) {
      max = Math.max(calculateMax(item), max);
    }

    for (let i = 0; i < inputs.length; i++) {
      if (!item.includes(i)) {
        permutation([...item, i]);
      }
    }
  };

  permutation([]);
  return max;
};

/**
 *
 * 질문, 오답이 발생했었는데 이때 index로 한게 아니라 원본값으로 해서 0 2 2 3 같은 경우는 중복처리가 됐음
 * 그래서 index로 변경해서 계산식에서 인덱스참조해서 계산했는데, 입력할 때 원본을 입력하고 index를 기억하는 방법은?
 * 가령 0,2,2,3 인 경우
 * 배열엔 [0,2,3]이 들어있고 들어가야 할 인덱스가 2번 인덱스임을 전달할 수 있는 방법은 무엇인가..
 */
