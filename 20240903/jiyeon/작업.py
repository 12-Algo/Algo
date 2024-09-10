import sys

input = sys.stdin.readline

n = int(input())
times = [0 for _ in range(n + 1)]
graph = [[] for _ in range(n + 1)]
for i in range(n):
    userInput = list(map(int, input().split(" ")))
    times[i + 1] = userInput[0]
    for a in userInput[2:]:
        graph[i + 1].append(a)

for i in range(2, n + 1):
    maxTime = 0
    for w in graph[i]:
        maxTime = max(maxTime, times[w])
    times[i] += maxTime

print(max(times))