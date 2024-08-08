const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [a, ...b] = input;
  console.log(solution(a.split(" ").map(Number)[1], b));
});

const solution = (a, g) => {
  const graph = g.map((e) => e.split(" ").map(Number));
  const houses = [];
  const chickBrand = [];
  graph.forEach((e, index) => {
    e.forEach((i, index2) => {
      if (i === 2) {
        chickBrand.push([index, index2]);
      }
      if (i === 1) {
        houses.push([index, index2]);
      }
    });
  });

  const result = [];

  const per = (item, index) => {
    if (item.length === a) {
      result.push(item);
    }

    for (let i = index; i < chickBrand.length; i++) {
      per([...item, chickBrand[i]], i + 1);
    }
  };

  per([], 0);

  let min = Infinity;
  result.forEach((e) => {
    let temp2 = 0;
    houses.forEach((house) => {
      let length = 5000;

      e.forEach((chicken) => {
        // 치킨 x,y
        const [cx, cy] = chicken;
        const [hx, hy] = house;
        length = Math.min(length, Math.abs(cx - hx) + Math.abs(cy - hy));
      });

      temp2 += length;
    });
    min = Math.min(temp2, min);
  });
  return min;
};
