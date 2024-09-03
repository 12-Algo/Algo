import sys
from collections import deque
input = sys.stdin.readline

n = int(input())

times = [0 for _ in range(n+1)]
dp = [0 for _ in range(n+1)]
degree = [0 for _ in range(n+1)]
graph = [[] for _ in range(n+1)]

for i in range(n):
    info = list(map(int, input().split()))
    times[i+1] = info[0]
    for j in info[1:-1]:
        graph[j].append(i+1)
        degree[i+1] += 1

dq = deque()

#진입차수 없는 애들 큐에넣어주기
for i in range(1, n+1):
    if(degree[i] == 0):
        dq.append(i)
        dp[i] = times[i]

while(dq):
    node = dq.popleft()
    for e in graph[node]:
        degree[e] -= 1
        dp[e] = max(dp[e], dp[node]+times[e])
        if(degree[e] == 0):
            dq.append(e)

for i in range(1, n+1):
    print(dp[i])