def solution(bandage, health, attacks):
    beforeTime = 0
    nowHealth = health
    for time, attack in attacks:
        timeDiff = time-beforeTime-1
        #체력 갱신
        nowHealth += timeDiff*bandage[1] + (timeDiff//bandage[0])*bandage[2]
        if(nowHealth >= health):
            nowHealth = health
        #공격하기
        nowHealth -= attack
        #살았는 지 확인
        if(nowHealth <= 0):
            return -1
        beforeTime = time
    return nowHealth

#attacks배열 돌면서 그 공격이 들어오기 전까지의 시간에 따라서 체력 계산해주기

# 만약 시간이 6, bandage가 [5, 1, 5]
# 초당 회복량은 6*bandage가[1]
# 추가 회복량은 (6//bandage가[0])*bandage가[2]
