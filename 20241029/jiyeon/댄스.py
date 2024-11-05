import sys
input = sys.stdin.readline

sys.setrecursionlimit(10**6)

def move(start, end):
    if(start == end):
        return 1
    elif(start == 0):
        return 2
    elif(abs(end-start)%2 == 0):
        return 4
    else:
        return 3

arr = list(map(int, input().split()))
dp = [[[-1]*5 for _ in range(5)] for _ in range(100000)]

def solution(n, l, r):
    if(n >= len(arr)-1): #종료조건
        return 0

    # if(dp[n][l][r] != -1):
    #     return dp[n][l][r]

    dp[n][l][r] = min(solution(n+1, arr[n], r) + move(l, arr[n]), solution(n+1, l, arr[n]) + move(r, arr[n]))
    return dp[n][l][r]

print(solution(0,0,0))




# dp[n][l][r]?
# 왼쪽발은 l 오른쪽발은 r, 몇번째 발판을 밟아야 하는가 n
