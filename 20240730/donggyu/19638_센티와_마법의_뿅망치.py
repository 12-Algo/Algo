import sys
import heapq 

#거인 수 / 센티 키 / 망치 횟수 제한
n, h, t = map(int, sys.stdin.readline().split())
giants = []

#최소 힙을 최대힙처럼 사용하기 위해 -hegith로 heapify
for _ in range(n):
    giants_h = int(sys.stdin.readline())
    giants.append(-1*giants_h)

heapq.heapify(giants)

cnt = 0
for _ in range(t):
    tallest = abs(heapq.heappop(giants))
    #가장 큰 거인의 키가 센티 키보다 낮다면 반복문 종료
    if tallest < h:
        heapq.heappush(giants, (-1*tallest))    
        break
    #가장 큰 거인의 키가 1이하면 반복문 종료
    if tallest <= 1:
        heapq.heappush(giants, (-1*tallest))
        break
    #가장 큰 거인의 키를 절반으로 만들고 다시 heapify 반복
    giant = -(tallest//2)
    heapq.heappush(giants, giant)
    cnt += 1

tallest = abs(heapq.heappop(giants))
print(tallest)
if(tallest<h):
    print("YES")
    print(cnt)
else:
    print("NO")
    print(tallest)
