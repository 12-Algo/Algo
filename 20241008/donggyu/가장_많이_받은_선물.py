#2024 카카오 윈터 인턴쉽

#dict를 통해 친구들마다 index 설정
def set_idx(friends):
    friend_dict = {}
    for i, f in enumerate(friends):
        friend_dict[f] = i
    return friend_dict

def set_map(friends, gifts):
    maps = [[0]*len(friends) for _ in range(len(friends))]
    
    friend_dict = set_idx(friends)    
    #maps[x][y] 값은 x가 y에게 선물을 받은 횟수 저장
    for gift in gifts:
        gift_from, gift_to = gift.split()
        x, y = friend_dict[gift_from], friend_dict[gift_to]
        maps[x][y] += 1
    
    return maps

#f의 선물지수
def gift_index(maps, friends):
    gift_idx = [0]*len(friends)
    for i in range(len(friends)):
        temp_idx = 0
        for j in range(len(friends)):
            temp_idx += maps[i][j]
            temp_idx -= maps[j][i]
        gift_idx[i] = temp_idx
    return gift_idx

def solution(friends, gifts):
    answer = 0
    friend_dict = set_idx(friends)
    maps = set_map(friends, gifts)
    answer_maps = [0]*len(friends)
    gift_idx = gift_index(maps, friends)
    visited = [[0]*len(friends) for _ in range(len(friends))]
    
    for i in range(len(friends)):
        for j in range(len(friends)):
            if i==j:
                continue
            if visited[i][j]:
                continue
            visited[i][j], visited[j][i]= 1, 1    
            #선물을 주고 받은 기록이 있다면
            if maps[i][j] != 0 or maps[j][i] != 0:
                #j가 더 많이 준 사람일 때
                if maps[i][j] < maps[j][i]:
                    answer_maps[j] += 1
                #i가 더 많이 준 사람일 때
                elif maps[i][j] > maps[j][i]:
                    answer_maps[i] += 1
            
            #주고 받은 기록이 없거나 주고받은 수가 같을 때
            if maps[i][j]-maps[j][i] == 0:
                if gift_idx[i] > gift_idx[j]:
                    answer_maps[i] += 1
                elif gift_idx[i] < gift_idx[j]:
                    answer_maps[j] += 1
    
    return max(answer_maps)
