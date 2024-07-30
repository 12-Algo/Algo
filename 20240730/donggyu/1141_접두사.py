import sys
input = sys.stdin.readline

n = int(input())
words = []
for _ in range(n):
    words.append(sys.stdin.readline().rstrip())

#길이순 정렬
words.sort(key=len)

cnt = 0
for i in range(n):
    for j in range(i+1, n):
        if words[i] == words[j][0:len(words[i])]:
            cnt += 1
            break
    
print(len(words)-cnt)