from collections import deque

dy = [0, 0, 1, -1]
dx = [1, -1, 0, 0]


def solution(places):
    answer = []
    for place in places:
        answer.append(isKeepingDistance(place))
    return answer


# 거리두기 지키면 1, 아니면 0 리턴
def isKeepingDistance(place):
    waitingRoom = [list(i) for i in place]
    people = []
    # 대기실에서 사람 찾기
    for y in range(5):
        for x in range(5):
            if (waitingRoom[y][x] == "P"):
                people.append((y, x))
    # 해당 사람 기준으로 주변 거리두기 준수 여부 검사
    for start_y, start_x in people:
        dq = deque()
        visit = [[False for _ in range(5)] for _ in range(5)]
        dq.append((start_y, start_x, 0))
        visit[start_y][start_x] = True
        while (dq):
            y, x, distance = dq.popleft()
            for i in range(4):
                ny, nx = y + dy[i], x + dx[i]
                if (0 <= ny < 5 and 0 <= nx < 5 and not visit[ny][nx] and distance <= 1):
                    if (waitingRoom[ny][nx] != "X"):
                        if (waitingRoom[ny][nx] == "P"):
                            return 0
                        if (distance == 0):
                            visit[ny][nx] = True
                            dq.append((ny, nx, distance + 1))
    return 1

# 모든 p찾고 하나씩 돌아가면서 시작 -> 벽 아닌 곳 찾아 들어가다가
# 거리가 2초과면 그만들어가!, p발견했는데 거리가 2이하다? return 0
