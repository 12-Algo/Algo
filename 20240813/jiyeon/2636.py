import sys
input = sys.stdin.readline
from collections import deque

dy = [0,0,1,-1]
dx = [1,-1,0,0]

def solution():
  y, x = map(int, input().split(" "))
  maps = [list(map(int, input().split(" "))) for _ in range(y)]
  time = -1

  while(True):
    time += 1
    countCheese = 0
    dq = deque()
    dq.append((0,0))
    visit = [[False for _ in range(x)] for _ in range(y)]
    visit[0][0] = True
    while(dq):
      now_y, now_x = dq.popleft()
      for i in range(4):
        next_y = now_y + dy[i]
        next_x = now_x + dx[i]
        if(0<=next_y<y and 0<=next_x<x):
          if(maps[next_y][next_x] == 1):
            maps[next_y][next_x] = 0
            visit[next_y][next_x] = True
            countCheese += 1
            continue
          if(visit[next_y][next_x] == False and maps[next_y][next_x] == 0):
            visit[next_y][next_x] = True
            dq.append((next_y, next_x))
    if(countCheese == 0):
      break
    result = countCheese 

  return [time, result]

result = solution()
print(result[0])
print(result[1])

