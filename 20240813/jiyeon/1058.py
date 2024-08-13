import sys
input = sys.stdin.readline
from collections import deque

def solution():
  result = 0
  people = int(input())
  graph = [[] for _ in range(people)]

  #그래프 만들기
  for i in range(people):
    flags = input()
    for j in range(people):
      if(flags[j] == "Y"):
        graph[i].append(j)
  
  #친구구하기
  for j in range(people):
    result = max(result, cal2friend(graph, j))
  return result

def cal2friend(graph, j):
  num2friend = 0
  visit = [False for _ in range(len(graph))]
  friends1 = graph[j]
  visit[j] = True
  for k in friends1:
    if(visit[k] == False):
      num2friend += 1
      visit[k] = True
    for h in graph[k]:
      if(visit[h] == False):
        num2friend += 1
        visit[h] = True
  return num2friend

# def cal2friend(graph, j):
#   num2friend = 0
#   visit = [False for _ in range(len(graph))]
#   visit[j] = True
#   dq = deque()
#   dq.append(j)
#   while(dq):
#     n = dq.popleft()
#     for h in graph[n]:
#       if(visit[h] == False):
#         visit[h] = True
#         num2friend += 1
#   return num2friend

print(solution())