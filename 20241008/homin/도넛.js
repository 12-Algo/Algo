function solution(edges) {
  const map = new Map();

  edges.forEach((e) => {
    const [from, to] = e;

    if (!map.has(from)) {
      map.set(from, [1, 0]);
    } else {
      map.set(from, [map.get(from)[0] + 1, map.get(from)[1]]);
    }

    if (!map.has(to)) {
      map.set(to, [0, 1]);
    } else {
      map.set(to, [map.get(to)[0], map.get(to)[1] + 1]);
    }
  });

  const answer = [0, 0, 0, 0];

  for (const [key, value] of map) {
    const [from, to] = value;

    // 시작 노드 조회
    if (from >= 2 && to === 0) {
      answer[0] = key;
    }

    // 8자 그래프의 중간은 반드시 2 / 2 이상
    if (from >= 2 && to >= 2) {
      answer[3] += 1;
    }

    // 직선 그래프의 최종 노드는 나가는 차수가 없음
    if (from === 0) {
      answer[2] += 1;
    }
  }

  const dounutGraph = map.get(answer[0])[0] - answer[2] - answer[3];
  answer[1] = dounutGraph;

  return answer;
}
