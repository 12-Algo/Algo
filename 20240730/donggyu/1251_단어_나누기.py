import sys

input_word = sys.stdin.readline().rstrip()
result_word = []

for i in range(1, len(input_word)-1):
    for j in range(i+1, len(input_word)):
        f = input_word[:i][::-1]
        m = input_word[i:j][::-1]
        e = input_word[j:][::-1]
        result_word.append((f+m+e))

result_word.sort()    
print(result_word[0])
