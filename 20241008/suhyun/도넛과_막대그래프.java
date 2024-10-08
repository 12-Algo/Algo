import java.util.*;

class Solution {
    private static HashMap<Integer, ArrayList<Integer>> nodeInfo = new HashMap<>();  // 인접행렬
    private static HashMap<Integer, Integer> inDegree = new HashMap<>();  // 진입차수
    private static Integer[] nodes;  // 모든 노드
    private static HashMap<Integer, Integer> nodeIdx = new HashMap<>();  // 노드의 방문리스트 인덱스
    private static boolean[] check;  // 방문 리스트
    private static int L;  // 노드 개수
    
    private static void input(int[][] edges) {
        Set<Integer> nodeSet = new HashSet<>();
        
        for (int[] edge : edges) {
            if (nodeInfo.containsKey(edge[0])) {
                nodeInfo.get(edge[0]).add(edge[1]);
            } else {
                nodeInfo.put(edge[0], new ArrayList<>());
                nodeInfo.get(edge[0]).add(edge[1]);
            }
            
            nodeSet.add(edge[0]);
            nodeSet.add(edge[1]);
            
            if (inDegree.containsKey(edge[1])) {
                inDegree.put(edge[1], inDegree.get(edge[1])+1);
            } else {
                inDegree.put(edge[1], 1);
            }
        }
        
        nodes = nodeSet.toArray(new Integer[0]);
        L = nodes.length;
        check = new boolean[L];
        
        for (int i = 0; i < L; i++) {
            nodeIdx.put(nodes[i], i);
        }
    }
    
    private static int bfs(int node) {
        boolean isStick = false;
        boolean isEight = false;
        boolean isDoughnut = false;
        
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(node);
        check[nodeIdx.get(node)] = true;
        int lastNode = node;
        
        while (!q.isEmpty()) {
            int now = q.poll();
            lastNode = now;
            
            if (!nodeInfo.containsKey(now)) {
                isStick = true;
                continue;
            } else if (nodeInfo.get(now).size() > 1) {
                isEight = true;
            }
            
            for (int next : nodeInfo.get(now)) {
                if (!check[nodeIdx.get(next)]) {
                    q.offer(next);
                    check[nodeIdx.get(next)] = true;
                }
            }
        }
        
        if (nodeInfo.containsKey(lastNode) && nodeInfo.get(lastNode).get(0) == node) {
            isDoughnut = true;
        }
        
        if (isEight) {
            return 3;
        } else if (isStick) {
            return 2;
        } else if (isDoughnut) {
            return 1;
        } else {
            return -1;
        }
    }
    
    public int[] solution(int[][] edges) {
        int[] ans = new int[4];
        input(edges);
        
        // 생성한 정점 찾기
        for (int i = 0; i < L; i++) {
            if (!inDegree.containsKey(nodes[i]) && nodeInfo.get(nodes[i]).size() > 1) {
                check[nodeIdx.get(nodes[i])] = true;
                ans[0] = nodes[i];
            }
        }
        
        for (int node : nodes) {
            if (!check[nodeIdx.get(node)]) {
                int resIdx = bfs(node);
                
                if (resIdx == -1) {
                    continue;
                }
                ans[resIdx]++;
            }
        }
        
        return ans;
    }
}