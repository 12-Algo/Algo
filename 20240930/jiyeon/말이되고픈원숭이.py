import sys
from collections import deque
input = sys.stdin.readline

horse_dy = [-1,-2,-2,-1,1,2,2,1]
horse_dx = [-2,-1,1,2,-2,-1,1,2]

dy = [1,-1,0,0]
dx = [0,0,1,-1]

k = int(input())
w, h = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(h)]

dq = deque()
dq.append([0,0,0])

visit = [[[-1 for _ in range(k+1)] for _ in range(w)] for _ in range(h)]
visit[0][0][0] = 0
result = w*h+4

while(dq):
    nowy, nowx, cnt = dq.popleft()

    if(nowy == h-1 and nowx == w-1):
        result = min(result, visit[nowy][nowx][cnt])
        break

    for i in range(4):
        ny = nowy + dy[i]
        nx = nowx + dx[i]
        if(0<=ny<h and 0<=nx<w and visit[ny][nx][cnt] == -1 and arr[ny][nx] != 1):
            visit[ny][nx][cnt] = visit[nowy][nowx][cnt] + 1
            dq.append([ny, nx, cnt])

    if(cnt<k):
        for i in range(8):
            ny = nowy + horse_dy[i]
            nx = nowx + horse_dx[i]
            if (0 <= ny < h and 0 <= nx < w and visit[ny][nx][cnt+1] == -1 and arr[ny][nx] != 1):
                visit[ny][nx][cnt + 1] = visit[nowy][nowx][cnt] + 1
                dq.append([ny, nx, cnt+1])

if(result == w*h+4):
    print(-1)
else:
    print(result)