function solution(land) {
    const dx = [0,1,0,-1];
    const dy = [1,0,-1,0];

    const N = land.length;
    const M = land[0].length;
    const visited = Array.from({length: N}, ()=> new Array(M).fill(0));
    const petroleum = [0];
    
    let maxDrilled = 0;
    let loafNumber = 1;
    
    // 덩어리마다 번호 매기기
    // 번호에 해당하는 petroleum 인덱스에 덩어리 크기 저장
    for(let i = 0; i < N; i++){
        for(let j = 0; j < M; j++){
            if(visited[i][j] === 0 && land[i][j] === 1){
                visited[i][j] = loafNumber;
                const amount = bfs(i,j);
                petroleum.push(amount);
                loafNumber++;
            }
        }
    }
    
    const petroleumLen = petroleum.length;
    
    // 열마다 덩어리가 몇개 닿는지 검사
    for(let j = 0; j < M; j++){
        const loafVisited =  new Array(petroleumLen).fill(0);
        let count = 0;
        for(let i = 0; i < N; i++){
            const targetLoaf = visited[i][j];
            if(land[i][j] === 1 && loafVisited[targetLoaf] === 0){
                loafVisited[targetLoaf] = 1;
                count += petroleum[targetLoaf];
                
            }
        }
        maxDrilled = Math.max(maxDrilled, count);
        
    }
    
    
    function bfs(r, c){
    const queue = [[r,c,1]];
    let head = 0;
    let count = 1;
    
    while(queue.length > head){
        const [x,y] = queue[head++];
        for(let i = 0; i < 4; i++){
            
            const [nx, ny] = [x+dx[i], y + dy[i]];
        
            if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || !land[nx][ny]) continue;
            visited[nx][ny] = loafNumber;
            count++;
            queue.push([nx, ny]);
        }
        
    }
        return count;
}
    
    return maxDrilled;
}

