import sys
input = sys.stdin.readline
from collections import deque

def solution():
  N, M = map(int, input().split())
  graph = [[] for _ in range(N + 1)]
  min_kb = N*N
  result = 1

  #그래프 만들
  for _ in range(M):
    node1, node2 = map(int, input().split())
    graph[node1].append(node2)
    graph[node2].append(node1)

  for i in range(N, 0, -1):
    kebinBaconNum = calKebinBacon(graph, i)
    if(kebinBaconNum <= min_kb):
      min_kb = kebinBaconNum
      result = i
  return result

def calKebinBacon(graph, start):
  kebinBaconNum = 0
  for j in range(1, len(graph)):
    if(start != j):
      visit = [False for _ in range(len(graph))]
      dq = deque()
      dq.append((start,1))
      visit[start] = True
      while(dq):
        node, num = dq.popleft()
        if(node == j):
          kebinBaconNum += num
          break
        for i in graph[node]:
          if(visit[i] == False):
            visit[i] = True
            dq.append((i, num+1))
  return kebinBaconNum
  
print(solution())
