def solution(diffs, times, limit):
    start = 1
    end = max(diffs)

    while (start <= end):
        mid = (start + end) // 2

        if (isAble(diffs, times, limit, mid)):
            end = mid - 1
        else:
            start = mid + 1

    return start


def isAble(diffs, times, limit, mid):
    time_prev = 0
    time = 0
    for i in range(len(diffs)):
        if (diffs[i] <= mid):
            time += times[i]
        else:
            time += (diffs[i] - mid) * (times[i] + time_prev) + times[i]
        time_prev = times[i]
    if (time <= limit):
        return True
    else:
        return False