import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))
arr.sort()
result = 0

for i in range(n):
    start = 0
    end = n-1

    while(start < end):
        if(start == i):
            start += 1
            continue
        elif(end == i):
            end -= 1
            continue

        num = arr[start] + arr[end]
        if(num == arr[i]):
            result += 1
            break
        elif(num < arr[i]):
            start += 1
        else:
            end -= 1

print(result)


