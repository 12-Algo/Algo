import sys
input = sys.stdin.readline

n, m = map(int, input().split())
knowPeople = list(map(int, input().split()))
knowPeopleList = knowPeople[1:]

graph = [[] for _ in range(m)]
for i in range(m):
    graph[i].extend(list(map(int, input().split()))[1:])

parents = [i for i in range(n+1)]

def find(a):
    if(a != parents[a]):
        parents[a] = find(parents[a])
    return parents[a]

def union(a, b):
    aa = find(a)
    bb = find(b)
    parents[max(aa, bb)] = min(aa, bb)

# 같은 파티에 있는 사람 한 집합으로 묶기
for party in graph:
    for i in range(len(party)):
        for j in range(i+1, len(party)):
            union(party[i], party[j])

# 진실을 아는 사람들을 한 집합으로 묶기
for i in range(len(knowPeopleList)-1):
    union(knowPeopleList[i], knowPeopleList[i+1])

knowPeopleParentList = list(map(lambda x : find(x), knowPeopleList))

cnt = 0
for party in graph:
    for person in party:
        if(find(person) in knowPeopleParentList):
            break
    else:
        cnt += 1

print(cnt)