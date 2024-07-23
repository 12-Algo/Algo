const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [_, switches, humanInput, ...human] = input;
  const answer = solution(switches.split(" ").map(Number), human);
  console.log(
    answer
      .map((e, i) => {
        if ((i + 1) % 20 === 0) return e + "\n";
        return e + " ";
      })
      .join("")
  );
});

const womanChange = (switches, number) => {
  let counting = Math.min(number - 1, switches.length - number);

  switches[number - 1] = switches[number - 1] === 0 ? 1 : 0;
  for (let i = 1; i <= counting; i++) {
    if (switches[number - i - 1] === switches[number + i - 1]) {
      switches[number - i - 1] = switches[number - i - 1] === 0 ? 1 : 0;
      switches[number + i - 1] = switches[number + i - 1] === 0 ? 1 : 0;
    } else {
      break;
    }
  }
  return switches;
};

const manChange = (switches, number) => {
  return switches.map((e, i) => {
    if ((i + 1) % number === 0) {
      return e === 0 ? 1 : 0;
    }
    return e;
  });
};

const solution = (switches, humans) => {
  let switchAnswer = switches;

  humans.forEach((e) => {
    const [gender, number] = e.split(" ").map(Number);
    switchAnswer =
      gender === 1
        ? manChange(switchAnswer, number)
        : womanChange(switchAnswer, number);
  });
  return switchAnswer;
};
