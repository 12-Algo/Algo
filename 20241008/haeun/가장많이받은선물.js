function solution(friends, gifts) {
    const len = friends.length;
    const friendMap = {};
    const giftTable = Array.from({length: len}, ()=> new Array(len).fill(0));
    const giftPoint = new Array(len).fill(0);
    const nextGift = new Array(len).fill(0);
    
    for(let i = 0; i < len; i++){
        friendMap[friends[i]] = i;
    }
    
    for(const gift of gifts){
        const [from, to] = gift.split(" ");
        const fromIdx = friendMap[from];
        const toIdx = friendMap[to];
        
        giftTable[fromIdx][toIdx]++;
        giftPoint[fromIdx]++;
        giftPoint[toIdx]--;
    }

    for(let fromIdx = 0; fromIdx < len; fromIdx++){
        for(let toIdx = fromIdx + 1; toIdx < len; toIdx++){
           
            const fromCount = giftTable[fromIdx][toIdx];
            const toCount = giftTable[toIdx][fromIdx];
            
                // 1. 선물 개수 비교
            
            if(fromCount > toCount) nextGift[fromIdx]++;
            else if(fromCount < toCount) nextGift[toIdx]++;
            else {
                
                    // 2. 선물지수 비교
                const fromPoint = giftPoint[fromIdx];
                const toPoint = giftPoint[toIdx];
                    
                if(fromPoint > toPoint) nextGift[fromIdx]++;
                if(toPoint > fromPoint) nextGift[toIdx]++;
            }
        }
    }
    return Math.max(...nextGift);
}