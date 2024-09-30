import sys
input = sys.stdin.readline

N, M, D = map(int, input().split())
maps = [list(map(int, input().split(" "))) for _ in range(N)]
result = []
max_anemy = 0

def dfs(depth, startIdx):
    global max_anemy
    if (depth == 3):
        c = simulate()
        max_anemy = max(max_anemy, c)
        return
    for i in range(startIdx, M):
        result.append(i)
        dfs(depth + 1, i + 1)
        result.pop()

def simulate():
  
    enemys = []
    cnt = 0
    for y in range(N):
        for x in range(M):
            if (maps[y][x] == 1):
                enemys.append((y, x))
    while (len(enemys) > 0):
        s = set()
        for i in range(3):
            enemys.sort(
                key=lambda x: (calDistance(x[0], x[1], N, result[i]), x[1]))
            if (calDistance(enemys[0][0], enemys[0][1], N, result[i]) <= D):
                s.add((enemys[0][0], enemys[0][1]))
        cnt += len(s)
        enemys = [item for item in enemys if tuple(item) not in s]
        enemys = [[enemy[0] + 1, enemy[1]] for enemy in enemys
                         if enemy[0] + 1 != N]
    return cnt


def calDistance(y1, x1, y2, x2):
    return abs(y1 - y2) + abs(x1 - x2)

dfs(0, 0)
print(max_anemy)

# 궁수는 n+1행의 칸에 있다잉
# 거리가 D이하인 적 중에 가깝고, 여러명이면 가장 왼쪽에 있는거

# 궁수는 같은 적 공격할 수 있고, 공격받으면 게임에서 제외
# 적은 아래로 한 칸 이동, 성이 있는 칸(즉 Y가N이상이면) 게임에서 제외

# 모든 적이 제외되면 게임 끝

# 5 5 1 => 행 열 궁수 공격 거리 제한

# 궁수 위치 놓고 시뮬 돌리기