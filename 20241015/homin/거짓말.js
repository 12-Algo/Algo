const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [nm, knowPeople, ...partyList] = input;
  console.log(solution(nm, knowPeople, partyList));
});

const solution = (nm, knowPeople, temp1) => {
  const [n, m] = nm.split(" ").map(Number);
  const [peopleCount, ...number] = knowPeople.split(" ");
  const knowPeopleList = number.map(Number);
  const partyList = temp1.map((e) => e.split(" ").map(Number));

  //   console.log(n, m, peopleCount, knowPeopleNumber, partyList);

  // 유니온파인드(그룹화) -> 진실알고있는사람 순회하면서 false처리 -> 남은애들 체크

  if (peopleCount === "0") {
    return partyList.length;
  }

  const visited = Array.from({ length: n + 1 }, (_, i) => i);

  const union = (x, y) => {
    const nowX = find(x);
    const nowY = find(y);

    if (nowX > nowY) {
      visited[nowX] = nowY;
    } else {
      visited[nowY] = nowX;
    }
  };

  const find = (x) => {
    if (x === visited[x]) return visited[x];

    const nextX = find(visited[x]);
    visited[x] = nextX;
    return nextX;
  };

  partyList.forEach((e) => {
    const [participantsCont, ...participantsList] = e;
    if (participantsCont === 1) {
      return;
    }
    for (let i = 1; i < participantsCont; i++) {
      union(participantsList[i - 1], participantsList[i]);
    }
  });

  for (let i = 0; i < n + 1; i++) {
    find(i);
  }

  const trueParty = visited.map((e, i) => {
    return knowPeopleList.map((e) => find(e)).includes(e) ? false : true;
  });

  const answer = partyList.map((e) => {
    const [_, ...arr] = e;
    return arr.every((j) => trueParty[j]);
  });

  return answer.filter((e) => e).length;
};

/**
 *
 * 묶고나서 검증해야하는 건 필수임
 * -> 7 8 묶었는데 당시에는 둘다 아니었다가 나중에 7이 거짓말 들어버리면 어떡할거니 ?
 *
 * 다 묶어놓고 검증을 새로 해야함
 */
