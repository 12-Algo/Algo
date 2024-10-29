function solution(points, routes) {
  let newRoutes = routes.map((e) => e.map((i) => [...points[i - 1]]));

  const checkAnswer = (arr) => arr.every((element) => element.length !== 0);

  let count = 0;

  const map = new Map();
  newRoutes.forEach((nowElement) => {
    if (map.has(JSON.stringify(nowElement[0]))) {
      map.set(
        JSON.stringify(nowElement[0]),
        map.get(JSON.stringify(nowElement[0])) + 1
      );
    } else {
      map.set(JSON.stringify(nowElement[0]), 1);
    }
  });

  count += [...map.values()].filter((e) => e > 1).length;

  while (newRoutes.length) {
    newRoutes.forEach((nowElement, i) => {
      // 현위치
      const [nowX, nowY] = nowElement[0];

      // 도착위치
      const [arriveX, arriveY] = nowElement[1];

      if (nowX !== arriveX) {
        if (nowX > arriveX) {
          newRoutes[i][0][0] = nowX - 1;
        } else {
          newRoutes[i][0][0] = nowX + 1;
        }
      } else {
        if (nowY > arriveY) {
          newRoutes[i][0][1] = nowY - 1;
        } else {
          newRoutes[i][0][1] = nowY + 1;
        }
      }
    });

    const map2 = new Map();
    newRoutes.forEach((nowElement) => {
      if (map2.has(JSON.stringify(nowElement[0]))) {
        map2.set(
          JSON.stringify(nowElement[0]),
          map2.get(JSON.stringify(nowElement[0])) + 1
        );
      } else {
        map2.set(JSON.stringify(nowElement[0]), 1);
      }
    });

    count += [...map2.values()].filter((e) => e > 1).length;

    for (let i = 0; i < newRoutes.length; i++) {
      if (JSON.stringify(newRoutes[i][0]) === JSON.stringify(newRoutes[i][1])) {
        // newRoutes[i] 특정 공이 목적지에 도달하면 shift해버림
        newRoutes[i].shift();
      }

      if (newRoutes[i].length === 1) {
        // 최종목적지 하나 남으면 마저 shift해줌
        newRoutes[i].shift();
      }
    }

    //도착한 공 빈배열 되면 제거
    newRoutes = newRoutes.filter((e) => e.length);
  }

  return count;
}

// 1. 각 기계별로 출발지를 정해놓고 해당 포인트에 도달하면 shift하는 방식으로 해야겠는데
