import sys

input = sys.stdin.readline

n = int(input())

arr = []
for _ in range(n):
    arr.append(list(map(int, input().strip().split())))

visited = [[0 for _ in range(n)] for _ in range(n)]
label = 2


def bfs(x, y):
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    queue = [[x, y]]
    arr[x][y] = label

    while (len(queue)):
        now_x, now_y = queue.pop(0)
        visited[now_x][now_y] = 1

        for i in range(4):
            nx = now_x + dx[i]
            ny = now_y + dy[i]
            if nx >= 0 and ny >= 0 and nx < n and ny < n and arr[nx][ny] == 1 and visited[nx][ny] == 0:
                queue.append([nx, ny])
                visited[nx][ny] = 1
                arr[nx][ny] = label


for x in range(n):
    for y in range(n):
        if arr[x][y] == 1 and visited[x][y] == 0:
            bfs(x, y)
            label += 1

answer = 500


def bfs2(num):
    visited = [[-1 for _ in range(n)] for _ in range(n)]
    queue = []
    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]

    for x_1 in range(n):
        for y_1 in range(n):
            if arr[x_1][y_1] == num:
                visited[x_1][y_1] = 0
                queue.append((x_1, y_1))
    while (len(queue)):
        global answer
        (inner_x, inner_y) = queue.pop(0)
        for i in range(4):
            nx = dx[i] + inner_x
            ny = dy[i] + inner_y
            if (nx >= 0 and ny >= 0 and nx < n and ny < n and visited[nx][ny] == -1 and arr[nx][ny] == 0):
                visited[nx][ny] = visited[inner_x][inner_y] + 1
                queue.append((nx, ny))
            if (nx >= 0 and ny >= 0 and nx < n and ny < n and visited[nx][ny] == -1 and arr[nx][ny] != n):
                answer = min(answer, visited[inner_x][inner_y])
                return


# 라벨링 된 지역을 순서대로 순회
for i in range(2, label):
    bfs2(i)

print(answer)
