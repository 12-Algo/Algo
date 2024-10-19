
from collections import deque


def solution(queue1, queue2):
    queue1 = deque(queue1)
    queue2 = deque(queue2)
    tot1 = sum(queue1)
    tot2 = sum(queue2)
    total = tot1 + tot2

    if total % 2 != 0:
        return -1

    limit = (len(queue1)) * 4
    answer = 0

    while True:
        if answer == limit:
            answer = -1
            break

        if tot1 > tot2:
            target = queue1.popleft()
            queue2.append(target)
            tot1 -= target
            tot2 += target
            answer += 1

        elif tot1 < tot2:
            target = queue2.popleft()
            queue1.append(target)
            tot1 += target
            tot2 -= target
            answer += 1
        else:
            break

    return answer