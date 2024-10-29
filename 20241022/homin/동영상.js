function solution(video_len, pos, op_start, op_end, commands) {
  const videoLength = video_len
    .split(":")
    .map(Number)
    .map((e, i) => (i === 0 ? e * 60 : +e))
    .reduce((acc, cur) => acc + cur, 0);

  let nowPos = pos
    .split(":")
    .map((e, i) => (i === 0 ? e * 60 : +e))
    .reduce((acc, cur) => acc + cur, 0);

  const [openingStart, openingEnd] = [op_start, op_end].map((e) =>
    e
      .split(":")
      .map((e, i) => (i === 0 ? e * 60 : +e))
      .reduce((acc, cur) => acc + cur, 0)
  );

  commands.forEach((e) => {
    if (nowPos >= openingStart && nowPos <= openingEnd) nowPos = openingEnd;

    nowPos = e === "next" ? nowPos + 10 : nowPos - 10;

    if (nowPos >= openingStart && nowPos <= openingEnd) nowPos = openingEnd;
    if (nowPos < 0) nowPos = 0;
    if (nowPos > videoLength) nowPos = videoLength;
  });

  return [Math.floor(nowPos / 60), nowPos % 60]
    .map((e) => ((e + "").length === 1 ? `0${e}` : e + ""))
    .join(":");
}
