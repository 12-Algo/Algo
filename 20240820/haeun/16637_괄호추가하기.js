const fs = require("fs");
const [n, input] = fs.readFileSync(0).toString().trim().split("\n");

let max = -Infinity; // 최대값을 저장할 변수, 초기값은 가장 작은 수로 설정
let nums = []; // 숫자들을 저장할 배열
let operators = []; // 연산자들을 저장할 배열

// 입력에서 숫자와 연산자를 분리하여 배열에 저장
for (let i = 0; i < +n; i++) {
  if (i % 2 === 0) {
    nums.push(+input[i]); // 짝수 인덱스는 숫자
  } else {
    operators.push(input[i]); // 홀수 인덱스는 연산자
  }
}

// 최대값을 찾기 위한 재귀 함수 호출
findMax(0, nums[0]);

// 최종적으로 계산된 최대값을 출력
console.log(max);

// idx는 현재 연산자의 인덱스, total은 현재까지 계산된 총합
function findMax(idx, total) {
  // 모든 연산을 수행했을 때, 최대값을 업데이트
  if (idx === operators.length) {
    max = Math.max(max, total);
    return;
  }

  // 현재 연산자에 따라 계산을 수행하고 다음 단계로 재귀 호출
  let cal = calculate(total, nums[idx + 1], operators[idx]);
  findMax(idx + 1, cal);

  // 괄호를 고려하여 두 개의 연산자를 묶어서 계산할 경우
  if (idx + 2 <= nums.length - 1) {
    cal = calculate(
      total,
      calculate(nums[idx + 1], nums[idx + 2], operators[idx + 1]),
      operators[idx]
    );
    findMax(idx + 2, cal);
  }
}

// 주어진 연산자에 따라 두 수를 계산하는 함수
function calculate(a, b, operator) {
  if (operator === "-") {
    return a - b;
  } else if (operator === "+") {
    return a + b;
  } else {
    return a * b;
  }
}
