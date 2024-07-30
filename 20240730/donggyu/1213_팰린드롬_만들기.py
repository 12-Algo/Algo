import sys
import collections
name = sys.stdin.readline().rstrip()

#counter 딕셔너리 생성
collection_word = collections.Counter(name)

def ans():
    front = ''
    odd = ''
    cnt = 0

    for key, value in list(collection_word.items()):
        if(value%2 == 1):
            cnt += 1
            odd += key
            if(cnt>=2):
                print("I'm Sorry Hansoo")
                return

    #value가 1일 경우 자동으로 0 
    for key, value in sorted(collection_word.items()):
        front += key*(value//2)
    print(front+odd+front[::-1])
    return

ans()