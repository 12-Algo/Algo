import math
import sys
input = sys.stdin.readline

c, n = map(int, input().split())
promotionInfo = [list(map(int, input().split())) for _ in range(n)]

dp = [sys.maxsize for _ in range(c+100)] #idx명의 고객을 확보하는데 사용되는 최소 비용
dp[0] = 0

for cost, people in promotionInfo:
    for i in range(people, c+100):
        dp[i] = min(dp[i], dp[i-people]+cost)

print(min(dp[c:]))

