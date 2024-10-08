function solution(edges) {
    const answer = [0, 0, 0, 0]; // 생성 정점, 도넛, 막대, 8자
    const edgeMap = {}; // key : [outdegree, indegree] 형식으로 저장

    for (let [from, to] of edges) {
        // outdegree
        if (!edgeMap[from]) {
            edgeMap[from] = [1, 0];
        } else {
            edgeMap[from][0]++;
        }

        // indegree
        if (!edgeMap[to]) {
            edgeMap[to] = [0, 1];
        } else {
            edgeMap[to][1]++;
        }
    }
    
    // 1: 도넛, 2: 막대, 3: 8자
    for (let key in edgeMap) {
        const [outdg, indg] = edgeMap[key];
        
        if (outdg >= 2 && indg === 0) { // 생성 정점
            answer[0] = +key;
        } else if (outdg === 0) { // 막대
            answer[2]++;
        } else if (outdg >= 2 && indg >= 2) { // 8자
            answer[3]++;
        }
    }

    // 도넛
    // 1: 도넛, 2: 막대, 3: 8자
    if (edgeMap[answer[0]]) {
        answer[1] = edgeMap[answer[0]][0] - answer[2] - answer[3];
    } 
    
    return answer;
}
