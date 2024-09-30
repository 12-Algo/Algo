import sys

input = sys.stdin.readline

n, m = map(int, input().strip().split())

graph = []

for _ in range(n):
    graph.append(list(map(int, input().strip().split())))

now_ice = 1
remove_ice_count = 0
dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

# def bfs():
#     visited = [[False] * m for _ in range(n)]
#     visited[0][0] = True
#
#     queue = [(0,0)]
#     while(len(queue)):
#         global now_ice
#         x,y = queue.pop(0)
#         for i in range(4):
#             nx = dx[i] + x
#             ny = dy[i] + y
#             if (nx >=0 and ny >=0 and nx <n and ny <m and visited[nx][ny] == False):
#                 queue.append((nx,ny))
#                 visited[nx][ny] = True
#                 if (graph[nx][ny] != 0):
#                     now_ice += 1


def remove_ice():
    zero_target = []
    global remove_ice_count
    remove_ice_count = 0
    for x in range(n):
        for y in range(m):
            if (graph[x][y] == 0):
                zero_target.append((x, y))

    for target in zero_target:
        x, y = target
        for i in range(4):
            nx = dx[i] + x
            ny = dy[i] + y
            if (nx >= 0 and ny >= 0 and nx < n and ny < m):
                graph[nx][ny] -= 1
                if (graph[nx][ny] == 0):
                    remove_ice_count += 1
                graph[nx][ny] = max(graph[nx][ny], 0)


def split_ice_counting():
    visited = [[False] * m for _ in range(n)]
    ice_count = 0

    def bfs(x, y):
        queue = [(x, y)]
        visited[x][y] = True
        while (len(queue)):
            x, y = queue.pop(0)
            for i in range(4):
                nx = dx[i] + x
                ny = dy[i] + y
                if (nx >= 0 and ny >= 0 and nx < n and ny < m and visited[nx][ny] == False and graph[nx][ny] > 0):
                    queue.append((nx, ny))
                    visited[nx][ny] = True

    for x in range(n):
        for y in range(m):
            if (graph[x][y] > 0 and visited[x][y] == False):
                bfs(x, y)
                ice_count += 1

    return ice_count


count = 0

while (split_ice_counting() == 1):
    remove_ice()
    count += 1
    if (split_ice_counting() == 0):
        count = 0
        break

print(count)
