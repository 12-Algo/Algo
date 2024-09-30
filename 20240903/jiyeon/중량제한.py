import sys
input = sys.stdin.readline

def find(c):
    if (c == parent[c]):
        return c
    else:
        parent[c] = find(parent[c])
    return parent[c]


def union(a, b):
    a = find(a)
    b = find(b)
    parent[max(a, b)] = min(a, b)

def isCycle(a, b):
    return find(a) == find(b)

n, m = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(m)]
node1, node2 = map(int, input().split())

parent = [i for i in range(n + 1)]  #부모
graph.sort(key=lambda x: -x[2])  #중량 내림차순 정렬

for i in graph:
    a, b, c = i[0], i[1], i[2]
    union(a, b)
    if (isCycle(node1, node2)):
        print(c)
        break