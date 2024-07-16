import collections
import math
import sys


if __name__ == '__main__':
    arr = []
    result = 0
    answer = 0
    daegak1 = 0
    daegak2 = 0
    for i in range(5):
        arr.append(list(map(int, input().split())))
    nums = []
    for _ in range(5):
        nums.append(list(map(int, input().split())))

    gameover = False
    for i in range(5):
        if gameover: break
        for j in range(5):
            if gameover: break
            x, y = -1, -1
            for w in range(5):
                for z in range(5):
                    if arr[w][z] == nums[i][j]:
                        arr[w][z] = -1
                        x = w
                        y = z
            #수평 검사
            if x != -1:
                flag = True
                for k in range(5):
                    if arr[x][k] != -1:
                        flag = False
                if flag == True:
                    answer += 1
            #수직 검사
            if y != -1:
                flag = True
                for k in range(5):
                    if arr[k][y] != -1:
                        flag = False
                if flag == True:
                    answer += 1
            # 대각선 검사
            flag = True
            if daegak1 == 0:
                for w in range(5):
                    if arr[w][w] != -1:
                        flag = False
                if flag == True:
                    daegak1 = 1
                    answer += 1
            flag = True
            if daegak2 == 0:
                for w in range(5):
                    if arr[4-w][w] != -1:
                        flag = False
                if flag == True:
                    daegak2 = 1
                    answer += 1

            if answer >= 3:
                gameover = True
                result = 5 * i + j + 1
    print(result)





