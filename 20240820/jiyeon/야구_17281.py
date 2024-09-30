import sys
input = sys.stdin.readline
from collections import deque

result = []
max_score = 0

def solution():
  global result
  global max_score
  max_score = 0
  r = int(input())
  result = [list(map(int, input().split())) for _ in range(r)]

  visit = [False for _ in range(10)]
  visit[1] = True
  order = [0,0,0,1,0,0,0,0,0]
  perm(visit, order, 0)
  print(max_score)
  
  
def perm(visit, order, num):
  global result
  global max_score
  if(num == 3):
    perm(visit, order, num+1)
    return
  
  if(num == 9):
    s = calScore(order, result)
    max_score = max(max_score, s)
    
    #점수 나온다음에 max_score랑 비교해주기
    return

  for i in range(1, 10):
    if(visit[i] == False):
      visit[i] = True
      order[num] = i
      perm(visit, order, num+1)
      visit[i] = False


def calScore(order, result):
  global max_score
  sum = 0
  now_index = 0
  for i in range(len(result)): #총 2라운드
    out = 0
    p1 = p2 = p3 = 0
    while(True):
      player = order[now_index]
      play = result[i][player-1]
      if(play == 0):
        out += 1
      elif(play == 1):
        sum += p3
        p1, p2, p3 = 1, p1, p2
      elif(play == 2):
        sum += p2 + p3
        p1, p2, p3 = 0, 1, p1
      elif(play == 3):
        sum += p1 + p2 + p3
        p1, p2, p3 = 0, 0, 1
      elif(play == 4):
        sum += p1 + p2 + p3 + 1
        p1, p2, p3 = 0, 0, 0
      now_index = (now_index+1)%9
      if(out >=3):
        break
    
  return sum


solution()