#조합 사용을 위한 itertools 메소드 호출
from itertools import combinations

n, m = map(int, input().split())
city = []
for i in range(n):
    l = list(map(int, input().split()))
    city.append(l)

def solution(n, m, city):
    chicken_matrix = []
    house_matrix = []
    #치킨집과 집 위치좌표 저장
    for i in range(n):
        for j in range(n):
            if city[i][j] == 2:
                chicken_matrix.append([i,j])
            elif city[i][j] == 1:
                house_matrix.append([i,j])

    #combination을 사용해서 가능한 조합을 리스트로 생성
    possible_store = list(combinations(chicken_matrix, m))
    
    #비교를 위한 초기값 세팅
    answer = 10000000000
    
    #가능한 모든 조합에 대해서 치킨 거리 확인
    for ps in possible_store:
        ds = check_ds(house_matrix, city, ps)
        answer = min(ds, answer)

    return answer

#치킨 거리 확인 함수
def check_ds(house_matrix, city, ps):
    #비교를 위해 도시에서 해당 치킨집을 제외한 치킨 집 제거
    tp_city = city
    for i in range(len(city)):
        for j in range(len(city)):
            if tp_city[i][j] == 2 and [i, j] not in ps:
                tp_city[i][j] = 0
    
    sum = 0
    result = 50000
    
    #각 집의 치킨 거리 계산하여 도시의 치킨 거리 산출
    for h in house_matrix:
        for p in ps:
            temp = abs(h[0]-p[0]) + abs(h[1]-p[1])     
            result = min(temp, result)
        
        sum += result
        result = 50000
    
    return sum

print(solution(n, m, city))