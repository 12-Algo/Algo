const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [tempTimeTable, ...tempChattingList] = input;
  const timeTable = tempTimeTable
    .split(" ")
    .map((e) => Number(e.split(":").join("")));
  const chattingList = tempChattingList
    .map((e) => e.split(" "))
    .map((e) => [Number(e[0].split(":").join("")), e[1]]);

  console.log(solution(timeTable, chattingList));
});

const solution = (timeTable, chattingList) => {
  const entrancePeople = new Set();
  const exitPeople = new Set();
  const [first, second, third] = timeTable;

  // 입장
  chattingList.forEach((e) => {
    const [time, nickname] = e;
    if (time <= first) {
      entrancePeople.add(nickname);
    }
    if (time >= second && time <= third) {
      exitPeople.add(nickname);
    }
  });

  const answer = [...exitPeople].filter((e) => entrancePeople.has(e)).length;
  return answer;
};
