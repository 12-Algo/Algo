def solution(video_len, pos, op_start, op_end, commands):
    answer = ''
    video_len_s = toSecond(video_len)
    pos_s = toSecond(pos)
    op_start_s = toSecond(op_start)
    op_end_s = toSecond(op_end)

    # 오프닝 건너뛰기
    if (op_start_s <= pos_s <= op_end_s):
        pos_s = op_end_s

    for command in commands:
        if (command == "prev"):
            pos_s -= 10
            if (pos_s < 0):
                pos_s = 0
        elif (command == "next"):
            pos_s += 10
            if (pos_s > video_len_s):
                pos_s = video_len_s

        # 오프닝 건너뛰기
        if (op_start_s <= pos_s <= op_end_s):
            pos_s = op_end_s
    answer = toTimeString(pos_s)
    return answer


def toSecond(time):
    m, s = map(int, time.split(":"))
    return m * 60 + s


def toTimeString(second):
    m = second // 60
    s = second % 60
    filled_m = "{:02}".format(m)
    filled_s = "{:02}".format(s)
    return filled_m + ":" + filled_s
