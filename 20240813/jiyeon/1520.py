import sys
input = sys.stdin.readline
from collections import deque

dy = [0,0,1,-1]
dx = [1,-1,0,0]

def solution():
  result = 0
  M, N = map(int, input().split())
  maps = [list(map(int, input().split())) for _ in range(M)]
  dp = [[-1 for _ in range(N)] for _ in range(M)]
  dfs(maps, dp, 0, 0)
  return dp[0][0]

def dfs(maps, dp, start_y, start_x):
  if(start_y == len(dp)-1 and start_x == len(dp[0])-1):
    return 1

  if(dp[start_y][start_x] == -1):
    dp[start_y][start_x] = 0
    for i in range(4):
      ny = start_y + dy[i]
      nx = start_x + dx[i]
      if(0<=ny<len(dp) and 0<=nx<len(dp[0]) and maps[start_y][start_x] > maps[ny][nx]):
        dp[start_y][start_x] += dfs(maps, dp, ny, nx)
  return dp[start_y][start_x]

print(solution())


