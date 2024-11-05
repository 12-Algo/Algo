import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split()) #격자 크기, 색상의 개수
maps = [list(map(int, input().split())) for _ in range(n)]


dy = [1,-1,0,0]
dx = [0,0,1,-1]

#일반 발견하면 돌아, 크기가 현재 저장된거 보다 크거나 같으면 갱신
def findBlock():
    global n, m
    totalVisit = [[False for _ in range(n)] for _ in range(n)]
    maxCnt = 0
    maxStart = [-1, -1]
    rainbowCnt = 0
    for y in range(n):
        for x in range(n):
            if(0<maps[y][x] and totalVisit[y][x] == False):
                rainbowtemp = 0
                cnt = 1
                number = maps[y][x]
                roundVisit = [[False for _ in range(n)] for _ in range(n)]
                totalVisit[y][x] = True
                roundVisit[y][x] = True
                dq = deque()
                dq.append((y, x))
                while(dq):
                    now_y, now_x = dq.popleft()
                    for i in range(4):
                        next_y = now_y + dy[i]
                        next_x = now_x + dx[i]
                        if(
                                0 <= next_y < n and 0 <= next_x < n and
                                roundVisit[next_y][next_x] == False and
                                (maps[next_y][next_x] == number or maps[next_y][next_x] == 0)
                        ):
                            cnt += 1
                            totalVisit[next_y][next_x] = True
                            roundVisit[next_y][next_x] = True
                            dq.append((next_y, next_x))
                            if(maps[next_y][next_x] == 0):
                                rainbowtemp += 1
                if(cnt > 1 and maxCnt<cnt):
                    maxCnt = cnt
                    maxStart = [y, x]
                    rainbowCnt = rainbowtemp
                elif(cnt > 1 and maxCnt==cnt):
                    if(rainbowCnt < rainbowtemp): #무지개 더 큼
                        rainbowCnt = rainbowtemp
                        maxStart = [y, x]
                        rainbowCnt = rainbowtemp
                    elif(rainbowCnt == rainbowtemp and y > maxStart[0]):
                        maxStart = [y, x]
                        rainbowCnt = rainbowtemp
                    elif(rainbowCnt == rainbowtemp and y == maxStart[0] and x > maxStart[1]):
                        maxStart = [y, x]
                        rainbowCnt = rainbowtemp
    maxStart.append(maxCnt)
    return maxStart

def remove(start_y, start_x):
    global n
    number = maps[start_y][start_x]
    maps[start_y][start_x] = -2
    dq = deque()
    dq.append((start_y, start_x))
    while(dq):
        now_y, now_x = dq.popleft()
        for i in range(4):
            next_y = now_y + dy[i]
            next_x = now_x + dx[i]
            if(
                    0<=next_y<n and 0<=next_x<n and
                    maps[next_y][next_x] != -2 and
                    (maps[next_y][next_x] == number or maps[next_y][next_x] == 0)
            ):
                maps[next_y][next_x] = -2
                dq.append((next_y, next_x))

#일단 비어있지(-2) 않으면 자리 비워주고(-2) 큐에 담으면서 내려가자.

#만약 내려가다가 검은블록(1)만나면, 그 위에 자리부터 개수만큼 쌓기

#만약 내려가다가 벽을 만나면, 그 자리부터 쌓기
def down():
    global n
    for x in range(n):
        dq = deque()
        for y in range(n+1):
            if(y == n):
                for i in range(y-1, y-1-len(dq), -1):
                    maps[i][x] = dq.pop()
                continue

            if(maps[y][x] >= 0):
                dq.append(maps[y][x])
                maps[y][x] = -2
            elif(maps[y][x] == -1):
                for i in range(y-1, y-1-len(dq), -1):
                    maps[i][x] = dq.pop()
           
def rotate():
    temp = [i[:] for i in maps]
    for y in range(n):
        for x in range(n):
            maps[n-x-1][y] = temp[y][x]



result = 0
while(True):
    y, x, cnt = findBlock()
    if(y == -1 and x == -1):
        break
    result += cnt*cnt
    remove(y, x)
    down()
    rotate()
    down()
print(result)