def find(c):
    if(c != parent[c]):
        parent[c] = find(parent[c])
    return parent[c]

def union(a, b):
    aa = find(a)
    bb = find(b)
    parent[max(aa, bb)] = min(aa, bb)

n, m, t = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(m)]
parent = [i for i in range(n+1)]
money = 0
edgeCnt = 0

graph.sort(key=lambda x : x[2])

for a, b, c in graph:
    if(find(a) != find(b)):
        union(a, b)
        money += c
        edgeCnt += 1

extraMoney = 0
for i in range(n-2):
    extraMoney += (i+1)*t

print(money + extraMoney)