import sys
input = sys.stdin.readline
from collections import deque
dl = [1,-1,0,0,0,0]
dr = [0,0,1,-1,0,0]
dc = [0,0,0,0,1,-1]

while(True):
    L, R, C = map(int, input().split())

    if(L==0 and R==0 and C==0):
        break

    start = [0, 0, 0]
    end = [0, 0, 0]

    field = []
    for _ in range(L):
        floor = [list(input().rstrip()) for _ in range(R)]
        input()
        field.append(floor)

    for l in range(L):
        for r in range(R):
            for c in range(C):
                if field[l][r][c] == 'S':
                    start = [l, r, c]
                elif field[l][r][c] == 'E':
                    end = [l, r, c]

    visit = [[[False for _ in range(C)] for _ in range(R)] for _ in range(L)]

    dq = deque()
    dq.append([start, 0])
    visit[start[0]][start[1]][start[2]] = True
    while (dq):
        now = dq.popleft()
        nowIndex = now[0]
        nowTime = now[1]

        if (nowIndex[0] == end[0] and nowIndex[1] == end[1] and nowIndex[2] == end[2]):
            print("Escaped in %d minute(s)." % now[1])
            break

        for i in range(6):
            nextL = nowIndex[0] + dl[i]
            nextR = nowIndex[1] + dr[i]
            nextC = nowIndex[2] + dc[i]

            if (0 <= nextL < L and 0 <= nextR < R and 0 <= nextC < C):
                if (not visit[nextL][nextR][nextC]):
                    if (field[nextL][nextR][nextC] != "#"):
                        dq.append([[nextL, nextR, nextC], nowTime + 1])
                        visit[nextL][nextR][nextC] = True

    else:
        print("Trapped!")



