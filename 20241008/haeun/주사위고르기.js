function solution(dice) {
    const len = dice.length;
    const groupSize = len / 2;
    const dices = new Array(len).fill(0).map((_,index)=>index+1)
    
    // A의 주사위 조합을 정하고 나머지는 B 주기
    const getCombinations = (array, selectNumber) => {
        const results = [];
        
        if (selectNumber === 1) {
            return array.map((element) => [element]);
        }
        
        array.forEach((fixed, index, origin) => {
            const rest = origin.slice(index+1);
            const combinations = getCombinations(rest, selectNumber - 1);
            const attached = combinations.map((combination) => [fixed, ...combination]);
            results.push(...attached);
        });
        
        return results;
    }
    
    const groups = getCombinations(dices, groupSize);
    const oppoGroups = groups.map((elemA) => {
        return dices.filter(elemB => !elemA.includes(elemB));
    })
    
    // 선택한 주사위로 가질 수 있는 모든 합을 구하기
    const getSums = (combo) => {
        const sums = [];
    
        const calSums = (count, sum) => {
            if (count === groupSize) {
                sums.push(sum);
                return;
            }
            
            for (let i=0; i<6; i++) {
                calSums(count + 1, sum + dice[combo[count] - 1][i]);
            }
        }
        calSums(0,0)
        
        return sums.sort((a,b) => a-b);
    }
        
    
    let answer;
    let wins = 0;
    const groupLen = groups.length;
    
    // A 결과와 B 결과 비교하기
    for (let k=0; k < groupLen; k++) {
        let nowWins = 0;
        
        const sumA = getSums(groups[k]);
        const sumB = getSums(oppoGroups[k]);
        
        const lenA = sumA.length;
        const lenB = sumB.length;
        
        let pointer = 0;
        
        for (let i=0; i<lenA; i++) {
            for (let j=pointer; j<lenB; j++) {
                if (sumA[i] <= sumB[j]) { 
                    pointer = j;
                    break; 
                }
                nowWins++;
            }
            nowWins += pointer
        }
        
        if (nowWins > wins) {
            wins = nowWins;
            answer = groups[k];
        }
    }
    
    return answer;    
}