import sys

input = sys.stdin.readline

while (True):
    L, R, C = map(int, input().strip().split(" "))

    if ([L, R, C] == [0, 0, 0]):
        break
    arr = []

    for _ in range(L):
        floor = []
        for _ in range(R):
            floor.append(list(input().strip()))
        arr.append(floor)
        input()

    startIndex = []
    endIndex = []

    for z in range(L):
        for y in range(R):
            for x in range(C):
                if (arr[z][y][x] == 'S'):
                    startIndex = [z, y, x]
                    arr[z][y][x] = 0
                if (arr[z][y][x] == 'E'):
                    endIndex = [z, y, x]
                if (arr[z][y][x] == '.'):
                    arr[z][y][x] = 0
                if (arr[z][y][x] == '#'):
                    arr[z][y][x] = -1

    queue = [startIndex]
    dz = [-1, 1, 0, 0, 0, 0]
    dx = [0, 0, -1, 1, 0, 0]
    dy = [0, 0, 0, 0, -1, 1]

    answer = 0

    while (len(queue)):
        [z, y, x] = queue.pop(0)

        for i in range(6):
            nz = z + dz[i]
            ny = y + dy[i]
            nx = x + dx[i]

            if ([nz, ny, nx] == endIndex):
                answer = arr[z][y][x] + 1
                break

            if (nz >= 0 and nx >= 0 and ny >= 0 and nz < L and ny < R and nx < C and arr[nz][ny][nx] == 0):
                arr[nz][ny][nx] = arr[z][y][x] + 1
                queue.append([nz, ny, nx])

    if (answer):
        print(f"Escaped in {answer} minute(s).")
    else:
        print("Trapped!")
