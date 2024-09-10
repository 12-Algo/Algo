N, M = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(M)]

graph.sort(key=lambda x : x[2])
parent = [i for i in range(N+1)]

def find(c):
    if c != parent[c]:
        parent[c] = find(parent[c])
    return parent[c]

def union(a, b):
    aa = find(a)
    bb = find(b)
    parent[max(aa, bb)] = min(aa, bb)

money = 0
maxUpkeepMoney = 0

for a, b, c in graph:
    if(find(a) != find(b)):
        union(a, b)
        money += c
        maxUpkeepMoney = max(maxUpkeepMoney, c)

print(money - maxUpkeepMoney)