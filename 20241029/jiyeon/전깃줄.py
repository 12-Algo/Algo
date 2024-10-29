import sys
input = sys.stdin.readline

cnt = int(input())
lines = [list(map(int, input().split())) for _ in range(cnt)]
lines.sort(key=lambda x:x[0])

dp = [1 for _ in range(cnt)]

for i in range(1, cnt):
    for j in range(i):
        if(lines[i][1] > lines[j][1]):
            dp[i] = max(dp[i], dp[j]+1)

print(cnt - max(dp))

# 1 8
# 2 2
# 3 9
# 4 1
# 6 4
# 7 6
# 9 7
# 10 10
# 8 2 9 1 4 6 7 10 => 증가하는 가장 긴 수열 구하기




