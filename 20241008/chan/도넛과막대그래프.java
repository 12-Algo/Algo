import java.util.*;

class Solution {
    static HashMap<Integer, List<Integer>> hmIn; // 진입 해시맵
    static HashMap<Integer, List<Integer>> hmOut; // 진출 해시맵
    static int[] answer;

    public int[] solution(int[][] edges) {
        answer = new int[4];
        hmIn = new HashMap<>();
        hmOut = new HashMap<>();
        
        hm(edges); // In, Out 입력
        calOut();
        calIn();
        
        // 정점의 진출 차수가 그래프의 총 개수 이므로 막대와 8자그래프를 빼주면 도넛그래프 개수가 나옴
        answer[1] = hmOut.get(answer[0]).size() - answer[2] - answer[3];
        
        return answer;
    }
    
    static void hm(int[][] edges) {// 진출, 진입 입력
        for (int[] e : edges) {
            if (!hmOut.containsKey(e[0])) hmOut.put(e[0], new ArrayList<>());
            hmOut.get(e[0]).add(e[1]);
        
            if (!hmIn.containsKey(e[1])) hmIn.put(e[1], new ArrayList<>());
            hmIn.get(e[1]).add(e[0]);
        }
    }
   
    static void calOut() {
        for (int e : hmOut.keySet()) {// 진출할 수 있는 정점들
            if (hmOut.get(e).size() >= 2) {// 2개 이상 진출할 수 있는 정점
                // 8자 그래프는 진출차수2개 진입차수2개 이상인 노드가1개가 무조건 있음
                if (hmIn.containsKey(e)) answer[3] += 1;// 진입도 받고 있다면 8자 진입이 없다면 생성한 정점임
                else answer[0] = e; // 생성한 정점은 나가는 간선만 있고 들어오는 간선은 없음
            }
        }//테스트2번의 3번 4번 11번이 진출이 2개 이상인데 4번은 진입이 없기에 생성정점 3,11은 진입 진출 둘 다 있어서 8자임
    }
    
    static void calIn() {// 막대 그래프의 끝은 진입만 받고 진출을 못하기 때문에
        for (int e : hmIn.keySet()) {// 진입받는 정점 중에서 진출하는 정점이 없는 정점은 막대그래프임
            if (!hmOut.containsKey(e)) answer[2] += 1;
        }
    }
}
