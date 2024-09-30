import sys
input = sys.stdin.readline

N = int(input())
arr = list(input().rstrip())
max_result = -2**31 - 1

def dfs(idx, sum):
    global max_result
    if (idx == N - 1):
        max_result = max(max_result, sum)
        return

    if (idx + 2 < N):
        dfs(idx + 2, cal(sum, arr[idx + 1], arr[idx + 2]))
    if (idx + 4 < N):
        next_sum = cal(arr[idx + 2], arr[idx + 3], arr[idx + 4])
        dfs(idx + 4, cal(sum, arr[idx + 1], next_sum))

def cal(num1, op, num2):
    num1 = int(num1)
    num2 = int(num2)
    if (op == "+"):
        return num1 + num2
    elif (op == "-"):
        return num1 - num2
    elif (op == "*"):
        return num1 * num2


dfs(0, int(arr[0]))
print(max_result)