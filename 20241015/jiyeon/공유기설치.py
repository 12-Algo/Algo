import sys
input = sys.stdin.readline

n, c = map(int, input().split())
houses = [int(input()) for _ in range(n)]
houses.sort()

def getMaxCntWithMid(houses, mid):
    cnt = 1
    location = houses[0]
    for i in range(1, len(houses)):
        if(location + mid <= houses[i]): #설치가능
            cnt += 1
            location = houses[i]
    return cnt

start = 1
end = houses[-1] - houses[0]

while(start<=end):
    mid = (start+end)//2
    if(getMaxCntWithMid(houses, mid) >= c):
        start = mid+1
    else:
        end = mid-1
print(end)

