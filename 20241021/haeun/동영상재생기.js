function solution(video_len, pos, op_start, op_end, commands) {
    const videoLen = changeSeconds(video_len)
    let posTime = changeSeconds(pos)
    const opStart = changeSeconds(op_start)
    const opEnd = changeSeconds(op_end)

    if (posTime >= opStart && posTime <= opEnd) posTime = opEnd;

    for (const command of commands) {
        if (command === "next") posTime = goNext(posTime);
        if (command === "prev") posTime = goPrev(posTime);
        if (posTime >= opStart && posTime <= opEnd) posTime = opEnd;
    }

    return changeForm(posTime);

    function changeSeconds(str) {
        const arr = str.split(":").map(Number)
        return arr[0] * 60 + arr[1];
    }

    function goNext(time) {
        if (time > videoLen - 10) return videoLen;
        return time + 10;
    }
    function goPrev(time) {
        if (time < 10) return 0;
        return time - 10;
    }


    function changeForm(time) {
        let minute = parseInt(time / 60).toString();
        let second = (time % 60).toString();
        if (minute < 10) minute = "0" + minute;
        if (second < 10) second = "0" + second;

        return `${minute}:${second}`
    }
}