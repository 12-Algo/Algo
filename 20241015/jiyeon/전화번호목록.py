import sys
input = sys.stdin.readline

def isInclude(a, b): #a가 b에 포함되는지
    if(len(a)>len(b)):
        return False
    for i in range(len(a)):
        if(a[i] != b[i]):
            break
    else:
        return True
    return False


def solution():
    n = int(input())
    tels = [input().rstrip() for _ in range(n)]
    tels.sort()
    for i in range(n-1):
        if (isInclude(tels[i], tels[i+1])):
            print("NO")
            return
    else:
        print("YES")

t = int(input())
for _ in range(t):
    solution()
