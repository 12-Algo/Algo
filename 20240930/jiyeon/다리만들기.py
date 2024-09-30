import sys
from collections import deque
input = sys.stdin.readline

#시간 초과남.......3초

dy = [1,-1,0,0]
dx = [0,0,1,-1]

n = int(input())
arr = [list(map(int, input().split())) for _ in range(n)]
cnt = 0
islands = [[0 for _ in range(n)] for _ in range(n)]

result = n*n+4

def getShortest(start, end, islands):
    global n
    shortestLength = n*n+4
    dq = deque()
    visit = [[False for _ in range(n)] for _ in range(n)]
    for y in range(n):
        for x in range(n):
            if(islands[y][x] == start):
                visit[y][x] = True
                dq.append([y, x, 0])
                while(dq):
                    nowy, nowx, length = dq.popleft()

                    if(islands[nowy][nowx] == end):
                        shortestLength = min(shortestLength, length)
                        continue

                    for i in range(4):
                        ny = nowy + dy[i]
                        nx = nowx + dx[i]
                        if(0<=ny<n and 0<=nx<n and not visit[ny][nx]):
                            visit[ny][nx] = True
                            if(islands[ny][nx] == start):
                                dq.appendleft([ny, nx, length])
                            else:
                                if(length+1 > shortestLength or length+1 > result):
                                    continue
                                dq.append([ny, nx, length+1])
                return shortestLength

#섬 체크하기
visit = [[False for _ in range(n)] for _ in range(n)]
for y in range(n):
    for x in range(n):
        if(not visit[y][x] and arr[y][x] == 1):
            dq = deque()
            dq.append([y, x])
            cnt += 1
            visit[y][x] = True
            while(dq):
                nowy, nowx = dq.popleft()
                islands[nowy][nowx] = cnt
                for i in range(4):
                    ny = nowy + dy[i]
                    nx = nowx + dx[i]
                    if(0<=ny<n and 0<=nx<n and not visit[ny][nx] and arr[ny][nx] == 1):
                        visit[ny][nx] = True
                        dq.append([ny, nx])

for i in range(1, cnt+1):
    for j in range(i+1, cnt+1):
        result = min(result, getShortest(i, j, islands)-1)

print(result)