my = []
another = []
myScores = []
anotherScores = []
answer = []
maxWin = 0
myScore = 0
anotherScore = 0
c = 0


def solution(dice):
    global answer
    answer = []
    # 주사위 뽑기
    visit = [False for _ in range(len(dice))]
    selectDice(visit, dice, 0, 0)
    return answer


def selectDice(visit, dice, cnt, start):
    n = len(dice)

    if (cnt == n // 2):
        getScores(visit, dice)
        return

    for i in range(start, n):
        if (not visit[i]):
            visit[i] = True
            selectDice(visit, dice, cnt + 1, i + 1)
            visit[i] = False


def getScores(visit, dice):
    global my, another, myScores, anotherScores, c
    my, another, myScores, anotherScores, c = [], [], [], [], 0

    # visit배열 가지고 나와 상대방 주사위 가져가기
    for i in range(len(dice)):
        if (visit[i]):
            my.append(i)
        else:
            another.append(i)

    rollDice(0, dice)
    calProbability()


def rollDice(picked, dice):
    global my, another, myScores, anotherScores, myScore, anotherScore
    if (picked == len(my)):
        myScores.append(myScore)
        anotherScores.append(anotherScore)
        return

    for i in range(6):
        myScore += dice[my[picked]][i]
        anotherScore += dice[another[picked]][i]
        rollDice(picked + 1, dice)
        myScore -= dice[my[picked]][i]
        anotherScore -= dice[another[picked]][i]


def calProbability():
    global myScores, anotherScores, maxWin, answer
    cnt = 0
    myScores.sort()
    anotherScores.sort()
    for m in myScores:

        start, end = 0, len(anotherScores) - 1

        while (start <= end):
            mid = (start + end) // 2
            if (m > anotherScores[mid]):
                start = mid + 1
            else:
                end = mid - 1
        cnt += end

    if (cnt > maxWin):
        maxWin = cnt
        answer = [i + 1 for i in my]

