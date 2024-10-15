function solution(places) {
  const newPlaces = places.map((e) => e.map((i) => i.split("")));

  const visited = Array.from({ length: 5 }, () =>
    Array.from({ length: 5 }, () => new Array(5).fill(-1))
  );

  const answer = [true, true, true, true, true];

  const XStore = [];
  const PStore = [];

  for (let z = 0; z < 5; z++) {
    for (let x = 0; x < 5; x++) {
      for (let y = 0; y < 5; y++) {
        if (newPlaces[z][x][y] === "X") {
          visited[z][x][y] = 0;
        }
        if (newPlaces[z][x][y] === "P") {
          PStore.push([z, x, y]);
        }
      }
    }
  }

  const dx = [-1, 1, 0, 0];
  const dy = [0, 0, -1, 1];

  let index = 2;

  PStore.forEach((e) => {
    const [z, x, y] = e;
    visited[z][x][y] = index;
    for (let i = 0; i < 4; i++) {
      const nx = dx[i] + x;
      const ny = dy[i] + y;

      if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && visited[z][nx][ny] !== 0) {
        if (visited[z][nx][ny] === -1) {
          visited[z][nx][ny] = index;
        } else {
          answer[z] = false;
        }
      }
    }
  });

  return answer.map((e) => (e ? 1 : 0));
}

// 1번 풀이 방법
// bfs로 파티션별로 구역을 나눴음
// BFS돌 때 P좌표값 저장해서 두 좌표간의 거리를 구하는데 2 이하면 false를 반환하게 함
// 생각하지 못한건 두 좌표사이에 파티션이 있는걸 아예 생각도못했음

// 2번 풀이 방법
// 층별로 모든 좌표값을 저장해놓음
// 층별로 P의 좌표에서 bfs 탐색 시작함 -> x인 방향으론 갈 수 없게 하고 거리가 2가 될 때 까지 순회해서 P가 있는지 검증함

// function solution(places) {
//     const newPlaces = places.map(e => e.map(i => i.split("")))
//     const visited = Array.from({length : 5}, () => Array.from({length : 5} , () => new Array(5).fill(-1)))

//     newPlaces.forEach((floor,z) => {
//         floor.forEach((e, x) => {
//             e.forEach((j,y) => {
//                 if (j==='X') {
//                     visited[z][x][y] = 0
//                 }
//             })
//         })
//     })

//     const floorStore = Array.from({length : 5} , () => [])

//     const bfs = (z,x,y) => {
//         const dx = [-1,1,0,0]
//         const dy = [0,0,-1,1]

//         const queue = [[z,x,y]]
//         const checkP = []

//         while (queue.length) {
//             const [z,x,y] = queue.shift()
//             if (newPlaces[z][x][y] === 'P') {
//                 checkP.push([x,y])
//             }

//             for (let i=0; i<4; i++) {
//                 const nx = x + dx[i]
//                 const ny = y + dy[i]

//                 if (nx >=0 && ny >=0 && nx <5 && ny <5 && visited[z][nx][ny] === -1) {
//                     queue.push([z,nx,ny])
//                     visited[z][nx][ny] = 0
//                 }
//             }
//         }
//         return checkP
//     }

//     for (let z=0; z<5; z++){
//         for (let x=0; x<5; x++) {
//             for (let y=0; y<5; y++) {
//                 if (visited[z][x][y] === -1) {
//                     visited[z][x][y] = 0
//                   floorStore[z].push(bfs(z,x,y))
//                 }
//             }
//         }
//     }

//     const answer = floorStore.map(e => {
//         const temp = e.map(j => {
//             if (j.length < 2) return true
//             let flag = false
//             for (let i=0; i < j.length; i++) {
//                 for (let q = i+1; q < j.length; q++) {
//                     console.log(j[i], j[q])
//                     if ((Math.abs(j[i][0] - j[q][0]) + Math.abs(j[i][1] - j[q][1])) <= 2) {
//                         flag = true
//                         break
//                     }
//                 }
//             }
//             return !flag
//         })

//         console.log(temp)

//         return temp.every(e => e)
//     })
// }
