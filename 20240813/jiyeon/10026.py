import sys
input = sys.stdin.readline
from collections import deque

def solution():
  dy = [0,0,1,-1]
  dx = [1,-1,0,0]
  normal = 0
  redGreen = 0
  N = int(input())
  map = [list(input().rstrip()) for _ in range(N)]
  visit = [[False for _ in range(N)] for _ in range(N)]
  for i in range(N):
    for j in range(N):
      if(visit[i][j] == False):
        normal += 1
        c = map[i][j]
        dq = deque()
        dq.append((i, j))
        visit[i][j] = True
        while(dq):
          y, x = dq.popleft()
          for k in range(4):
            ny = y + dy[k]
            nx = x + dx[k]
            if(0<= ny < N and 0<= nx < N):
              if(map[ny][nx] == c and visit[ny][nx] == False):
                dq.append((ny, nx))
                visit[ny][nx] = True
  redGreenVisit = [[False for _ in range(N)] for _ in range(N)]

  #적록색약 맵
  for i in range(N):
    for j in range(N):
      if(map[i][j] == 'R'):
        map[i][j] = 'G'

  for i in range(N):
    for j in range(N):
      if(redGreenVisit[i][j] == False):
        redGreen += 1
        c = map[i][j]
        dq = deque()
        dq.append((i, j))
        redGreenVisit[i][j] = True
        while(dq):
          y, x = dq.popleft()
          for k in range(4):
            ny = y + dy[k]
            nx = x + dx[k]
            if(0<= ny < N and 0<= nx < N):
              if(map[ny][nx] == c and redGreenVisit[ny][nx] == False):
                dq.append((ny, nx))
                redGreenVisit[ny][nx] = True
    
  
  return [normal, redGreen]


print(*solution())


