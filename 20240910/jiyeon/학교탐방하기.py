def find(c, parent):
    if (c != parent[c]):
        parent[c] = find(parent[c], parent)
    return parent[c]


def union(a, b, parent):
    aa = find(a, parent)
    bb = find(b, parent)
    parent[max(aa, bb)] = min(aa, bb)


n, m = map(int, input().split())
roads = [list(map(int, input().split())) for _ in range(m + 1)]

maxAsc = 0
minAsc = 0

# 오르막 많이 선택하는 경우
maxParent = [i for i in range(n + 1)]
roads.sort(key=lambda x: x[2])

for a, b, c in roads:
    if (find(a, maxParent) != find(b, maxParent)):
        union(a, b, maxParent)
        if (c == 0):
            maxAsc += 1
# 내리막 많이 선택하는 경우
minParent = [i for i in range(n + 1)]
roads.sort(key=lambda x: -x[2])
for a, b, c in roads:
    if (find(a, minParent) != find(b, minParent)):
        union(a, b, minParent)
        if (c == 0):
            minAsc += 1

maxAscHp = (maxAsc) ** 2
minAscHp = (minAsc) ** 2
print(maxAscHp - minAscHp)








