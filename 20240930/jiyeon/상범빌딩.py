import sys
from collections import deque
input = sys.stdin.readline

#빙산 몇 개인 지 아는 함수
#녹이는 함수

dy = [0,0,1,-1]
dx = [1,-1,0,0]

def melt(n, m, ice):
    meltIce = [[0 for _ in range(m)] for _ in range(n)]
    for y in range(n):
        for x in range(m):
            if(ice[y][x] != 0):
                cnt = 0
                for i in range(4):
                    ny = y + dy[i]
                    nx = x + dx[i]
                    if(0<=ny<n and 0<=nx<m and ice[ny][nx] == 0):
                        cnt+=1
                meltIce[y][x] = cnt

    for y in range(n):
        for x in range(m):
            ice[y][x] -= meltIce[y][x]
            if(ice[y][x] < 0):
                ice[y][x] = 0

def isAllMelted(n, m, ice):
    cnt = 0
    for y in range(n):
        for x in range(m):
            if(ice[y][x] != 0):
                cnt += 1
    if(cnt == 0):
        return True
    else:
        return False

def countIce(n, m, ice):
    visit = [[False for _ in range(m)] for _ in range(n)]
    cnt = 0
    for y in range(n):
        for x in range(m):
            if(ice[y][x] != 0 and not visit[y][x]):
                cnt += 1
                dq = deque()
                dq.append([y, x])
                visit[y][x] = True
                while(dq):
                    nowy, nowx = dq.popleft()
                    for i in range(4):
                        nexty = nowy + dy[i]
                        nextx = nowx + dx[i]
                        if(
                            0<=nexty<N and 0<=nextx<M and
                            not visit[nexty][nextx] and
                            ice[nexty][nextx] != 0
                        ):
                            dq.append([nexty, nextx])
                            visit[nexty][nextx] = True
    return cnt

N, M = map(int, input().split())
ice = [list(map(int, input().split())) for _ in range(N)]

time = 0
while(True):
    melt(N, M, ice)
    time += 1
    if(isAllMelted(N, M, ice)):
        print(0)
        break
    if(countIce(N, M, ice) > 1):
        print(time)
        break