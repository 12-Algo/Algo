function solution(bandage, health, attacks) {
  const lastTime = attacks.at(-1)[0];
  const [bandTime, healHp, bonusHp] = bandage;

  const maxHp = health;

  let [nowHp, isSuccess, attack] = [health, 0, false];

  let attackIndex = 0;
  let attackTime = attacks[attackIndex][0]; // 초기 공격당하는 시간 설정

  attacks.push([0, 0]); // 다음공격체크할 때 인덱싱에러 터저버려서 그냥 임의로 하나 입력해둠

  for (let i = 1; i <= lastTime; i++) {
    if (i === attackTime) {
      // 공격받을 순간일 때
      nowHp -= attacks[attackIndex][1]; // 체력감소
      attackIndex += 1;
      attackTime = attacks[attackIndex][0]; // 다음 공격 체크
      isSuccess = 0;
    } else {
      // 공격받지 않는 순간일 때
      isSuccess += 1;
      nowHp = Math.min(maxHp, nowHp + healHp);
    }

    if (nowHp <= 0) {
      // 공격받고 체력이 0 이하면 감소
      return -1;
    }

    if (isSuccess === bandTime) {
      // 연속시간을 다 채웠을 때
      isSuccess = 0;
      nowHp = Math.min(maxHp, nowHp + bonusHp);
    }
  }
  return nowHp;
}

// 1. 문제에서 주어진 순서대로 구현을 진행함
// 2. 체력변화, 연속성공 , 공격유무를 표에서 보여주니까 최대한 코드를 표처럼 구현하려고 했음
