import sys
from collections import deque
input = sys.stdin.readline

dy = [1,-1,0,0]
dx = [0,0,1,-1]

M, N = map(int, input().split())

field = [list(map(int, list(input().rstrip()))) for _ in range(N)]
countArr = [[-1 for _ in range(M)] for _ in range(N)]

dq = deque()
dq.append((0,0))
countArr[0][0] = 0

while(dq):
    y, x = dq.popleft()
    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]
        if(ny>=N or ny<0 or nx>=M or nx<0):
            continue
        if(countArr[ny][nx] == -1):
            if(field[ny][nx] == 1): #벽
                dq.append((ny, nx))
                countArr[ny][nx] = countArr[y][x] + 1
            elif(field[ny][nx] == 0): #빈방
                dq.appendleft((ny, nx))
                countArr[ny][nx] = countArr[y][x]


print(countArr[N-1][M-1])
