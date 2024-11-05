import sys
input = sys.stdin.readline

n = int(input())
maps = [list(map(int, input().split())) for _ in range(n)]

dy = [1, -1, 0, 0]
dx = [0, 0, 1, -1]

dp = [[-1 for _ in range(n)] for _ in range(n)]

def dfs(y, x):
    global n
    if(dp[y][x] != -1): #계속 현재의 최대를 갱신하기 때문에 그냥 리턴
        return dp[y][x]
    dp[y][x] = 1

    for i in range(4):
        ny = y + dy[i]
        nx = x + dx[i]
        if(0<=ny<n and 0<=nx<n and maps[ny][nx] > maps[y][x]):
            dp[y][x] = max(dp[y][x], dfs(ny, nx)+1)

    return dp[y][x]

result = 0
for y in range(n):
    for x in range(n):
        result = max(result, dfs(y, x))

print(result)


# 처음 풀이
# 좌표 잡고 dfs로 탐색 시작해서 주변에 현재 좌표보다 큰 대나무가 있다면 탐색해줬다.
# 이때 dp(해당 좌표에 도착하기까지 최대 좌표)를 이용해서
# 만약 현재좌표 dp에서 +1한 값과 비교하여 next의 dp가 크거나 같다면, 해당 경로는 탐색할 필요가 없으므로 패스
# 그러나 next의 dp가 작다면, dp[현재좌표]+1이 dp[next]로 갱신되고, 그렇다면 해당 경로로 시작해 다시 탐색하면서 값들을 갱신해줘야한다.
# 여기서 문제는!! 이미 그 경로를 타고 들어가면서 갱신을 해줬을텐데, 또 중복으로 갱신해줘야 한다는 것이다.

# 참고 풀이
# dp를 통해서 그 값을 바로 가져다 쓸 수 있도록 해야한다. 특정 경로를 파고들어가면서 +1을 하도록 할게 아니라,
# 특정 좌표를 봤을때 다시 파고들어갈일이 없도록 dp에 그 좌표로 시작해서 파고들어갔을때의 최대값을 넣어두자 => 재귀로 구현

