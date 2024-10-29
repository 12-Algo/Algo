import sys
input = sys.stdin.readline

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
    dp[n][l][r] = min(solution(n+1, ))









dp[n][l][r]?
왼쪽발은 l 오른쪽발은 r, 몇번째 발판을 밟아야 하는가 n


