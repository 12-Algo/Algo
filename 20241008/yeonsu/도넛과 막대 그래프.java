import java.util.ArrayList;

class Solution {
    private static ArrayList<Integer>[] graph;
    public int[] solution(int[][] edges) {
        int[] answer = {0,0,0,0};
        //도넛 막대 8자 그래프 수의 합은 2이상!!!!!
        //-> 생성노드의 나가는 간선은 2개 이상!! 들어오는 간선은 없음!!
        //나가는 간선이 2개 이상이면서 들어오는 간선이 없는 것을 찾아야함.
        //생성 노드를 먼저 찾는 것이 우선.
        //나가는 간선이 2개 이상이면서 들어오는 간선이 없는 것은 생성노드밖에 없기 때문
        //한 노드에서 나가는 간선이 2개, 들어오는 간선 2개인 경우 -> 8자
        //나가는 간선하나 들어오는 간선 하나 있는 경우 -> 도넛
        //나가는 간선밖에 없는 경우 -> 막대 또는 생성 노드
        int N = edges.length;
        graph = new ArrayList[1000000];
        int[] outdegree = new int[1000000];
        int[] indegree = new int[1000000];
        for (int i=0;i<1000000;i++) {
                graph[i] = new ArrayList<>();
        }
        
        for (int i=0;i<N;i++) {
            graph[edges[i][0]].add(edges[i][1]);
            outdegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
        }
        //생성 노드 찾기
        int generateNode = -1;
        for (int i=0;i<1000000;i++) {
            if (outdegree[i] >= 2 && indegree[i] == 0) {
                generateNode = i;
            }
        }
        int donut = 0;
        int macdae = 0;
        int eight = 0;
        for (int k : graph[generateNode]) {
            int type = findoutTypeOfGraph(k, k, true);
            // // System.out.println(type);
            if (type == 0) {
                donut++;
            } else if (type == 1) {
                macdae++;
            } else {
                eight++;
            }
        }
        answer[0] = generateNode;
        answer[1] = donut;
        answer[2] = macdae;
        answer[3] = eight;
        return answer;
    }
    
    //돌면서 간선이 2개가 나가는 노드를 찾았다 -> 8자 그래프
    //돌면서 간선이 1개지만 마지막에 다시 출발한 노드로 돌아온다 -> 도넛 그래프
    //return 0 -> 도넛, 1 -> 막대, 2 -> 8자
    private static int findoutTypeOfGraph(int k, int start, boolean checkFirst) {
        // System.out.println(k + " " + start + " " + checkFirst);
        //나가는 간선이 2개라면 8자 그래프
        if (graph[k].size() == 2) return 2;
        //다시 출발한 노드로 돌아온다면, 도넛 그래프
        if (!checkFirst && k == start) return 0;
        
        for (int nextK : graph[k]) {
            return findoutTypeOfGraph(nextK, start, false);
        }
        
        //그냥 맥 없이 끝나버리면, 막대 그래프
        return 1;
    }
}