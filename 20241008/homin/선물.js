function solution(friends, gifts) {
  const dic = {};
  const answer = {};
  const degree = {};

  friends.forEach((e) => {
    dic[e] = Array.from({ length: 2 }, () => []);
    answer[e] = 0;
    degree[e] = 0;
  });

  gifts.forEach((e) => {
    const [from, to] = e.split(" ");
    dic[from][0].push(to);
    dic[to][1].push(from);
    degree[to] -= 1;
    degree[from] += 1;
  });

  for (let i = 0; i < friends.length; i++) {
    for (let j = i + 1; j < friends.length; j++) {
      const giveGift = dic[friends[i]][0].filter(
        (e) => e === friends[j]
      ).length;
      const recieveGift = dic[friends[i]][1].filter(
        (e) => e === friends[j]
      ).length;
      if (giveGift < recieveGift) {
        answer[friends[j]] += 1;
      }
      if (giveGift > recieveGift) {
        answer[friends[i]] += 1;
      }
      if (giveGift === recieveGift) {
        if (degree[friends[i]] < degree[friends[j]]) {
          answer[friends[j]] += 1;
        }

        if (degree[friends[i]] > degree[friends[j]]) {
          answer[friends[i]] += 1;
        }
      }
    }
  }

  return Math.max(...Object.values(answer));
}

// 이름별로 객체를 만들고 각 이름별로 [[~에게 보냈다], [~로부터 받았다]]를 기록한다

// 선물지수 : 내가 보낸 선물의 총 갯수 - 내가 받은 선물의 총 갯수
// 서로 나눈 선물의 갯수가 같고, 선물지수도 같다면 선물을 주고받지 않는다
