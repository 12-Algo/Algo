import sys
from collections import Counter
#문자열 길이 s / 부분문자열 길이 p
s, p = map(int, sys.stdin.readline().split())
word = list(sys.stdin.readline())
#A C G T
minimal = list(map(int, sys.stdin.readline().split()))

start = 0
end = p-1
        #A C G T
check = [0, 0, 0, 0]
def plus(ch):
    if ch == 'A':
        check[0] += 1
    elif ch == 'C':
        check[1] += 1
    elif ch == 'G':
        check[2] += 1
    elif ch == 'T':
        check[3] += 1
    
def delete(ch):
    if ch == 'A':
        check[0] -= 1
    elif ch == 'C':
        check[1] -= 1
    elif ch == 'G':
        check[2] -= 1
    elif ch == 'T':
        check[3] -= 1

ans = 0
#초기값 세팅
for i in range(p):
    plus(word[i])
if minimal[0] <= check[0] and minimal[1] <= check[1] and minimal[2] <= check[2] and minimal[3] <= check[3]:
    ans += 1
print(check)
print(minimal)
while(True):
    if end >= s-1:
        break
    delete(word[start])
    start += 1
    end += 1
    plus(word[end])
    
    print(check)
    print(minimal)
    if minimal[0] <= check[0] and minimal[1] <= check[1] and minimal[2] <= check[2] and minimal[3] <= check[3]:
        ans += 1
 
print(ans)
