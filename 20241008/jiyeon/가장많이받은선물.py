def solution(edges):
    #생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수
    answer = [0, 0, 0, 0]

    nodeCnt = dict()
    for edge in edges:
        out_edge = edge[0]
        in_edge = edge[1]
        if not nodeCnt.get(out_edge):
            nodeCnt[out_edge] = [0, 0]
        if not nodeCnt.get(in_edge):
            nodeCnt[in_edge] = [0, 0]
        nodeCnt[out_edge][0] += 1 #나간 개수 카운팅
        nodeCnt[in_edge][1] += 1 #들어온 개수 카운팅

    for key, cnts in nodeCnt.items():
        out_edge, in_edge = cnts[0], cnts[1]
        #나가는 것만 2개 이상인게 생성점
        if out_edge >= 2 and in_edge == 0:
            answer[0] = key
        elif out_edge == 0 and in_edge > 0:
            answer[2] += 1
        elif in_edge >= 2 and out_edge >= 2:
            answer[3] += 1
    answer[1] = nodeCnt[answer[0]][0] - answer[2] - answer[3]
    return answer