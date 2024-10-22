from collections import defaultdict
from collections import deque

def solution(points, routes):
    answer = 0
    robot = [deque() for _ in range(len(routes))] #로봇들이 어떻게 움직여야 하는지
    robotLocation = [] #로봇들의 현재 위치
    for i in range(len(routes)):  # i+1번째 로봇의 운송경로
        # 해당 로봇의 시작점 초기화
        startPoint = points[routes[i][0] - 1]
        robotLocation.append(startPoint[:])
        # 운송경로대로 움직이기 위해 이동해야할 순서
        for j in range(len(routes[0]) - 1):
            startPoint = points[routes[i][j] - 1]
            endPoint = points[routes[i][j + 1] - 1]
            if (endPoint[0] != startPoint[0]):
                robot[i].append([endPoint[0] - startPoint[0], "y"])
            if (endPoint[1] != startPoint[1]):
                robot[i].append([endPoint[1] - startPoint[1], "x"])

    # 시작 위치에서 겹치는 거 있는 지 확인
    dq = defaultdict(int)
    for y, x in robotLocation:
        if (dq[(y, x)] == 1):
            answer += 1
        dq[(y, x)] += 1

    while (True):
        dq = defaultdict(int)
        for d in range(len(robot)):
            # d번째 로봇 이동
            if (len(robot[d]) == 0):
                continue

            if (robot[d][0][1] == 'y'):
                if (robot[d][0][0] > 0):
                    ny = robotLocation[d][0] + 1
                    robot[d][0][0] -= 1
                else:
                    ny = robotLocation[d][0] - 1
                    robot[d][0][0] += 1
                robotLocation[d][0] = ny
            elif (robot[d][0][1] == 'x'):
                if (robot[d][0][0] > 0):
                    nx = robotLocation[d][1] + 1
                    robot[d][0][0] -= 1
                else:
                    nx = robotLocation[d][1] - 1
                    robot[d][0][0] += 1
                robotLocation[d][1] = nx

            if (robot[d][0][0] == 0):
                robot[d].popleft()

            # 새로 이동한 좌표를 dict에 넣으며 충돌난 게 있는지 확인하기
            if (dq[(robotLocation[d][0], robotLocation[d][1])] == 1):
                answer += 1
            dq[(robotLocation[d][0], robotLocation[d][1])] += 1

        #아직 더 이동해야할 로봇 수가 <= 1 이면 끝내기
        cnt = 0
        for d in range(len(robot)):
            if (len(robot[d]) != 0):
                cnt += 1
        if (cnt <= 1):
            break

    return answer