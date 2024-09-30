import sys
from collections import deque

input = sys.stdin.readline

n, m = map(int, input().split())
safeZone = [list(input().strip()) for _ in range(n)]
visit = [[False for _ in range(m)] for _ in range(n)]
direction = {'D': [1, 0], 'R': [0, 1], 'L': [0, -1], 'U': [-1, 0]}
dy = [1, -1, 0, 0]  #아래, 위, 오른, 왼
dx = [0, 0, 1, -1]
ans = ['U', 'D', 'L', 'R']
dq = deque()
cnt = 0

for y in range(n):
    for x in range(m):
        if (not visit[y][x]):
            cnt += 1
            visit[y][x] = True
            dq.append((y, x))
            while (dq):
                nowY, nowX = dq.popleft()
                #다음노드
                d = safeZone[nowY][nowX]
                ny, nx = nowY + direction[d][0], nowX + direction[d][1]
                if (0 <= ny < n and 0 <= nx < m and not visit[ny][nx]):
                    visit[ny][nx] = True
                    dq.append((ny, nx))
                #현재 노드로 들어오는 거 찾기
                for i in range(4):
                    by = nowY + dy[i]
                    bx = nowX + dx[i]
                    if (0 <= by < n and 0 <= bx < m and not visit[by][bx]):
                        if (safeZone[by][bx] == ans[i]):
                            visit[by][bx] = True
                            dq.append((by, bx))

print(cnt)