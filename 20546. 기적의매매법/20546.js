const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
const input = [];

rl.on("line", (line) => {
  input.push(line);
}).on("close", () => {
  const [money, rest] = input;

  const stocks = rest.split(" ").map(Number);

  const [junhunChange, junhunStock] = junhun(Number(money), stocks);

  const [seongminChage, seongminStock] = seongmin(Number(money), stocks);

  const lastStock = stocks.pop();

  const junhunMoney = junhunChange + lastStock * junhunStock;
  const seongminMoney = seongminChage + lastStock * seongminStock;

  let answer = "";

  if (junhunMoney === seongminMoney) answer = "SAMESAME";
  if (junhunMoney > seongminMoney) answer = "BNP";
  if (junhunMoney < seongminMoney) answer = "TIMING";

  console.log(answer);
});

const junhun = (money, stocks) => {
  let junhunStock = 0;
  let junhunChange = money;
  for (const stock of stocks) {
    const shareStock = Math.floor(junhunChange / stock);

    if (shareStock > 0) {
      junhunStock += shareStock;
      junhunChange -= shareStock * stock;
    }
  }
  return [junhunChange, junhunStock];
};

const seongmin = (money, stocks) => {
  let seongminStock = 0;
  let seongminChange = money;
  let flag = "mid";
  let counting = 0;
  let last = 0;

  for (const stock of stocks) {
    if (stock > last && flag === "up") {
      counting += 1;
    }

    if (stock > last && (flag === "down" || flag === "mid")) {
      flag = "up";
      counting = 1;
    }

    if (stock < last && flag === "down") {
      counting += 1;
    }

    if (stock < last && (flag === "up" || flag === "mid")) {
      flag = "down";
      counting = 1;
    }

    if (stock === last) {
      flag = "mid";
    }
    last = stock;

    if (counting >= 3 && flag === "up") {
      seongminChange += Number(stock) * seongminStock;
      seongminStock = 0;
    }

    if (counting >= 3 && flag === "down") {
      const shareStock = Math.floor(seongminChange / stock);
      seongminStock += shareStock;
      seongminChange -= shareStock * stock;
    }
  }
  return [seongminChange, seongminStock];
};
