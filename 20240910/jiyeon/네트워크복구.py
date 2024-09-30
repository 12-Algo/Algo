import heapq
import sys
input = sys.stdin.readline
INF = int(1e9)

N, M = map(int, input().split())
graph = [[] for _ in range(N+1)]
result = [0 for _ in range(N+1)]

for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append([b, c])
    graph[b].append([a, c])

distance = [INF for _ in range(N+1)]

def dijkstra(start):
    q = []
    heapq.heappush(q, (0, start))
    distance[start] = 0
    while q:
        dist, node = heapq.heappop(q)
        if(distance[node] < dist):
            continue
        for linkedNode, linkedDist in graph[node]:
            cost = dist + linkedDist
            if(cost < distance[linkedNode]):
                distance[linkedNode] = cost
                result[linkedNode] = node
                heapq.heappush(q, (cost, linkedNode))

dijkstra(1)
cnt = 0
for a in result:
    if(a != 0):
        cnt += 1

print(cnt)

for i in range(N+1):
    if(result[i] != 0):
        print(str(i) + " " + str(result[i]))